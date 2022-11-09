package platform.javabnb;

// εισαγωγές
import java.time.Year;
import java.time.Month;
import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDate;
import java.security.SecureRandom;
import java.time.format.FormatStyle;
import java.time.format.DateTimeFormatter;
import static java.lang.Character.isDigit;
import static java.lang.Character.isLetter;
import static java.lang.Character.toUpperCase;
import static java.lang.Character.isLetterOrDigit;
import static java.lang.Character.getNumericValue;
import static java.time.temporal.ChronoUnit.DAYS;
import static java.lang.Math.pow;

// εκτελεί το πρόγραμμα
public class JavaBnB {

    public static void main(String[] args) {

        // ενδεικτική κατοικία
        House house = new House("034897394", "TA-1204", "Taurus",
                "Thiseos 70", Landscape.STREET, 8, 12, 92.3,
                14.99, false, true, true, true);

        // ενδεικτικό διαμέρισμα
        Apartment apartment = new Apartment("034897394", "CH-1799",
                "Chania", "Agiou Markou 8", Landscape.SEA, 6, 4, 3,
                502.6, 43.99, false, true, true, false, true, true);

        // ενδεικτική μονοκατοικία
        DetachedHouse detachedHouse = new DetachedHouse("185137199",
                "TR-1922", "Tripoli", "Karaiskaki 70", Landscape.MOUNTAIN,
                2, 2, 840.3, 79.99, 34.1, 15.3, true, true, true, false,
                true);

        // ενδεικτικοί ιδιοκτήτες
        Owner owner1 = new Owner("034897394", "AM 160039", "Marcus",
                "Aurelius", "Xarokopou 17, Kallithea 176 71");

        Owner owner2 = new Owner("185137199", "AΚ 154038", "Flavius",
                "Belisarius", "Ikaron 3, Kozani 501 00");

        // λίστα με όλους τους ιδιοκτήτες
        ArrayList<Owner> owners = new ArrayList<>();
        owners.add(owner1);
        owners.add(owner2);

        // ενδεικτικοί ενοικιαστές
        Tenant tenant1 = new Tenant("094190731", "AB 178946", "Caesar",
                "Augustus", "agsts@mail.com");

        Tenant tenant2 = new Tenant("050207627", "AH 184322", "Scipio",
                "Africanus", "kapod@mail.com");

        // λίστα με όλους τους ενοικιαστές
        ArrayList<Tenant> tenants = new ArrayList<>();
        tenants.add(tenant1);
        tenants.add(tenant2);

        // δημιούργησε καταχωρήσεις για τις τρεις κατοικίες
        Registration property1 = new Registration(house);
        Registration property2 = new Registration(apartment);
        Registration property3 = new Registration(detachedHouse);

        // λίστα με όλες τις καταχωρήσεις κατοικιών
        ArrayList<Registration> registrationList = new ArrayList<>();
        registrationList.add(property1);
        registrationList.add(property2);
        registrationList.add(property3);

        // ο owner1 καταχωρεί το σπίτι και το διαμέρισμα
        System.out.printf("House%n---------------------%n%s"
                + "%n---------------------", owner1.addRegistration(property1));
        System.out.printf("%n%nApartment%n---------------------%n%s"
                + "%n---------------------", owner1.addRegistration(property2));

        // ο owner2 καταχωρεί τη μονοκατοικία
        System.out.printf("%n%nDetached House%n---------------------%n%s"
                + "%n---------------------", owner2.addRegistration(property3));

        // δημιούργησε κρατήσεις για το σπίτι και το διαμέρισμα
        Reservation reservation1 = new Reservation(property1, LocalDate.
                of(Year.now().getValue() + 1, Month.AUGUST, 15),
                LocalDate.of(Year.now().getValue() + 1, Month.AUGUST, 29),
                "TA-2022-01453", 14.99);

        Reservation reservation2 = new Reservation(property2, LocalDate.
                of(Year.now().getValue() + 1, Month.SEPTEMBER, 17),
                LocalDate.of(Year.now().getValue() + 1, Month.OCTOBER, 1),
                "CH-2022-01821", 43.99);

        // ο tenant1 νοικιάζει το σπίτι
        System.out.printf("%n%nReservation%n-----------%n%s%n%n>>> Reservation "
                + "was successful%n%n", tenant1.addReservation(reservation1));

        // ο tenant2 νοικιάζει το διαμέρισμα
        System.out.printf("Reservation%n-----------%n%s%n%n>>> Reservation "
                + "was successful%n%n", tenant2.addReservation(reservation2));

        // δήλωσε μεταβλητές
        Landscape view;
        Owner goldenOwner;
        Tenant goldenTenant;
        LocalDate start, end;
        Registration goldenProperty;
        Reservation goldenReservation;
        Scanner input = new Scanner(System.in);
        int user, service, people, bedrooms, floor;
        SecureRandom randomNumbers = new SecureRandom();
        double metroDistance, garden, pool, rentMoney, propertyCost;
        String s, municipality, firstName, lastName, taxNumber, idNumber,
                houseId, reservationCode, email, address;
        boolean internetAccess, television, kitchen, privateParking, elevator,
                balcony, barbeque;

        // επιλογή χρήστη ή τερματισμός προγράμματος
        System.out.printf("User Selection%n--------------%n1 - Tenant%n"
                + "2 - Owner%n3 - End program%n%n? ");

        // είσοδος χρήστη
        s = input.nextLine();

        // ελέγχει αν η είσοδος είναι έγκυρη
        while (!s.equals("1") && !s.equals("2") && !s.equals("3")) {
            System.out.print("? ");
            s = input.nextLine();
        }

        // μετατρέπει την είσοδο σε ακέραιο
        user = Integer.parseInt(s);

        while (true) {

            // ο χρήστης είναι ενοικιαστής
            if (user == 1) {

                // μενού επιλογών
                System.out.printf("%nChoice Menu%n-----------%n1 - Create a "
                        + "reservation%n2 - Change a reservation%n3 - Cancel a "
                        + "reservation%n4 - Show report%n%n? ");

                // είσοδος χρήστη
                s = input.nextLine();

                // ελέγχει αν η είσοδος είναι έγκυρη
                while (!s.equals("1") && !s.equals("2") && !s.equals("3")
                        && !s.equals("4")) {
                    System.out.print("? ");
                    s = input.nextLine();
                }

                // μετατρέπει την είσοδο σε ακέραιο
                service = Integer.parseInt(s);

                switch (service) {
                    case 1: // δημιουργία κράτησης

                        // κριτήρια αναζήτησης
                        System.out.printf("%nCriteria%n--------%nMunicipality: ");
                        municipality = enterMunicipality(null);

                        System.out.print("Start date: ");
                        start = enterStartDate();

                        System.out.print("End date: ");
                        end = enterEndDate(start);

                        System.out.print("People: ");
                        people = enterPeople();

                        // λίστα διαθέσιμων κατοικιών
                        ArrayList<Registration> availableHouses = new ArrayList<>();

                        // ελέγχει αν υπάρχουν διαθέσιμες κατοικίες με βάση τα
                        // προηγούμενα κριτήρια και εάν ναι, τις εμφανίζει και
                        // τις προσθέτει στην λίστα διαθέσιμων κατοικιών
                        for (Registration property : registrationList) {
                            if (property.getHouse() != null && property.
                                    getRentalPeriod().equals("-")
                                    && property.getHouse().people >= people
                                    && property.getHouse().municipality.
                                            equals(municipality)) {
                                System.out.printf("%nHouse%n------------------%n"
                                        + "House ID:  %s%nDaily Rent: $%.2f%n---"
                                        + "---------------%n", property.
                                                getHouse().houseId, property.
                                                getHouse().rentMoney);
                                availableHouses.add(property);
                            }
                            else if (property.getApartment() != null && property.
                                    getRentalPeriod().equals("-")
                                    && property.getApartment().people >= people
                                    && property.getApartment().municipality.
                                            equals(municipality)) {
                                System.out.printf("%nApartment%n------------------"
                                        + "%nHouse ID:  %s%nDaily Rent: $%.2f%n"
                                        + "------------------%n", property.
                                                getApartment().houseId, property.
                                                getApartment().rentMoney);
                                availableHouses.add(property);
                            }
                            else if (property.getDetachedHouse() != null
                                    && property.getRentalPeriod().equals("-")
                                    && property.getDetachedHouse().people >= people
                                    && property.getDetachedHouse().municipality.
                                            equals(municipality)) {
                                System.out.printf("%nDetached House%n--------------"
                                        + "----%nHouse ID:  %s%nDaily Rent: "
                                        + "$%.2f%n------------------%n",
                                        property.getDetachedHouse().houseId,
                                        property.getDetachedHouse().rentMoney);
                                availableHouses.add(property);
                            }
                        }

                        // δεν υπάρχουν διαθέσιμες κατοικίες
                        if (availableHouses.isEmpty()) {
                            System.out.printf("%n>>> No results%n");
                            break; // επέστρεψε στην επιλογή χρήστη
                        }

                        // εισήγαγε τον Α.Φ.Μ.
                        System.out.printf("%nEnter Tax Number: ");
                        taxNumber = enterTaxNumber();

                        // εκχώρησε στον goldenTenant
                        // την ενδεικτική τιμή null
                        goldenTenant = null;

                        // προσδιορίζει αν ο ενοικιαστής υπάρχει ήδη στην
                        // πλατφόρμα από προηγούμενη καταχώρηση
                        for (Tenant t : tenants) {
                            if (t.getTaxNumber().equals(taxNumber)) {
                                System.out.printf("%n>>> Welcome %s %s!",
                                        t.getFirstName(), t.getLastName());
                                goldenTenant = t;
                                break;
                            }
                        }

                        // οι διαθέσιμες κατοικίες ανήκουν στον ενοικιαστή
                        if (tenantOwnsProperties(taxNumber, availableHouses)) {
                            System.out.printf("%n>>> Can't rent your own "
                                    + "properties%n");
                            break; // επέστρεψε στην επιλογή χρήστη
                        }

                        // ο ενοικιαστής υπάρχει ήδη στην πλατφόρμα από
                        // προηγούμενη καταχώρηση
                        if (goldenTenant != null) {
                            System.out.printf("%n%nInstructions%n------------%n"
                                    + "1) Enter the House ID of the desired "
                                    + "property%n2) Pay initial rent%n");
                        }
                        // ο ενοικιαστής είναι ιδιοκτήτης
                        else if (isOwner(taxNumber, owners)) {

                            // αναζητά τον ιδιοκτήτη στην λίστα ιδιοκτητών
                            // και προτρέπει μόνο για email
                            for (Owner n : owners) {
                                if (n.getTaxNumber().equals(taxNumber)) {
                                    System.out.printf("%n>>> Welcome %s %s!%n>>>"
                                            + " Your full name and ID Number are"
                                            + " already registered.%n>>> Please "
                                            + "provide an email address to become"
                                            + " a tenant.%n%nEnter email: ",
                                            n.getFirstName(), n.getLastName());

                                    // εισαγωγή email
                                    email = enterEmail();

                                    // δημιούργησε νέο ενοικιαστή με βάση
                                    // το email και τα στοιχεία του ιδιοκτήτη
                                    goldenTenant = new Tenant(taxNumber, n.
                                            getIdNumber(), n.getFirstName(),
                                            n.getLastName(), email);

                                    // πρόσθεσε τον ιδιοκτήτη στην λίστα ενοικιαστών
                                    tenants.add(goldenTenant);
                                    System.out.printf("%nInstructions%n------"
                                            + "------%n1) Enter the House ID of"
                                            + " the desired property%n2) Pay "
                                            + "initial rent%n");
                                    break;
                                }
                            }
                        }
                        else { // δημιούργησε νεό ενοικιαστή

                            // προτροπή για όνομα
                            System.out.printf("%nNew Tenant%n----------%n");
                            System.out.printf("Enter first name: ");
                            firstName = enterFirstName();

                            // προτροπή για επώνυμο
                            System.out.printf("Enter last name: ");
                            lastName = enterLastName();

                            System.out.printf("Enter ID Number: ");
                            idNumber = enterIdNumber();

                            System.out.printf("Enter email: ");
                            email = enterEmail();

                            // δημιούργησε νέο ενοικιαστή
                            goldenTenant = new Tenant(taxNumber, idNumber,
                                    firstName, lastName, email);

                            // πρόσθεσε τον νέο ενοικαστή στην λίστα ενοικιαστών
                            tenants.add(goldenTenant);
                            System.out.printf("%nInstructions%n------------%n1) Enter"
                                    + " the House ID of the desired property%n2) Pay "
                                    + "initial rent%n");
                        }

                        // εκχώρησε στο goldenProperty
                        // την ενδεικτική τιμή null
                        goldenProperty = null;

                        // μηδένισε το κόστος κατοικίας
                        propertyCost = 0;

                        // προσδιορίζει αν ο αριθμός ταυτοποίησης κατοικίας που
                        // εισάγει ο χρήστης ταιριάζει με μια από τις διαθέσιμες
                        // κατοικίες
                        while (true) {

                            // είσοδος χρήστη
                            System.out.printf("%nEnter House ID: ");
                            houseId = enterHouseId();

                            // ελέγχει αν υπάρχουν διαθέσιμες κατοικίες με βάση
                            // τον αριθμό ταυτοποίησης κατοικίας που εισήγαγε ο
                            // χρήστης και αν ναι, λαμβάνει το κόστος ενοικιάσης
                            // και εκχωρεί την θέση τους στο goldenProperty
                            for (Registration r : availableHouses) {
                                if (r.getHouse() != null && r.getHouse().houseId.
                                        equals(houseId) && !r.getHouse().taxNumber.
                                        equals(taxNumber)) {
                                    propertyCost = r.getHouse().rentMoney;
                                    goldenProperty = r;
                                    break;
                                }
                                else if (r.getApartment() != null && r.
                                        getApartment().houseId.equals(houseId)
                                        && !r.getApartment().taxNumber.
                                                equals(taxNumber)) {
                                    propertyCost = r.
                                            getApartment().rentMoney;
                                    goldenProperty = r;
                                    break;
                                }
                                else if (r.getDetachedHouse() != null && r.
                                        getDetachedHouse().houseId.equals(houseId)
                                        && !r.getDetachedHouse().taxNumber.
                                                equals(taxNumber)) {
                                    propertyCost = r.
                                            getDetachedHouse().rentMoney;
                                    goldenProperty = r;
                                    break;
                                }
                            }

                            // η κατοικία βρέθηκε
                            if (goldenProperty != null) {
                                break;
                            }
                            else { // η κατοικία με τον καθορισμένο αριθμό ταυτοποίησης
                                // είτε δεν υπάρχει είτε δεν είναι διαθέσιμη με βάση τα
                                // συγκεκριμένα κριτήρια αναζήτησης
                                System.out.printf("%n>>> Property with House ID: %s "
                                        + "either doesn't exist or is already rented",
                                        houseId);
                            }
                        }

                        // είσοδος χρήστη
                        System.out.printf("Pay initial rent: ");
                        rentMoney = payRent();

                        // αναγκάζει τον χρήστη να εισάγει το κατάλληλο ποσό χρημάτων
                        while (rentMoney != propertyCost) {
                            if (rentMoney > propertyCost) {
                                System.out.printf("%n>>> Invalid payment amount:"
                                        + " $%.2f > $%.2f%nPay initial rent: ",
                                        rentMoney, propertyCost);
                                rentMoney = payRent();
                            }
                            else {
                                System.out.printf("%n>>> Invalid payment amount:"
                                        + " $%.2f < $%.2f%nPay initial rent: ",
                                        rentMoney, propertyCost);
                                rentMoney = payRent();
                            }
                        }

                        // εκχώρησε προεπιλεγμένη τιμή στον κωδικό κράτησης
                        reservationCode = "XX-YEAR-KKKKK";

                        // προσδιορίζει το είδος κατοικίας και δημιουργεί τον
                        // κωδικό κράτησης
                        if (goldenProperty.getHouse() != null) {
                            reservationCode = String.format("%c%c-%s-%05d",
                                    goldenProperty.getHouse().houseId.charAt(0),
                                    goldenProperty.getHouse().houseId.charAt(1),
                                    LocalDate.now().getYear(), randomNumbers.
                                    nextInt(100000));
                        }
                        else if (goldenProperty.getApartment() != null) {
                            reservationCode = String.format("%c%c-%s-%05d",
                                    goldenProperty.getApartment().houseId.charAt(0),
                                    goldenProperty.getApartment().houseId.charAt(1),
                                    LocalDate.now().getYear(), randomNumbers.
                                    nextInt(100000));
                        }
                        else if (goldenProperty.getDetachedHouse() != null) {
                            reservationCode = String.format("%c%c-%s-%05d",
                                    goldenProperty.getDetachedHouse().houseId.charAt(0),
                                    goldenProperty.getDetachedHouse().houseId.charAt(1),
                                    LocalDate.now().getYear(), randomNumbers.
                                    nextInt(100000));
                        }

                        // δημιουργεί την κράτηση και εμφάνιζει κατάλληλο μήνυμα
                        try {
                            System.out.printf("%nReservation%n-----------%n%s%n%n"
                                    + ">>> Reservation was successfull%n", goldenTenant.
                                            addReservation(new Reservation(goldenProperty,
                                                    start, end, reservationCode, rentMoney)));
                        }
                        catch (IllegalArgumentException error) {
                            System.out.printf("%n>>> %s%n", error.getMessage());
                        }

                        // επέστρεψε στην επιλογή χρήστη
                        break;

                    case 2: // αλλαγή κράτησης

                        // εισήγαγε τον Α.Φ.Μ.
                        System.out.printf("%nChange Reservation%n-----------------%n"
                                + "Enter Tax Number: ");
                        taxNumber = enterTaxNumber();

                        // εκχώρησε στο goldenReservation και στον goldenTenant
                        // την ενδεικτική τιμή null
                        goldenReservation = null;
                        goldenTenant = null;

                        // προσδιορίζει αν ο ενοικιαστής υπάρχει ήδη στην
                        // πλατφόρμα από προηγούμενη καταχώρηση
                        for (Tenant t : tenants) {
                            if (t.getTaxNumber().equals(taxNumber)) {
                                goldenTenant = t;
                                break;
                            }
                        }

                        // ο ενοικιαστής δεν βρέθηκε ή δεν έχει κρατήσεις
                        if (goldenTenant == null || goldenTenant.
                                getReservationList().isEmpty()) {
                            System.out.printf("%n>>> There are no reservations "
                                    + "to change%n");
                            break; // επέστρεψε στην επιλογή χρήστη
                        }

                        // αναγκάζει τον χρήστη να εισάγει έγκυρο αριθμό κράτησης
                        // και εκχωρεί την θέση της κράτησης στο goldenReservation
                        while (true) {

                            // είσοδος χρήστη
                            System.out.printf("Enter Reservation Code: ");
                            reservationCode = enterReservationCode();

                            // αναζητά την κράτηση που έχει τον ίδιο αριθμό
                            // κράτησης με αυτόν που εισήγαγε ο χρήστης
                            for (Reservation r : goldenTenant.getReservationList()) {
                                if (r.getReservationCode().equals(reservationCode)) {
                                    goldenReservation = r;
                                    break;
                                }
                            }

                            // η κράτηση βρέθηκε
                            if (goldenReservation != null) {
                                break;
                            }
                            else { // δεν υπάρχουν κρατήσεις με αυτόν τον κωδικό
                                System.out.printf("%n>>> There are no reservations "
                                        + "with code: %s%n", reservationCode);
                            }
                        }

                        System.out.printf("Enter start date: ");
                        start = enterStartDate();

                        System.out.printf("Enter end date: ");
                        end = enterEndDate(start);

                        // αλλάζει την κράτηση και εμφανίζει κατάλληλο μήνυμα
                        try {
                            System.out.printf("%nReservation%n-----------%n%s%n%n"
                                    + ">>> Reservation change was successful%n",
                                    goldenTenant.changeReservation(reservationCode,
                                            start, end));
                        }
                        catch (IllegalArgumentException error) {
                            System.out.printf("%n>>> %s%n", error.getMessage());
                        }

                        // επέστρεψε στην επιλογή χρήστη
                        break;

                    case 3: // ακύρωση κράτησης

                        // εισήγαγε τον Α.Φ.Μ.
                        System.out.printf("%nCancel Reservation%n-----------------%n"
                                + "Enter Tax Number: ");
                        taxNumber = enterTaxNumber();

                        // εκχώρησε στο goldenReservation και στον goldenTenant
                        // την ενδεικτική τιμή null
                        goldenReservation = null;
                        goldenTenant = null;

                        // προσδιορίζει αν ο ενοικιαστής υπάρχει ήδη στην
                        // πλατφόρμα από προηγούμενη καταχώρηση
                        for (Tenant t : tenants) {
                            if (t.getTaxNumber().equals(taxNumber)) {
                                goldenTenant = t;
                                break;
                            }
                        }

                        // ο ενοικιαστής δεν βρέθηκε ή δεν έχει κρατήσεις
                        if (goldenTenant == null || goldenTenant.
                                getReservationList().isEmpty()) {
                            System.out.printf("%n>>> There are no reservations "
                                    + "to cancel%n");
                            break;
                        }

                        // αναγκάζει τον χρήστη να εισάγει έγκυρο αριθμό κράτησης
                        // και εκχωρεί την θέση της κράτησης στο goldenReservation
                        while (true) {

                            // είσοδος χρήστη
                            System.out.printf("Enter Reservation Code: ");
                            reservationCode = enterReservationCode();

                            // αναζητά την κράτηση που έχει τον ίδιο αριθμό
                            // κράτησης με αυτόν που εισήγαγε ο χρήστης
                            for (Reservation r : goldenTenant.getReservationList()) {
                                if (r.getReservationCode().equals(reservationCode)) {
                                    goldenReservation = r;
                                    break;
                                }
                            }

                            // η κράτηση βρέθηκε
                            if (goldenReservation != null) {
                                break;
                            }
                            else { // δεν υπάρχουν κρατήσεις με αυτόν τον κωδικό
                                System.out.printf("%n>>> There are no reservations "
                                        + "with code: %s%n", reservationCode);
                            }
                        }

                        // ακυρώνει την κράτηση και εμφανίζει κατάλληλο μήνυμα
                        try {
                            System.out.printf("%nReservation%n-----------%n%s%n%n"
                                    + ">>> Reservation cancellation was successful%n",
                                    goldenTenant.deleteReservation(reservationCode,
                                            goldenReservation.getStart(),
                                            goldenReservation.getEnd()));
                        }
                        catch (IllegalArgumentException error) {
                            System.out.printf("%n>>> %s%n", error.getMessage());
                        }

                        // επέστρεψε στην επιλογή χρήστη
                        break;

                    case 4: // εμφάνιση αναφορών

                        // εισήγαγε τον Α.Φ.Μ.
                        System.out.printf("%nEnter Tax Number: ");
                        taxNumber = enterTaxNumber();

                        // εκχώρησε στον goldenTenant την ενδεικτική τιμή null
                        goldenTenant = null;

                        // προσδιορίζει αν ο ενοικιαστής υπάρχει ήδη στην
                        // πλατφόρμα από προηγούμενη καταχώρηση
                        for (Tenant t : tenants) {
                            if (t.getTaxNumber().equals(taxNumber)) {
                                goldenTenant = t;
                                break;
                            }
                        }

                        // ο ενοικιαστής δεν βρέθηκε
                        if (goldenTenant == null) {
                            System.out.printf("%n>>> Tenant with Tax Number: %s "
                                    + "doesn't exist%n", taxNumber);
                            break;
                        }

                        // μενού επιλογών
                        System.out.printf("%nReport%n------%n1 - List of all "
                                + "reservations made%n2 - Total cost (including "
                                + "cancellation costs)%n%n? ");

                        // είσοδος χρήστη
                        s = input.nextLine();

                        // ελέγχει αν η είσοδος είναι έγκυρη
                        while (!s.equals("1") && !s.equals("2")) {
                            System.out.print("? ");
                            s = input.nextLine();
                        }

                        // μετατρέπει την είσοδο σε ακέραιο
                        service = Integer.parseInt(s);

                        // εμφάνισε τη λίστα κρατήσεων
                        if (service == 1) {
                            System.out.print(goldenTenant.showReservations());
                        }
                        // εμφάνισε το συνολικό κόστος των κρατήσεων
                        else if (service == 2) {
                            System.out.printf("%n-----------%nTotal Cost: $%.2f%n"
                                    + "-----------%n", goldenTenant.getTotalCost());
                        }

                        // επέστρεψε στην επιλογή χρήστη
                        break;
                }
            }
            else if (user == 2) { // ο χρήστης είναι ιδιοκτήτης

                // μενού επιλογών
                System.out.printf("%nChoice Menu%n-----------%n1 - Register a "
                        + "property%n2 - Show report%n%n? ");

                // είσοδος δεδομένων
                s = input.nextLine();

                // ελέγχει αν η είσοδος είναι έγκυρη
                while (!s.equals("1") && !s.equals("2")) {
                    System.out.print("? ");
                    s = input.nextLine();
                }

                // μετατρέπει την είσοδο σε ακέραιο
                service = Integer.parseInt(s);

                switch (service) {
                    case 1: // καταχώρηση ακινήτου

                        // εισήγαγε τον Α.Φ.Μ.
                        System.out.printf("%nRegister Property%n-----------------%n");
                        System.out.print("Enter Tax Number: ");
                        taxNumber = enterTaxNumber();

                        // εκχώρησε στον goldenOwner την ενδεικτική τιμή null
                        goldenOwner = null;

                        // προσδιορίζει αν ο ιδιοκτήτης υπάρχει ήδη στην
                        // πλατφόρμα από προηγούμενη καταχώρηση
                        for (Owner n : owners) {
                            if (n.getTaxNumber().equals(taxNumber)) {
                                System.out.printf("%n>>> Welcome %s %s!",
                                        n.getFirstName(), n.getLastName());
                                goldenOwner = n;
                                break;
                            }
                        }

                        // ο ιδιοκτήτης υπάρχει ήδη στην πλατφόρμα από
                        // προηγούμενη καταχώρηση
                        if (goldenOwner != null) {
                            System.out.printf("%n%nProperty Type%n-------------%n1) "
                                    + "House%n2) Apartment%n3) Detached House%n%n? ");

                            // επέλεξε είδος κατοικίας
                            s = input.nextLine();
                        }
                        // ο ιδιοκτήτης είναι ενοικιαστής
                        else if (isTenant(taxNumber, tenants)) {

                            // αναζητά τον ενοικιαστή στην λίστα ενοικιαστών
                            // και προτρέπει μόνο για διεύθυνση
                            for (Tenant t : tenants) {
                                if (t.getTaxNumber().equals(taxNumber)) {
                                    System.out.printf("%n>>> Welcome %s %s!%n>>>"
                                            + " Your full name and ID Number are"
                                            + " already registered.%n>>> Please "
                                            + "provide an address to become an "
                                            + "owner.%n%nEnter address: ",
                                            t.getFirstName(), t.getLastName());

                                    // εισαγωγή διεύθυνσης
                                    address = enterAddress();

                                    // δημιούργησε νέο ιδιοκτήτη με βάση τη
                                    // διεύθυνση και τα στοιχεία του ενοικιαστή
                                    goldenOwner = new Owner(taxNumber, t.
                                            getIdNumber(), t.getFirstName(),
                                            t.getLastName(), address);

                                    // πρόσθεσε τον ενοικιαστή στην λίστα ιδιοκτητών
                                    owners.add(goldenOwner);
                                    System.out.printf("%nProperty Type%n"
                                            + "-------------%n1) House%n2)"
                                            + " Apartment%n3) Detached "
                                            + "House%n%n? ");

                                    // επέλεξε είδος κατοικίας
                                    s = input.nextLine();
                                    break;
                                }
                            }
                        }
                        else { // δημιούργησε νεό ιδιοκτήτη

                            // προτροπή για όνομα
                            System.out.printf("%nNew Owner%n----------%n");
                            System.out.printf("Enter first name: ");
                            firstName = enterFirstName();

                            // προτροπή για επώνυμο
                            System.out.printf("Enter last name: ");
                            lastName = enterLastName();

                            System.out.printf("Enter ID Number: ");
                            idNumber = enterIdNumber();

                            System.out.printf("Enter address: ");
                            address = enterAddress();

                            // δημιούργησε νέο ιδιοκτήτη
                            goldenOwner = new Owner(taxNumber, idNumber,
                                    firstName, lastName, address);

                            // πρόσθεσε τον νέο ιδιοκτήτη στην λίστα ιδιοκτητών
                            owners.add(goldenOwner);
                            System.out.printf("%nProperty Type%n-------------%n1) "
                                    + "House%n2) Apartment%n3) Detached House%n%n? ");

                            // επέλεξε είδος κατοικίας
                            s = input.nextLine();
                        }

                        // ελέγχει αν η είσοδος είναι έγκυρη
                        while (!s.equals("1") && !s.equals("2") && !s.equals("3")) {
                            System.out.print("? ");
                            s = input.nextLine();
                        }

                        // μετατρέπει την είσοδο σε ακέραιο
                        service = Integer.parseInt(s);

                        System.out.printf("%nEnter view: ");
                        s = input.nextLine();

                        // αναγκάζει τον χρήστη να επιλέξει έγκυρη θέα
                        while (!s.toLowerCase().equals("street")
                                && !s.toLowerCase().equals("mountain")
                                && !s.toLowerCase().equals("sea")) {
                            System.out.printf("%n>>> Available landscapes are "
                                    + "\"Street\", \"Mountain\" and \"Sea\"%n"
                                    + "Enter view: ");

                            // είσοδος θέας
                            s = input.nextLine();
                        }

                        // εκχωρεί στη μεταβλητή view τη κατάλληλη θέα
                        // ανάλογα με την είσοδο του χρήστη
                        switch (s.toLowerCase()) {
                            case "street":
                                view = Landscape.STREET;
                                break;
                            case "mountain":
                                view = Landscape.MOUNTAIN;
                                break;
                            default:
                                view = Landscape.SEA;
                                break;
                        }

                        System.out.printf("Enter House ID: ");
                        houseId = enterHouseId();

                        System.out.printf("Enter municipality: ");
                        municipality = enterMunicipality(houseId);

                        // εισαγωγή όλων των κοινών χαρακτηριστικών κάθε κατοικίας
                        System.out.printf("Enter address: ");
                        address = enterAddress();

                        System.out.printf("Enter people: ");
                        people = enterPeople();

                        System.out.printf("Enter bedrooms: ");
                        bedrooms = enterBedrooms();

                        System.out.printf("Enter metro distance: ");
                        metroDistance = enterMetroDistance();

                        System.out.printf("Enter daily rent: ");
                        rentMoney = enterRentMoney();

                        System.out.printf("Internet access? ");
                        internetAccess = enterBoolean("Internet access");

                        System.out.printf("Television? ");
                        television = enterBoolean("Television");

                        System.out.printf("Kitchen? ");
                        kitchen = enterBoolean("Kitchen");

                        System.out.printf("Private parking? ");
                        privateParking = enterBoolean("Private parking");

                        // προσδιορίζει τον τύπο της ιδιοκτησίας
                        switch (service) {
                            case 1: // σπίτι

                                // δημιουργία κατοικίας
                                House goldenHouse = new House(taxNumber, houseId,
                                        municipality, address, view, people, bedrooms,
                                        metroDistance, rentMoney, internetAccess,
                                        television, kitchen, privateParking);

                                // πρόσθεσε την νέα καταχώρηση στην
                                // λίστα καταχωρήσεων κατοικιών
                                goldenProperty = new Registration(goldenHouse);
                                registrationList.add(goldenProperty);

                                // καταχώρηση κατοικίας
                                System.out.printf("%nHouse%n---------------------%n%s"
                                        + "%n---------------------%n", goldenOwner.
                                                addRegistration(goldenProperty));
                                break;

                            case 2: // διαμέρισμα
                                System.out.printf("Elevator? ");
                                elevator = enterBoolean("Elevator");

                                System.out.printf("Balcony? ");
                                balcony = enterBoolean("Balcony");

                                System.out.printf("Enter floor number: ");
                                floor = enterFloorNumber();

                                // δημιουργία διαμερίσματος
                                Apartment goldenApartment = new Apartment(taxNumber,
                                        houseId, municipality, address, view, people,
                                        bedrooms, floor, metroDistance, rentMoney,
                                        internetAccess, television, kitchen,
                                        privateParking, elevator, balcony);

                                // πρόσθεσε την νέα καταχώρηση στην
                                // λίστα καταχωρήσεων κατοικιών
                                goldenProperty = new Registration(goldenApartment);
                                registrationList.add(goldenProperty);

                                // καταχώρηση διαμερίσματος
                                System.out.printf("%nApartment%n---------------------%n"
                                        + "%s%n---------------------%n", goldenOwner.
                                                addRegistration(goldenProperty));
                                break;

                            case 3: // μονοκατοικία
                                System.out.printf("Barbeque? ");
                                barbeque = enterBoolean("Barbeque");

                                System.out.printf("Enter garden size: ");
                                garden = enterGarden();

                                System.out.printf("Enter pool size: ");
                                pool = enterPool();

                                // δημιουργία μονοκατοικίας
                                DetachedHouse goldenDetachedHouse
                                        = new DetachedHouse(taxNumber, houseId,
                                                municipality, address, view, people,
                                                bedrooms, metroDistance, rentMoney,
                                                garden, pool, internetAccess,
                                                television, kitchen, privateParking,
                                                barbeque);

                                // πρόσθεσε την νέα καταχώρηση στην
                                // λίστα καταχωρήσεων κατοικιών
                                goldenProperty = new Registration(goldenDetachedHouse);
                                registrationList.add(goldenProperty);

                                // καταχώρηση μονοκατοικίας
                                System.out.printf("%nDetached House%n------------"
                                        + "---------%n%s%n---------------------%n",
                                        goldenOwner.addRegistration(goldenProperty));
                                break;
                        }

                        // επέστρεψε στην επιλογή χρήστη
                        break;

                    case 2: // εμφάνιση αναφορών

                        // εισήγαγε τον Α.Φ.Μ.
                        System.out.printf("%nEnter Tax Number: ");
                        taxNumber = enterTaxNumber();

                        // εκχώρησε στον goldenOwner την ενδεικτική τιμή null
                        goldenOwner = null;

                        // προσδιορίζει αν ο ιδιοκτήτης υπάρχει ήδη στην
                        // πλατφόρμα από προηγούμενη καταχώρηση
                        for (Owner n : owners) {
                            if (n.getTaxNumber().equals(taxNumber)) {
                                goldenOwner = n;
                                break;
                            }
                        }

                        // ο ιδιοκτήτης δεν βρέθηκε
                        if (goldenOwner == null) {
                            System.out.printf("%n>>> Owner with Tax Number: %s "
                                    + "doesn't exist%n", taxNumber);
                            break;
                        }

                        // μενού επιλογών
                        System.out.printf("%nReport%n------%n1 - List of all "
                                + "registered properties%n2 - Rental period of "
                                + "a single property%n%n? ");

                        // είσοδος χρήστη
                        s = input.nextLine();

                        // ελέγχει αν η είσοδος είναι έγκυρη
                        while (!s.equals("1") && !s.equals("2")) {
                            System.out.print("? ");
                            s = input.nextLine();
                        }

                        // μετατρέπει την είσοδο σε ακέραιο
                        service = Integer.parseInt(s);

                        // εκχώρησε στο goldenProperty την ενδεικτική τιμή null
                        goldenProperty = null;

                        // εμφάνισε το σύνολο των ακινήτων που
                        // είναι διαθέσιμα προς ενοικίαση
                        if (service == 1) {
                            System.out.print(goldenOwner.showRegistrations());
                            break; // επέστρεψε στην επιλογή χρήστη
                        }
                        // δεν έχουν γίνει ακόμα καταχωρήσεις κατοικιών
                        else if (service == 2 && goldenOwner.
                                getRegistrationList().isEmpty()) {
                            System.out.printf("%n>>> There are no properties "
                                    + "registered by owner %s %s%n", goldenOwner.
                                            getFirstName(), goldenOwner.getLastName());
                            break; // επέστρεψε στην επιλογή χρήστη
                        }

                        // προσδιορίζει αν ο αριθμός ταυτοποίησης κατοικίας που
                        // εισάγει ο χρήστης υπάρχει σε μια από τις καταχωρήσεις
                        while (true) {

                            // είσοδος χρήστη
                            System.out.printf("%nEnter House ID: ");
                            houseId = enterHouseId();

                            // ελέγχει για τον αριθμό ταυτοποίησης κατοικίας
                            // στη λίστα καταχωρήσεων του ιδιοκτήτη
                            for (Registration r : goldenOwner.getRegistrationList()) {
                                if (r.getHouse() != null
                                        && r.getHouse().houseId.equals(houseId)) {
                                    goldenProperty = r;
                                    break;
                                }
                                else if (r.getApartment() != null
                                        && r.getApartment().houseId.equals(houseId)) {
                                    goldenProperty = r;
                                    break;
                                }
                                else if (r.getDetachedHouse() != null && r.
                                        getDetachedHouse().houseId.equals(houseId)) {
                                    goldenProperty = r;
                                    break;
                                }
                            }

                            // η ιδιοκτησία βρέθηκε
                            if (goldenProperty != null) {
                                break;
                            }
                            else { // η ιδιοκτησία με τον καθορισμένο αριθμό
                                // ταυτοποίησης δεν υπάρχει
                                System.out.printf("%n>>> Property with House ID:"
                                        + " %s doesn't exist", houseId);
                            }
                        }

                        // εμφάνισε το συνολικό χρονικό διάστημα ενοικίασης
                        System.out.printf("%n--------------%n%s%n--------------%n",
                                goldenOwner.showRegistration(houseId));

                        // αριθμός ημερών που μεσολάβησαν
                        long days;

                        // αν η ιδιοκτησία δεν είναι ενοικιασμένη
                        if (DAYS.between(goldenProperty.getStart(),
                                goldenProperty.getEnd()) == 0) {
                            days = 0;
                        }
                        else { // διαφορετικά
                            days = DAYS.between(goldenProperty.getStart(),
                                    goldenProperty.getEnd()) + 1;
                        }

                        // εμφάνισε τις ημέρες που μεσολάβησαν
                        System.out.printf("%n>>> Days: %d%n", days);

                        // επέστρεψε στην επιλογή χρήστη
                        break;
                }
            }
            else { // τερμάτισε το πρόγραμμα
                System.out.printf("%n>>> Program terminated%n");
                break;
            }

            // επιλογή χρήστη ή τερματισμός προγράμματος
            System.out.printf("%nUser Selection%n--------------%n1 - Tenant%n"
                    + "2 - Owner%n3 - End program%n%n? ");

            // είσοδος χρήστη
            s = input.nextLine();

            // ελέγχει αν η είσοδος είναι έγκυρη
            while (!s.equals("1") && !s.equals("2") && !s.equals("3")) {
                System.out.print("? ");
                s = input.nextLine();
            }

            // μετατρέπει την είσοδο σε ακέραιο
            user = Integer.parseInt(s);
        }
    }

