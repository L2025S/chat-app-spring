package se.sprinto.hakan.chatapp;

import org.springframework.stereotype.Component;
import se.sprinto.hakan.chatapp.service.UserService;
import se.sprinto.hakan.chatapp.service.MessageService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

@Component
public class ChatServer {

    private final UserService userService;
    private final MessageService messageService;

    private final List<ClientHandler> clients = new ArrayList<>();

    public ChatServer(UserService userService, MessageService messageService) {
        this.userService = userService;
        this.messageService = messageService;
        startServer();
    }

    private void startServer() {
        new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(5555)) {
                System.out.println("Server startad på port 5555...");

                while (true) {
                    Socket socket = serverSocket.accept();
                    ClientHandler handler =
                            new ClientHandler(socket, this, userService, messageService);
                    clients.add(handler);
                    new Thread(handler).start();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void broadcast(String message, ClientHandler from) {
        for (ClientHandler client : clients) {
            if (client != from) {
                client.sendMessage(from.getUser().getUsername() + ": " + message);
            }
        }
    }

    public void removeClient(ClientHandler c) {
        clients.remove(c);
    }
}
