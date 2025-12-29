package Order;

public enum DishType {

    SOUP("Суп", 800),
    SALAD("Салат", 400),
    STEAK("Стейк", 1500),
    PASTA("Паста", 1000),
    CAKE("Десерт", 600),
    JUICE("Сок", 200),
    COFFEE("Кофе", 300);

    private final String name;
    private final long cookingTimeMs;

    DishType(String name, long cookingTimeMs) {
        this.name = name;
        this.cookingTimeMs = cookingTimeMs;
    }

    public String getName() {
        return name;
    }

    public long getCookingTimeMs() {
        return cookingTimeMs;
    }
}

