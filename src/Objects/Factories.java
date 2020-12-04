package Objects;

import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Behruz Mansurov
 */
public class Factories {
    private static int i = 0;
    private static final Random random = new Random();
    private static final String FACTORIES[] =
            {
                    "Малаховский мясокомбинат",
                    "Слободской мясокомбинат",
                    "Мясославль",
                    "Великолукский мясокомбинат",
                    "Саянский мясокомбинат",
                    "Мортадель",
                    "БахрушинЪ",
                    "Гелиос-М",
                    "Талина",
                    "МЯСОПРОДУКТ"
            };
    private static final String TEMPLATES = "INSERT INTO factories(name, id_location, worker_num) VALUES('%s', %s, %s);";

    public static String generate() {
        return String.format(TEMPLATES, FACTORIES[i++], 1 + random.nextInt(1), 0);
    }
}
