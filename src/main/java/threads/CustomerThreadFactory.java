package threads;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Фабрика по созданию новых именнованных потоков
 */
public class CustomerThreadFactory implements ThreadFactory {
        private final AtomicInteger counter = new AtomicInteger(0);
        private final String prefix = "Специалист";

        @Override
        public Thread newThread(Runnable task) {
            return new Thread(task, prefix + counter.incrementAndGet());
        }
}
