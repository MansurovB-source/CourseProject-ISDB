package Objects;

import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Behruz Mansurov
 */
public class Orders {
    private static final Random random = new Random();
    private static final String TEMPLATES = "INSERT INTO orders(i_from, id_sausages, sausages_weight, special) VALUES(%s, %s, %s, '%s');";

    public static String generate() {
        return String.format(TEMPLATES, 1 + random.nextInt(9999), 1 + random.nextInt(30), 1 + random.nextInt(99), "FALSE");
    }
}
