package network;

import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.io.IOException;

public class ServerDiscovery {
    private byte[] buffer = new byte[256];
    private byte[] request;

    public void Search () {
        try (DatagramSocket socket = new DatagramSocket();) {
            socket.setBroadcast(true);

            request = "SMGD_DISCOVERY".getBytes(StandardCharsets.UTF_8);

            InetAddress broadcast = InetAddress.getByName("255.255.255.255");

            DatagramPacket discoverTCP = new DatagramPacket(request, request.length, broadcast, 000);

            socket.send(discoverTCP);

            DatagramPacket messageReceived = new DatagramPacket(buffer, buffer.length);

            socket.receive(messageReceived);

            String converted = new String (messageReceived.getData(), 0, messageReceived.getLength(), StandardCharsets.UTF_8);

            String[] data = converted.split(";");

            String serverIP = data[0];
            int serverPort = Integer.parseInt(data[1]);

            System.out.println("Servidor encontrado!");
            System.out.println("IP: " + serverIP);
            System.out.println("Porta TCP: " + serverPort);
        }
        catch (IOException error) {
            error.printStackTrace();
        }
    }

    public static void main(String[] args) {

    ServerDiscovery discovery = new ServerDiscovery();

    discovery.Search();
    }
}
