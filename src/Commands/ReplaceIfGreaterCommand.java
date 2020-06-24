package Commands;

import App.Receiver;

/**
 *  Команда replace_if_greater
 */
public class ReplaceIfGreaterCommand extends Command {
    public ReplaceIfGreaterCommand(){}
    public ReplaceIfGreaterCommand(Receiver receiver) {
        super(receiver);
    }

    @Override
    public String execute(String[] arguments) {
        if (arguments.length > needArguments()) return "Слишком много аргументов! Аргументы будут проигнорированы.";
        try {
            return receiver.replaceGreater(Long.parseLong(arguments[0]));
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
        return "Заменить значение по ключу, если новое значение больше старого.";
    }

    @Override
    public String commandName() {
        return "replace_if_greater";
    }
}
