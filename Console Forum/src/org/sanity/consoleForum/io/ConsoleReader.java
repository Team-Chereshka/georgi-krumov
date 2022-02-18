package org.sanity.consoleForum.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ConsoleReader implements InputReader {
    private BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public String readLine() {
        try {
            return bufferedReader.readLine();
        } catch (IOException ignored) { }

        return null;
    }
}
