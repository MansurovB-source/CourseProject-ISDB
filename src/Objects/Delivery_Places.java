package Objects;

import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Behruz Mansurov
 */
public class Delivery_Places {
    private static final Random random = new Random();
    private  static String generateRandomAddress() {
        return STREETS[random.nextInt(STREETS.length)];
    }
    private static String generate() {
        return String.format(TEMPLATES, 1 + random.nextInt(1), generateRandomAddress());
    }

    private static final String TEMPLATES = "INSERT INTO delivery_places(id_location, address) VALUES(%s, '%s');";
    private static final String[] STREETS =
            {
                    "State Street",
                    "Broadway",
                    "Pearl Street",
                    "Hudson Avenue",
                    "Maiden Lane and Pine Street",
                    "Clinton Avenue",
                    "Madison Avenue",
                    "Washington Avenue",
                    "Lark Street",
                    "Knox Street/Northern Boulevard/Henry Johnson Boulevard",
                    "Central Avenue",
                    "Delaware Avenue",
                    "New Scotland Avenue",
                    "Other historic streets",
                    "Melrose Avenue",
                    "Manning Boulevard",
                    "Southern Boulevard",
                    "Numbered streets/avenues",
                    "Limited-access highways",
                    "New York State Thruway",
                    "Interstate 90",
                    "Interstate 787",
                    "Crosstown Arterial",
                    "Northway/Fuller Road Alternate",
                    "South Mall Expressway"
            };
}
