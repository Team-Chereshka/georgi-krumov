package Models.Software;

import Enums.SoftwareType;

public class LightSoftware extends Software{

    private static final SoftwareType TYPE = SoftwareType.LIGHT;


    public LightSoftware(String name, String hardwareComponentName,
                         int capacityConsumption, int memoryConsumption) {
        super(name, TYPE, hardwareComponentName,
                capacityConsumption + (capacityConsumption / 2),
                memoryConsumption - (memoryConsumption / 2));
    }
}
