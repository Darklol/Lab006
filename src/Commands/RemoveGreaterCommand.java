package Commands;

import App.Receiver;

/**
 *  Команда remove_if_greater
 */
public class RemoveGreaterCommand extends Command {
    public RemoveGreaterCommand(){}
    public RemoveGreaterCommand(Receiver receiver){
        super(receiver);
    }

    @Override
    public String execute(String[] arguments) {
        if (arguments.length<needArguments()) return "У команды должны быть аргументы!";
        try {
            return receiver.removeGreater((Long.parseLong(arguments[0])));
        } catch (IllegalArgumentException e) {

        }
        return "Неправильный ввод аргумента!";
    }



    @Override
    public int needArguments() {
        return 1;
    }

    @Override
    public String manual() {
        return "Удалить из коллекции все элементы, превышающие заданный.";
    }

    @Override
    public String commandName() {
        return "remove_greater";
    }

}
