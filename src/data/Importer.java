package data;

import dao.DAOError;
import model.BankAccount;
import model.Category;
import model.Operation;
import service.BankAccountService;
import service.CategoryService;
import service.OperationService;

import java.io.*;
import java.util.List;

public abstract class Importer {
    protected final BankAccountService bankAccountService;
    protected final CategoryService categoryService;
    protected final OperationService operationService;

    public Importer(BankAccountService bankAccountService, CategoryService categoryService, OperationService operationService) {
        this.bankAccountService = bankAccountService;
        this.categoryService = categoryService;
        this.operationService = operationService;
    }

    public void importData(File accountFile, File categoriesFile, File operationsFile) throws IOException {
        try (FileReader accountReader = new FileReader(accountFile);
             FileReader categoryReader = new FileReader(categoriesFile);
             FileReader operationReader = new FileReader(operationsFile)) {
            List<BankAccount> accounts = importAccounts(accountReader);
            List<Category> categories = importCategories(categoryReader);
            List<Operation> operations = importOperations(operationReader);

            for (BankAccount bankAccount : accounts) {
                try {
                    bankAccountService.create(bankAccount);
                } catch (DAOError e) {
                    System.out.println(e);
                }
            }
            for (Category category : categories) {
                try {
                    categoryService.create(category);
                } catch (DAOError e) {
                    System.out.println(e);
                }
            }
            for (Operation operation : operations) {
                try {
                    operationService.create(operation);
                } catch (DAOError e) {
                    System.out.println(e);
                }
            }
        }
    }

    protected abstract List<BankAccount> importAccounts(FileReader accountReader) throws IOException;

    protected abstract List<Category> importCategories(FileReader categoryReader) throws IOException;

    protected abstract List<Operation> importOperations(FileReader operationReader) throws IOException;
}
