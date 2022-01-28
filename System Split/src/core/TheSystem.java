package core;

import Models.Hardware.Hardware;
import Models.Hardware.HeavyHardware;
import Models.Hardware.PowerHardware;
import Models.Software.ExpressSoftware;
import Models.Software.LightSoftware;
import Models.Software.Software;

import java.util.ArrayList;
import java.util.List;

public class TheSystem {

    private List<Hardware> registeredHardwareList;
    private List<Hardware> dumpList;

    public TheSystem() {
        this.registeredHardwareList = new ArrayList<>();
        this.dumpList = new ArrayList<>();
    }


    public Hardware checkForHardWare(String hardwareComponentName) {
        Hardware hardwareToReturn = null;

        for (Hardware h : registeredHardwareList) {
            if (h.getName().equals(hardwareComponentName)) {
                hardwareToReturn = h;
                break;
            }
        }
        return hardwareToReturn;
    }

    public void registerSoftware(String command, String[] args) {
        String hardwareComponentName = args[0];
        String name = args[1];
        int capacity = Integer.parseInt(args[2]);
        int memory = Integer.parseInt(args[3]);

        //set software to being a specific type.If no type is set - return null.
        Software software = switch (command) {
            case "RegisterExpressSoftware" -> new ExpressSoftware(name, hardwareComponentName, capacity, memory);
            case "RegisterLightSoftware" -> new LightSoftware(name, hardwareComponentName, capacity, memory);
            default -> null;
        };

        Hardware hardwareToCheck = checkForHardWare(hardwareComponentName);

        if (hardwareToCheck != null) {
            hardwareToCheck.registerSoftware(software,
                    software.getCapacityConsumption(), software.getMemoryConsumption());
        }
    }

    public void hardwareRegistration(String command, String[] tokens) {
        String name = tokens[0];
        int capacity = Integer.parseInt(tokens[1]);
        int memory = Integer.parseInt(tokens[2]);
        Hardware hardware = switch (command) {
            case "RegisterPowerHardware" -> new PowerHardware(name, capacity, memory);
            case "RegisterHeavyHardware" -> new HeavyHardware(name, capacity, memory);
            default -> null;
        };
        this.registeredHardwareList.add(hardware);
    }


    public void releaseSoftware(String[] tokens) {
        String hardwareComponentName = tokens[0];
        String softwareName = tokens[1];

        Hardware hardwareToCheck = checkForHardWare(hardwareComponentName);

        if (hardwareToCheck != null) {
            Software softwareToCheck = hardwareToCheck.checkForSoftware(softwareName);
            if (softwareToCheck != null) {
                hardwareToCheck.removeSoftware(softwareToCheck);
            }
        }
    }

    public void analyze() {
        int countSoftawreComponents = 0;
        int memoryInUse = 0;
        int maxMemory = 0;
        int capacityInUse = 0;
        int maxCapacity = 0;
        for (Hardware hardware : registeredHardwareList) {
            if (!hardware.getSoftwareList().isEmpty()) {
                for (Software software : hardware.getSoftwareList()) {
                    countSoftawreComponents++;
                }
            }
            memoryInUse += hardware.getMemoryUsed();
            maxMemory += hardware.getMaximumMemory();
            capacityInUse += hardware.getCapacityUsed();
            maxCapacity += hardware.getMaximumCapacity();
        }

        System.out.printf("System Analysis%n" +
                        "Hardware Components: %d%n" +
                        "Software Components: %d%n" +
                        "Total Operational Memory: %d / %d%n" +
                        "Total Capacity Taken: %d / %d%n", registeredHardwareList.size(), countSoftawreComponents,
                memoryInUse, maxMemory,
                capacityInUse, maxCapacity);
    }

    public void split() {
        List<Hardware> heavyHardware = new ArrayList<>();
        for (Hardware hardware : registeredHardwareList) {
            //MIGHT BREAK CAUSE OF ALL CAPITAL LETTERS!
            if (hardware.getType().equals("POWER")) {
                System.out.println(hardware);
            } else {
                heavyHardware.add(hardware);
            }
        }

        for (Hardware hardware : heavyHardware) {
            System.out.println(hardware);
        }
    }

    //dump" && "restore" commands work like a charm

    public void dumpHardware(String hardwareComponentName) {
        for (Hardware hardware : registeredHardwareList) {
            if (hardware.getName().equals(hardwareComponentName)) {
                dumpList.add(hardware);
                registeredHardwareList.remove(hardware);
                break;
            }
        }
    }

    public void restoreHardware(String hardwareComponentName) {
        for (Hardware hardware : dumpList) {
            if (hardware.getName().equals(hardwareComponentName)) {
                registeredHardwareList.add(hardware);
                dumpList.remove(hardware);
                break;
            }
        }
    }

    public void destroyHardware(String hardwareComponentName) {
        dumpList
                .removeIf(hardware ->
                        hardware.getName()
                                .equals(hardwareComponentName));
    }

    public void dumpAnalyze() {
        int powerHardwareComponents = 0;
        int heavyHardwareComponents = 0;
        int expressSoftwareComponents = 0;
        int lightSoftwareComponents = 0;
        int totalDumpedMemory = 0;
        int totalDumpedCapacity = 0;

        for (Hardware hardware : dumpList) {
            if (hardware.getType().equals("POWER")) {
                powerHardwareComponents++;
            } else {
                heavyHardwareComponents++;
            }

            for (Software software : hardware.getSoftwareList()) {
                if (software.getType().equals("EXPRESS")) {
                    expressSoftwareComponents++;
                } else {
                    lightSoftwareComponents++;
                }
                totalDumpedCapacity += software.getCapacityConsumption();
                totalDumpedMemory += software.getMemoryConsumption();
            }
        }

        System.out.printf("Dump Analysis%n" +
                "Power Hardware Components: %d%n" +
                "Heavy Hardware Components: %d%n" +
                "Express Software Components - %d%n" +
                "Light Software Components: - %d%n" +
                "Total Dumped Memory: %d%n" +
                "Total Dumped Capacity: %d%n", powerHardwareComponents, heavyHardwareComponents,
                expressSoftwareComponents, lightSoftwareComponents,
                totalDumpedMemory, totalDumpedCapacity);
    }
}
