package Order;

public class Dish {

    private final DishType type;

    public Dish(DishType type) {
        this.type = type;
        System.out.printf("[КУХНЯ] Блюдо готово: %s%n", type.getName());
    }

    public DishType getType() {
        return type;
    }

    @Override
    public String toString() {
        return type.getName();
    }
}
