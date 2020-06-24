package Commands;

import App.Receiver;

/**
 *  Команда print_unique_killer
 */
public class PrintUniqueKillerCommand extends Command {

    public PrintUniqueKillerCommand(Receiver receiver) {
        super(receiver);
    }

    @Override
    public String execute(String[] arguments) {
        if (arguments.length > needArguments()) return "Слишком много аргументов! Лишние " +
                "аргументы будут проигнорированы.";
        return receiver.printUniqueKiller();
    }

    @Override
    public int needArguments() {
        return 0;
    }

    @Override
    public String manual() {
        return "Вывести уникальные значения поля killer всех элементов коллекции.";
    }

    @Override
    public String commandName() {
        return "print_unique_killer";
    }
}
