package Models.Software;

import Enums.SoftwareType;

public abstract class Software {

    private String name;
    private SoftwareType type;
    private String hardwareComponentName;
    private int capacityConsumption;
    private int memoryConsumption;


    public Software(String name, SoftwareType type, String hardwareComponentName,
                    int capacityConsumption, int memoryConsumption) {
        this.name = name;
        this.type = type;
        this.hardwareComponentName = hardwareComponentName;
        this.capacityConsumption = capacityConsumption;
        this.memoryConsumption = memoryConsumption;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type.name();
    }

    public int getCapacityConsumption() {
        return capacityConsumption;
    }

    public int getMemoryConsumption() {
        return memoryConsumption;
    }

}
