package network;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerConnection {
    private Socket socket;
    private PrintWriter output;
    private final ServerDiscovery discovery;
    private String message;

    public void setMessage(String message) {
        this.message = message;
    }

    public ServerConnection() {
        this.discovery = new ServerDiscovery();
    }

    public boolean connect() {

        while (!Thread.currentThread().isInterrupted()) {

            boolean found = discovery.search();

            if (found) {
                String hostAddress = discovery.getServerIP();
                int hostPort = discovery.getServerPort();

                try {
                    socket = new Socket(hostAddress, hostPort);

                    output = new PrintWriter(socket.getOutputStream(), true);

                    System.out.println("Conexão TCP estabelecida!");
                    return true;
                }
                catch (IOException error) {
                    System.out.println("Não foi possível conectar ao Host.");
                    disconnect();
                }
            }

            System.out.println("Tentando novamente...");

            try {
            Thread.sleep(3000);
            }
            catch (InterruptedException error) {
                error.printStackTrace();
            }
        }
        return false;
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

    public void waitInterval() {
        try {
            Thread.sleep(discovery.getIntervalConnection());
        }
        catch (InterruptedException error) {
            error.printStackTrace();
        }
    }

    public void send() {
        output.println(message);
    }
}
