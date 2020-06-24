package Commands;

import App.Receiver;

import java.io.IOException;

/**
 * Команда execute
 */
public class ExecuteScriptCommand extends Command{
    public ExecuteScriptCommand(Receiver receiver) {
        super(receiver);
    }

    @Override
    public String execute(String[] arguments) {
        if (arguments.length > needArguments()) return "Слишком много аргументов! Аргументы будут проигнорированы.";
                try {
                    return receiver.executeScript(arguments[0]);
                } catch (IOException e) {
                    System.out.println("Файл не найден!");
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
        return "Считать и исполнить скрипт из указанного файла.";
    }

    @Override
    public String commandName() {
        return "execute_script";
    }


}

