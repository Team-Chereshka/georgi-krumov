package net.java.main.Repositories;

import net.java.main.models.units.Unit;

import java.util.LinkedHashMap;
import java.util.Map;


public class UnitRepository {

    private Map<String, Unit> units;


    public UnitRepository() {
        this.units = new LinkedHashMap<>();
    }

    public void addUnit(Unit unit) {
        this.units.put(unit.getName(), unit);
    }

    public void removeUnit(Unit unit) {
        this.units.remove(unit);
    }


    public Unit getUnit(String unitName) {
        if (!this.units.containsKey(unitName)) {
            return null;
        }
        return  this.units.get(unitName);
    }

}
