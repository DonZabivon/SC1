package command.account;

import command.Command;
import model.BankAccount;
import service.BankAccountService;

import java.util.List;
import java.util.function.Consumer;

public class ListAccountCommand implements Command {
    private final BankAccountService bankAccountService;
    private final Consumer<List<BankAccount>> accountConsumer;

    public ListAccountCommand(BankAccountService bankAccountService, Consumer<List<BankAccount>> accountConsumer) {
        this.bankAccountService = bankAccountService;
        this.accountConsumer = accountConsumer;
    }

    @Override
    public void execute() throws Exception {
        List<BankAccount> accountList = bankAccountService.getAll();
        accountConsumer.accept(accountList);
    }
}
