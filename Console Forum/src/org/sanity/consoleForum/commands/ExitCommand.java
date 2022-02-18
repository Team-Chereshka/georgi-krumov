package org.sanity.consoleForum.commands;

import org.sanity.consoleForum.commands.Attributes.CommandExample;
import org.sanity.consoleForum.commands.Attributes.CommandHelper;
import org.sanity.consoleForum.common.Constants;
import org.sanity.consoleForum.core.EngineState;
import org.sanity.consoleForum.core.Principal;
import org.sanity.consoleForum.database.EntityManager;
import org.sanity.consoleForum.io.InputReader;
import org.sanity.consoleForum.io.OutputWriter;

import java.io.IOException;
import java.util.List;

@CommandHelper(help = "Exits the Console Forum application.")
@CommandExample(example = "Exit")
public class ExitCommand implements Command {
    private InputReader consoleReader;

    private OutputWriter consoleWriter;

    private List<String> parameters;

    private Principal principal;

    private boolean resetsConsole;

    private EntityManager entityManager;

    @Override
    public InputReader getConsoleReader() {
        return this.consoleReader;
    }

    @Override
    public void setConsoleReader(InputReader inputReader) {
        this.consoleReader = inputReader;
    }

    @Override
    public OutputWriter getConsoleWriter() {
        return this.consoleWriter;
    }

    @Override
    public void setConsoleWriter(OutputWriter outputWriter) {
        this.consoleWriter = outputWriter;
    }

    @Override
    public List<String> getParameters() {
        return this.parameters;
    }

    @Override
    public void setParameters(List<String> parameters) {
        this.parameters = parameters;
    }

    @Override
    public Principal getPrincipal() {
        return this.principal;
    }

    @Override
    public void setPrincipal(Principal principal) {
        this.principal = principal;
    }

    @Override
    public boolean getResetsConsole() {
        return this.resetsConsole;
    }

    @Override
    public void setResetsConsole(boolean resetsConsole) {
        this.resetsConsole = resetsConsole;
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void execute() throws IOException, InterruptedException {
        this.consoleWriter.write(Constants.CONSOLE_FORUM_OUTPUT_PREFIX);
        this.consoleWriter.writeLine("Saving Database and Exiting Console Forum...");

        // entity manager needs to store all data in db files
        this.entityManager.store();
        Thread.sleep(Constants.EXIT_NOTIFICATION_DELAY);

        EngineState.IsRunning = false;
    }
}

