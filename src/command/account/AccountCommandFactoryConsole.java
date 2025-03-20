package command.account;

import command.Command;
import model.BankAccount;
import service.BankAccountService;

import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.DoubleSupplier;
import java.util.function.LongSupplier;
import java.util.function.Supplier;

public class AccountCommandFactoryConsole implements AccountCommandFactoryInterface {
    private final Scanner scanner;
    private final BankAccountService bankAccountService;

    private LongSupplier idSupplier;

    private Supplier<String> nameSupplier;

    private DoubleSupplier amountSupplier;

    private Consumer<BankAccount> accountPrinter;

    private Consumer<List<BankAccount>> listPrinter;

    public AccountCommandFactoryConsole(Scanner scanner, BankAccountService bankAccountService) {
        this.scanner = scanner;
        this.bankAccountService = bankAccountService;

        idSupplier = () -> {
            System.out.print("Введите идентификатор счета: ");
            return Long.parseLong(scanner.nextLine());
        };

        nameSupplier = () -> {
            System.out.print("Введите название счета: ");
            return scanner.nextLine();
        };

        amountSupplier = () -> {
            System.out.print("Введите сумму: ");
            return Double.parseDouble(scanner.nextLine());
        };

        accountPrinter = account -> {
            if (account != null) {
                System.out.println(account);
            } else {
                System.out.println("Не найдено");
            }
        };

        listPrinter = accounts -> {
            for (BankAccount account: accounts) {
                accountPrinter.accept(account);
            }
        };
    }

    @Override
    public Command newCreateAccountCommand() {
        return new CreateAccountCommand(bankAccountService, nameSupplier, amountSupplier);
    }

    @Override
    public Command newDeleteAccountCommand() {
        return new DeleteAccountCommand(bankAccountService, idSupplier);

    }

    @Override
    public Command newFindAccountCommand() {
        return new FindAccountCommand(bankAccountService, idSupplier, accountPrinter);
    }

    @Override
    public Command newUpdateAccountCommand() {
        return new UpdateAccountCommand(bankAccountService, idSupplier, nameSupplier, amountSupplier);
    }

    @Override
    public Command newListAccountCommand() {
        return new ListAccountCommand(bankAccountService, listPrinter);
    }
}
