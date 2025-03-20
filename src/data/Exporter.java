package data;

import model.BankAccount;
import model.Category;
import model.Operation;
import service.BankAccountService;
import service.CategoryService;
import service.OperationService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public abstract class Exporter {
    protected final BankAccountService bankAccountService;
    protected final CategoryService categoryService;
    protected final OperationService operationService;

    protected Exporter(BankAccountService bankAccountService, CategoryService categoryService, OperationService operationService) {
        this.bankAccountService = bankAccountService;
        this.categoryService = categoryService;
        this.operationService = operationService;
    }

    public void exportData(File accountFile, File categoriesFile, File operationsFile) throws IOException {
        try (FileOutputStream accountStr = new FileOutputStream(accountFile);
             FileOutputStream categoryStr = new FileOutputStream(categoriesFile);
             FileOutputStream operationStr = new FileOutputStream(operationsFile)) {
            exportAccounts(accountStr, bankAccountService.getAll());
            exportCategories(categoryStr, categoryService.getAll());
            exportOperations(operationStr, operationService.getAll());
        }
    }

    protected abstract void exportAccounts(FileOutputStream accountStr, List<BankAccount> accounts);
    protected abstract void exportCategories(FileOutputStream categoryStr, List<Category> categories);
    protected abstract void exportOperations(FileOutputStream operationStr, List<Operation> operations);
}
