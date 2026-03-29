import java.io.Serializable;
import java.util.*;

public class Ironer implements Serializable {
    private int id;
    private String brand;
    private List<Appointment> assignedAppointments;
    private List<TimeSlot> reservations;

    public Ironer(int id, String brand) {
        this.id = id;
        this.brand = brand;
        this.assignedAppointments = new ArrayList<>();
        this.reservations = new ArrayList<>();
    }

    public int getId() { return id; }
    public String getBrand() { return brand; }
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
            if (!(end.before(slot.start) || start.after(slot.end))) {
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

    private static class TimeSlot {
        Date start;
        Date end;

        TimeSlot(Date start, Date end) {
            this.start = start;
            this.end = end;
        }
    }
}
