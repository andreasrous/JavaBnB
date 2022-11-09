package platform.javabnb;

// εισαγωγές
import java.time.LocalDate;
import java.time.format.FormatStyle;
import java.time.format.DateTimeFormatter;
import static java.lang.Character.isDigit;
import static java.lang.Character.toLowerCase;
import static java.lang.Character.toUpperCase;

// κλάση Κράτηση
public class Reservation {

    // χαρακτηριστικά κράτησης
    private LocalDate start;
    private LocalDate end;
    private String rentalPeriod;
    private final Registration property;
    private final String reservationCode;
    private double payAmount;

    public Reservation(Registration property, LocalDate start, LocalDate end,
            String reservationCode, double payAmount) {

        if (reservationCode.length() != 13) {
            throw new IllegalArgumentException("Reservation Code must be "
                    + "exactly 13 characters long.");
        }
        else if (property.getHouse() != null && (toLowerCase(reservationCode.
                charAt(0)) != toLowerCase(property.getHouse().municipality.
                charAt(0)) || toLowerCase(reservationCode.charAt(1))
                != toLowerCase(property.getHouse().municipality.charAt(1)))) {
            throw new IllegalArgumentException("Invalid Reservation Code: "
                    + reservationCode + ", first 2 characters should be "
                    + toUpperCase(property.getHouse().municipality.charAt(0))
                    + toUpperCase(property.getHouse().municipality.charAt(1))
                    + ".");
        }
        else if (property.getApartment() != null && (toLowerCase(reservationCode.
                charAt(0)) != toLowerCase(property.getApartment().municipality.
                charAt(0)) || toLowerCase(reservationCode.charAt(1))
                != toLowerCase(property.getApartment().municipality.charAt(1)))) {
            throw new IllegalArgumentException("Invalid Reservation Code: "
                    + reservationCode + ", first 2 characters should be "
                    + toUpperCase(property.getApartment().municipality.charAt(0))
                    + toUpperCase(property.getApartment().municipality.charAt(1))
                    + ".");
        }
        else if (property.getDetachedHouse() != null && (toLowerCase(reservationCode.
                charAt(0)) != toLowerCase(property.getDetachedHouse().municipality.
                charAt(0)) || toLowerCase(reservationCode.charAt(1))
                != toLowerCase(property.getDetachedHouse().municipality.charAt(1)))) {
            throw new IllegalArgumentException("Invalid Reservation Code: "
                    + reservationCode + ", first 2 characters should be "
                    + toUpperCase(property.getDetachedHouse().municipality.charAt(0))
                    + toUpperCase(property.getDetachedHouse().municipality.charAt(1))
                    + ".");
        }
        else if (reservationCode.charAt(2) != '-') {
            throw new IllegalArgumentException("Invalid Reservation Code: "
                    + reservationCode + ", first 2 letters should be followed "
                    + "by a hyphen.");
        }
        else if (!isDigit(reservationCode.charAt(3))
                || !isDigit(reservationCode.charAt(4))
                || !isDigit(reservationCode.charAt(5))
                || !isDigit(reservationCode.charAt(6))) {
            throw new IllegalArgumentException("Invalid Reservation Code: "
                    + reservationCode + ", middle 4 characters should be "
                    + "digits.");
        }
        else if (reservationCode.charAt(7) != '-') {
            throw new IllegalArgumentException("Invalid Reservation Code: "
                    + reservationCode + ", middle 4 digits should be followed "
                    + "by a hyphen.");
        }
        else if (!isDigit(reservationCode.charAt(8))
                || !isDigit(reservationCode.charAt(9))
                || !isDigit(reservationCode.charAt(10))
                || !isDigit(reservationCode.charAt(11))
                || !isDigit(reservationCode.charAt(12))) {
            throw new IllegalArgumentException("Invalid Reservation Code: "
                    + reservationCode + ", last 5 characters should be "
                    + "digits.");
        }
        else {
            this.reservationCode = "" + toUpperCase(reservationCode.charAt(0))
                    + toUpperCase(reservationCode.charAt(1))
                    + reservationCode.substring(2);
        }

        if (start.isAfter(LocalDate.now()) && end.isAfter(start)
                && end.isBefore(start.plusDays(15))) {
            rentalPeriod = String.format("%s - %s", start.format(DateTimeFormatter
                    .ofLocalizedDate(FormatStyle.SHORT)), end.format(DateTimeFormatter
                    .ofLocalizedDate(FormatStyle.SHORT)));
            property.setRentalPeriod(rentalPeriod);
            this.start = start;
            this.end = end;
        }
        else if (!start.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Invalid start date: " + start.
                    format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT))
                    + ".");
        }
        else if (!end.isAfter(start) || !end.isBefore(start.plusDays(15))) {
            throw new IllegalArgumentException("Invalid end date: " + end.
                    format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT))
                    + ".");
        }

        if (property.getHouse() != null) {
            if (payAmount == property.getHouse().rentMoney) {
                this.payAmount = payAmount;
            }
            else if (payAmount > property.getHouse().rentMoney) {
                throw new IllegalArgumentException("Invalid payment amount: $"
                        + payAmount + " > $" + property.getHouse().rentMoney
                        + ".");
            }
            else {
                throw new IllegalArgumentException("Invalid payment amount: $"
                        + payAmount + " < $" + property.getHouse().rentMoney
                        + ".");
            }
        }
        else if (property.getApartment() != null) {
            if (payAmount == property.getApartment().rentMoney) {
                this.payAmount = payAmount;
            }
            else if (payAmount > property.getApartment().rentMoney) {
                throw new IllegalArgumentException("Invalid payment amount: $"
                        + payAmount + " > $" + property.getApartment().rentMoney
                        + ".");
            }
            else {
                throw new IllegalArgumentException("Invalid payment amount: $"
                        + payAmount + " < $" + property.getApartment().rentMoney
                        + ".");
            }
        }
        else if (property.getDetachedHouse() != null) {
            if (payAmount == property.getDetachedHouse().rentMoney) {
                this.payAmount = payAmount;
            }
            else if (payAmount > property.getDetachedHouse().rentMoney) {
                throw new IllegalArgumentException("Invalid payment amount: $"
                        + payAmount + " > $" + property.getDetachedHouse().rentMoney
                        + ".");
            }
            else {
                throw new IllegalArgumentException("Invalid payment amount: $"
                        + payAmount + " < $" + property.getDetachedHouse().rentMoney
                        + ".");
            }
        }

        this.property = property;
    }

    public void setStart(LocalDate start) {
        if (start.isAfter(LocalDate.now())) {
            rentalPeriod = String.format("%s - %s", start.format(DateTimeFormatter
                    .ofLocalizedDate(FormatStyle.SHORT)), end.format(DateTimeFormatter
                    .ofLocalizedDate(FormatStyle.SHORT)));
        }
        else if (!start.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Invalid start date: " + start.
                    format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT))
                    + ".");
        }
        this.start = start;
    }

    public void setEnd(LocalDate end) {
        if (end.isAfter(start) && end.isBefore(start.plusDays(15))) {
            rentalPeriod = String.format("%s - %s", start.format(DateTimeFormatter
                    .ofLocalizedDate(FormatStyle.SHORT)), end.format(DateTimeFormatter
                    .ofLocalizedDate(FormatStyle.SHORT)));
        }
        else if (!end.isAfter(start) || !end.isBefore(start.plusDays(15))) {
            throw new IllegalArgumentException("Invalid end date: " + end.
                    format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT))
                    + ".");
        }
        this.end = end;
    }

    public void setPayAmount(double payAmount) {
        if (property.getHouse() != null) {
            if (payAmount > property.getHouse().rentMoney) {
                throw new IllegalArgumentException("Invalid payment amount: $"
                        + payAmount + " > $" + property.getHouse().rentMoney
                        + ".");
            }
            else {
                throw new IllegalArgumentException("Invalid payment amount: $"
                        + payAmount + " < $" + property.getHouse().rentMoney
                        + ".");
            }
        }
        else if (property.getApartment() != null) {
            if (payAmount > property.getApartment().rentMoney) {
                throw new IllegalArgumentException("Invalid payment amount: $"
                        + payAmount + " > $" + property.getApartment().rentMoney
                        + ".");
            }
            else {
                throw new IllegalArgumentException("Invalid payment amount: $"
                        + payAmount + " < $" + property.getApartment().rentMoney
                        + ".");
            }
        }
        else if (property.getDetachedHouse() != null) {
            if (payAmount > property.getDetachedHouse().rentMoney) {
                throw new IllegalArgumentException("Invalid payment amount: $"
                        + payAmount + " > $" + property.
                                getDetachedHouse().rentMoney + ".");
            }
            else {
                throw new IllegalArgumentException("Invalid payment amount: $"
                        + payAmount + " < $" + property.
                                getDetachedHouse().rentMoney + ".");
            }
        }
        this.payAmount = payAmount;
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }

    public String getRentalPeriod() {
        return rentalPeriod;
    }

    public Registration getProperty() {
        return property;
    }

    public String getReservationCode() {
        return reservationCode;
    }

    public double getPayAmount() {
        return payAmount;
    }

    @Override
    public String toString() {
        return String.format("Reservation Code: %s%nRental Period: %s%n"
                + "Payment Amount: $%.2f", reservationCode, rentalPeriod,
                payAmount);
    }
}
