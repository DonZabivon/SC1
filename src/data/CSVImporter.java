package data;

import model.BankAccount;
import model.Category;
import model.Operation;
import model.Type;
import service.BankAccountService;
import service.CategoryService;
import service.OperationService;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CSVImporter extends Importer {

    public CSVImporter(BankAccountService bankAccountService, CategoryService categoryService, OperationService operationService) {
        super(bankAccountService, categoryService, operationService);
    }

    @Override
    protected List<BankAccount> importAccounts(FileReader accountReader) throws IOException {
        List<BankAccount> accounts = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(accountReader)) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split("\t");
                long id = Long.parseLong(fields[0]);
                String name = fields[1].substring(1, fields[1].length()-1);
                double balance = Double.parseDouble(fields[2].replace(",", "."));
                BankAccount bankAccount = BankAccount.createAccount(name, balance);
                bankAccount.setId(id);
                accounts.add(bankAccount);
            }
        }
        return accounts;
    }

    @Override
    protected List<Category> importCategories(FileReader categoryReader) throws IOException {
        List<Category> categories = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(categoryReader)) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split("\t");
                long id = Long.parseLong(fields[0]);
                Type type = Type.valueOf(fields[1]);
                String name = fields[2].substring(1, fields[2].length()-1);
                Category category = Category.createCategory(type, name);
                category.setId(id);
                categories.add(category);
            }
        }
        return categories;
    }

    @Override
    protected List<Operation> importOperations(FileReader operationReader) throws IOException {
        List<Operation> operations = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(operationReader)) {
            br.readLine();
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split("\t");
                long id = Long.parseLong(fields[0]);
                Type type = Type.valueOf(fields[1]);
                long accountId = Long.parseLong(fields[2]);
                double amount = Double.parseDouble(fields[3].replace(",", "."));
                LocalDateTime date = LocalDateTime.parse(fields[4]);
                String desc = fields[5].substring(1, fields[5].length()-1);
                long categoryId = Long.parseLong(fields[6]);
                Operation operation = Operation.createOperation(type, accountId, amount, date, desc, categoryId);
                operation.setId(id);
                operations.add(operation);
            }
        }
        return operations;
    }
}
