package net.java.main.models.units;

import net.java.main.enums.UnitType;
import net.java.main.interfaces.CombatHandler;

public class Unit {
    private String name;
    private UnitType unitType;
    private int range;
    private int xPosition;
    private int yPosition;
    private int healthPoints;
    private int energyPoints;
    private int attackPoints;
    private int defencePoints;
    private CombatHandler combatHandler;

    public Unit(String name, int xPosition, int yPosition) {
        this.name = name;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
    }

    public String getName() {
        return this.name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    public UnitType getUnitType() {
        return unitType;
    }

    protected void setUnitType(UnitType unitType) {
        this.unitType = unitType;
    }

    public int getRange() {
        return this.range;
    }

    protected void setRange(Integer range) {
        this.range = range;
    }

    public int getXPosition() {
        return this.xPosition;
    }

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public int getYPosition() {
        return this.yPosition;
    }

    public void setYPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    public int getHealthPoints() {
        return this.healthPoints;
    }

    protected void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public int getEnergyPoints() {
        return this.energyPoints;
    }

    protected void setEnergyPoints(int energyPoints) {
        this.energyPoints = energyPoints;
    }

    public int getAttackPoints() {
        return this.attackPoints;
    }

    protected void setAttackPoints(int attackPoints) {
        this.attackPoints = attackPoints;
    }

    public int getDefencePoints() {
        return this.defencePoints;
    }

    protected void setDefencePoints(int defencePoints) {
        this.defencePoints = defencePoints;
    }

    public CombatHandler getCombatHandler() {
        return this.combatHandler;
    }

    protected void setCombatHandler(CombatHandler combatHandler) {
        this.combatHandler = combatHandler;
    }
}
