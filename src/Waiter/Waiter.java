package Waiter;

import Kitchen.Kitchen;
import Order.Dish;
import Order.DishType;
import Order.Order;

import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicLong;

public class Waiter implements Runnable {

    private final int waiterId;
    private final Kitchen kitchen;
    private final AtomicLong orderIdGenerator;
    private final Random random = new Random();

    public Waiter(int waiterId, Kitchen kitchen, AtomicLong orderIdGenerator) {
        this.waiterId = waiterId;
        this.kitchen = kitchen;
        this.orderIdGenerator = orderIdGenerator;
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {

                DishType dishType = randomDish();
                long orderId = orderIdGenerator.incrementAndGet();
                Order order = new Order(orderId, dishType);

                System.out.printf("[ОФИЦИАНТ-%d] Принял %s%n", waiterId, order);

                kitchen.submitOrder(order);

                Dish dish = order.getResultFuture().get();

                System.out.printf("[ОФИЦИАНТ-%d] Доставил %s клиенту%n", waiterId, dish.getType().getName());

                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.printf("[ОФИЦИАНТ-%d] Завершает работу%n", waiterId);
        } catch (ExecutionException e) {
            System.out.printf("[ОФИЦИАНТ-%d] Ошибка при выполнении заказа: %s%n", waiterId, e.getMessage());
        }
    }

    private DishType randomDish() {
        DishType[] values = DishType.values();
        return values[random.nextInt(values.length)];
    }
}

