package Objects;

import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Behruz Mansurov
 */
public class Storages {
    private static int i = 0;
    private static final Random random = new Random();
    private static final String TEMPLATES = "INSERT INTO storages(id_factory, id_sausage, sausages_weight) VALUES(%s, %s, %s);";

    public static String generate() {
        String s = String.format(TEMPLATES, ( 1 + (i % 9)), (1 + (i % 30)), 100000 + random.nextInt(200000));
        i++;
        return s;
    }
}
