import java.io.Serializable;
import java.util.*;

public class Machines implements Serializable {
    private int id;
    private String brand;
    private double capacityKg;
    private double availableCapacity;
    private List<Appointment> assignedAppointments;
    private List<TimeSlot> reservations;

    public Machines(int id, String brand, double capacityKg) {
        this.id = id;
        this.brand = brand;
        this.capacityKg = capacityKg;
        this.availableCapacity = capacityKg;
        this.assignedAppointments = new ArrayList<>();
        this.reservations = new ArrayList<>();
    }

    public int getId() { return id; }
    public String getBrand() { return brand; }
    public double getCapacityKg() { return capacityKg; }
    public double getAvailableCapacity() { return availableCapacity; }
    public void setAvailableCapacity(double availableCapacity) { this.availableCapacity = availableCapacity; }
    public List<Appointment> getAssignedAppointments() { return assignedAppointments; }

    public void addAppointment(Appointment appt) {
        assignedAppointments.add(appt);
    }

    public boolean isAvailableAt(Date start, int durationMinutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(start);
        cal.add(Calendar.MINUTE, durationMinutes);
        Date end = cal.getTime();

        for (TimeSlot slot : reservations) {
            if (!(end.compareTo(slot.start) <= 0 || start.compareTo(slot.end) >= 0)) {
                return false;
            }
        }
        return true;
    }

    public void reserve(Date start, int durationMinutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(start);
        cal.add(Calendar.MINUTE, durationMinutes);
        Date end = cal.getTime();
        reservations.add(new TimeSlot(start, end));
    }

    private static class TimeSlot implements Serializable {
        Date start;
        Date end;

        TimeSlot(Date start, Date end) {
            this.start = start;
            this.end = end;
        }
    }
}
