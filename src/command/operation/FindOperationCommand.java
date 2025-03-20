package command.operation;

import command.Command;
import model.Operation;
import service.OperationService;

import java.util.function.Consumer;
import java.util.function.LongSupplier;

public class FindOperationCommand implements Command {
    private final OperationService operationService;
    private final LongSupplier idSupplier;
    private final Consumer<Operation> operationConsumer;

    public FindOperationCommand(OperationService operationService, LongSupplier idSupplier, Consumer<Operation> operationConsumer) {
        this.operationService = operationService;
        this.idSupplier = idSupplier;
        this.operationConsumer = operationConsumer;
    }

    @Override
    public void execute() throws Exception {
        Operation operation = operationService.findById(idSupplier.getAsLong());
        operationConsumer.accept(operation);
    }
}
