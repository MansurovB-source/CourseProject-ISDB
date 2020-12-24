package Objects;

import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Behruz Mansurov
 */
public class Animals {
    private static final Random random = new Random();
    private static int i = 0;
    private static final String ANIMALS[] =
            {
                    "Порода кур Барневельдер",
                    "Корова Айрширская",
                    "Свинья Уржумская",
                    "Порода кур Авиколор",
                    "Корова Ярославская",
                    "Свинья Ландрас",
                    "Порода кур Адлерская серебристая",
                    "Корова Красно-пестрая",
                    "Свинья Дюрок",
                    "Порода кур Азиль",
                    "Корова Черно-пестрая",
                    "Свинья Азиатская вислобрюхая",
                    "Порода кур Австралорп",
                    "Корова Голландская",
                    "Свинья Кармал",
                    "Порода кур Амераукана",
                    "Корова Красная",
                    "Порода кур Амрокс",
                    "Корова Голштинская",
                    "Свинья Пьетрен",
                    "Порода кур Банкивские джунглевые куры",
                    "Корова Холмогорская",
                    "Свинья Лакомб",
                    "Порода кур Андалузские куры",
                    "Корова Тагильская",
                    "Порода кур ",
                    "Свинья Гемпширская",
                    "Порода кур Аям Цемани - черная экзотика",
            };
    private static final String TEMPLATES = "INSERT INTO animals(name, id_location) VALUES('%s', %s);";

    public static String generate() {
        return String.format(TEMPLATES, ANIMALS[i++], 1 + random.nextInt(1));
    }
}
