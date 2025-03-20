package decorator;

import command.Command;

public class StatCommand implements Command {
    private final Command command;

    public StatCommand(Command command) {
        this.command = command;
    }

    @Override
    public void execute() throws Exception {
        long startTime = System.currentTimeMillis();
        command.execute();
        long endTime = System.currentTimeMillis();
        double seconds = (endTime - startTime) / 1000.0;
        System.out.printf("Время выполнения: %.2f сек%n", seconds);
    }
}