    // επιστρέφει την ημερομηνία έναρξης ενοικίασης
    public static LocalDate enterStartDate() {
        LocalDate start;
        Scanner input = new Scanner(System.in);
        String message1 = String.format("%n>>> Reservations start from: %s%n",
                LocalDate.now().plusDays(1).format(DateTimeFormatter.
                        ofLocalizedDate(FormatStyle.SHORT)));

        // αναγκάζει τον χρήστη να εισάγει έγκυρη ημερομηνία έναρξης,
        // δηλαδή 1 μέρα μετά από την ημερομηνία/ημέρα του συστήματος
        while (true) {
            try {
                start = LocalDate.parse(input.nextLine(),
                        DateTimeFormatter.ofPattern("d/M/yy"));
                if (start.isAfter(LocalDate.now())) {
                    break;
                }
                else {
                    throw new java.time.DateTimeException(message1);
                }
            }
            catch (java.time.DateTimeException error) {
                System.out.printf("%sStart date: ", error.
                        getMessage().equals(message1) ? message1
                        : String.format("%n>>> Start date should "
                                + "follow the format: d/M/yy%n"));
            }
        }
        return start;
    }

    // επιστρέφει την ημερομηνία λήξης ενοικίασης
    public static LocalDate enterEndDate(LocalDate start) {
        LocalDate end;
        Scanner input = new Scanner(System.in);
        String message2 = String.format("%n>>> Reservations end period: "
                + "%s - %s%n", start.plusDays(1).format(DateTimeFormatter.
                        ofLocalizedDate(FormatStyle.SHORT)), start.plusDays(14).
                format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)));

        // αναγκάζει τον χρήστη να εισάγει έγκυρη ημερομηνία λήξης,
        // δηλαδή 1 μέρα μετά από την ημερομηνία έναρξης και χωρίς
        // να ξεπερνά τις 15 συνεχόμενες μέρες ενοικίασης
        while (true) {
            try {
                end = LocalDate.parse(input.nextLine(),
                        DateTimeFormatter.ofPattern("d/M/yy"));
                if (end.isAfter(start) && end.isBefore(start.
                        plusDays(15))) {
                    break;
                }
                else {
                    throw new java.time.DateTimeException(message2);
                }
            }
            catch (java.time.DateTimeException error) {
                System.out.printf("%sEnd date: ", error.
                        getMessage().equals(message2) ? message2
                        : String.format("%n>>> End date should "
                                + "follow the format: d/M/yy%n"));
            }
        }
        return end;
    }

    // επιστρέφει τον αριθμό των ανθρώπων που μπορούν να φιλοξενηθούν
    public static int enterPeople() {
        Scanner input = new Scanner(System.in);
        String s = input.nextLine();

        // αναγκάζει τον χρήστη να εισάγει έναν αριθμό από το 1 εώς το 8
        while (!s.equals("1") && !s.equals("2") && !s.equals("3")
                && !s.equals("4") && !s.equals("5") && !s.equals("6")
                && !s.equals("7") && !s.equals("8")) {
            System.out.printf("%n>>> Τhe number of people should "
                    + "be between 1 and 8%nPeople: ");
            s = input.next();
        }
        return Integer.parseInt(s);
    }

    // επιστρέφει τον αριθμό των υπνοδωματιών που μπορούν
    // να υπάρχουν σε μια κατοικία
    public static int enterBedrooms() {
        Scanner input = new Scanner(System.in);
        String s = input.nextLine();

        // αναγκάζει τον χρήστη να εισάγει έναν αριθμό από το 0 εώς το 12
        while (!s.equals("0") && !s.equals("1") && !s.equals("2")
                && !s.equals("3") && !s.equals("4") && !s.equals("5")
                && !s.equals("6") && !s.equals("7") && !s.equals("8")
                && !s.equals("9") && !s.equals("10") && !s.equals("11")
                && !s.equals("12")) {
            System.out.printf("%n>>> Τhe number of bedrooms should "
                    + "be between 0 and 12%nEnter bedrooms: ");
            s = input.next();
        }
        return Integer.parseInt(s);
    }

    // επιστρέφει τον αριθμό του ορόφου του διαμερίσματος
    public static int enterFloorNumber() {
        Scanner input = new Scanner(System.in);
        String s = input.nextLine();

        // αναγκάζει τον χρήστη να εισάγει έναν αριθμό από το 0 εώς το 10
        while (!s.equals("0") && !s.equals("1") && !s.equals("2")
                && !s.equals("3") && !s.equals("4") && !s.equals("5")
                && !s.equals("6") && !s.equals("7") && !s.equals("8")
                && !s.equals("9") && !s.equals("10")) {
            System.out.printf("%n>>> Floor number should be between 0 and 10%n"
                    + "Enter floor number: ");
            s = input.nextLine();
        }
        return Integer.parseInt(s);
    }

    // επιστρέφει την απόσταση από τον κοντινότερο σταθμό μετρό
    public static double enterMetroDistance() {
        Scanner input = new Scanner(System.in);
        String s = input.nextLine();
        double metroDistance;

        // αναγκάζει τον χρήστη να εισάγει έναν
        // πραγματικό αριθμό από το 0 εώς το 2000
        while (true) {
            try {
                metroDistance = Double.parseDouble(s);
                if (metroDistance < 0.0 || metroDistance > 2000.0) {
                    System.out.printf("%n>>> %s%nEnter metro distance: ",
                            metroDistance > 2000.0 ? "Distance from nearest "
                                    + "metro station should not exceed 2 km"
                                    : "Distance cannot be negative");
                    s = input.nextLine();
                }
                else {
                    break;
                }
            }
            catch (NumberFormatException error) {
                System.out.printf("%n>>> Invalid input, please try again%n"
                        + "Enter metro distance: ");
                s = input.nextLine();
            }
        }
        return metroDistance;
    }

    // προσδιορίζει αν υπάρχει ή όχι το καθορισμένο χαρακτηριστικό
    public static boolean enterBoolean(String attribute) {
        Scanner input = new Scanner(System.in);
        String s = input.nextLine();

        // αναγκάζει τον χρήστη να επιλέξει μεταξύ δύο επιλογών: "ναι" ή "όχι"
        while (!s.toLowerCase().equals("yes") && !s.toLowerCase().equals("no")) {
            System.out.printf("%n>>> Answer can be either \"yes\" or \"no\""
                    + "%n%s? ", attribute);
            s = input.nextLine();
        }
        return s.equals("yes");
    }

    // επιστρέφει το μέγεθος του κήπου σε τετραγωνικά μέτρα
    public static double enterGarden() {
        Scanner input = new Scanner(System.in);
        String s = input.nextLine();
        double garden;

        // αναγκάζει τον χρήστη να εισάγει έναν
        // πραγματικό αριθμό από το 0 εώς το 200
        while (true) {
            try {
                garden = Double.parseDouble(s);
                if (garden < 0.0 || garden > 200.0) {
                    System.out.printf("%n>>> %s%nEnter garden size: ", garden > 200.0
                            ? "Garden should not be larger than 200 square meters"
                            : "Invalid garden size, please try again");
                    s = input.nextLine();
                }
                else {
                    break;
                }
            }
            catch (NumberFormatException error) {
                System.out.printf("%n>>> Invalid input, please try again%n"
                        + "Enter garden size: ");
                s = input.nextLine();
            }
        }
        return garden;
    }

    // επιστρέφει το μέγεθος της πισίνας σε τετραγωνικά μέτρα
    public static double enterPool() {
        Scanner input = new Scanner(System.in);
        String s = input.nextLine();
        double pool;

        // αναγκάζει τον χρήστη να εισάγει έναν
        // πραγματικό αριθμό από το 0 εώς το 100
        while (true) {
            try {
                pool = Double.parseDouble(s);
                if (pool < 0.0 || pool > 100.0) {
                    System.out.printf("%n>>> %s%nEnter pool size: ", pool > 100.0
                            ? "Pool should not be larger than 100 square meters"
                            : "Invalid pool size, please try again");
                    s = input.nextLine();
                }
                else {
                    break;
                }
            }
            catch (NumberFormatException error) {
                System.out.printf("%n>>> Invalid input, please try again%n"
                        + "Enter pool size: ");
                s = input.nextLine();
            }
        }
        return pool;
    }

    // επιστρέφει τον Α.Φ.Μ.
    public static String enterTaxNumber() {
        Scanner input = new Scanner(System.in);
        String taxNumber = input.nextLine();

        // ο βρόχος χρησιμοποιεί έναν αλγόριθμο επαλήθευσης του Α.Φ.Μ.,
        // επομένως το Α.Φ.Μ. δεν μπορεί να είναι απλά ένας τυχαίος
        // εννιαψήφιος αριθμός
        while (true) {
            if (taxNumber.length() == 9) {
                try {
                    Integer.parseInt(taxNumber);
                }
                catch (NumberFormatException error) {
                    System.out.printf("%n>>> Tax Number must consist only of "
                            + "digits%nEnter Tax Number: ");
                    taxNumber = input.nextLine();
                    continue;
                }

                int sum = 0;
                int product;
                int lastDigit = getNumericValue(taxNumber.charAt(8));

                for (int i = 0; i < 8; i++) {
                    product = getNumericValue(taxNumber.charAt(i));
                    product *= pow(2, 8 - i);
                    sum += product;
                }

                if ((sum % 11) % 10 == lastDigit && !taxNumber.
                        equals("000000000")) {
                    break;
                }
                else {
                    System.out.printf("%n>>> Invalid Tax Number%nTax Number: ");
                    taxNumber = input.nextLine();
                }
            }
            else {
                System.out.printf("%n>>> Tax Number must be exactly 9 digits "
                        + "long%nTax Number: ");
                taxNumber = input.nextLine();
            }
        }
        return taxNumber;
    }

    // επιστρέφει τον αριθμό ταυτοποίησης κατοικίας
    public static String enterHouseId() {
        Scanner input = new Scanner(System.in);
        String houseId = input.nextLine();

        // αναγκάζει τον χρήστη να εισάγει έγκυρο άριθμο ταυτοποίησης κατοικίας
        // και εμφανίζει κατάλληλο μήνυμα για κάθε λανθασμένη είσοδο
        while (true) {
            if (houseId.length() != 7) {
                System.out.printf("%n>>> House ID must be exactly 7 characters "
                        + "long%nEnter House ID: ");
                houseId = input.nextLine();
            }
            else if (!isLetter(houseId.charAt(0))
                    || !isLetter(houseId.charAt(1))) {
                System.out.printf("%n>>> Invalid House ID, first 2 characters "
                        + "should be letters%nEnter House ID: ");
                houseId = input.nextLine();
            }
            else if (houseId.charAt(2) != '-') {
                System.out.printf("%n>>> Invalid House ID, letters and digits "
                        + "should be separated by a hyphen%nEnter House ID: ");
                houseId = input.nextLine();
            }
            else if (!isDigit(houseId.charAt(3)) || !isDigit(houseId.charAt(4))
                    || !isDigit(houseId.charAt(5)) || !isDigit(houseId.charAt(6))) {
                System.out.printf("%n>>> Invalid House ID, last 4 characters "
                        + "should be digits%nEnter House ID: ");
                houseId = input.nextLine();
            }
            else {
                houseId = String.format("%c%c%s", toUpperCase(houseId.charAt(0)),
                        toUpperCase(houseId.charAt(1)), houseId.substring(2));
                break;
            }
        }
        return houseId;
    }

    // επιστρέφει το κόστος ενοικίασης
    public static double enterRentMoney() {
        double rentMoney;
        Scanner input = new Scanner(System.in);
        String rent = input.nextLine();

        // αναγκάζει τον χρήστη να εισάγει έναν
        // πραγματικό αριθμό από το 0 εώς το 200
        while (true) {
            try {
                rentMoney = Double.parseDouble(rent);
                if (rentMoney < 0.0 || rentMoney > 200.0) {
                    System.out.printf("%n>>> %s%nEnter daily rent: ",
                            rentMoney > 200.0 ? "The daily rental cost of the "
                                    + "property should not exceed $200" : "The "
                                    + "daily rental cost cannot be negative");
                    rent = input.nextLine();
                }
                else {
                    break;
                }
            }
            catch (NumberFormatException error) {
                System.out.printf("%n>>> Invalid input, please try again"
                        + "%nEnter daily rent: ");
                rent = input.nextLine();
            }
        }
        return rentMoney;
    }

    // χρησιμοποιείται για την πληρωμή ενοικίου και επιστρέφει το ποσό πληρωμής
    public static double payRent() {
        double rentMoney;
        Scanner input = new Scanner(System.in);
        String rent = input.nextLine();

        // αναγκάζει τον χρήστη να εισάγει έναν
        // πραγματικό αριθμό από το 0 εώς το 200
        while (true) {
            try {
                rentMoney = Double.parseDouble(rent);
                if (rentMoney < 0.0 || rentMoney > 200.0) {
                    System.out.printf("%n>>> %s%nPay initial rent: ",
                            rentMoney > 200.0 ? "No property costs more than "
                                    + "$200" : "Rent cannot be negative");
                    rent = input.nextLine();
                }
                else {
                    break;
                }
            }
            catch (NumberFormatException error) {
                System.out.printf("%n>>> Invalid input, please try again%nPay "
                        + "initial rent: ");
                rent = input.nextLine();
            }
        }
        return rentMoney;
    }

    // επιστρέφει τον αριθμό ταυτότητας
    public static String enterIdNumber() {
        Scanner input = new Scanner(System.in);
        String idNumber = input.nextLine();

        // αναγκάζει τον χρήστη να εισάγει έγκυρο άριθμο ταυτότητας
        // και εμφανίζει κατάλληλο μήνυμα για κάθε λανθασμένη είσοδο
        while (true) {
            if (idNumber.length() != 9) {
                System.out.printf("%n>>> ID Number must be exactly 9 "
                        + "characters long%nEnter ID Number: ");
                idNumber = input.nextLine();
            }
            else if (!isLetter(idNumber.charAt(0)) || !isLetter(idNumber.charAt(1))) {
                System.out.printf("%n>>> Invalid ID Number, first 2 characters "
                        + "should be letters%nEnter ID Number: ");
                idNumber = input.nextLine();
            }
            else if (idNumber.charAt(2) != ' ') {
                System.out.printf("%n>>> Invalid ID Number, letters and digits "
                        + "should be separated by a space%nEnter ID Number: ");
                idNumber = input.nextLine();
            }
            else if (!isDigit(idNumber.charAt(3)) || !isDigit(idNumber.charAt(4))
                    || !isDigit(idNumber.charAt(5)) || !isDigit(idNumber.charAt(6))
                    || !isDigit(idNumber.charAt(7)) || !isDigit(idNumber.charAt(8))) {
                System.out.printf("%n>>> Invalid ID Number, last 6 characters "
                        + "should be digits%nEnter ID Number: ");
                idNumber = input.nextLine();
            }
            else {
                idNumber = String.format("%c%c%s", toUpperCase(idNumber.
                        charAt(0)), toUpperCase(idNumber.charAt(1)),
                        idNumber.substring(2));
                break;
            }
        }
        return idNumber;
    }

    // επιστρέφει έγκυρη διεύθυνση
    public static String enterAddress() {
        Scanner input = new Scanner(System.in);
        String address = input.nextLine();

        // αναγκάζει τον χρήστη να εισάγει έγκυρη διεύθυνση και
        // εμφανίζει κατάλληλο μήνυμα για κάθε λανθασμένη είσοδο
        while (true) {
            // αν η διεύθυνση είναι έγκυρη, μετέτρεψε το πρώτο
            // γράμμα σε κεφαλαίο και βγες από τον βρόχο
            if (address.length() > 4 && address.length() < 18 && isalnum(address)) {
                address = String.format("%c%s",
                        address.toUpperCase().charAt(0),
                        address.substring(1));
                break;
            }
            // η διεύθυνση είναι μικρότερη από 5 χαρακτήρες
            else if (address.length() < 5 && isalnum(address)) {
                System.out.printf("%n>>> Address should "
                        + "be at least 5 characters long%n"
                        + "Enter address: ");
                address = input.nextLine();
            }
            // η διεύθυνση είναι μεγαλύτερη από 17 χαρακτήρες
            else if (address.length() > 17 && isalnum(address)) {
                System.out.printf("%n>>> Address should "
                        + "be no longer than 17 characters long%n"
                        + "Enter address: ");
                address = input.nextLine();
            }
            else { // η διεύθυνση περιέχει χαρακτήρες εκτός γραμμάτων και αριθμών
                System.out.printf("%n>>> Invalid input, please"
                        + " try again%nEnter address: ");
                address = input.nextLine();
            }
        }
        return address;
    }

    // επιστρέφει έγκυρο όνομα δήμου
    public static String enterMunicipality(String houseId) {
        Scanner input = new Scanner(System.in);
        String municipality = input.nextLine();

        // αν πρόκειται για καταχώρηση κατοικίας
        if (houseId != null) {
            // αναγκάζει τον χρήστη να εισάγει έγκυρο όνομα δήμου και
            // εμφανίζει κατάλληλο μήνυμα για κάθε λανθασμένη είσοδο
            while (true) {
                // αν το όνομα του δήμου είναι έγκυρο, μετέτρεψε το πρώτο
                // γράμμα σε κεφαλαίο και βγες από τον βρόχο
                if (municipality.length() > 4 && municipality.length() < 13
                        && isalpha(municipality) && municipality.
                        toLowerCase().charAt(0) == houseId.toLowerCase().
                        charAt(0) && municipality.toLowerCase().
                        charAt(1) == houseId.toLowerCase().charAt(1)) {
                    municipality = String.format("%c%s",
                            municipality.toUpperCase().charAt(0),
                            municipality.substring(1));
                    break;
                }
                // το όνομα του δήμου είναι μικρότερο από 5 χαρακτήρες
                else if (municipality.length() < 5 && isalpha(municipality)) {
                    System.out.printf("%n>>> Municipality should "
                            + "be at least 5 characters long%n"
                            + "Enter municipality: ");
                    municipality = input.nextLine();
                }
                // το όνομα του δήμου είναι μεγαλύτερο από 12 χαρακτήρες
                else if (municipality.length() > 12 && isalpha(municipality)) {
                    System.out.printf("%n>>> Municipality should "
                            + "be no longer than 12 characters long%n"
                            + "Enter municipality: ");
                    municipality = input.nextLine();
                }
                // το όνομα του δήμου περιέχει χαρακτήρες εκτός γραμμάτων
                else if (!isalpha(municipality)) {
                    System.out.printf("%n>>> Invalid input, please"
                            + " try again%nEnter municipality: ");
                    municipality = input.nextLine();
                }
                else { // τα πρώτα δύο γράμματα του δήμου διαφέρουν
                    // από αυτά του αριθμού ταυτοποίησης κατοικίας
                    System.out.printf("%n>>> The first two characters"
                            + " of the municipality should match the "
                            + "first 2 characters of the House ID%n"
                            + "Enter municipality: ");
                    municipality = input.nextLine();
                }
            }
        }
        else { // αν πρόκειται για αναζήτηση
            while (true) {
                // αν το όνομα του δήμου είναι έγκυρο, μετέτρεψε το πρώτο
                // γράμμα σε κεφαλαίο και βγες από τον βρόχο
                if (municipality.length() > 4 && municipality.length() < 13
                        && isalpha(municipality)) {
                    municipality = String.format("%c%s",
                            municipality.toUpperCase().charAt(0),
                            municipality.substring(1));
                    break;
                }
                // το όνομα του δήμου είναι μικρότερο από 5 χαρακτήρες
                else if (municipality.length() < 5 && isalpha(municipality)) {
                    System.out.printf("%n>>> Municipality should "
                            + "be at least 5 characters long%n"
                            + "Enter municipality: ");
                    municipality = input.nextLine();
                }
                // το όνομα του δήμου είναι μεγαλύτερο από 12 χαρακτήρες
                else if (municipality.length() > 12 && isalpha(municipality)) {
                    System.out.printf("%n>>> Municipality should "
                            + "be no longer than 12 characters long%n"
                            + "Enter municipality: ");
                    municipality = input.nextLine();
                }
                else { // το όνομα του δήμου περιέχει χαρακτήρες εκτός γραμμάτων
                    System.out.printf("%n>>> Invalid input, please"
                            + " try again%nEnter municipality: ");
                    municipality = input.nextLine();
                }
            }
        }
        return municipality;
    }

    // επιστρέφει έγκυρο όνομα
    public static String enterFirstName() {
        Scanner input = new Scanner(System.in);
        String first = input.nextLine();

        // αναγκάζει τον χρήστη να εισάγει έγκυρο όνομα και
        // εμφανίζει κατάλληλο μήνυμα για κάθε λανθασμένη είσοδο
        while (true) {
            // αν το όνομα είναι έγκυρο, μετέτρεψε το πρώτο
            // γράμμα σε κεφαλαίο και βγες από τον βρόχο
            if (first.length() > 3 && first.length() < 11 && isalpha(first)) {
                first = String.format("%c%s",
                        first.toUpperCase().charAt(0),
                        first.substring(1));
                break;
            }
            // το όνομα είναι μικρότερο από 4 χαρακτήρες
            else if (first.length() < 4 && isalpha(first)) {
                System.out.printf("%n>>> First name should "
                        + "be at least 4 characters long%n"
                        + "Enter first name: ");
                first = input.nextLine();
            }
            // το όνομα είναι μεγαλύτερο από 10 χαρακτήρες
            else if (first.length() > 10 && isalpha(first)) {
                System.out.printf("%n>>> First name should "
                        + "be no longer than 10 characters long%n"
                        + "Enter first name: ");
                first = input.nextLine();
            }
            else { // το όνομα περιέχει χαρακτήρες εκτός γραμμάτων
                System.out.printf("%n>>> Invalid input, please"
                        + " try again%nEnter first name: ");
                first = input.nextLine();
            }
        }
        return first;
    }

    // επιστρέφει έγκυρο επώνυμο
    public static String enterLastName() {
        Scanner input = new Scanner(System.in);
        String last = input.nextLine();

        // αναγκάζει τον χρήστη να εισάγει έγκυρο επώνυμο και
        // εμφανίζει κατάλληλο μήνυμα για κάθε λανθασμένη είσοδο
        while (true) {
            // αν το επώνυμο είναι έγκυρο, μετέτρεψε το πρώτο
            // γράμμα σε κεφαλαίο και βγες από τον βρόχο
            if (last.length() > 3 && last.length() < 21 && isalpha(last)) {
                last = String.format("%c%s",
                        last.toUpperCase().charAt(0),
                        last.substring(1));
                break;
            }
            // το επώνυμο είναι μικρότερο από 4 χαρακτήρες
            else if (last.length() < 4 && isalpha(last)) {
                System.out.printf("%n>>> Last name should "
                        + "be at least 4 characters long%n"
                        + "Enter last name: ");
                last = input.nextLine();
            }
            // το επώνυμο είναι μεγαλύτερο από 20 χαρακτήρες
            else if (last.length() > 20 && isalpha(last)) {
                System.out.printf("%n>>> Last name should "
                        + "be no longer than 20 characters long%n"
                        + "Enter last name: ");
                last = input.nextLine();
            }
            else { // το επώνυμο περιέχει χαρακτήρες εκτός γραμμάτων
                System.out.printf("%n>>> Invalid input, please"
                        + " try again%nEnter last name: ");
                last = input.nextLine();
            }
        }
        return last;
    }

    public static boolean isalnum(String s) {
        if (s == null) {
            return false;
        }

        for (int i = 0; i < s.length(); i++) {
            if (isLetterOrDigit(s.charAt(i)) == false && s.charAt(i) != ' ') {
                return false;
            }
        }
        return true;
    }

    public static boolean isalpha(String s) {
        if (s == null) {
            return false;
        }

        for (int i = 0; i < s.length(); i++) {
            if (isLetter(s.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

    // επιστρέφει ένα έγκυρο email
    public static String enterEmail() {
        Scanner input = new Scanner(System.in);
        String email = input.nextLine();

        // αναγκάζει τον χρήστη να εισάγει ένα έγκυρο email και
        // εμφανίζει κατάλληλο μήνυμα για κάθε λανθασμένη είσοδο
        while (true) {
            if (email.length() == 14) {
                if (email.charAt(5) != '@') {
                    System.out.printf("%n>>> Invalid email address, the "
                            + "username should be followed by an at (@) "
                            + "symbol%nEnter email: ");
                    email = input.nextLine();
                }
                if (email.charAt(10) != '.') {
                    System.out.printf("%n>>> Invalid email address, the domain "
                            + "name should end with an extension such as \".zzz"
                            + "\"%nEnter email: ");
                    email = input.nextLine();
                }
                else {
                    break;
                }
            }
            else {
                System.out.printf("%n>>> Email address must be exactly 14 "
                        + "characters long%nEnter email: ");
                email = input.nextLine();
            }
        }
        return email;
    }

    // επιστρέφει τον κωδικό κράτησης
    public static String enterReservationCode() {
        Scanner input = new Scanner(System.in);
        String reservationCode = input.nextLine();

        // αναγκάζει τον χρήστη να εισάγει έγκυρο κωδικό κράτησης
        // και εμφανίζει κατάλληλο μήνυμα για κάθε λανθασμένη είσοδο
        while (true) {
            if (reservationCode.length() != 13) {
                System.out.printf("%n>>> Reservation Code must be "
                        + "exactly 13 characters long%nEnter Reservation "
                        + "Code: ");
                reservationCode = input.nextLine();
            }
            else if (!isLetter(reservationCode.charAt(0))
                    || !isLetter(reservationCode.charAt(1))) {
                System.out.printf("%n>>> Invalid Reservation Code, first 2 "
                        + "characters should be letters%nEnter Reservation "
                        + "Code: ");
                reservationCode = input.nextLine();
            }
            else if (reservationCode.charAt(2) != '-') {
                System.out.printf("%n>>> Invalid Reservation Code, first 2 "
                        + "letters should be followed by a hyphen%nEnter "
                        + "Reservation Code: ");
                reservationCode = input.nextLine();
            }
            else if (!isDigit(reservationCode.charAt(3))
                    || !isDigit(reservationCode.charAt(4))
                    || !isDigit(reservationCode.charAt(5))
                    || !isDigit(reservationCode.charAt(6))) {
                System.out.printf("%n>>> Invalid Reservation Code, middle 4 "
                        + "characters should be digits%nEnter Reservation "
                        + "Code: ");
                reservationCode = input.nextLine();
            }
            else if (reservationCode.charAt(7) != '-') {
                System.out.printf("%n>>> Invalid Reservation Code, middle 4 "
                        + "digits should be followed by a hyphen%nEnter "
                        + "Reservation Code: ");
                reservationCode = input.nextLine();
            }
            else if (!isDigit(reservationCode.charAt(8))
                    || !isDigit(reservationCode.charAt(9))
                    || !isDigit(reservationCode.charAt(10))
                    || !isDigit(reservationCode.charAt(11))
                    || !isDigit(reservationCode.charAt(12))) {
                System.out.printf("%n>>> Invalid Reservation Code, last 5 "
                        + "characters should be digits%nEnter Reservation "
                        + "Code: ");
                reservationCode = input.nextLine();
            }
            else {
                reservationCode = String.format("%c%c%s",
                        toUpperCase(reservationCode.charAt(0)),
                        toUpperCase(reservationCode.charAt(1)),
                        reservationCode.substring(2));
                break;
            }
        }
        return reservationCode;
    }

    // προσδιορίζει εάν ή όχι ο ενοικιαστής είναι και ιδιοκτήτης
    public static boolean tenantOwnsProperties(String taxNumber,
            ArrayList<Registration> availableHouses) {
        int ownedProperties = 0;
        for (Registration r : availableHouses) {
            if (r.getHouse() != null
                    && r.getHouse().taxNumber.equals(taxNumber)) {
                ownedProperties++;
            }
            else if (r.getApartment() != null
                    && r.getApartment().taxNumber.equals(taxNumber)) {
                ownedProperties++;
            }
            else if (r.getDetachedHouse() != null
                    && r.getDetachedHouse().taxNumber.equals(taxNumber)) {
                ownedProperties++;
            }
        }
        return ownedProperties == availableHouses.size();
    }

    // προσδιορίζει αν το καθορισμένο όνομα περιέχει μόνο γράμματα
    public static boolean isAlpha(String name) {
        char[] chars = name.toCharArray();

        for (char c : chars) {
            if (!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }

    // προσδιορίζει αν ο ιδιοκτήτης είναι και ενοικιαστής
    public static boolean isTenant(String taxNumber, ArrayList<Tenant> tenants) {
        for (Tenant t : tenants) {
            if (t.getTaxNumber().equals(taxNumber)) {
                return true;
            }
        }
        return false;
    }

    // προσδιορίζει αν ο ενοικιαστής είναι και ιδιοκτήτης
    public static boolean isOwner(String taxNumber, ArrayList<Owner> owners) {
        for (Owner n : owners) {
            if (n.getTaxNumber().equals(taxNumber)) {
                return true;
            }
        }
        return false;
    }
}
