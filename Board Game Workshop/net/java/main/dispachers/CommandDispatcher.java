package net.java.main.dispachers;

import net.java.main.Repositories.UnitRepository;
import net.java.main.commands.Command;
import net.java.main.commands.GameOverCommand;
import net.java.main.exceptions.GameException;
import net.java.main.models.BattlegroundImpl;
import net.java.main.models.units.Carrier;
import net.java.main.models.units.Marine;

public class CommandDispatcher {

    UnitRepository unitRepository = new UnitRepository();

    BattlegroundImpl battleground = new BattlegroundImpl(unitRepository);


    public String dispatchCommand(String[] args, UnitRepository unitRepository) throws GameException {


        //field is created successfully but can't set unit positions on it .MUST RESOLVE
        battleground.createField();
        //TODO: add commands
        switch (args[0]) {
            //TODO:implement fight command
            case "game-over":
                return new GameOverCommand().execute(args);
            //in format: spawn {unitType} {name} {coordinateX} {coordinateY}
            case "spawn":
                String unitType = args[1];
                String unitName = args[2];
                String xPosition = args[3];
                String yPosition = args[4];
                if (unitType.equals("Marine")) {
                    Marine marine = new Marine(unitName, Integer.parseInt(xPosition), Integer.parseInt(yPosition));
                    unitRepository.addUnit(marine);
                    return String.format("%s of type Marine has spawn @(%d, %d)", marine.getName(), marine.getXPosition(), marine.getYPosition());
                } else if (unitType.equals("Carrier")) {
                    Carrier carrier = new Carrier(unitName, Integer.parseInt(xPosition), Integer.parseInt(yPosition));
                    unitRepository.addUnit(carrier);
                    return String.format("%s of type Carrier has spawn @(%d, %d)", carrier.getName(), carrier.getXPosition(), carrier.getYPosition());
                }
            case "status":
                unitName = args[1];
                unitRepository.getUnit(unitName);
                return String.format("->%s%n" +
                                "-Type:%s%n" +
                                "-Position:(%d, %d)%n" +
                                "-Attack Points:%d%n" +
                                "-Defence Points:%d%n" +
                                "-Energy Points:%d%n" +
                                "-Health Points:%d%n", unitName,
                        unitRepository.getUnit(unitName).getUnitType().name(), unitRepository.getUnit(unitName).getXPosition(),
                        unitRepository.getUnit(unitName).getYPosition(), unitRepository.getUnit(unitName).getAttackPoints(),
                        unitRepository.getUnit(unitName).getDefencePoints(), unitRepository.getUnit(unitName).getEnergyPoints(),
                        unitRepository.getUnit(unitName).getHealthPoints());

            case "move":
                unitName = args[1];
                xPosition = args[2];
                yPosition = args[3];
                battleground.move(unitRepository.getUnit(unitName), Integer.parseInt(xPosition), Integer.parseInt(yPosition));
                return String.format("%s moved to @(%d, %d)", unitName, Integer.parseInt(xPosition), Integer.parseInt(yPosition));


            default:
                return new Command().execute(args);
        }
    }
}
