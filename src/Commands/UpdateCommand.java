package Commands;

import App.Receiver;

/**
 *  Команда update
 */
public class UpdateCommand extends Command {

    public UpdateCommand(Receiver receiver) {
        super(receiver);
    }

    @Override
    public String execute(String[] arguments) {
        if (arguments.length > needArguments()) return "Слишком много аргументов! Аргументы будут проигнорированы.";
        try {
            return receiver.update(Long.parseLong(arguments[0]));
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
        return "Обновить значение элемента, ID которого равен заданному.";
    }

    @Override
    public String commandName() {
        return "update";
    }
}
