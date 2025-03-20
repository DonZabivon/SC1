package command.operation;

import command.Command;

public interface OperationCommandFactoryInterface {
    Command newCreateOperationCommand();
    Command newDeleteOperationCommand();
    Command newUpdateOperationCommand();
    Command newFindOperationCommand();
    Command newListOperationCommand();
}
