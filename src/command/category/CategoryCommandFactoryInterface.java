package command.category;

import command.Command;

public interface CategoryCommandFactoryInterface {
    Command newCreateCategoryCommand();
    Command newDeleteCategoryCommand();
    Command newUpdateCategoryCommand();
    Command newFindCategoryCommand();
    Command newListCategoryCommand();
}
