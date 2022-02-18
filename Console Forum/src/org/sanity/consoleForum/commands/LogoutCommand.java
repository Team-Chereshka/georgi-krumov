package org.sanity.consoleForum.commands;

import org.sanity.consoleForum.commands.Attributes.*;
import org.sanity.consoleForum.common.Constants;
import org.sanity.consoleForum.core.Principal;
import org.sanity.consoleForum.database.EntityManager;
import org.sanity.consoleForum.io.InputReader;
import org.sanity.consoleForum.io.OutputWriter;

import java.io.IOException;
import java.util.List;

@UserCommand
@ModeratorCommand
@AdminCommand
@CommandHelper(help = "Logs out the currently logged-in User.")
@CommandExample(example = "Logout")
public class LogoutCommand implements Command {
    private static final String SuccessLogoutMessage = "Successfully logged out.";
    private static final String UnsuccessfulLogoutMessage = "No user is currently logged in!";

    public InputReader consoleReader;

    public OutputWriter consoleWriter;

    public List<String> parameters;

    public Principal principal;

    public boolean resetsConsole = true;

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
        return this.entityManager;
    }

    @Override
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void execute() throws IOException, InterruptedException {


            this.getPrincipal().signOut();

            this.consoleWriter.write(Constants.CONSOLE_FORUM_OUTPUT_PREFIX);
            this.consoleWriter.successLine(SuccessLogoutMessage);

            Thread.sleep(Constants.NOTIFICATION_DELAY);
        }
    }

