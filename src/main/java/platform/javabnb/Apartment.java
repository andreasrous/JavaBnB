package platform.javabnb;

// το Διαμέρισμα κληρονομεί από την Κατοικία
public class Apartment extends House {

    // επιπλέον χαρακτηριστικά διαμερίσματος
    private final int floor;
    private final boolean elevator, balcony;

    public Apartment(String taxNumber, String houseId, String municipality,
            String address, Landscape view, int people, int bedrooms, int floor,
            double metroDistance, double rentMoney, boolean internetAccess,
            boolean television, boolean kitchen, boolean privateParking,
            boolean elevator, boolean balcony) {

        super(taxNumber, houseId, municipality, address, view, people,
                bedrooms, metroDistance, rentMoney, internetAccess,
                television, kitchen, privateParking);

        if (floor < 0 || floor > 10) {
            throw new IllegalArgumentException("Invalid floor number: " + floor
                    + ".");
        }
        else {
            this.floor = floor;
        }

        this.elevator = elevator;
        this.balcony = balcony;
    }

    public int getFloor() {
        return floor;
    }

    public boolean isElevator() {
        return elevator;
    }

    public boolean isBalcony() {
        return balcony;
    }

    @Override
    public String toString() {
        return String.format("House ID: %-17s| Comfort Level: %-14.1f| "
                + "Elevator: %s%nMunicipality: %-13s| Metro Distance: %.1f %-7s| "
                + "Balcony: %s%nAddress: %-18s| Internet: %-19s| Floor: %d%n"
                + "View: %-21s| Television: %-17s|%nBedrooms: %-17s| Kitchen: %-20s|%n"
                + "People: %-19s| Private Parking: %-12s|%n%n-----------%n"
                + "Daily Rent: $%.2f%n-----------", houseId, comfortLevel,
                elevator, municipality, metroDistance, "m", balcony, address,
                internetAccess, floor, view.getLandscape(), television, bedrooms,
                kitchen, people, privateParking, rentMoney);
    }
}
