import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Appointment implements Serializable {


    private int id;
    private Customer customer;
    private Date appointmentDate;

    private List<WashingMachine> washingMachinesColored;
    private List<WashingMachine> washingMachinesWhite;
    private List<Dryer> dryersColored;
    private List<Dryer> dryersWhite;
    private Ironer ironerUsed;

    private double price;
    private int estimatedTime; // in minutes
    private Map<String, Integer> clothingQuantities;

    public Appointment(int id, Customer customer, Date appointmentDate,
                       List<WashingMachine> washingMachinesColored,
                       List<WashingMachine> washingMachinesWhite,
                       List<Dryer> dryersColored,
                       List<Dryer> dryersWhite,
                       Ironer ironerUsed,
                       double price, int estimatedTime,
                       Map<String, Integer> clothingQuantities) {
        this.id = id;
        this.customer = customer;
        this.appointmentDate = appointmentDate;
        this.washingMachinesColored = washingMachinesColored;
        this.washingMachinesWhite = washingMachinesWhite;
        this.dryersColored = dryersColored;
        this.dryersWhite = dryersWhite;
        this.ironerUsed = ironerUsed;
        this.price = price;
        this.estimatedTime = estimatedTime;
        this.clothingQuantities = clothingQuantities;
    }

    public int getId() { return id; }
    public Customer getCustomer() { return customer; }
    public Date getAppointmentDate() { return appointmentDate; }

    public List<WashingMachine> getWashingMachinesColored() { return washingMachinesColored; }
    public List<WashingMachine> getWashingMachinesWhite() { return washingMachinesWhite; }

    public List<Dryer> getDryersColored() { return dryersColored; }
    public List<Dryer> getDryersWhite() { return dryersWhite; }

    public Ironer getIronerUsed() { return ironerUsed; }

    public double getPrice() { return price; }
    public int getEstimatedTime() { return estimatedTime; }
    public Map<String, Integer> getClothingQuantities() { return clothingQuantities; }


    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        String wmColoredStr = machinesToString(washingMachinesColored);
        String wmWhiteStr = machinesToString(washingMachinesWhite);
        String dColoredStr = machinesToString(dryersColored);
        String dWhiteStr = machinesToString(dryersWhite);

        return """
                Randevu ID: %d
                Müşteri: %s
                Tarih: %s
                Yıkama (Renkli): %s
                Yıkama (Beyaz): %s
                Kurutma (Renkli): %s
                Kurutma (Beyaz): %s
                Ütücü: %s
                Ücret: %.2f TL
                Tahmini Süre: %d dakika
                """.formatted(
                id,
                customer.getName(),
                sdf.format(appointmentDate),
                wmColoredStr,
                wmWhiteStr,
                dColoredStr,
                dWhiteStr,
                ironerUsed != null ? ironerUsed.getBrand() + " (ID: " + ironerUsed.getId() + ")" : "Yok",
                price,
                estimatedTime
        );
    }

    private String machinesToString(List<? extends Machines> list) {
        if (list == null || list.isEmpty()) return "Yok";
        StringBuilder sb = new StringBuilder();
        for (Machines m : list) {
            sb.append(m.getBrand()).append(" (ID: ").append(m.getId()).append("), ");
        }
        return sb.substring(0, sb.length() - 2);
    }
}
