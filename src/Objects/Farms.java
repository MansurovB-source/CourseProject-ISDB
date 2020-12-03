package Objects;

import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Behruz Mansurov
 */
public class Farms {
    private static final Random random = new Random();
    private static final String TEMPLATES = "INSERT INTO farms(id_factory, id_location, id_animal) VALUES(%s, %s, %s);";

    public static String generate() {
        return String.format(TEMPLATES, 1 + random.nextInt(9), 1 + random.nextInt(1), 1 + random.nextInt(30));
    }
}
