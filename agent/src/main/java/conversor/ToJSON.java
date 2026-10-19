package conversor;

import model.Device;
import model.Drive;

public class ToJSON {

    public static String convert(Device device) {

        StringBuilder message = new StringBuilder();

        message.append("=== DADOS DO DISPOSITIVO ===\n\n");

        message.append("Nome do dispositivo: ")
               .append(device.getDeviceName())
               .append("\n");

        message.append("Sistema operacional: ")
               .append(device.getOperatingSystem())
               .append("\n");

        message.append("Processador: ")
               .append(device.getProcessorName())
               .append("\n");

        message.append("Uso da CPU: ")
               .append(String.format("%.1f%%", device.getCpuLoad()))
               .append("\n");

        message.append("Frequência da CPU: ")
               .append(String.format("%.2f GHz", device.getCpuFrequency()))
               .append("\n");

        message.append("Temperatura da CPU: ")
               .append(String.format("%.1f °C", device.getCpuTemperature()))
               .append("\n");

        message.append("RAM Total: ")
               .append(String.format("%.2f GB", bytesToGB(device.getRamTotal())))
               .append("\n");

        message.append("RAM em uso: ")
               .append(String.format("%.2f GB", bytesToGB(device.getRamUsed())))
               .append("\n");

        message.append("\n=== UNIDADES DE ARMAZENAMENTO ===\n");

        for (Drive drive : device.getDrives()) {

            message.append("\nUnidade: ")
                   .append(drive.getName())
                   .append("\n");

            message.append("Espaço total: ")
                   .append(String.format("%.2f GB", bytesToGB(drive.getTotalSpace())))
                   .append("\n");

            message.append("Espaço utilizado: ")
                   .append(String.format("%.2f GB", bytesToGB(drive.getUsedSpace())))
                   .append("\n");
        }

        return message.toString();
    }

    private static double bytesToGB(long bytes) {
        return bytes / 1024.0 / 1024.0 / 1024.0;
    }
}