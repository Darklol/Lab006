package Server;

import App.Receiver;
import App.Response;
import App.SerializationManager;
import Client.Request;
import Commands.*;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * По паттерну "команда"
 * Класс, вызывающий команды
 */
public class Server {

    private DatagramChannel channel;
    private SocketAddress address;
    private byte[] buffer;
    private Receiver receiver;
    private RegisteredCommands commands;
    public final int BUFFER_SIZE = 65536;

    /**
     * стандартный конструктор, устанавливающий экземпляр ресивера и инициализирующий коллекцию команд
     * @param receiver
     */
    public Server(Receiver receiver){
        this.receiver = receiver;
        commands = new RegisteredCommands();
        buffer = new byte[BUFFER_SIZE];
    }

    public void connect(int port) throws IOException {
        address = new InetSocketAddress(port);
        channel = DatagramChannel.open();
        channel.configureBlocking(false);
        channel.bind(address);
    }
    /**
     * пустой конструктор, нужен для работы команды help
     */
    public Server(){

    };

    /**
     * Метод, использующийся для работы команды execute_script
     *
     */
//    public void execute(String commandLine){
//        String[] input = commandLine.split("\\s+");
//        String[] arguments = new String[input.length-1];
//        System.arraycopy(input, 1, arguments, 0, arguments.length);
//        if (commandsName.containsKey(input[0])){
//            commandsName.get(input[0]).execute(arguments);
//        } else {
//            System.out.println("Такой команды не существует. Проверьте правильность ввода команды в скрипте.");
//        }
//
//    }

    public void run() {
        try {
            while (true) {
                ByteBuffer byteBuffer = ByteBuffer.wrap(buffer);
                do {
                    address = channel.receive(byteBuffer);
                } while (address == null);
                Request request = SerializationManager.readObject(buffer);
                System.out.println("Сервер получил команду " + request.getCommand().commandName());
                Command command = request.getCommand();
                command.setReceiver(receiver);
                String result = command.execute(request.getArguments());
                System.out.println("Команда " + command + " выполнена, посылаю ответ клиенту...");
                Response response = new Response(result);
                byte[] answer = SerializationManager.writeObject(response);
                byteBuffer = ByteBuffer.wrap(answer);
                channel.send(byteBuffer, address);
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Сервер ожидает команду, а клиент отправляет нечто неизвестное...");
        } catch (IOException e) {
            System.out.println("Проблемы с подключением...");
            e.printStackTrace();
        } catch (ClassCastException e) {
            System.out.println("Сервер ожидал команду, а получил что-то не то...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String inputResult(String[] input){
        System.out.println("Введите команду:");
        String[] arguments = new String[input.length-1];
        System.arraycopy(input, 1, arguments, 0, arguments.length);
        if (!commands.getCommandsName().containsKey(input[0])) return "Такой команды не существует!";
        return commands.getCommandsName().get(input[0]).execute(arguments);
    }


}
