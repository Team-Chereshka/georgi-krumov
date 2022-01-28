import core.TheSystem;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        TheSystem system = new TheSystem();

        while (!input.equals("System Split")) {
            String[] tokens = input.split("\\(");
            String command = tokens[0];
            String[] data = tokens[1].split(", ");
            data[data.length - 1] =
                    data[data.length - 1].substring(0, data[data.length - 1].length() - 1);

            switch (command){
                case "RegisterPowerHardware", "RegisterHeavyHardware" ->
                        system.hardwareRegistration(command, data);
                case "RegisterExpressSoftware", "RegisterLightSoftware" ->
                        system.registerSoftware(command, data);
                case "ReleaseSoftwareComponent" -> system.analyze();
                case "Analyze" -> system.analyze();
                case "Dump" -> system.dumpHardware(data[0]);
                case "Restore" -> system.restoreHardware(data[0]);
                case "Destroy" -> system.destroyHardware(data[0]);
                case "DumpAnalyze" -> system.dumpAnalyze();
            }
            input = scanner.nextLine();
        }
    system.split();
    }
}
