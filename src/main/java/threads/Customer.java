package threads;

import tools.Call;

import java.sql.Timestamp;
import java.util.Random;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Customer implements Runnable {

    private ConcurrentLinkedQueue<Call> calls;
    private int maxPeriodOfProcessing;

    /**
     * Создание объекта потока, который обрабатывает звонки
     * @param calls Объект-очереди, откуда берутся звонки для обработки
     * @param maxPeriodOfProcessing Максимальный период ожидания для обработки звонка
     */
    public Customer(ConcurrentLinkedQueue<Call> calls, int maxPeriodOfProcessing) {
        this.calls = calls;
        this.maxPeriodOfProcessing = maxPeriodOfProcessing;
    }

    @Override
    public void run() {
        try {
            //обрабатываем звонки до тех пор, пока они еще есть в очереди
            while (!calls.isEmpty()) {
                Call temp = calls.poll();
                System.out.printf("Поступил звонок от %s специалисту %s. Время звонка: %s.\n",
                        temp.getPhoneNumber(),
                        Thread.currentThread().getName(),
                        temp.getCallDate());
                Thread.sleep(new Random(System.currentTimeMillis()).nextInt(maxPeriodOfProcessing));
                System.out.printf("Звонок от %s обработан специалистом %s. Текущее время: %s.\n",
                        temp.getPhoneNumber(),
                        Thread.currentThread().getName(),
                        new Timestamp(System.currentTimeMillis()));
            }
        } catch (InterruptedException e) {

        } finally {
            System.out.printf("Специалист %s завершил работу.\n", Thread.currentThread().getName());
        }
    }
}