package network;

import config.HostConfig;

import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;
import java.io.IOException;

public class DiscoveryResponder {
    private HostConfig config;
    private byte[] buffer = new byte[256];
    private byte[] response;

    public DiscoveryResponder (HostConfig config) {
        this.config = config;

        response = (config.getServerAddress() + ";" + config.getServerPort() + ";" +config.getIntervalConnection()).getBytes(StandardCharsets.UTF_8);
    }

    public void start () {
        try {
            DatagramSocket socket = new DatagramSocket(config.getDiscoveryPort());

            System.out.println("Esperando conexão...");

            DatagramPacket messageReceived = new DatagramPacket(buffer, buffer.length);

            socket.receive(messageReceived);

            InetAddress guestAddress = messageReceived.getAddress();
            int guestPort = messageReceived.getPort();

            DatagramPacket messageForSend = new DatagramPacket(response, response.length, guestAddress, guestPort);

            socket.send(messageForSend);

            socket.close();
        }
        catch (IOException error) {
            error.printStackTrace();
        }
    }
}
