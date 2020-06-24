package Client;

import Commands.Command;

import java.io.Serializable;

public class Request implements Serializable {
    private String[] arguments;
    private Command command;

    public Request(Command command, String[] arguments){
        this.command = command;
        this.arguments = arguments;
    }
}
