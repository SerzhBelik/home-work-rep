package HomeWork5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

public class Main {
    public static final int CARS_COUNT = 4;
    private static CyclicBarrier cbStart = new CyclicBarrier(CARS_COUNT+1);
    private static CyclicBarrier cbFinish = new CyclicBarrier(CARS_COUNT+1);
    private static Semaphore smpTunnel = new Semaphore(CARS_COUNT/2 +1);
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
//        cb = new CyclicBarrier(CARS_COUNT+1);
        try {
            cbStart.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
//        Semaphore smp = new Semaphore(CARS_COUNT/2);
        try {
            cbFinish.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }

        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }

    public static CyclicBarrier getCyclicBarrierStart(){
        return cbStart;
    }

    public static Semaphore getSemaphore(){
        return smpTunnel;
    }

    public static CyclicBarrier getCyclicBarrierFinish(){
        return cbFinish;
    }
}

