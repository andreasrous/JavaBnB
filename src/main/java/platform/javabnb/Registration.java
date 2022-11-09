package platform.javabnb;

// εισαγωγές
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// κλάση Καταχώρηση
public class Registration {

    // μια καταχώρηση μπορεί να περιέχει 1 από τα 3 είδη κατοικίας
    private House house;
    private Apartment apartment;
    private DetachedHouse detachedHouse;

    // διάστημα ενοικίασης της συγκεκριμένης κατοικίας
    private String rentalPeriod = "-";

    public Registration(House house) {
        this.house = house;
    }

    public Registration(Apartment apartment) {
        this.apartment = apartment;
    }

    public Registration(DetachedHouse detachedHouse) {
        this.detachedHouse = detachedHouse;
    }

    public House getHouse() {
        return house;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public DetachedHouse getDetachedHouse() {
        return detachedHouse;
    }

    public String getRentalPeriod() {
        return rentalPeriod;
    }

    public void setRentalPeriod(String rentalPeriod) {
        this.rentalPeriod = rentalPeriod;
    }

    public LocalDate getStart() {
        if (rentalPeriod.equals("-")) {
            return LocalDate.now();
        }
        else {
            char[] array = rentalPeriod.toCharArray();
            int i;

            for (i = 0; i < array.length; i++) {
                if (array[i] == ' ') {
                    break;
                }
            }
            return LocalDate.parse(rentalPeriod.substring(0, i),
                    DateTimeFormatter.ofPattern("d/M/yy"));
        }
    }

    public LocalDate getEnd() {
        if (rentalPeriod.equals("-")) {
            return LocalDate.now();
        }
        else {
            char[] array = rentalPeriod.toCharArray();
            int i;

            for (i = 0; i < array.length; i++) {
                if (array[i] == '-') {
                    break;
                }
            }
            return LocalDate.parse(rentalPeriod.substring(i + 2),
                    DateTimeFormatter.ofPattern("d/M/yy"));
        }
    }

    @Override
    public String toString() {
        if (house != null) {
            return "" + house;
        }
        else if (apartment != null) {
            return "" + apartment;
        }
        else {
            return "" + detachedHouse;
        }
    }
}
