import collector.DeviceCollector;
import model.Device;

public class MainAgent {
    public static void main(String[] args) {
        Device device = DeviceCollector.collect();
    }
}