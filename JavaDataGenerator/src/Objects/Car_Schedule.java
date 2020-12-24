package Objects;

import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Behruz Mansurov
 */
public class Car_Schedule {
    private static final Random random = new Random();
    private static final String TEMPLATES = "INSERT INTO car_schedule(id_schedule, id_car) VALUES(%s, %s);";

    public static String generate() {
        return String.format(TEMPLATES, 1 + random.nextInt(20000), 1 + random.nextInt(100));
    }
}
