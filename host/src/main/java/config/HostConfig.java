package config;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class HostConfig {
    private String serverAddress;
    private int serverPort = 50505; // Porta para conexão TCP
    private int discoveryPort = 9000; // Porta para conexão UDP
    private int maxClients = 10;
    private int connectionTimeout = 5000;
    
    public HostConfig() {
        try {
            serverAddress = InetAddress.getLocalHost().getHostAddress();
        }
        catch (UnknownHostException error) {
            serverAddress = ("desconhecido");
        }
    }

    public String getServerAddress() {
        return serverAddress;
    }

    public int getServerPort() {
        return serverPort;
    }

    public int getDiscoveryPort() {
        return discoveryPort;
    }

    public int getMaxClients() {
        return maxClients;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }
}