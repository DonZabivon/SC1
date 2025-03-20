package command.account;

import command.Command;
import model.BankAccount;
import service.BankAccountService;

import java.util.function.DoubleSupplier;
import java.util.function.LongSupplier;
import java.util.function.Supplier;

public class UpdateAccountCommand implements Command {
    private final BankAccountService bankAccountService;
    private final LongSupplier idSupplier;
    private final Supplier<String> nameSupplier;
    private final DoubleSupplier balanceSupplier;

    public UpdateAccountCommand(BankAccountService bankAccountService, LongSupplier idSupplier, Supplier<String> nameSupplier, DoubleSupplier balanceSupplier) {
        this.bankAccountService = bankAccountService;
        this.idSupplier = idSupplier;
        this.nameSupplier = nameSupplier;
        this.balanceSupplier = balanceSupplier;
    }

    @Override
    public void execute() throws Exception {
        long id = idSupplier.getAsLong();
        BankAccount account = bankAccountService.findById(id);
        String name = nameSupplier.get();
        double balance = balanceSupplier.getAsDouble();
        bankAccountService.update(account);
    }
}
