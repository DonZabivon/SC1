package command.account;

import command.Command;
import service.BankAccountService;

import java.util.function.LongSupplier;

public class DeleteAccountCommand implements Command {
    private final BankAccountService bankAccountService;
    private final LongSupplier idSupplier;

    public DeleteAccountCommand(BankAccountService bankAccountService, LongSupplier idSupplier) {
        this.bankAccountService = bankAccountService;
        this.idSupplier = idSupplier;
    }

    @Override
    public void execute() throws Exception {
        bankAccountService.delete(idSupplier.getAsLong());
    }
}
