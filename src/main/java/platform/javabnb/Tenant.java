package platform.javabnb;

// εισαγωγές
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.FormatStyle;
import java.time.format.DateTimeFormatter;
import static java.time.temporal.ChronoUnit.DAYS;
import static java.lang.Character.*;
import static java.lang.Math.pow;

// κλάση Ενοικιαστής
public class Tenant {

    // στοιχεία ενοικιαστή
    private final String taxNumber;
    private final String firstName;
    private final String lastName;
    private final String idNumber;
    private final String email;

    // συνολικό κόστος κρατήσεων
    private double totalCost;

    // λίστα κρατήσεων κατοικιών
    private ArrayList<Reservation> reservationList;

    public Tenant(String taxNumber, String idNumber, String firstName,
            String lastName, String email) {

        if (taxNumber.length() == 9) {
            try {
                Integer.parseInt(taxNumber);
            }
            catch (NumberFormatException error) {
                throw new IllegalArgumentException("Tax Identification Number "
                        + "must consist only of digits.");
            }

            int sum = 0;
            int product;
            int lastDigit = getNumericValue(taxNumber.charAt(8));

            for (int i = 0; i < 8; i++) {
                product = getNumericValue(taxNumber.charAt(i));
                product *= pow(2, 8 - i);
                sum += product;
            }

            if ((sum % 11) % 10 == lastDigit && !taxNumber.equals("000000000")) {
                this.taxNumber = taxNumber;
            }
            else {
                throw new IllegalArgumentException("Invalid Tax Identification "
                        + "Number: " + taxNumber + ".");
            }
        }
        else {
            throw new IllegalArgumentException("Tax Identification Number "
                    + "must be exactly 9 digits long.");
        }

        if (idNumber.length() != 9) {
            throw new IllegalArgumentException("ID Number must be exactly 9 "
                    + "characters long.");
        }
        else if (!isLetter(idNumber.charAt(0)) || !isLetter(idNumber.charAt(1))) {
            throw new IllegalArgumentException("Invalid ID Number: " + idNumber
                    + ", first 2 characters should be letters.");
        }
        else if (idNumber.charAt(2) != ' ') {
            throw new IllegalArgumentException("Invalid ID Number: " + idNumber
                    + ", letters and digits should be separated by a space.");
        }
        else if (!isDigit(idNumber.charAt(3)) || !isDigit(idNumber.charAt(4))
                || !isDigit(idNumber.charAt(5)) || !isDigit(idNumber.charAt(6))
                || !isDigit(idNumber.charAt(7)) || !isDigit(idNumber.charAt(8))) {
            throw new IllegalArgumentException("Invalid ID Number: " + idNumber
                    + ", last 6 characters should be digits.");
        }
        else {
            this.idNumber = "" + toUpperCase(idNumber.charAt(0))
                    + toUpperCase(idNumber.charAt(1)) + idNumber.substring(2);
        }

        if (email.length() == 14) {
            if (email.charAt(5) != '@') {
                throw new IllegalArgumentException("Invalid email address: "
                        + email + ", the username should be followed by an at"
                        + " (@) symbol.");
            }
            if (email.charAt(10) != '.') {
                throw new IllegalArgumentException("Invalid email address: "
                        + email + ", the domain name should end with an "
                        + "extension such as \".zzz\".");
            }
            this.email = email;
        }
        else {
            throw new IllegalArgumentException("Email address must be exactly "
                    + "14 characters long.");
        }

        this.totalCost = 0;
        this.firstName = firstName;
        this.lastName = lastName;
        this.reservationList = new ArrayList<>();
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public String getEmail() {
        return email;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public ArrayList<Reservation> getReservationList() {
        return reservationList;
    }

    public void setReservationList(ArrayList<Reservation> reservationList) {
        this.reservationList = reservationList;
    }

    public String addReservation(Reservation reservation) {
        totalCost += reservation.getPayAmount();
        reservationList.add(reservation);
        return "" + reservation;
    }

    public String deleteReservation(String reservationCode, LocalDate start,
            LocalDate end) {
        if (reservationCode.length() != 13) {
            throw new IllegalArgumentException("Reservation Code must be "
                    + "exactly 13 characters long.");
        }
        else if (!isLetter(reservationCode.charAt(0))
                || !isLetter(reservationCode.charAt(1))) {
            throw new IllegalArgumentException("Invalid Reservation Code: "
                    + reservationCode + ", first 2 characters should be letters.");
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
            reservationCode = "" + toUpperCase(reservationCode.charAt(0))
                    + toUpperCase(reservationCode.charAt(1))
                    + reservationCode.substring(2);
        }

        if (!start.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Invalid start date: " + start.
                    format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT))
                    + ".");
        }
        else if (!end.isAfter(start) || !end.isBefore(start.plusDays(15))) {
            throw new IllegalArgumentException("Invalid end date: " + end.
                    format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT))
                    + ".");
        }

        double charge, reservationCost, daysBeforeReservation;
        for (int i = 0; i < reservationList.size(); i++) {
            if (reservationList.get(i).getProperty().getHouse() != null
                    && reservationCode.equals(reservationList.
                            get(i).getReservationCode())) {
                reservationCost = reservationList.get(i).getProperty().
                        getHouse().rentMoney;
                daysBeforeReservation = DAYS.between(LocalDate.now(), start);
                charge = reservationCost * 0.1 + reservationCost
                        / daysBeforeReservation;
                reservationList.get(i).getProperty().
                        setRentalPeriod("-");
                totalCost += charge - reservationList.get(i).getPayAmount();
                reservationList.remove(i);
                return String.format("Reservation Code: %s%nCharge: $%.2f",
                        reservationCode, charge);
            }
            else if (reservationList.get(i).getProperty().getApartment() != null
                    && reservationCode.equals(reservationList.get(i).
                            getReservationCode())) {
                reservationCost = reservationList.get(i).getProperty().
                        getApartment().rentMoney;
                daysBeforeReservation = DAYS.between(LocalDate.now(), start);
                charge = reservationCost * 0.1 + reservationCost
                        / daysBeforeReservation;
                reservationList.get(i).getProperty().
                        setRentalPeriod("-");
                totalCost += charge - reservationList.get(i).getPayAmount();
                reservationList.remove(i);
                return String.format("Reservation Code: %s%nCharge: $%.2f",
                        reservationCode, charge);
            }
            else if (reservationList.get(i).getProperty().getDetachedHouse()
                    != null && reservationCode.equals(reservationList.get(i).
                            getReservationCode())) {
                reservationCost = reservationList.get(i).getProperty().
                        getDetachedHouse().rentMoney;
                daysBeforeReservation = DAYS.between(LocalDate.now(), start);
                charge = reservationCost * 0.1 + reservationCost
                        / daysBeforeReservation;
                reservationList.get(i).getProperty().
                        setRentalPeriod("-");
                totalCost += charge - reservationList.get(i).getPayAmount();
                reservationList.remove(i);
                return String.format("Reservation Code: %s%nCharge: $%.2f",
                        reservationCode, charge);
            }
        }
        return "There are no reservations with code: " + reservationCode + ".";
    }

    public String changeReservation(String reservationCode, LocalDate start,
            LocalDate end) {
        if (reservationCode.length() != 13) {
            throw new IllegalArgumentException("Reservation Code must be "
                    + "exactly 13 characters long.");
        }
        else if (!isLetter(reservationCode.charAt(0))
                || !isLetter(reservationCode.charAt(1))) {
            throw new IllegalArgumentException("Invalid Reservation Code: "
                    + reservationCode + ", first 2 characters should be letters.");
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
            reservationCode = "" + toUpperCase(reservationCode.charAt(0))
                    + toUpperCase(reservationCode.charAt(1))
                    + reservationCode.substring(2);
        }

        if (!start.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Invalid start date: " + start.
                    format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT))
                    + ".");
        }
        else if (!end.isAfter(start) || !end.isBefore(start.plusDays(15))) {
            throw new IllegalArgumentException("Invalid end date: " + end.
                    format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT))
                    + ".");
        }

        for (int i = 0; i < reservationList.size(); i++) {
            if (reservationCode.equals(reservationList.get(i).
                    getReservationCode())) {
                reservationList.get(i).setStart(start);
                reservationList.get(i).setEnd(end);
                reservationList.get(i).getProperty().setRentalPeriod("" + start
                        + " - " + end);
                return String.format("Reservation Code: %s%nRental Period: %s",
                        reservationList.get(i).getReservationCode(),
                        reservationList.get(i).getRentalPeriod());
            }
        }
        return "There are no reservations with code: " + reservationCode + ".";
    }

    public String showReservations() {
        String allReservations = "";
        if (!reservationList.isEmpty()) {
            for (Reservation reservation : reservationList) {
                allReservations = allReservations.concat(String.format("%nBooking "
                        + "Information%n-------------------%n%s%n", reservation));
            }
        }
        else {
            allReservations = String.format("%n>>> Tenant %s %s hasn't "
                    + "reserved a property yet%n", firstName, lastName);
        }
        return allReservations;
    }

    @Override
    public String toString() {
        return String.format("Tenant: %s %s%nEmail: %s%nTax Number: %s%nID "
                + "Number: %s", firstName, lastName, email, taxNumber, idNumber);
    }
}
