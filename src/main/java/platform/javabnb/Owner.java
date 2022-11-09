package platform.javabnb;

// εισαγωγές
import java.util.ArrayList;
import static java.lang.Character.*;
import static java.lang.Math.pow;

// κλάση Ιδιοκτήτης
public class Owner {

    // στοιχεία ιδιοκτήτη
    private final String taxNumber;
    private final String firstName;
    private final String lastName;
    private final String idNumber;
    private final String address;

    // λίστα καταχωρήσεων κατοικιών
    private ArrayList<Registration> registrationList;

    public Owner(String taxNumber, String idNumber, String firstName,
            String lastName, String address) {

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

        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.registrationList = new ArrayList<>();
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

    public String getAddress() {
        return address;
    }

    public ArrayList<Registration> getRegistrationList() {
        return registrationList;
    }

    public void setRegistrationList(ArrayList<Registration> registrationList) {
        this.registrationList = registrationList;
    }

    public String addRegistration(Registration property) {
        registrationList.add(property);
        if (property.getHouse() != null) {
            return String.format("House ID: %s%nOwner: %s %s%nTax Number: %s",
                    property.getHouse().houseId, firstName, lastName, taxNumber);
        }
        else if (property.getApartment() != null) {
            return String.format("House ID: %s%nOwner: %s %s%nTax Number: %s",
                    property.getApartment().houseId, firstName, lastName,
                    taxNumber);
        }
        else if (property.getDetachedHouse() != null) {
            return String.format("House ID: %s%nOwner: %s %s%nTax Number: %s",
                    property.getDetachedHouse().houseId, firstName, lastName,
                    taxNumber);
        }
        return String.format("House ID: ???%nOwner: ???%nTax Number: ???");
    }

    public String showRegistration(String houseId) {
        if (houseId.length() != 7) {
            throw new IllegalArgumentException("House Identification Number "
                    + "must be exactly 7 characters long.");
        }
        else if (!isLetter(houseId.charAt(0)) || !isLetter(houseId.charAt(1))) {
            throw new IllegalArgumentException("Invalid House Identification "
                    + "Number: " + houseId + ", first 2 characters "
                    + "should be letters.");
        }
        else if (houseId.charAt(2) != '-') {
            throw new IllegalArgumentException("Invalid House Identification "
                    + "Number: " + houseId + ", letters and digits should "
                    + "be separated by a hyphen.");
        }
        else if (!isDigit(houseId.charAt(3)) || !isDigit(houseId.charAt(4))
                || !isDigit(houseId.charAt(5)) || !isDigit(houseId.charAt(6))) {
            throw new IllegalArgumentException("Invalid House Identification "
                    + "Number: " + houseId + ", last 4 characters should be "
                    + "digits.");
        }
        else {
            houseId = "" + toUpperCase(houseId.charAt(0))
                    + toUpperCase(houseId.charAt(1)) + houseId.substring(2);
        }

        for (int i = 0; i < registrationList.size(); i++) {
            if (registrationList.get(i).getHouse() != null && houseId.
                    equals(registrationList.get(i).getHouse().houseId)) {
                return String.format("Rental Period: %s", registrationList.
                        get(i).getRentalPeriod());
            }
            else if (registrationList.get(i).getApartment() != null && houseId.
                    equals(registrationList.get(i).getApartment().houseId)) {
                return String.format("Rental Period: %s", registrationList.
                        get(i).getRentalPeriod());
            }
            else if (registrationList.get(i).getDetachedHouse() != null
                    && houseId.equals(registrationList.get(i).
                            getDetachedHouse().houseId)) {
                return String.format("Rental Period: %s", registrationList.
                        get(i).getRentalPeriod());
            }
        }
        return "Property with ID: " + houseId + " doesn't exist.";
    }

    public String showRegistrations() {
        String allRegistrations = "";
        if (!registrationList.isEmpty()) {
            for (Registration property : registrationList) {
                allRegistrations = allRegistrations.concat(String.format("%nHouse"
                        + " Information%n-----------------%n%s%n", property));
            }
        }
        else {
            allRegistrations = String.format("%n>>> Owner %s %s hasn't "
                    + "registered a property yet%n", firstName, lastName);
        }
        return allRegistrations;
    }

    @Override
    public String toString() {
        return String.format("Owner: %s %s%nAddress: %s%nTax Number: %s%nID "
                + "Number: %s", firstName, lastName, address, taxNumber, idNumber);
    }
}
