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
                    "Корова Айрширская",
                    "Корова Ярославская",
                    "Корова Красно-пестрая",
                    "Корова Черно-пестрая",
                    "Корова Голландская",
                    "Корова Красная",
                    "Корова Голштинская",
                    "Корова Холмогорская",
                    "Корова Тагильская",
                    "Свинья Уржумская",
                    "Свинья Ландрас",
                    "Свинья Дюрок",
                    "Свинья Азиатская вислобрюхая",
                    "Свинья Кармал",
                    "Свинья Пьетрен",
                    "Свинья Лакомб",
                    "Свинья Гемпширская",
                    "Свинья Темворс",
                    "Порода кур Авиколор",
                    "Порода кур Австралорп",
                    "Порода кур Адлерская серебристая",
                    "Порода кур Азиль",
                    "Порода кур Амераукана",
                    "Порода кур Амрокс",
                    "Порода кур Андалузские куры",
                    "Порода кур Аям Цемани - черная экзотика",
                    "Порода кур Банкивские джунглевые куры",
                    "Порода кур Барневельдер",
                    "Порода кур ",
            };
    private static final String TEMPLATES = "INSERT INTO animals(name, id_location) VALUES('%s', %s);";

    public static String generate() {
        return String.format(TEMPLATES, ANIMALS[random.nextInt(ANIMALS.length)], 1 + random.nextInt(1));
    }
}
