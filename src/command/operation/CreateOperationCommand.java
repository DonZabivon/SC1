package command.operation;

import command.Command;
import model.BankAccount;
import model.Category;
import model.Operation;
import model.Type;
import service.BankAccountService;
import service.CategoryService;
import service.OperationService;

import java.time.LocalDateTime;
import java.util.function.DoubleSupplier;
import java.util.function.LongSupplier;
import java.util.function.Supplier;

public class CreateOperationCommand implements Command {
    private final BankAccountService bankAccountService;
    private final CategoryService categoryService;
    private final OperationService operationService;
    private final LongSupplier bankAccountIdSupplier;
    private final LongSupplier categoryIdSupplier;
    private final DoubleSupplier amountSupplier;
    private final Supplier<String> descriptionSupplier;
    private final Supplier<LocalDateTime> timeSupplier;

    public CreateOperationCommand(BankAccountService bankAccountService, CategoryService categoryService, OperationService operationService, LongSupplier bankAccountIdSupplier, LongSupplier categoryIdSupplier, DoubleSupplier amountSupplier, Supplier<String> descriptionSupplier, Supplier<LocalDateTime> timeSupplier) {
        this.bankAccountService = bankAccountService;
        this.categoryService = categoryService;
        this.operationService = operationService;
        this.bankAccountIdSupplier = bankAccountIdSupplier;
        this.categoryIdSupplier = categoryIdSupplier;
        this.amountSupplier = amountSupplier;
        this.descriptionSupplier = descriptionSupplier;
        this.timeSupplier = timeSupplier;
    }

    @Override
    public void execute() throws Exception {
        long bankAccountId = bankAccountIdSupplier.getAsLong();
        long categoryId = categoryIdSupplier.getAsLong();
        BankAccount bankAccount = bankAccountService.findById(bankAccountId);
        Category category = categoryService.findById(categoryId);
        Type type = category.getType();

        double amount = amountSupplier.getAsDouble();
        String description = descriptionSupplier.get();
        LocalDateTime dateTime = timeSupplier.get();

        Operation operation = Operation.createOperation(type, bankAccount.getId(), amount, dateTime, description, category.getId());
        operationService.create(operation);

        // TODO
        // Здесь, наверное, нужно добавить зачисление/списание средств со счета
    }
}
