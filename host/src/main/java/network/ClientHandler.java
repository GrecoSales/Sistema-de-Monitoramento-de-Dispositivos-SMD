package network;

import config.HostConfig;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler {
    private HostConfig config;
    private Socket socket;
    private PrintWriter input;
    private BufferedReader output;
    private boolean connected;

    public boolean connect () {
        

        return connected =  true;
    }

}
