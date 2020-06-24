package Commands;

import App.Receiver;

/**
 *  Команда help
 */
public class HelpCommand extends Command {
    public HelpCommand(){}
    public HelpCommand(Receiver receiver) {
        super(receiver);
    }

    @Override
    public String execute(String[] arguments) {
        if (arguments.length > needArguments()) return "Слишком много аргументов! Лишние " +
                "аргументы будут проигнорированы.";
        return receiver.help();
    }

    @Override
    public int needArguments() {
        return 0;
    }

    @Override
    public String manual() {
        return "Вывести справку по доступным командам.";
    }

    @Override
    public String commandName() {
        return "help";
    }
}
