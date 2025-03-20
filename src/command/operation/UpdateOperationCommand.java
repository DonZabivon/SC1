package command.operation;

import command.Command;
import model.Operation;
import service.OperationService;

import java.util.function.LongSupplier;
import java.util.function.Supplier;

public class UpdateOperationCommand implements Command {
    private final OperationService operationService;
    private final LongSupplier idSupplier;
    private final Supplier<String> descriptionSupplier;

    public UpdateOperationCommand(OperationService operationService, LongSupplier idSupplier, Supplier<String> descriptionSupplier) {
        this.operationService = operationService;
        this.idSupplier = idSupplier;
        this.descriptionSupplier = descriptionSupplier;
    }

    @Override
    public void execute() throws Exception {
        long id = idSupplier.getAsLong();
        Operation operation = operationService.findById(id);
        operation.setDescription(descriptionSupplier.get());
        operationService.update(operation);
    }
}
