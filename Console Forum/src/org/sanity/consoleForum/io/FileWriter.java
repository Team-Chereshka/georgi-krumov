package org.sanity.consoleForum.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Collections;

public class FileWriter implements OutputWriter {
    private final String pathToFile;

    private final StandardOpenOption option;

    public FileWriter(String pathToFile, StandardOpenOption option)
    {
        this.pathToFile = pathToFile;
        this.option = option;
    }

    @Override
    public void write(Object output) throws IOException {
        Files.write(Path.of(this.pathToFile), Collections.singleton(output.toString()), this.option);
    }

    @Override
    public void write(Object output, Object... parameters) throws IOException {
        Files.write(Path.of(this.pathToFile), Collections.singleton(String.format(output.toString(), parameters)), this.option);
    }

    @Override
    public void writeLine(Object output) throws IOException {
        Files.write(Path.of(this.pathToFile), Collections.singleton(output.toString() + System.lineSeparator()), this.option);
    }

    @Override
    public void writeLine(Object output, Object[] parameters) throws IOException {
        Files.write(Path.of(this.pathToFile), Collections.singleton(String.format(output.toString(), parameters) + System.lineSeparator()), this.option);
    }

    @Override
    public void errorLine(Object output, Object[] parameters) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void successLine(Object output, Object[] parameters) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void importantLine(Object output, Object[] parameters) {
        throw new UnsupportedOperationException();
    }
}
