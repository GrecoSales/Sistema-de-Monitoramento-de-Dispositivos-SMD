package collector;

import model.Device;
import model.Drive;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import oshi.spi.SystemInfoFactory;
import oshi.spi.SystemInfoProvider;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.FileSystem;
import oshi.software.os.OSFileStore;
import oshi.software.os.OperatingSystem;

public class DeviceCollector {
    public static Device collect() {
        SystemInfoProvider si = SystemInfoFactory.create();
        HardwareAbstractionLayer hal = si.getHardware();
        OperatingSystem os = si.getOperatingSystem();

        GlobalMemory mem = hal.getMemory();
        FileSystem fs = os.getFileSystem();

        Device device = new Device();

        try {
            device.setDeviceName(InetAddress.getLocalHost().getHostName());
        }
        catch (UnknownHostException error) {
            device.setDeviceName("Desconhecido");
        }

        device.setOperatingSystem(os.getFamily());
        device.setProcessorName(hal.getProcessor().getProcessorIdentifier().getName());
        device.setCpuLoad(hal.getProcessor().getSystemCpuLoad(1000L) * 100);
        device.setCpuFrequency(hal.getProcessor().getMaxFreq() / 1_000_000_000.0);
        device.setCpuTemperature(hal.getSensors().getCpuTemperature());
        device.setRamTotal(mem.getTotal());
        device.setRamUsed(mem.getTotal() - mem.getAvailable());

        List <Drive> drives = new ArrayList<>();

        for (OSFileStore fileStore : fs.getFileStores()) {
            Drive drive = new Drive();

            drive.setName(fileStore.getName());
            drive.setTotalSpace(fileStore.getTotalSpace());
            drive.setUsedSpace(fileStore.getTotalSpace() - fileStore.getFreeSpace());

            drives.add(drive);
        }

        device.setDrives(drives);

        return device;
    }
}
