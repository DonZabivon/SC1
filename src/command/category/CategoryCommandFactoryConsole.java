package command.category;

import command.Command;
import model.BankAccount;
import model.Category;
import model.Type;
import service.CategoryService;

import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.LongSupplier;
import java.util.function.Supplier;

public class CategoryCommandFactoryConsole implements CategoryCommandFactoryInterface {
    private final Scanner scanner;
    private final CategoryService categoryService;
    private LongSupplier idSupplier;
    private Supplier<Type> typeSupplier;
    private Supplier<String> nameSupplier;
    private Consumer<Category> categoryPrinter;
    private Consumer<List<Category>> listPrinter;

    public CategoryCommandFactoryConsole(Scanner scanner, CategoryService categoryService) {
        this.scanner = scanner;
        this.categoryService = categoryService;

        idSupplier = () -> {
            System.out.print("Введите идентификатор категории: ");
            return Long.parseLong(scanner.nextLine());
        };

        typeSupplier = () -> {
            // TODO
            System.out.print("Введите тип категории (0 - ДОХОД, 1 - РАСХОД): ");
            int choice = Integer.parseInt(scanner.nextLine());
            if (choice == 0) {
                return Type.INCOME;
            }
            return Type.OUTCOME;
        };

        nameSupplier = () -> {
            System.out.print("Введите название категории: ");
            return scanner.nextLine();
        };

        categoryPrinter = category -> {
            if (category != null) {
                System.out.println(category);
            } else {
                System.out.println("Не найдено");
            }
        };

        listPrinter = categories -> {
            for (Category category: categories) {
                categoryPrinter.accept(category);
            }
        };
    }

    @Override
    public Command newCreateCategoryCommand() {
        return new CreateCategoryCommand(categoryService, typeSupplier, nameSupplier);
    }

    @Override
    public Command newDeleteCategoryCommand() {
        return new DeleteCategoryCommand(categoryService, idSupplier);
    }

    @Override
    public Command newUpdateCategoryCommand() {
        return new UpdateCategoryCommand(categoryService, idSupplier, nameSupplier);
    }

    @Override
    public Command newFindCategoryCommand() {
        return new FindCategoryCommand(categoryService, idSupplier, categoryPrinter);
    }

    @Override
    public Command newListCategoryCommand() {
        return new ListCategoryCommand(categoryService, listPrinter);
    }
}
