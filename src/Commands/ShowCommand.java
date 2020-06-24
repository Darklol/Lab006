package Commands;

import App.Receiver;

/**
 *  Команда show
 */
public class ShowCommand extends Command {

    public ShowCommand(Receiver receiver) {
        super(receiver);
    }

    @Override
    public String execute(String[] arguments) {
        if (arguments.length > needArguments()) return "Слишком много аргументов! Лишние " +
                "аргументы будут проигнорированы.";
        return receiver.show();
    }

    @Override
    public int needArguments() {
        return 0;
    }

    @Override
    public String manual() {
        return "Вывести все элементы коллекции в строковом представлении.";
    }

    @Override
    public String commandName() {
        return "show";
    }
}
