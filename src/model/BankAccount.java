package model;

import java.math.BigDecimal;

public class BankAccount implements Entity {
    private long id; // уникальный идентификатор счета
    private String name; // название счета
    private BigDecimal balance; // текущий баланс счета

    private BankAccount() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return String.format("%d, %s, %.2f",
                getId(), getName(), getBalance().doubleValue());
    }

    /**
     * Factory Method
     * @param name
     * @param balance
     * @return
     */
    public static BankAccount createAccount(String name, double balance) {
        BankAccount account = new BankAccount();
        account.setId(0);
        account.setName(name);
        account.setBalance(new BigDecimal(balance));
        return account;
    }
}
