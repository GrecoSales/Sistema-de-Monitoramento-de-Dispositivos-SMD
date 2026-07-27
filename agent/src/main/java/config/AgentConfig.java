package config;

public class AgentConfig {
    private String serverAddress;
    private int serverPort;
    private int discoveryPort;
    private int collectionInterval;
    private int reconnectInterval;
    private int connectionTimeout;

    public AgentConfig(String serverAddress, int serverPort, int collectionInterval, int reconnectInterval, int connectionTimeout) {
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
        this.collectionInterval = collectionInterval;
        this.reconnectInterval = reconnectInterval;
        this.connectionTimeout = connectionTimeout;
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

    public int getCollectionInterval() {
        return collectionInterval;
    }

    public int getReconnectInterval() {
        return reconnectInterval;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }
}
