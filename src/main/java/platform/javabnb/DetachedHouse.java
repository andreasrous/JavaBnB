package platform.javabnb;

// η Μονοκατοικία κληρονομεί από την Κατοικία
public class DetachedHouse extends House {

    // επιπλέον χαρακτηριστικά μονοκατοικίας
    private final double garden, pool;
    private final boolean barbeque;

    public DetachedHouse(String taxNumber, String houseId, String municipality,
            String address, Landscape view, int people, int bedrooms,
            double metroDistance, double rentMoney, double garden, double pool,
            boolean internetAccess, boolean television, boolean kitchen,
            boolean privateParking, boolean barbeque) {

        super(taxNumber, houseId, municipality, address, view, people,
                bedrooms, metroDistance, rentMoney, internetAccess,
                television, kitchen, privateParking);

        if (garden < 0.0 || garden > 200.0) {
            throw new IllegalArgumentException("Invalid garden size: " + garden
                    + ".");
        }
        else {
            this.garden = garden;
        }

        if (pool < 0.0 || pool > 100.0) {
            throw new IllegalArgumentException("Invalid pool size: " + pool
                    + ".");
        }
        else {
            this.pool = pool;
        }

        this.barbeque = barbeque;
    }

    public double getGarden() {
        return garden;
    }

    public double getPool() {
        return pool;
    }

    public boolean isBarbeque() {
        return barbeque;
    }

    @Override
    public String toString() {
        return String.format("House ID: %-17s| Comfort Level: %-14.1f| "
                + "Barbeque: %s%nMunicipality: %-13s| Metro Distance: %.1f %-7s| "
                + "Garden: %.1f sq m%nAddress: %-18s| Internet: %-19s| Pool: %.1f sq m%n"
                + "View: %-21s| Television: %-17s|%nBedrooms: %-17s| Kitchen: %-20s|%n"
                + "People: %-19s| Private Parking: %-12s|%n%n-----------%n"
                + "Daily Rent: $%.2f%n-----------", houseId, comfortLevel,
                barbeque, municipality, metroDistance, "m", garden, address,
                internetAccess, pool, view.getLandscape(), television, bedrooms,
                kitchen, people, privateParking, rentMoney);
    }
}
