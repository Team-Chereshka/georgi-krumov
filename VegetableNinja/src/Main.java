import javax.imageio.metadata.IIOMetadataFormatImpl;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String ninja1Name = scanner.nextLine();
        String ninja2Name = scanner.nextLine();
        int[] arr = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt).toArray();

        Ninja ninja1 = new Ninja(ninja1Name);
        Ninja ninja2 = new Ninja(ninja2Name);

        Field field = new Field(ninja1, ninja2);
        field.createMatrix(arr[0], arr[1], scanner);


        boolean gameEnded = false;

        while (!gameEnded) {

            //U, D, L, R -> directions to move
            String tokens = scanner.nextLine();

            char[] commands = tokens.toCharArray();

            for (int i = 0; i < commands.length && !gameEnded; i++) {
                char currentCommand = commands[i];
                gameEnded = field.moveCurrentPlayer(currentCommand);
            }
        }
    }
}

