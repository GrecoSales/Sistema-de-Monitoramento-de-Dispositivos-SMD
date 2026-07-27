package network;

import config.AgentConfig;
import network.ServerDiscovery;

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
