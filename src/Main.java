import command.CreateAccountCommand;
import command.DeleteAccountCommand;
import command.FindAccountCommand;
import command.UpdateAccountCommand;
import dao.BankAccountDAO;
import dao.inmemory.InMemoryBankAccountDAO;
import model.BankAccount;
import service.BankAccountService;

import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.DoubleSupplier;
import java.util.function.LongSupplier;
import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LongSupplier idSupplier = () -> {
            System.out.print("Enter id: ");
            return Long.parseLong(scanner.nextLine());
        };

        Supplier<String> nameSupplier = () -> {
            System.out.print("Enter name: ");
            return scanner.nextLine();
        };

        DoubleSupplier amountSupplier = () -> {
            System.out.print("Enter amount: ");
            return Double.parseDouble(scanner.nextLine());
        };

        Consumer<BankAccount> accountPrinter = account -> {
            if (account != null) {
                System.out.printf("%d, %s, %.2f%n",
                        account.getId(), account.getName(), account.getBalance().doubleValue());
            } else {
                System.out.println("Not Found");
            }
        };

        BankAccountDAO bankAccountDAO = new InMemoryBankAccountDAO();
        BankAccountService service = new BankAccountService(bankAccountDAO);

        BankAccount account1 = BankAccount.createAccount("Account 1", 1000);
        BankAccount account2 = BankAccount.createAccount("Account 2", 2000);
        BankAccount account3 = BankAccount.createAccount("Account 3", 3000);

        CreateAccountCommand createAccountCommand = new CreateAccountCommand(service, nameSupplier, amountSupplier);
        DeleteAccountCommand deleteAccountCommand = new DeleteAccountCommand(service, idSupplier);
        FindAccountCommand findAccountCommand = new FindAccountCommand(service, idSupplier, accountPrinter);

        try {
            service.create(account1);
            service.create(account2);
            service.create(account3);

            //for (int i = 0; i < 3; ++i)
            //    createAccountCommand.execute();

            for (BankAccount account: service.getAll()) {
                accountPrinter.accept(account);
            }

            //for (int i = 0; i < 3; ++i)
            //    findAccountCommand.execute();

            //for (int i = 0; i < 3; ++i)
            //    deleteAccountCommand.execute();

            UpdateAccountCommand updateAccountCommand = new UpdateAccountCommand(service, idSupplier, nameSupplier, amountSupplier);
            updateAccountCommand.execute();

            for (BankAccount account: service.getAll()) {
                accountPrinter.accept(account);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}