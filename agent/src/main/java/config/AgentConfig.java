package config;

public class AgentConfig {
    private String serverAddress;
    private int serverPort;
    private int discoveryPort;
    private int collectionInterval;
    private int reconnectInterval;
    private int connectionTimeout;

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
