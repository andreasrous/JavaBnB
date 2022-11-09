package platform.javabnb;

// εισαγωγές
import static java.lang.Character.*;
import static java.lang.Math.pow;

// κλάση Κατοικία
public class House {

    // χαρακτηριστικά κατοικίας
    protected final Landscape view;
    protected final double metroDistance;

    protected final String taxNumber,
            houseId,
            municipality,
            address;

    protected int people,
            bedrooms;

    protected double rentMoney,
            comfortLevel;

    protected boolean internetAccess,
            television,
            kitchen,
            privateParking;

    public House(String taxNumber, String houseId, String municipality,
            String address, Landscape view, int people, int bedrooms,
            double metroDistance, double rentMoney, boolean internetAccess,
            boolean television, boolean kitchen, boolean privateParking) {

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

        if (houseId.length() != 7) {
            throw new IllegalArgumentException("House Identification Number "
                    + "must be exactly 7 characters long.");
        }
        else if (toLowerCase(houseId.charAt(0))
                != toLowerCase(municipality.charAt(0))
                || toLowerCase(houseId.charAt(1))
                != toLowerCase(municipality.charAt(1))) {
            throw new IllegalArgumentException("Invalid House Identification "
                    + "Number: " + houseId + ", first 2 characters "
                    + "should be " + toUpperCase(municipality.charAt(0))
                    + toUpperCase(municipality.charAt(1)) + ".");
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
            this.houseId = "" + toUpperCase(houseId.charAt(0))
                    + toUpperCase(houseId.charAt(1)) + houseId.substring(2);
        }

        if (people < 1 || people > 8) {
            throw new IllegalArgumentException(people > 8 ? "Ηouse can not "
                    + "accommodate more than 8 people" : "Invalid room size: "
                    + people + ".");
        }
        else {
            this.people = people;
        }

        if (bedrooms < 0 || bedrooms > 12) {
            throw new IllegalArgumentException(bedrooms > 12 ? "House can not "
                    + "have more than 12 bedrooms." : "Invalid bedroom number: "
                    + bedrooms + ".");
        }
        else {
            this.bedrooms = bedrooms;
        }

        if (metroDistance < 0.0 || metroDistance > 2000.0) {
            throw new IllegalArgumentException(metroDistance > 2000.0
                    ? "Distance from nearest metro station should not exceed "
                    + "2 km." : "Invalid distance from nearest metro station: "
                    + metroDistance + ".");
        }
        else {
            this.metroDistance = metroDistance;
        }

        if (rentMoney < 0.0 || rentMoney > 200.0) {
            throw new IllegalArgumentException(rentMoney > 200.0 ? "The daily"
                    + " rental cost of the property should not exceed $200."
                    : "Invalid daily rental cost: $" + rentMoney + ".");
        }
        else {
            this.rentMoney = rentMoney;
        }

        this.municipality = municipality;
        this.address = address;
        this.view = view;
        this.comfortLevel = (double) people / bedrooms;
        this.internetAccess = internetAccess;
        this.television = television;
        this.kitchen = kitchen;
        this.privateParking = privateParking;
    }

    public Landscape getView() {
        return view;
    }

    public double getMetroDistance() {
        return metroDistance;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public String getHouseId() {
        return houseId;
    }

    public String getMunicipality() {
        return municipality;
    }

    public String getAddress() {
        return address;
    }

    public int getPeople() {
        return people;
    }

    public void setPeople(int people) {
        if (people < 1 || people > 8) {
            throw new IllegalArgumentException(people > 8 ? "Ηouse can not "
                    + "accommodate more than 8 people" : "Invalid room size: "
                    + people + ".");
        }
        else {
            comfortLevel = (double) people / bedrooms;
            this.people = people;
        }
    }

    public int getBedrooms() {
        return bedrooms;
    }

    public void setBedrooms(int bedrooms) {
        if (bedrooms < 0 || bedrooms > 12) {
            throw new IllegalArgumentException(bedrooms > 12 ? "House can not "
                    + "have more than 12 bedrooms." : "Invalid bedroom number: "
                    + bedrooms + ".");
        }
        else {
            comfortLevel = (double) people / bedrooms;
            this.bedrooms = bedrooms;
        }
    }

    public double getRentMoney() {
        return rentMoney;
    }

    public void setRentMoney(double rentMoney) {
        if (rentMoney < 0.0 || rentMoney > 200.0) {
            throw new IllegalArgumentException(rentMoney > 200.0 ? "The daily"
                    + " rental cost of the property should not exceed $200."
                    : "Invalid daily rental cost: $" + rentMoney + ".");
        }
        else {
            this.rentMoney = rentMoney;
        }
    }

    public double getComfortLevel() {
        return comfortLevel;
    }

    public boolean isInternetAccess() {
        return internetAccess;
    }

    public void setInternetAccess(boolean internetAccess) {
        this.internetAccess = internetAccess;
    }

    public boolean isTelevision() {
        return television;
    }

    public void setTelevision(boolean television) {
        this.television = television;
    }

    public boolean isKitchen() {
        return kitchen;
    }

    public void setKitchen(boolean kitchen) {
        this.kitchen = kitchen;
    }

    public boolean isPrivateParking() {
        return privateParking;
    }

    public void setPrivateParking(boolean privateParking) {
        this.privateParking = privateParking;
    }

    @Override
    public String toString() {
        return String.format("House ID: %-17s| Comfort Level: %.1f%n"
                + "Municipality: %-13s| Metro Distance: %.1f m%nAddress: %-18s| "
                + "Internet: %s%nView: %-21s| Television: %s%nBedrooms: %-17s| "
                + "Kitchen: %s%nPeople: %-19s| Private Parking: %s%n%n"
                + "-----------%nDaily Rent: $%.2f%n-----------", houseId,
                comfortLevel, municipality, metroDistance, address,
                internetAccess, view.getLandscape(), television, bedrooms,
                kitchen, people, privateParking, rentMoney);
    }
}
