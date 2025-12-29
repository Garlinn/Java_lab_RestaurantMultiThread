package Order;

import java.util.concurrent.CompletableFuture;

public class Order {

    private final long id;
    private final DishType dishType;

    private final CompletableFuture<Dish> resultFuture;

    public Order(long id, DishType dishType) {
        this.id = id;
        this.dishType = dishType;
        this.resultFuture = new CompletableFuture<>();

        System.out.printf("[ЗАКАЗ] Создан заказ #%d (%s)%n", id, dishType.getName());
    }

    public long getId() {
        return id;
    }

    public DishType getDishType() {
        return dishType;
    }

    public CompletableFuture<Dish> getResultFuture() {
        return resultFuture;
    }

    public void complete(Dish dish) {
        resultFuture.complete(dish);

        System.out.printf("[ЗАКАЗ #%d] Готов (%s)%n", id, dish.getType().getName());
    }

    @Override
    public String toString() {
        return "Заказ #" + id + " (" + dishType.getName() + ")";
    }
}