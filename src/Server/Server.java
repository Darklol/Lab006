package Server;

import App.Receiver;
import App.SerializationManager;
import com.google.gson.internal.$Gson$Preconditions;

import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class Server {
        private DatagramChannel channel;
        private SocketAddress address;
        private Receiver receiver;
        private byte[] buffer;

    public void run() {
        try {
            while (true) {
                ByteBuffer byteBuffer = ByteBuffer.wrap(buffer);
                do {
                    address = channel.receive(byteBuffer);
                } while (address == null);
                String[] input = SerializationManager.readObject(buffer);
                System.out.println("Сервер получил данные ввода " + input);
                String result = processCommand(application, command);
                log.info("Server receive command " + command.toString());
                System.out.println("Команда " + command + " выполнена, посылаю ответ клиенту...");
                log.info("Command " + command.toString() + " is completed, send an answer to the client");
                Response response = new Response(result);
                byte[] answer = responseSerializationManager.writeObject(response);
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
}
