package command.category;

import command.Command;
import model.BankAccount;
import model.Category;
import service.CategoryService;

import java.util.List;
import java.util.function.Consumer;

public class ListCategoryCommand implements Command {
    private final CategoryService categoryService;
    private final Consumer<List<Category>> categoryConsumer;

    public ListCategoryCommand(CategoryService categoryService, Consumer<List<Category>> categoryConsumer) {
        this.categoryService = categoryService;
        this.categoryConsumer = categoryConsumer;
    }

    @Override
    public void execute() throws Exception {
        List<Category> categoryList = categoryService.getAll();
        categoryConsumer.accept(categoryList);
    }
}
