package Objects;

import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Behruz Mansurov
 */
public class Subscriptions {
    private static final Random random = new Random();
    private static int i = 0;
    private static int price = 500;
    private static final String SUBSCRIPTIONS[] =
            {
                    "Basic",
                    "Medium",
                    "High",
                    "Pro"
            };
    private static final String TEMPLATES = "INSERT INTO subscriptions(name, price, discount) VALUES('%s', %s, %s);";

    public static String generate() {
        return String.format(TEMPLATES, SUBSCRIPTIONS[i++], price += 200, 5 + random.nextInt(15));
    }
}
