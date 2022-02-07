package core;

import exceptions.AuthorizationFailedException;
import io.InputReader;
import repositories.Database;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Engine {
    public static void main(String[] args) throws IOException, AuthorizationFailedException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Database database = new Database();
        InputReader inputReader = new InputReader();


        while (true) {
            String input = reader.readLine();
            if (input.equals("End")) {
                break;
            }
            inputReader.readLine(input);
        }
    }
}
