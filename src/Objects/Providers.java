package Objects;

import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Behruz Mansurov
 */
public class Providers {
    private static final Random random = new Random();
    private static final float salary = 500000;
    private static final String TEMPLATES = "INSERT INTO providers(id_human, salary, id_factory, id_delivery_place) VALUES(%s, %s, %s, %s);";

    public static String generate() {
        return String.format(TEMPLATES, 1 + random.nextInt(9999), salary + random.nextInt(1500000), 1 + random.nextInt(9), 1 + random.nextInt(24));
    }
}
