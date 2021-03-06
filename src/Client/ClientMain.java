package Client;

import java.io.IOException;

public class ClientMain {
    private static Client activeClient = null;

    public static Client getActiveClient() {
        return activeClient;
    }

    public static void main(String[] args) {
        try {
            if (args.length < 2) throw new IllegalArgumentException();
            String host = args[0];
            int port = Integer.parseInt(args[1]);
            Client client = new Client();
            activeClient = client;
            client.connect(host, port);
            while (true){
                client.inputSendAndGetAnswer();
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Запускайте клиента с двумя аргументами: <host> <port>");
        } catch (IOException e) {
            System.out.println("Проблемы с подключением, проверьте корректны ли аргументы программы...");
            e.printStackTrace();
        }
    }

}
