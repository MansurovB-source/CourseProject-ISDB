package Objects;

import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Behruz Mansurov
 */
public class Cars {
    private static final Random random = new Random();
    private static final String TEMPLATES = "INSERT INTO cars(capacity, id_factory) VALUES(%s, %s);";

    public static String generate() {
        return String.format(TEMPLATES, 1000 + random.nextInt(9000), 1 + random.nextInt(9));
    }
}
