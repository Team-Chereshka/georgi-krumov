package org.sanity.consoleForum.core;

import org.sanity.consoleForum.database.EntityManager;

import java.io.IOException;

public class ConsoleForumEngine implements Engine {
    private final CommandManager consoleForumCommandManager;
    private final EntityManager entityManager;

    public ConsoleForumEngine(CommandManager consoleForumCommandManager, EntityManager entityManager) {
        this.consoleForumCommandManager = consoleForumCommandManager;
        this.entityManager = entityManager;
    }


    @Override
    public void run() {
        EngineState.IsRunning = true;

        this.entityManager.initialize();

        this.consoleForumCommandManager.reset();

        while (EngineState.IsRunning) {
            try {
                this.consoleForumCommandManager.handleInput();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

