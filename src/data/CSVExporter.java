package data;

import model.BankAccount;
import model.Category;
import model.Operation;
import service.BankAccountService;
import service.CategoryService;
import service.OperationService;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.List;

public class CSVExporter extends Exporter {
    public CSVExporter(BankAccountService bankAccountService, CategoryService categoryService, OperationService operationService) {
        super(bankAccountService, categoryService, operationService);
    }

    @Override
    protected void exportAccounts(FileOutputStream accountStr, List<BankAccount> accounts) {
        try (PrintWriter pw = new PrintWriter(new OutputStreamWriter(accountStr))) {
            pw.printf("ID\tName\tBalance%n");
            for (BankAccount bankAccount : accounts) {
                pw.printf("%d\t'%s'\t%.2f%n",
                        bankAccount.getId(), bankAccount.getName(), bankAccount.getBalance());
            }
        }
    }

    @Override
    protected void exportCategories(FileOutputStream categoryStr, List<Category> categories) {
        try (PrintWriter pw = new PrintWriter(new OutputStreamWriter(categoryStr))) {
            pw.printf("ID\tType\tName%n");
            for (Category category : categories) {
                pw.printf("%d\t%s\t'%s'%n",
                        category.getId(), category.getType(), category.getName());
            }
        }
    }

    @Override
    protected void exportOperations(FileOutputStream operationStr, List<Operation> operations) {
        try (PrintWriter pw = new PrintWriter(new OutputStreamWriter(operationStr))) {
            pw.printf("ID\tType\tAccountID\tAmount\tDate\tDescription\tCategoryID%n");
            for (Operation operation : operations) {
                pw.printf("%d\t%s\t%d\t%.2f\t%s\t'%s'\t%d%n",
                        operation.getId(), operation.getType(), operation.getBankAccountId(),
                        operation.getAmount(), operation.getDate(), operation.getDescription(),
                        operation.getCategoryId());
            }
        }
    }
}
