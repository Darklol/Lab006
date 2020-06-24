package Commands;

import App.Receiver;

/**
 *  Команда print_field_ascending_description
 */
public class PrintAscendingDescCommand extends Command {

    public PrintAscendingDescCommand(Receiver receiver) {
        super(receiver);
    }

    @Override
    public String execute(String[] arguments) {
        if (arguments.length > needArguments()) return "Слишком много аргументов! Лишние " +
                "аргументы будут проигнорированы.";
        return receiver.printFieldAscendDesc();
    }

    @Override
    public int needArguments() {
        return 0;
    }

    @Override
    public String manual() {
        return "Вывести значения всех элементов поля description в порядке возрастания.";
    }

    @Override
    public String commandName() {
        return "print_field_ascending_description";
    }
}
