package Commands;

import App.Receiver;

/**
 *  Команда clear
 */
public class ClearCommand extends Command {
    public ClearCommand(){}
    public ClearCommand(Receiver receiver, String[] arguments) {
        super(receiver, arguments);
    }

    @Override
    public String execute() {
        if (arguments.length > needArguments()) return "Слишком много аргументов! Лишние " +
                "аргументы будут проигнорированы.";
        return receiver.clear();
    }

    @Override
    public int needArguments() {
        return 0;
    }

    @Override
    public String manual() {
        return "Очистить коллекцию.";
    }

    @Override
    public String commandName() {
        return "clear";
    }
}
