package Objects;

import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Behruz Mansurov
 */
public class Clients {
    private static int i = 1;
    private static final Random random = new Random();
    private static final float salary = 500000;
    private static final String TEMPLATES = "INSERT INTO clients(id_human, id_delivery_place, id_subscription) VALUES(%s, %s, %s);";

    public static String generate() {
        return String.format(TEMPLATES, 1 + random.nextInt(9999), (i++) % 300, 1 + random.nextInt(3));
    }
}
