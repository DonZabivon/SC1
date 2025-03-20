package command.category;

import command.Command;
import service.CategoryService;

import java.util.function.LongSupplier;

public class DeleteCategoryCommand implements Command {
    private final CategoryService categoryService;
    private final LongSupplier idSupplier;

    public DeleteCategoryCommand(CategoryService categoryService, LongSupplier idSupplier) {
        this.categoryService = categoryService;
        this.idSupplier = idSupplier;
    }

    @Override
    public void execute() throws Exception {
        categoryService.delete(idSupplier.getAsLong());
    }
}
