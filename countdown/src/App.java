import java.util.concurrent.*;

public class App {
    public static void main(String[] args) throws Exception {
        ExecutorService executor = Executors.newSingleThreadExecutor();

        executor.submit(() -> {
            try {
                for (int i = 1; i <= 60; i++) {
                    Thread.sleep(1000);
                    System.out.println("Counting..." + i);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executor.shutdown();
    }
}
