import oshi.spi.SystemInfoFactory;
import oshi.spi.SystemInfoProvider;

import java.util.List;

import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.FileSystem;
import oshi.software.os.OSFileStore;
import oshi.software.os.OperatingSystem;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class MainAgent {
    public static void main(String[] args) {
        SystemInfoProvider si = SystemInfoFactory.create();
        HardwareAbstractionLayer hal = si.getHardware();
        OperatingSystem os = si.getOperatingSystem();

        GlobalMemory mem = hal.getMemory();
        FileSystem fs = os.getFileSystem();

        String deviceName;
        try {
            deviceName = InetAddress.getLocalHost().getHostName();
        }
        catch (UnknownHostException error) {
            deviceName = "Desconhecido";
        }
        String opSystem = os.getFamily();
        String processorName = hal.getProcessor().getProcessorIdentifier().getName();
        double cpuLoad = hal.getProcessor().getSystemCpuLoad(1000L) * 100;
        double cpuFrequency = hal.getProcessor().getMaxFreq() / 1_000_000_000.0;
        double temperature = hal.getSensors().getCpuTemperature();
        long ramTotal = mem.getTotal();
        long ramUsed = ramTotal - mem.getAvailable();
        String driveName;
        long driveTotal;
        long driveUsed;

        System.out.println("Nome do dispositivo: " + deviceName);
        System.out.println("Sistema Operacional: " + opSystem);
        System.out.println("Processador: " + processorName);
        System.out.printf("Utilização da CPU: " + "%.0f%%%n", cpuLoad);
        System.out.printf("Frequência da CPU " + "%.2f GHz%n", cpuFrequency);
        if (temperature > 0) {
            System.out.printf("Temperatura: %.1f ºC%n", temperature);
        }
        else {
            System.out.println("Temperatura indisponível");
        }
        System.out.printf("RAM Total: %.1f GB%n", ramTotal / 1_000_000_000.0);
        System.out.printf("RAM utilizada: %.1f GB%n", ramUsed / 1_000_000_000.0);

        for (OSFileStore drive : fs.getFileStores()) {
            driveName = drive.getName();
            driveTotal = drive.getTotalSpace();
            driveUsed = driveTotal - drive.getFreeSpace();

            System.out.println(driveName);
            System.out.printf("Armazenamento total: %.1f GB%n", driveTotal / 1_000_000_000.0);
            System.out.printf("Armazenamento utilizado: %.1f GB%n", driveUsed / 1_000_000_000.0);
        }

    }
}