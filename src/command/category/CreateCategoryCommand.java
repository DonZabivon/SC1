package command.category;

import command.Command;
import model.Category;
import model.Type;
import service.CategoryService;


import java.util.function.Supplier;

public class CreateCategoryCommand implements Command {
    private final CategoryService categoryService;
    private final Supplier<Type> typeSupplier;
    private final Supplier<String> nameSupplier;

    public CreateCategoryCommand(CategoryService categoryService, Supplier<Type> typeSupplier, Supplier<String> nameSupplier) {
        this.categoryService = categoryService;
        this.typeSupplier = typeSupplier;
        this.nameSupplier = nameSupplier;
    }

    @Override
    public void execute() throws Exception {
        categoryService.create(Category.createCategory(typeSupplier.get(), nameSupplier.get()));
    }
}
