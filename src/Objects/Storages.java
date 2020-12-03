package Objects;

import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Behruz Mansurov
 */
public class Storages {
    private static final Random random = new Random();
    private static final String TEMPLATES = "INSERT INTO storages(id_factory, id_sausage, sausages_weight) VALUES(%s, %s, %s);";

    public static String generate() {
        return String.format(TEMPLATES, 1 + random.nextInt(9), 1 + random.nextInt(30), 1 + 1000 + random.nextInt(9000));
    }
}
