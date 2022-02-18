package org.sanity.consoleForum.core;

import java.io.IOException;

public interface CommandManager {
    void reset();

    void handleInput() throws IOException, InterruptedException;
}
