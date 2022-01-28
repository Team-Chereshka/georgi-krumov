package net.java.main.models;

import net.java.main.Repositories.UnitRepository;
import net.java.main.exceptions.InvalidPositionException;
import net.java.main.interfaces.Battleground;
import net.java.main.models.units.Unit;

import java.util.List;


public class BattlegroundImpl implements Battleground {

    private int[][] field;
    private final UnitRepository unitRepository;

    public BattlegroundImpl(UnitRepository unitRepository) {
        this.unitRepository = unitRepository;
    }

    //TODO:FUGURE OUT A WAY TO GET UNITS IN GIVEN RANGE
    @Override
    public List<Unit> getUnitsInRange(int xPosition, int yPosition, int range) {
        return null;
    }

    @Override
    public void add(Unit unit) throws InvalidPositionException {
        unitRepository.addUnit(unit);
    }

    @Override
    public void remove(Unit unit) throws InvalidPositionException {
        unitRepository.removeUnit(unit);
    }

    @Override
    public void move(Unit unit, int xPosition, int yPosition) throws InvalidPositionException {

        unit.setxPosition(xPosition);
        unit.setYPosition(yPosition);
    }


    private boolean isInsideBoundaries(int unitRow, int unitCol) {
        if (unitRow < 0 || unitRow >= this.field.length ||
                unitCol < 0 || unitCol >= this.field[0].length) {
            return false;
        }
        return true;
    }

    public void createField() {
        int[][] field = new int[5][5];
        for (int row = 0; row < field.length; row++) {
            for (int col = 0; col < field.length; col++) {
                field[row][col] = row + col;
            }
        }
        this.field = field;
    }
}
