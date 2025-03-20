package command.category;

import command.Command;
import model.BankAccount;
import model.Category;
import service.CategoryService;

import java.util.function.LongSupplier;
import java.util.function.Supplier;

public class UpdateCategoryCommand implements Command {
    private final CategoryService categoryService;
    private final LongSupplier idSupplier;
    private final Supplier<String> nameSupplier;

    public UpdateCategoryCommand(CategoryService categoryService, LongSupplier idSupplier, Supplier<String> nameSupplier) {
        this.categoryService = categoryService;
        this.idSupplier = idSupplier;
        this.nameSupplier = nameSupplier;
    }

    @Override
    public void execute() throws Exception {
        long id = idSupplier.getAsLong();
        Category category = categoryService.findById(id);
        String name = nameSupplier.get();
        category.setName(name);
        categoryService.update(category);
    }
}
