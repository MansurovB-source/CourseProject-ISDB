package Objects;

import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Behruz Mansurov
 */
public class Return_Provider {
    private static final Random random = new Random();
    private static final String TEMPLATES = "INSERT INTO return_provider(_from, id_sausage, sausages_weight) VALUES(%s, %s, %s);";

    public static String generate() {
        return String.format(TEMPLATES, 1 + random.nextInt(299), 1 + random.nextInt(30), 1 + random.nextInt(999));
    }
}
