package org.sanity.consoleForum.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileReader implements InputReader {
    private final List<String> fileContent;

    private int currentLineIndex;

    public FileReader(String pathToFile) throws IOException {
        this.fileContent = Files.readAllLines(Path.of(pathToFile));
        this.currentLineIndex = 0;
    }

    private String readNextLine()
    {
        if (this.currentLineIndex < this.fileContent.size())
        {
            return this.fileContent.get(this.currentLineIndex++);
        }

        return null;
    }

    @Override
    public String readLine() {
        return this.readNextLine();
    }
}
