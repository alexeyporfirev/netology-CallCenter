package application;

import threads.Customer;
import threads.CustomerThreadFactory;
import threads.Producer;
import tools.Call;

import java.util.concurrent.*;

public class Main {

    private final static int CALLS_PER_SECOND = 10;
    private final static int MAX_PERIOD_OF_CALL_PROCESSING = 1000;

    public static void main(String[] args) throws InterruptedException {
        // создаем очередь
        ConcurrentLinkedQueue<Call> calls = new ConcurrentLinkedQueue<Call>();
        System.out.println("Число доступных специалистов: " + Runtime.getRuntime().availableProcessors());

        //создаем поток для генерации звонков
        Thread threadATC = new Thread(null, new Producer(calls, CALLS_PER_SECOND), "ATC поток");

        // создаем набор потоков для обработки звонков
        final ExecutorService threadPool = Executors
                .newFixedThreadPool(Runtime.getRuntime().availableProcessors(), new CustomerThreadFactory());

        // запускаем поток по генерации звонков и ждем 500 мс прежде, чем запустить потоки обработчики звонков
        threadATC.start();
        Thread.currentThread().sleep(500);

        for (int i =0; i< Runtime.getRuntime().availableProcessors(); i++) {
            threadPool.submit(new Customer(calls, MAX_PERIOD_OF_CALL_PROCESSING));
        }

        // даем потоку генератору отработать 3 с и прерываем его
        Thread.currentThread().sleep(3000);
        threadATC.interrupt();

        // ждем завершения работы потоков по обработке звонков
        threadPool.shutdown();
        threadPool.awaitTermination(200000, TimeUnit.MILLISECONDS);
    }
}
