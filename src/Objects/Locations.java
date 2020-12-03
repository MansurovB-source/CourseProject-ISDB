package Objects;

import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Behruz Mansurov
 */
public class Locations {
    private static final Random random = new Random();
    private static final String COUNTRY = "RUSSIA";
    private static final String[] CITY =
            {
                    "MOSCOW",
                    "SAINT-PETERSBURG"
            };
    private static final String TEMPLATES = "INSERT INTO locations(country, city) VALUES('%s', '%s');";

    public static String generate() {
        return String.format(TEMPLATES, COUNTRY, CITY[random.nextInt(1)]);
    }
}
