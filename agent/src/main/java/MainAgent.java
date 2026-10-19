import collector.DeviceCollector;
import model.Device;
import network.ServerConnection;
import conversor.ToJSON;

public class MainAgent {
    public static void main(String[] args) {
        ServerConnection connection = new ServerConnection();
        
        connection.connect();
        
        while (connection.isConnected()) {
            Device device = DeviceCollector.collect();
            String message = ToJSON.convert(device);

            connection.setMessage(message);
            connection.send();

            connection.waitInterval();
        }

        connection.disconnect();
    }
}