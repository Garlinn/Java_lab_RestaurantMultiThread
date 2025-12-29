import Kitchen.Kitchen;
import Order.Order;
import Waiter.Waiter;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        int cooksCount = 2;
        BlockingQueue<Order> orderQueue = new LinkedBlockingQueue<>();
        Kitchen kitchen = new Kitchen(orderQueue, cooksCount);

        AtomicLong orderIdGenerator = new AtomicLong(0);

        int waitersCount = 3;
        ExecutorService waitersPool = Executors.newFixedThreadPool(waitersCount);

        for (int i = 1; i <= waitersCount; i++) {
            waitersPool.submit(new Waiter(i, kitchen, orderIdGenerator));
        }

        System.out.println("[РЕСТОРАН] Открытие ресторана");

        Thread.sleep(20_000); //время работы ресторана

        System.out.println("[РЕСТОРАН] Завершение работы официантов");
        waitersPool.shutdownNow();
        waitersPool.awaitTermination(5, TimeUnit.SECONDS);

        System.out.println("[РЕСТОРАН] Завершение работы кухни");
        kitchen.shutdown();

        System.out.println("[РЕСТОРАН] Закрытие ресторана");
    }
}
