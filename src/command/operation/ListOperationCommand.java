package command.operation;

import command.Command;
import model.Operation;
import service.OperationService;

import java.util.List;
import java.util.function.Consumer;

public class ListOperationCommand implements Command {
    private final OperationService operationService;
    private final Consumer<List<Operation>> operationConsumer;

    public ListOperationCommand(OperationService operationService, Consumer<List<Operation>> operationConsumer) {
        this.operationService = operationService;
        this.operationConsumer = operationConsumer;
    }

    @Override
    public void execute() throws Exception {
        List<Operation> operationList = operationService.getAll();
        operationConsumer.accept(operationList);
    }
}
