package model;

public class Drive {
    String name;
    long totalSpace;
    long usedSpace;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public long getTotalSpace() {
        return totalSpace;
    }
    public void setTotalSpace(long totalSpace) {
        this.totalSpace = totalSpace;
    }
    public long getUsedSpace() {
        return usedSpace;
    }
    public void setUsedSpace(long usedSpace) {
        this.usedSpace = usedSpace;
    }
}