package Kitchen;

import Order.Order;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Kitchen {

    private final BlockingQueue<Order> orderQueue;
    private final ExecutorService cookPool;

    public Kitchen(BlockingQueue<Order> orderQueue, int cooksCount) {
        this.orderQueue = orderQueue;
        this.cookPool = Executors.newFixedThreadPool(cooksCount);

        System.out.printf("[КУХНЯ] Открыта. Поваров: %d%n", cooksCount);

        for (int i = 1; i <= cooksCount; i++) {
            cookPool.submit(new Cooking(i, orderQueue));
        }
    }

    public void submitOrder(Order order) {
        try {
            orderQueue.put(order);
            System.out.printf("[КУХНЯ] Принят %s. В очереди: %d%n", order, orderQueue.size());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("[КУХНЯ] Ошибка при приёме заказа");
        }
    }

    public void shutdown() {
        cookPool.shutdownNow();
        System.out.println("[КУХНЯ] Закрывается");
    }
}
