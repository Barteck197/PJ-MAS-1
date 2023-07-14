import java.time.LocalDate;
import java.time.Period;

public class Booking extends ObjectPlus{
    private int bookingId;
    private static int globalBookingId = 0;

    // Atrybut złożony - data rozpoczęcia pobytu
    private LocalDate arrivalDate;
    private LocalDate departureDate;

    public Booking(LocalDate arrivalDate, LocalDate departureDate) {
        // Musimy wywołać konstruktor z klasy ObjectPlus
        // aby poprawnie dodać obiekt Booking do ekstensji jego klasy.
        super();

        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        bookingId = ++globalBookingId;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    // Atrybut pochodny - długość pobytu w dniach
    public int getStayDurationDays() {
        Period stayDuration = Period.between(arrivalDate, departureDate);
        return stayDuration.getDays();
    }
}
