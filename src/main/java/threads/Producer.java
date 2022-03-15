package threads;

import tools.Call;
import tools.CallGenerator;

import java.util.concurrent.ConcurrentLinkedQueue;

public class Producer implements Runnable {

    private ConcurrentLinkedQueue<Call> calls;
    private int callsPerSecond;
    private final static int TIME_PERIOD = 1000;


    /**
     * Создание объекта потока, который генерирует звонки
     * @param calls Объект-очереди, куда помещаются новые сгенерированные звонки
     * @param callsPerSecond Кол-во звонков, генерируемых в секунду
     */
    public Producer(ConcurrentLinkedQueue<Call> calls, int callsPerSecond) {
        this.calls = calls;
        this.callsPerSecond = callsPerSecond;
    }

    @Override
    public void run() {
        try {
            // генерируем звонки до тех пор, пока работу потока-генератора не прервут
            while (!Thread.currentThread().isInterrupted()) {
                calls.addAll(CallGenerator.generateCalls(callsPerSecond));
                System.out.println("Сгенерировано " + callsPerSecond + " новых звонков.");
                Thread.sleep(TIME_PERIOD);
            }
        } catch (InterruptedException e) {

        } finally {
            System.out.println("Генератор звонков завершил свою работу.");
        }
    }
}
