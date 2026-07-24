package network;

import config.AgentConfig;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerConnection {
    private AgentConfig config;
    private Socket socket;
    private PrintWriter output;
    private BufferedReader input;
    private boolean connected;

    public boolean connect() {
        config.getServerAddress();
        config.getServerPort();
        config.getConnectionTimeout();

        
    }

    public void disconnect() {

    }

    public boolean isConnected() {

    }

    public void send(String message) {

    }

    public String receive() {

    }

}
