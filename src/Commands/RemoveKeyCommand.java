package Commands;

import App.Receiver;

/**
 *  Команда remove_key
 */
public class RemoveKeyCommand extends Command {
    public RemoveKeyCommand(){}
    public RemoveKeyCommand(Receiver receiver){
        super(receiver);
    }

    @Override
    public String execute(String[] arguments) {
        if (arguments.length > needArguments()) return "Слишком много аргументов! Аргументы будут проигнорированы.";
        try {
            return receiver.remove(Long.parseLong(arguments[0]));
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
        return "Удалить элемент коллекции по его ключу.";
    }

    @Override
    public String commandName() {
        return "remove_key";
    }
}
