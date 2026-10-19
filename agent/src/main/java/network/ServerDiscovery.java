package network;

import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.nio.charset.StandardCharsets;
import java.io.IOException;

public class ServerDiscovery {
    private byte[] buffer = new byte[256];
    private byte[] request;

    String serverIP;
    int serverPort;
    int intervalConnection;

    public String getServerIP() {
        return serverIP;
    }

    public int getServerPort() {
        return serverPort;
    }

    public int getIntervalConnection() {
        return intervalConnection;
    }

    public boolean search () {
        try (DatagramSocket socket = new DatagramSocket();) {
            socket.setBroadcast(true);
            socket.setSoTimeout(3000);

            request = "SMGD_DISCOVERY".getBytes(StandardCharsets.UTF_8);

            InetAddress broadcast = InetAddress.getByName("255.255.255.255");

            DatagramPacket discoverTCP = new DatagramPacket(request, request.length, broadcast, 9000);

            socket.send(discoverTCP);

            DatagramPacket messageReceived = new DatagramPacket(buffer, buffer.length);

            socket.receive(messageReceived);

            String converted = new String (messageReceived.getData(), 0, messageReceived.getLength(), StandardCharsets.UTF_8);

            String[] data = converted.split(";");

            serverIP = data[0];
            serverPort = Integer.parseInt(data[1]);
            intervalConnection = Integer.parseInt(data[2]);

            System.out.println("Servidor encontrado!");
            System.out.println("IP: " + serverIP);
            System.out.println("Porta TCP: " + serverPort);

            return true;
        }
        catch (SocketTimeoutException error) {
            System.out.println("Nenhum servidor encontrado.");;
            return false;
        }
        catch (IOException error) {
            error.printStackTrace();
            return false;
        }
    }
}
