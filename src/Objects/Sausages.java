package Objects;

import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Behruz Mansurov
 */
public class Sausages {
    private static Random random = new Random();
    private static int start_price = 50;

    private static String generateRandomSausage() {
        return SAUSAGES[random.nextInt(SAUSAGES.length)];
    }

    private static double generateRandomPrice() {
        return start_price + random.nextInt(2951);
    }

    public static String generate() {
        return String.format(TEMPLATES, generateRandomSausage(), generateRandomPrice());
    }

    private static final String TEMPLATES = "INSERT INTO sausages(name, price) VALUES('%s', %s);";

    private static final String SAUSAGES[] =
            {
                    "Краковская",
                    "Таллинская",
                    "Украинская жареная",
                    "Одесская",
                    "Украинская",
                    "Баранья",
                    "Польская",
                    "Особая",
                    "Зернистая",
                    "Свиная",
                    "Московская",
                    "Любительская",
                    "Деликатесная",
                    "Ростовская",
                    "Сервелат",
                    "Московская",
                    "Любительская",
                    "Баранья",
                    "Докторская",
                    "Молочная",
                    "Русская",
                    "Телячья",
                    "Обыкновенная",
                    "Столовая",
                    "Ветчинная",
                    "Чайная",
                    "Молодежная",
                    "Заказной",
                    "Любительский",
                    "Ветчинный",
                    "Говяжий"
            };
}
