import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.time.LocalDate;

public class Main {
    // Deklaracja pliku, który umożliwia utrwalenie ekstensji
    static File solidFile = new File(".\\data\\file.bin");
    public static void main(String[] args) {
        if (solidFile.exists()){
            try {
                System.out.println("Plik istnieje, wczytujemy dane z pliku");

                // Deserializacja pliku...
                ObjectInputStream inStream = new ObjectInputStream(Files.newInputStream(solidFile.toPath()));
                // ... oraz wczytanie obiektów klas do ich ekstensji
                ObjectPlus.readExtents(inStream);

                inStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Plik nie istnieje: tworzymy obiekty");

            Person p1 = new Person("Mikołaj", "Krajewski");
            Person p2 = new Person("Aleksandra", "Lewandowska", "+48667199231");
            Person p3 = new Person("Władysław", "Nowak");
            Person p4 = new Person("Joanna", "Baran");

            // Przykład odwołania do obiektów z ekstensji
            try {
                ObjectPlus.showExtent(Person.class);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            // Przykład użycia atrybutu złożonego
            LocalDate arrival1 = LocalDate.of(2022,11,20);
            LocalDate departure1 = LocalDate.of(2022,11,26);

            Booking b1 = new Booking(arrival1, departure1);
            Booking b2 = new Booking(LocalDate.of(2022,11,12), LocalDate.of(2022,11,14));

            // Przykład użycia atrybutu opcjonalnego
            if (p2.hasPhoneNumber()){
                System.out.println(p2.getPhoneNumber());
            } else {
                p2.setPhoneNumber("+48 934201554");
                System.out.println(p2.getPhoneNumber());
            }

            // Przykład użycia atrybutu powtarzalnego
            p1.addBookingToPerson(b1);
            p1.addBookingToPerson(
                    new Booking(
                            LocalDate.of(2022,12,20),
                            LocalDate.of(2023,1,2)
                    ));
            System.out.println(p1.getBookingList());

            // Przykład użycia atrybutu pochodnego
            int b1duration = b1.getStayDurationDays();
            System.out.println(b1duration);

            // Przykład przesłonięcia metody
            p1.balanceUp(123.00f);
            p1.balanceUp(10);

            // Przykład użycia metody klasowej
            Person high = Person.getPersonWithHighestBalance();
            // Przy okazji przykład użycia przeciążonej metody
            System.out.println(high.toString());

        }

        // Tak czy siak po zakończeniu pracy programu aktualizujemy plik
        try {
            ObjectOutputStream outStream = new ObjectOutputStream(
                    Files.newOutputStream(solidFile.toPath()));
            ObjectPlus.writeExtents(outStream);
            outStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}