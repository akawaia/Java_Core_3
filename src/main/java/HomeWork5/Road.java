package HomeWork5;

import java.io.File;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Road extends Stage {

    private final int length;

    public Road(int length) {
        this.length = length;
        this.description = "Дорога " + length + " метров";
    }

    @Override
    public void go(Car c) {
        try {
            System.out.println(c.getName() + " начал этап: " + description);
            Thread.sleep(length / c.getSpeed() * 1000);
            System.out.println(c.getName() + " закончил этап: " + description);
            if (length == 40) {
                MainClass.COUNT_DOWN_LATCH_FINISH.countDown();
                if (MainClass.COUNT_DOWN_LATCH_FINISH.getCount() == MainClass.CARS_COUNT - 1) {
                    System.out.println(c.getName() + " WIN!!!!");
                }
                if(MainClass.COUNT_DOWN_LATCH_FINISH.getCount() == 0) System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка завершилась!!!");
                MainClass.COUNT_DOWN_LATCH_FINISH.await();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}