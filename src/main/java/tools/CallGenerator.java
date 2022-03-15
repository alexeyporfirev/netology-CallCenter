package tools;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class CallGenerator {

    // кол-во цифр в номере
    private final static int NUMBER_OF_DIGITS = 11;
    private static Random rand = new Random(System.currentTimeMillis());

    /**
     * Создание объекта-генератора набора звонков
     * @param numberOfCalls Кол-во звонков, которые необходимо сгенерировать
     * @return Список с объектами сгенерированных звонков
     */
    public static List<Call> generateCalls(int numberOfCalls) {
        List<Call> calls = new ArrayList<Call>();
        for (int i = 0; i< numberOfCalls; i++) {
            calls.add(generateCall());
        }
        return calls;
    }

    /**
     * Генерация одного звонка
     * @return Новый объект звонка
     */
    private static Call generateCall() {
        StringBuilder phone = new StringBuilder();
        for(int i = 0; i < NUMBER_OF_DIGITS; i++) {
            phone.append(rand.nextInt(9));
        }
        return new Call(phone.toString(), new Timestamp(System.currentTimeMillis()));
    }

}
