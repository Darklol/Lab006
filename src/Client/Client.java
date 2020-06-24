package Client;

import App.Receiver;
import App.Response;
import App.SerializationManager;
import Server.RegisteredCommands;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * По паттерну "команда"
 * Класс, вызывающий команды
 */
public class Client {

    private Scanner scanner = new Scanner(System.in);
    private Receiver receiver;
    private RegisteredCommands commands;
    private ByteBuffer byteBuffer;
    private DatagramChannel channel;
    private SocketAddress address;
    public static int BUFFER_SIZE = 65536;

    /**
     * стандартный конструктор, устанавливающий экземпляр ресивера и инициализирующий коллекцию команд
     * @param
     */
    public Client(){
        commands = new RegisteredCommands();
    }

    public void connect(String host, int port) {
        address = new InetSocketAddress(host, port);
        try {
            channel = DatagramChannel.open();
            channel.configureBlocking(false);
            channel.connect(address);
        } catch (IOException e) {
            System.out.println("Ошибка подключения");
        }
    }

    public void inputSendAndGetAnswer() throws IOException {
        System.out.println("Введите команду:");
        String string = null;
        try {
            string = scanner.nextLine().trim();
        } catch (NoSuchElementException e) {
            System.out.println("Why are you so cruel...");
            System.exit(0);
        }
        String[] input = string.split("\\s+");
        String[] arguments = new String[input.length-1];
        System.arraycopy(input, 1, arguments, 0, arguments.length);
        if (input[0].equals("exit")) {
            System.out.println("Завершение работы клиента.");
            System.exit(0);
        }
        if (!commands.getCommandsName().containsKey(input[0])){
            System.out.println("Такой команды не существует. Проверьте правильность ввода команды.");
            return;}
        Request request = new Request(commands.getCommandsName().get(input[0]), arguments);
        byte[] requestInBytes = SerializationManager.writeObject(request);
        byteBuffer = ByteBuffer.wrap(requestInBytes);
        byteBuffer.flip();
        channel.send(byteBuffer, address);
        byteBuffer.clear();
        address = channel.receive(byteBuffer);
        byte[] responseInBytes = new byte[byteBuffer.remaining()];
        byteBuffer.get(requestInBytes);
        System.out.println("Data Received");
        try {
            Response response = SerializationManager.readObject(responseInBytes);
            System.out.println(response.getAnswer());
        } catch (ClassNotFoundException e) {
            System.out.println("Что-то пошло не так.");
        }

    }

    /**
     * Метод, использующийся для работы команды execute_script
     * @param commandLine
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


}
