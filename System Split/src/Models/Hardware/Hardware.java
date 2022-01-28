package Models.Hardware;

import Enums.HardwareType;
import Models.Software.Software;

import java.util.ArrayList;
import java.util.List;

public class Hardware {

    private String name;
    private HardwareType type;
    private int maximumCapacity;
    private int maximumMemory;
    private int capacityUsed;
    private int memoryUsed;
    private List<Software> softwareList;


    public Hardware(String name, HardwareType type, int maximumCapacity, int maximumMemory) {
        this.name = name;
        this.type = type;
        this.maximumCapacity = maximumCapacity;
        this.maximumMemory = maximumMemory;
        this.softwareList = new ArrayList<>();
        capacityUsed = 0;
        memoryUsed = 0;
    }

    public String getName() {
        return name;
    }

    public void registerSoftware(Software software, int capacity, int memory) {
        if (this.capacityUsed + capacity <= maximumCapacity && this.memoryUsed + memory <= maximumMemory) {
            softwareList.add(software);
            updateMemory(memory, '+');
            updateCapacity(capacity, '+');
        }
    }

    private void updateCapacity(int capacity, char function) {
        switch (function) {
            case '-' -> this.capacityUsed = this.capacityUsed - capacity;
            case '+' -> this.capacityUsed = this.capacityUsed + capacity;
        }
    }

    private void updateMemory(int memory, char function) {
        switch (function) {
            case '-' -> this.memoryUsed = this.memoryUsed - memory;
            case '+' -> this.memoryUsed = this.memoryUsed + memory;
        }
    }

    public Software checkForSoftware(String softwareName) {
        Software softwareToReturn = null;
        for (Software s : softwareList) {
            if (s.getName().equals(softwareName)) {
                softwareToReturn = s;
            }
        }
        return softwareToReturn;
    }

    public void removeSoftware(Software softwareToCheck) {
        int softwareMemory = softwareToCheck.getMemoryConsumption();
        int softwareCapacity = softwareToCheck.getCapacityConsumption();
        this.softwareList.remove(softwareToCheck);
        updateMemory(softwareMemory, '-');
        updateCapacity(softwareCapacity, '-');
    }

    public List<Software> getSoftwareList() {
        return softwareList;
    }

    public String getType() {
        return type.name();
    }

    public int getMaximumCapacity() {
        return maximumCapacity;
    }

    public int getMaximumMemory() {
        return maximumMemory;
    }

    public int getCapacityUsed() {
        return capacityUsed;
    }

    public int getMemoryUsed() {
        return memoryUsed;
    }

    @Override
    public String toString() {
        int countOfExpressSoftware = 0;
        int countOfLightSoftware = 0;
        StringBuilder sb = new StringBuilder();

        if (!this.softwareList.isEmpty()) {
            for (Software s : softwareList) {
                if (s.getType().equals("EXPRESS")) {
                    countOfExpressSoftware++;
                } else if (s.getType().equals("LIGHT")) {
                    countOfLightSoftware++;
                }
                sb.append(s.getName()).append(", ");
            }
        } else {
            sb.append("None, ");
        }
        sb.replace(sb.length() - 2,
                sb.length() - 1, "");

        return String.format("Hardware Component - %s%n" +
                "Express Software Components: %d%n" +
                "Light Software Components: %d%n" +
                "Memory Usage: %d/%d%n" +
                "Capacity Usage: %d/%d%n" +
                "Type: %s%n" +
                "Software Components: %s", this.getName(),
                countOfExpressSoftware, countOfLightSoftware,
                this.memoryUsed, this.maximumMemory, this.capacityUsed,
                this.maximumCapacity, this.getType(), sb);
    }
}
