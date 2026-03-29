import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class CRS {
    private List<Appointment> appointments = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();
    private List<WashingMachine> washingMachines = new ArrayList<>();
    private List<Dryer> dryers = new ArrayList<>();
    private List<Ironer> ironers = new ArrayList<>();


    private final Map<String, Double> clothingWeights;
    private final Map<String, Integer> ironingTimes;

    public CRS() {
        clothingWeights = new HashMap<>();
        clothingWeights.put("T-Shirt", 0.2);
        clothingWeights.put("Gömlek", 0.3);
        clothingWeights.put("Etek", 0.25);
        clothingWeights.put("Pantolon", 0.6);
        clothingWeights.put("Kazak", 0.5);
        clothingWeights.put("Ceket", 0.8);
        clothingWeights.put("Takım Elbise", 1.2);

        ironingTimes = new HashMap<>();
        ironingTimes.put("T-Shirt", 2);
        ironingTimes.put("Gömlek", 3);
        ironingTimes.put("Etek", 3);
        ironingTimes.put("Pantolon", 4);
        ironingTimes.put("Kazak", 5);
        ironingTimes.put("Ceket", 6);
        ironingTimes.put("Takım Elbise", 8);
    }

    public void addCustomer(Customer c) {
        customers.add(c);
    }

    public void addWashingMachine(WashingMachine wm) {
        washingMachines.add(wm);
    }

    public void addDryer(Dryer d) {
        dryers.add(d);
    }

    public void addIroner(Ironer i) {
        ironers.add(i);
    }

    public Customer findCustomerById(int id) {
        for (Customer c : customers) {
            if (c.getCustomerId()==id) return c;
        }
        return null;
    }

    public Appointment createAppointment(String customerId, Date desiredDate, boolean washing, boolean drying, boolean ironing, Map<String, Integer> colored, Map<String, Integer> whites, int appointmentCounter) throws Exception {
        Customer customer = findCustomerById(Integer.parseInt(customerId));
        if (customer == null) throw new IDException("Bu ID'ye sahip müşteri bulunamadı: " + customerId);
        if(!washing&&!drying&&!ironing) throw new NoCommand("Lütfen bir işlem seçiniz");
        Set<Machines> inUse = new HashSet<>();

        List<WashingMachine> usedWashersColored = washing && hasClothingToProcess(colored)
                ? allocateMachinesByPieces(washingMachines, colored, desiredDate, 60, inUse) : new ArrayList<>();

        List<WashingMachine> usedWashersWhite = washing && hasClothingToProcess(whites)
                ? allocateMachinesByPieces(washingMachines, whites, desiredDate, 60, inUse) : new ArrayList<>();

        List<Dryer> usedDryersColored = drying && hasClothingToProcess(colored)
                ? allocateMachinesByPieces(dryers, colored, addMinutes(desiredDate, 60), 60, inUse) : new ArrayList<>();

        List<Dryer> usedDryersWhite = drying && hasClothingToProcess(whites)
                ? allocateMachinesByPieces(dryers, whites, addMinutes(desiredDate, 60), 60, inUse) : new ArrayList<>();

        int ironingTime = estimateIroningTime(colored, whites);
        Ironer usedIroner = ironing ? allocateIroner(addMinutes(desiredDate, 120), ironingTime) : null;

        int estimatedTime = estimateTotalTime(washing, drying, ironing, colored, whites);
        double price = calculatePrice(usedWashersColored.size() + usedWashersWhite.size(), usedDryersColored.size() + usedDryersWhite.size(), ironing, colored, whites);

        if (customer.getBalance() < price) throw new YetersizBakiye("Yetersiz bakiye");

        Map<String, Integer> total = mergeMaps(colored, whites);

        Appointment appt = new Appointment(appointmentCounter, customer, desiredDate,
                usedWashersColored, usedWashersWhite,
                usedDryersColored, usedDryersWhite,
                usedIroner, price, estimatedTime, total);

        for (Machines m : usedWashersColored) m.reserve(desiredDate, 60);
        for (Machines m : usedWashersWhite) m.reserve(desiredDate, 60);
        for (Machines m : usedDryersColored) m.reserve(addMinutes(desiredDate, 60), 60);
        for (Machines m : usedDryersWhite) m.reserve(addMinutes(desiredDate, 60), 60);
        if (usedIroner != null) usedIroner.reserve(addMinutes(desiredDate, 120), ironingTime);

        appointments.add(appt);
        customer.setBalance(customer.getBalance() - price);
        saveAppointments();
        saveDryers();
        saveIroners();
        saveWashingMachines();
        saveCustomers();
        return appt;
    }

    private boolean hasClothingToProcess(Map<String, Integer> clothing) {
        return clothing.values().stream().anyMatch(v -> v > 0);
    }

    private <T extends Machines> List<T> allocateMachinesByPieces(List<T> machines, Map<String, Integer> clothing, Date date, int duration, Set<Machines> inUse) throws NoAvailableMachine {
        List<T> allocated = new ArrayList<>();
        List<Double> weights = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : clothing.entrySet()) {
            double weight = clothingWeights.get(entry.getKey());
            for (int i = 0; i < entry.getValue(); i++) {
                weights.add(weight);
            }
        }

        Collections.sort(weights, Collections.reverseOrder());

        for (double w : weights) {
            boolean placed = false;
            for (T m : allocated) {
                if (m.getAvailableCapacity() >= w) {
                    m.setAvailableCapacity(m.getAvailableCapacity() - w);
                    placed = true;
                    break;
                }
            }
            if (!placed) {
                boolean found = false;
                for (T m : machines) {
                    if (!inUse.contains(m) && m.isAvailableAt(date, duration)) {
                        m.setAvailableCapacity(m.getCapacityKg() - w);
                        allocated.add(m);
                        inUse.add(m);
                        found = true;
                        break;
                    }
                }
                if (!found) throw new NoAvailableMachine("Yeterli makine yok");
            }
        }
        return allocated;
    }

    private Ironer allocateIroner(Date date, int duration) throws NoAvailableMachine {
        for (Ironer i : ironers) {
            if (i.isAvailableAt(date, duration)) {
                return i;
            }
        }
        throw new NoAvailableMachine("Uygun ütü bulunamadı");
    }

    private int estimateIroningTime(Map<String, Integer> colored, Map<String, Integer> whites) {
        return estimateTimeForMap(colored) + estimateTimeForMap(whites);
    }

    private int estimateTotalTime(boolean washing, boolean drying, boolean ironing, Map<String, Integer> colored, Map<String, Integer> whites) {
        int time = 0;
        if (washing) time += 60;
        if (drying) time += 60;
        if (ironing) time += estimateIroningTime(colored, whites);
        return time;
    }

    private int estimateTimeForMap(Map<String, Integer> clothes) {
        return clothes.entrySet().stream()
                .mapToInt(e -> ironingTimes.getOrDefault(e.getKey(), 0) * e.getValue())
                .sum();
    }

    private double calculatePrice(int washerCount, int dryerCount, boolean ironing, Map<String, Integer> colored, Map<String, Integer> whites) {
        double price = washerCount * 30 + dryerCount * 25;
        if (ironing) {
            price += estimateTimeForMap(colored) * 1.5;
            price += estimateTimeForMap(whites) * 1.5;
        }
        return price;
    }

    private Map<String, Integer> mergeMaps(Map<String, Integer> map1, Map<String, Integer> map2) {
        Map<String, Integer> result = new HashMap<>(map1);
        for (Map.Entry<String, Integer> entry : map2.entrySet()) {
            result.put(entry.getKey(), result.getOrDefault(entry.getKey(), 0) + entry.getValue());
        }
        return result;
    }

    private Date addMinutes(Date date, int minutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, minutes);
        return cal.getTime();
    }


    public void cancelAppointment(int id) throws IDException, IOException {
        Iterator<Appointment> it = appointments.iterator();
        while (it.hasNext()) {
            Appointment a = it.next();
            for (Machines wm : a.getWashingMachinesColored()) wm.setAvailableCapacity(wm.getCapacityKg());
            for (Machines wm : a.getWashingMachinesWhite()) wm.setAvailableCapacity(wm.getCapacityKg());
            for (Machines d : a.getDryersColored()) d.setAvailableCapacity(d.getCapacityKg());
            for (Machines d : a.getDryersWhite()) d.setAvailableCapacity(d.getCapacityKg());
            if (a.getIronerUsed() != null) ;
            if (a.getId() == id) {
                it.remove();
                saveCustomers();
                saveAppointments();
                saveWashingMachines();
                saveDryers();
                saveIroners();
                return;
            }
        }
        throw new IDException("Randevu ID bulunamadı");
    }

    public void listAppointments() {
        appointments.forEach(System.out::println);
    }

    public List<Appointment> getAppointmentsByCustomerId(int customerId) {
        List<Appointment> result = new ArrayList<>();
        for (Appointment a : appointments) {
            if (a.getCustomer().getCustomerId()==customerId) {
                result.add(a);
            }
        }
        return result;
    }

    public List<Customer> getCustomers() { return customers; }

    public List<Appointment> getAppointments() {
        return this.appointments;
    }

    public void saveCustomers() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("customers.dat"));
        oos.writeObject(customers);
        oos.close();
    }

    public void loadCustomers() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("customers.dat"));
        customers = (List<Customer>) ois.readObject();
        ois.close();
    }

    public void saveAppointments() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("appointments.dat"));
        oos.writeObject(appointments);
        oos.close();
    }

    public void loadAppointments() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("appointments.dat"));
        appointments = (List<Appointment>) ois.readObject();
        ois.close();
    }

    public void saveWashingMachines() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("washingmachines.dat"));
        oos.writeObject(washingMachines);
        oos.close();
    }

    public void loadWashingMachines() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("washingmachines.dat"));
        washingMachines = (List<WashingMachine>) ois.readObject();
        ois.close();
    }

    public void saveDryers() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("dryers.dat"));
        oos.writeObject(dryers);
        oos.close();
    }

    public void loadDryers() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("dryers.dat"));
        dryers = (List<Dryer>) ois.readObject();
        ois.close();
    }

    public void saveIroners() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("ironers.dat"));
        oos.writeObject(ironers);
        oos.close();
    }

    public void loadIroners() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("ironers.dat"));
        ironers = (List<Ironer>) ois.readObject();
        ois.close();
    }


}
