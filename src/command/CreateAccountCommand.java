package command;

import model.BankAccount;
import service.BankAccountService;

import java.util.function.DoubleSupplier;
import java.util.function.Supplier;

public class CreateAccountCommand implements Command {
    private final BankAccountService bankAccountService;
    private final Supplier<String> nameSupplier;
    private final DoubleSupplier balanceSupplier;

    public CreateAccountCommand(BankAccountService bankAccountService, Supplier<String> nameSupplier, DoubleSupplier balanceSupplier) {
        this.bankAccountService = bankAccountService;
        this.nameSupplier = nameSupplier;
        this.balanceSupplier = balanceSupplier;
    }

    @Override
    public void execute() throws Exception {
        bankAccountService.create(BankAccount.createAccount(nameSupplier.get(), balanceSupplier.getAsDouble()));
    }
}
