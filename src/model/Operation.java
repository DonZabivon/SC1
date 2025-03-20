package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Operation implements Entity {
    private long id; // уникальный идентификатор операции
    private Type type; // тип операции (доход или расход)
    private long bankAccountId; // ссылка на счет, к которому относится операция
    private BigDecimal amount; // сумма операции
    private LocalDateTime date; // дата операции
    private String description; // описание операции (необязательное поле)
    private long categoryId; // ссылка на категорию, к которой относится операция

    private Operation() {}

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

    public long getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(long bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return String.format("%d, %s (%d), %d, %.2f, %s, %s",
                getId(),
                getType(),
                getCategoryId(),
                getBankAccountId(),
                getAmount(),
                getDate().toString(),
                getDescription());
    }

    /**
     * Factory Method
     * @param type
     * @param bankAccountId
     * @param amount
     * @param date
     * @param description
     * @param categoryId
     * @return
     */
    public static Operation createOperation(Type type, long bankAccountId, double amount, LocalDateTime date, String description, long categoryId) {
        Operation operation = new Operation();
        operation.setId(0);
        operation.setType(type);
        operation.setBankAccountId(bankAccountId);
        operation.setAmount(new BigDecimal(amount));
        operation.setDate(date);
        operation.setDescription(description);
        operation.setCategoryId(categoryId);
        return operation;
    }
}
