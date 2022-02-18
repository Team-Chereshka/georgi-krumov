package org.sanity.consoleForum.io;

import java.io.IOException;

public interface OutputWriter {
    void write(Object output) throws IOException;

    void write(Object output, Object... parameters) throws IOException;

    void writeLine(Object output) throws IOException;

    void writeLine(Object output, Object... parameters) throws IOException;

    void errorLine(Object output, Object... parameters);

    void successLine(Object output, Object... parameters);

    void importantLine(Object output, Object... parameters);
}
