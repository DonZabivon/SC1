package model;

public class Category implements Entity {
    private long id; // уникальный идентификатор категории
    private Type type; // тип категории (доход или расход)
    private String name; // название категории (например, "Кафе", "Зарплата")

    private Category() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("%d, %s, %s", getId(), getType(), getName());
    }

    /**
     * Factory Method
     * @param type
     * @param name
     * @return
     */
    public static Category createCategory(Type type, String name) {
        Category category = new Category();
        category.setId(0);
        category.setType(type);
        category.setName(name);
        return category;
    }
}
