package Server;

import App.Receiver;
import Commands.*;

import java.util.HashMap;

public class RegisteredCommands {

    private HashMap<String, Command> commandsName = new HashMap<String,Command>();
    private Receiver receiver;
    /**
     * Метод, инициализирующий коллекцию HashMap, с ключом - командой в строковом представлении,
     * и со значением - экземпляр команды
     */
    public RegisteredCommands(){
        addCommandsNames();
    }



    private void addCommandsNames(){
        commandsName.put(new HelpCommand().commandName(), new HelpCommand());
        commandsName.put(new InfoCommand().commandName(),new InfoCommand());
        commandsName.put(new ShowCommand().commandName(),new ShowCommand());
        commandsName.put(new InsertCommand().commandName(),new InsertCommand());
        commandsName.put(new UpdateCommand().commandName(), new UpdateCommand());
        commandsName.put(new RemoveKeyCommand().commandName(), new RemoveKeyCommand());
        commandsName.put(new ClearCommand().commandName(),new ClearCommand());
//        commandsName.put(new ExecuteScriptCommand().commandName(), new ExecuteScriptCommand());
        commandsName.put(new RemoveGreaterKeyCommand().commandName(),new RemoveGreaterKeyCommand());
        commandsName.put(new ReplaceIfGreaterCommand().commandName(),new ReplaceIfGreaterCommand());
        commandsName.put(new RemoveGreaterCommand().commandName(),new RemoveGreaterCommand());
        commandsName.put(new MinByNameCommand().commandName(),new MinByNameCommand());
        commandsName.put(new PrintUniqueKillerCommand().commandName(),new PrintUniqueKillerCommand());
        commandsName.put(new PrintAscendingDescCommand().commandName(),new PrintAscendingDescCommand());
    }

    public HashMap<String, Command> getCommandsName() {
        return commandsName;
    }
}
