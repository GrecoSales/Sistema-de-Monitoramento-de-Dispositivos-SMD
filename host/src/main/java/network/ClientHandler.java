package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ClientHandler {
    private Socket socket;
    private BufferedReader output;

    public ClientHandler(Socket socket) {
        this.socket = socket;

        try {
            output = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        }
        catch (IOException error) {
            error.printStackTrace();
        }
    }

    public String receive() {
        try {
            return output.readLine();
        }
        catch (IOException error) {
            error.printStackTrace();
            return null;
        }
    }

    public void disconnect() {
        try {
            if (socket != null) {
                socket.close();
            }
        }
        catch (IOException error) {
            error.printStackTrace();
        }
    }

    public boolean isConnected() {
        return socket != null && socket.isConnected();
    }
}
