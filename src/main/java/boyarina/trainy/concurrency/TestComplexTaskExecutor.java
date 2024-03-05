package boyarina.trainy.concurrency;

import lombok.AllArgsConstructor;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestComplexTaskExecutor {
    public static void main(String[] args) {
        ComplexTaskExecutor taskExecutor = new ComplexTaskExecutor(5); // Количество задач для выполнения

        Runnable testRunnable = () -> {
            System.out.println(Thread.currentThread().getName() + " started the test.");

            // Выполнение задач
            try {
                taskExecutor.executeTasks(5);
            } catch (BrokenBarrierException | InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println(Thread.currentThread().getName() + " completed the test.");
        };

        Thread thread1 = new Thread(testRunnable, "TestThread-1");
        Thread thread2 = new Thread(testRunnable, "TestThread-2");

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @AllArgsConstructor
    public static class ComplexTaskExecutor {
        public int numberOfTasks;
        public static CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

        public void executeTasks(int numberOfTasks) throws BrokenBarrierException, InterruptedException {
            System.out.println("Step wait execute");
            ExecutorService service = Executors.newFixedThreadPool(numberOfTasks);
            service.submit(() -> {
                try {
                    ComplexTask.execute();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
            cyclicBarrier.await();
            System.out.println("All steps execute");
        }
    }

    public static class ComplexTask {
        public static void execute() throws InterruptedException {
            Thread.sleep(1000);
            System.out.println("Step execute!");
        }
    }
}
