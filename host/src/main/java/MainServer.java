import config.HostConfig;
import network.*;

import java.net.Socket;

public class MainServer {
    public static void main(String[] args) {
        HostConfig config = new HostConfig();

        DiscoveryResponder discovery = new DiscoveryResponder(config);
        discovery.start();

        ServerListener listener = new ServerListener(config);
        listener.start();

        Socket socket = listener.waitConnection();

        ClientHandler client = new ClientHandler(socket);

        while (client.isConnected()) {
            System.out.println(client.receive());
        }
    }
}