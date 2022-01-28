package Models.Hardware;

import Enums.HardwareType;

public class HeavyHardware extends Hardware {

    private static final HardwareType TYPE = HardwareType.HEAVY;

    public HeavyHardware(String name,
                         int maximumCapacity, int maximumMemory) {
        super(name, TYPE,
                maximumCapacity * 2,
                maximumMemory - (maximumMemory / 4));
    }
}
