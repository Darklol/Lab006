package Commands;

import App.Receiver;

/**
 *  Команда info
 */
public class InfoCommand extends Command {
    public InfoCommand(){}
    public InfoCommand(Receiver receiver) {
        super(receiver);
    }

    @Override
    public String execute(String[] arguments) {
        if (arguments.length > needArguments()) return "Слишком много аргументов! Лишние " +
                "аргументы будут проигнорированы.";
        return receiver.info();
    }

    @Override
    public int needArguments() {
        return 0;
    }

    @Override
    public String manual() {
        return "Вывести информацию о коллекции.";
    }

    @Override
    public String commandName() {
        return "info";
    }
}
