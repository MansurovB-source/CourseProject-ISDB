package Objects;

import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Behruz Mansurov
 */
public class Factory_sausages {
    private static final Random random = new Random();
    private static final String TEMPLATES = "INSERT INTO factory_sausages(id_factory, id_sausage) VALUES(%s, %s);";

    public static String generate() {
        return String.format(TEMPLATES, 1 + random.nextInt(9), 1 + random.nextInt(30));
    }
}
