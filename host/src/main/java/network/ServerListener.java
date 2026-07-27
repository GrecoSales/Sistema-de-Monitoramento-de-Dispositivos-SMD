package network;

import config.HostConfig;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListener {
    private HostConfig config;
    private ServerSocket serverSocket;
    private boolean running;

    public ServerListener (HostConfig config) {
        this.config = config;
    }

    public void Start() {
        try {
            serverSocket = new ServerSocket(config.getServerPort());

            running = true;

            while (running) {
                Socket client = serverSocket.accept();

                System.out.println("Novo agente conectado: " + client.getInetAddress().getHostAddress());
            }
        }
        catch (IOException error) {
            error.printStackTrace();
        }
    }
}