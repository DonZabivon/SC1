package command.category;

import command.Command;
import model.Category;
import service.CategoryService;

import java.util.function.Consumer;
import java.util.function.LongSupplier;

public class FindCategoryCommand implements Command {
    private final CategoryService categoryService;
    private final LongSupplier idSupplier;
    private final Consumer<Category> categoryConsumer;

    public FindCategoryCommand(CategoryService categoryService, LongSupplier idSupplier, Consumer<Category> categoryConsumer) {
        this.categoryService = categoryService;
        this.idSupplier = idSupplier;
        this.categoryConsumer = categoryConsumer;
    }

    @Override
    public void execute() throws Exception {
        Category category = categoryService.findById(idSupplier.getAsLong());
        categoryConsumer.accept(category);
    }
}
