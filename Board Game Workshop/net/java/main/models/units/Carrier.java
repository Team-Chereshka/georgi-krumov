package net.java.main.models.units;

import net.java.main.enums.UnitType;

public class Carrier extends Unit {

    private static final int RANGE = 2;
    private static final UnitType UNIT_TYPE = UnitType.Carrier;
    private static final int HEALTH_POINTS = 50;
    private static final int ENERGY_POINTS = 80;
    private static final int ATTACK_POINTS = 15;
    private static final int DEFENCE_POINTS = 5;

    //TODO: create spell to cast!

    public Carrier(String name, int xPosition, int yPosition) {
        super(name, xPosition, yPosition);
        this.setRange(RANGE);
        this.setUnitType(UNIT_TYPE);
        this.setHealthPoints(HEALTH_POINTS);
        this.setEnergyPoints(ENERGY_POINTS);
        this.setAttackPoints(ATTACK_POINTS);
        this.setDefencePoints(DEFENCE_POINTS);
    }
}
