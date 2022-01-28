package Models.Hardware;

import Enums.HardwareType;

public class PowerHardware extends Hardware {

    private static final HardwareType TYPE = HardwareType.POWER;


    public PowerHardware(String name,
                         int maximumCapacity, int maximumMemory) {
        super(name, TYPE,
                maximumCapacity - ((maximumCapacity * 3)/ 4),
                maximumMemory + ((maximumMemory * 3)/ 4));
    }
}
