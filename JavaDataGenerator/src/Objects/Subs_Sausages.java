package Objects;

import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Behruz Mansurov
 */
public class Subs_Sausages {
    private static final Random random = new Random();
    private static final String TEMPLATES = "INSERT INTO subs_sausages(id_subscription, id_sausage) VALUES(%s, %s);";

    public static String generate() {
        return String.format(TEMPLATES, 1 + random.nextInt(3), 1 + random.nextInt(30));
    }
}
