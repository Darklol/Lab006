package Commands;

import App.Receiver;

/**
 *  Команда remove_if_greater_key
 */
public class RemoveGreaterKeyCommand extends Command {

    public RemoveGreaterKeyCommand(Receiver receiver) {
        super(receiver);
    }

    @Override
    public String execute(String[] arguments) {
        if (arguments.length > needArguments()) return "Слишком много аргументов! Аргументы будут проигнорированы.";
        try {
            return receiver.removeGreaterKey(Long.parseLong(arguments[0]));
        } catch (IllegalArgumentException e) {
            System.out.println("Неправильный ввод аргумента!");
        }
        return "Неизвестная ошибка при попытке исполнения команды";
    }

    @Override
    public int needArguments() {
        return 1;
    }

    @Override
    public String manual() {
        return "Удалить из коллекции все элементы, ключ которых превышает заданный.";
    }

    @Override
    public String commandName() {
        return "remove_greater_key";
    }
}
