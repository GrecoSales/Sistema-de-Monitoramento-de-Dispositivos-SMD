package model;

import java.util.List;

public class Device {
    String deviceName;
    String operatingSystem;
    String processorName;
    double cpuLoad;
    double cpuFrequency;
    double cpuTemperature;
    long ramTotal;
    long ramUsed;
    List<Drive> drives;

    public String getDeviceName() {
        return deviceName;
    }
    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }
    public String getOperatingSystem() {
        return operatingSystem;
    }
    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }
    public String getProcessorName() {
        return processorName;
    }
    public void setProcessorName(String processorName) {
        this.processorName = processorName;
    }
    public double getCpuLoad() {
        return cpuLoad;
    }
    public void setCpuLoad(double cpuLoad) {
        this.cpuLoad = cpuLoad;
    }
    public double getCpuFrequency() {
        return cpuFrequency;
    }
    public void setCpuFrequency(double cpuFrequency) {
        this.cpuFrequency = cpuFrequency;
    }
    public double getCpuTemperature() {
        return cpuTemperature;
    }
    public void setCpuTemperature(double cpuTemperature) {
        this.cpuTemperature = cpuTemperature;
    }
    public long getRamTotal() {
        return ramTotal;
    }
    public void setRamTotal(long ramTotal) {
        this.ramTotal = ramTotal;
    }
    public long getRamUsed() {
        return ramUsed;
    }
    public void setRamUsed(long ramUsed) {
        this.ramUsed = ramUsed;
    }
    public List<Drive> getDrives() {
        return drives;
    }
    public void setDrives(List<Drive> drives) {
        this.drives = drives;
    }
}
