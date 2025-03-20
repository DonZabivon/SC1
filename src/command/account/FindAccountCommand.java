package command.account;

import command.Command;
import model.BankAccount;
import service.BankAccountService;

import java.util.function.Consumer;
import java.util.function.LongSupplier;

public class FindAccountCommand implements Command {
    private final BankAccountService bankAccountService;
    private final LongSupplier idSupplier;
    private final Consumer<BankAccount> accountConsumer;

    public FindAccountCommand(BankAccountService bankAccountService, LongSupplier idSupplier, Consumer<BankAccount> accountConsumer) {
        this.bankAccountService = bankAccountService;
        this.idSupplier = idSupplier;
        this.accountConsumer = accountConsumer;
    }

    @Override
    public void execute() throws Exception {
        BankAccount account = bankAccountService.findById(idSupplier.getAsLong());
        accountConsumer.accept(account);
    }
}
