package command.operation;

import command.Command;
import service.OperationService;

import java.util.function.LongSupplier;

public class DeleteOperationCommand implements Command {
    private final OperationService operationService;
    private final LongSupplier idSupplier;

    public DeleteOperationCommand(OperationService operationService, LongSupplier idSupplier) {
        this.operationService = operationService;
        this.idSupplier = idSupplier;
    }

    @Override
    public void execute() throws Exception {
        operationService.delete(idSupplier.getAsLong());
    }
}
