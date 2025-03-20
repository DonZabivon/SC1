package command.operation;

import command.Command;
import model.Operation;
import service.BankAccountService;
import service.CategoryService;
import service.OperationService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.DoubleSupplier;
import java.util.function.LongSupplier;
import java.util.function.Supplier;

public class OperationCommandFactoryConsole implements OperationCommandFactoryInterface {
    private final Scanner scanner;
    private final BankAccountService bankAccountService;
    private final CategoryService categoryService;
    private final OperationService operationService;
    private LongSupplier idSupplier;
    private LongSupplier bankAccountIdSupplier;
    private LongSupplier categoryIdSupplier;
    private DoubleSupplier amountSupplier;
    private Supplier<String> descriptionSupplier;
    private Supplier<LocalDateTime> timeSupplier;
    private Consumer<Operation> operationPrinter;
    private Consumer<List<Operation>> listPrinter;


    public OperationCommandFactoryConsole(Scanner scanner, BankAccountService bankAccountService, CategoryService categoryService, OperationService operationService) {
        this.scanner = scanner;
        this.bankAccountService = bankAccountService;
        this.categoryService = categoryService;
        this.operationService = operationService;

        idSupplier = () -> {
            System.out.print("Введите идентификатор операции: ");
            return Long.parseLong(scanner.nextLine());
        };

        bankAccountIdSupplier = () -> {
            System.out.print("Введите идентификатор счета: ");
            return Long.parseLong(scanner.nextLine());
        };

        categoryIdSupplier = () -> {
            System.out.print("Введите идентификатор категории: ");
            return Long.parseLong(scanner.nextLine());
        };

        descriptionSupplier = () -> {
            System.out.print("Введите описание операции: ");
            return scanner.nextLine();
        };

        amountSupplier = () -> {
            System.out.print("Введите сумму: ");
            return Double.parseDouble(scanner.nextLine());
        };

        timeSupplier = () -> {
            return LocalDateTime.now();
        };

        operationPrinter = operation -> {
            if (operation != null) {
                System.out.println(operation);
            } else {
                System.out.println("Не найдено");
            }
        };

        listPrinter = operations -> {
            for (Operation operation : operations) {
                operationPrinter.accept(operation);
            }
        };
    }

    @Override
    public Command newCreateOperationCommand() {
        return new CreateOperationCommand(bankAccountService, categoryService, operationService, bankAccountIdSupplier, categoryIdSupplier, amountSupplier, descriptionSupplier, timeSupplier);
    }

    @Override
    public Command newDeleteOperationCommand() {
        return new DeleteOperationCommand(operationService, idSupplier);
    }

    @Override
    public Command newUpdateOperationCommand() {
        return new UpdateOperationCommand(operationService, idSupplier, descriptionSupplier);
    }

    @Override
    public Command newFindOperationCommand() {
        return new FindOperationCommand(operationService, idSupplier, operationPrinter);
    }

    @Override
    public Command newListOperationCommand() {
        return new ListOperationCommand(operationService, listPrinter);
    }
}
