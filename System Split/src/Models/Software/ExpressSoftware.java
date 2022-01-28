package Models.Software;

import Enums.SoftwareType;

public class ExpressSoftware extends Software{

    private static final SoftwareType TYPE = SoftwareType.EXPRESS;


    public ExpressSoftware(String name, String hardwareComponentName, int capacityConsumption, int memoryConsumption) {
        super(name, TYPE, hardwareComponentName, capacityConsumption,
                memoryConsumption * 2);
    }
}
