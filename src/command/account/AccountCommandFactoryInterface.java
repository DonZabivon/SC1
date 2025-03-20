package command.account;

import command.Command;

public interface AccountCommandFactoryInterface {
    Command newCreateAccountCommand();
    Command newDeleteAccountCommand();
    Command newFindAccountCommand();
    Command newUpdateAccountCommand();
    Command newListAccountCommand();
}
