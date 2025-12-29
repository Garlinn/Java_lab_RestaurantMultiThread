package Kitchen;

import Order.Dish;
import Order.Order;

import java.util.concurrent.BlockingQueue;

public class Cooking implements Runnable {

    private final int cookId;
    private final BlockingQueue<Order> orderQueue;

    public Cooking(int cookId, BlockingQueue<Order> orderQueue) {
        this.cookId = cookId;
        this.orderQueue = orderQueue;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {

                Order order = orderQueue.take();

                System.out.printf("[ПОВАР-%d] Начал готовить %s%n", cookId, order);

                Thread.sleep(order.getDishType().getCookingTimeMs());

                Dish dish = new Dish(order.getDishType());

                order.complete(dish);

                System.out.printf("[ПОВАР-%d] Закончил %s%n", cookId, order);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.printf("[ПОВАР-%d] Завершает работу%n", cookId);
        }
    }
}

