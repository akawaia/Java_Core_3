package HomeWork5;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class MainClass {

    public static final int CARS_COUNT = 4;

    protected static final CountDownLatch COUNT_DOWN_LATCH = new CountDownLatch(CARS_COUNT);
    protected static final CountDownLatch COUNT_DOWN_LATCH_FINISH = new CountDownLatch(CARS_COUNT);
    protected static final Semaphore SEMAPHORE = new Semaphore(2);


    public static void main(String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }
        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }

    }


    private static void semaphoreExample() {
        Semaphore semaphore = new Semaphore(3);

        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    System.out.printf("Thread %d before semaphore\n", finalI);
                    semaphore.acquire();
                    System.out.printf("Thread %d after semaphore\n", finalI);
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.printf("Thread %d free from semaphore\n", finalI);
                    semaphore.release();
                }
            }).start();
        }
    }
}
