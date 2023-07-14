import java.util.ArrayList;
import java.util.List;

public class Person extends ObjectPlus{
    // Atrybut klasowy
    private static int globalPersonId = 0;
    private int personId;
    private String firstName;
    private String lastName;
    private float balance;

    // Atrybut opcjonalny
    private String phoneNumber;

    /**
     * Atrybut powtarzalny - lista rezerwacji danej osoby w obrębie systemu.
     * Zakładamy, że jedna osoba może wiele razy dokonywać rezerwacji
     * oraz, że nie może wielokrotnie dokonywać tej samej rezerwacji
     */
    private List<Booking> bookingList;

    // Konstruktor bez atrybutu opcjonalnego
    public Person(String personFirstName, String personLastName) {
        // Musimy wywołać konstruktor z klasy ObjectPlus
        // aby poprawnie dodać obiekt Person do ekstensji jego klasy.
        super();

        firstName = personFirstName;
        lastName = personLastName;
        personId = ++globalPersonId;
        balance = 0.00f;
        bookingList = new ArrayList<>();
    }

    // Konstruktor z atrybutem opcjonalnym
    public Person(String personFirstName, String personLastName, String personPhoneNumber) {
        // Musimy wywołać konstruktor z klasy ObjectPlus
        // aby poprawnie dodać obiekt Person do ekstensji jego klasy.
        super();

        firstName = personFirstName;
        lastName = personLastName;
        phoneNumber = personPhoneNumber;
        personId = ++globalPersonId;
        balance = 0.00f;
        bookingList = new ArrayList<>();
    }

    public int getPersonId() {
        return personId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public float getBalance() {
        return balance;
    }

    // Metoda przesłaniana
    // saldo można zwiększyć o podaną kwotę
    public void balanceUp(float amount) {
        this.balance = this.balance + amount;
    }

    // Metoda przesłaniana
    // Saldo można zwiększyć o podany procent
    public void balanceUp(int percentage) {
        this.balance = this.balance * (1 + (percentage/100.0f));
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Booking> getBookingList() {
        return bookingList;
    }

    public void addBookingToPerson(Booking addedBooking){
        if (!bookingList.contains(addedBooking)){
            bookingList.add(addedBooking);
        }
    }

    // Metoda do sprawdzania czy atrybut opcjonalny jest zadeklarowany
    public boolean hasPhoneNumber() {
        return phoneNumber != null;
    }

    // Metoda klasowa - pokaż osobę z najwyższym saldem
    public static Person getPersonWithHighestBalance() {
        Person personWithHighestBalance;
        try {
            // Iterujemy po obiektach ekstensji klasy Person
            Iterable<Person> personExtent = ObjectPlus.getExtent(Person.class);
            personWithHighestBalance = personExtent.iterator().next();
            float highestBalance = 0.00f;
            for (Person p : personExtent) {
                // Szukamy najwyższego salda
                if (p.getBalance() > highestBalance) {
                    personWithHighestBalance = p;
                }
            }
        } catch (ClassNotFoundException e) {
            // Na wypadek gdyby ktoś chciał wywołać metodę
            // nie mając utworzonych obietków Person
            throw new RuntimeException(e);
        }
        return personWithHighestBalance;
    }

    // Metoda przeciążana
    @Override
    public String toString() {
        if (phoneNumber == null) {
            return "Osoba: " +
                    "imię = '" + firstName + '\'' +
                    ", nazwisko = '" + lastName + '\'' +
                    ", saldo konta = " + balance +
                    '}';
        } else {
            return "Osoba: " +
                    "imię = '" + firstName + '\'' +
                    ", nazwisko = '" + lastName + '\'' +
                    ", nr telefonu = '" + phoneNumber + '\'' +
                    ", saldo konta = " + balance +
                    '}';
        }
    }
}
