package network;

import config.HostConfig;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListener {

    private HostConfig config;
    private ServerSocket serverSocket;
    private boolean running;

    public ServerListener(HostConfig config) {
        this.config = config;
    }

    public void start() {
        try {
            serverSocket = new ServerSocket(config.getServerPort());

            running = true;

            System.out.println("Servidor TCP iniciado.");
        }
        catch (IOException error) {
            error.printStackTrace();
        }
    }

    public Socket waitConnection() {
        try {
            Socket client = serverSocket.accept();

            System.out.println("Novo agente conectado: " + client.getInetAddress().getHostAddress());

            return client;
        }
        catch (IOException error) {
            error.printStackTrace();
            return null;
        }
    }

    public boolean isRunning() {
        return running;
    }
}