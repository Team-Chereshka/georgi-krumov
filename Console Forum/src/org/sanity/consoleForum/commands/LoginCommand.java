package org.sanity.consoleForum.commands;

import org.sanity.consoleForum.commands.Attributes.CommandExample;
import org.sanity.consoleForum.commands.Attributes.CommandHelper;
import org.sanity.consoleForum.common.Constants;
import org.sanity.consoleForum.core.Principal;
import org.sanity.consoleForum.database.EntityManager;
import org.sanity.consoleForum.io.InputReader;
import org.sanity.consoleForum.io.OutputWriter;
import org.sanity.consoleForum.models.User;

import java.io.IOException;
import java.util.List;

@CommandHelper(help = "Logs in a User by given username and password.")
@CommandExample(example = "Login|John|123")
public class LoginCommand implements Command {
    private static final String SuccessLoginMessage = "Successfully logged in with User - %s!";

    private static final String ErrorLoginMessage = "Invalid username or password.";

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
        String username = this.getParameters().get(0);
        String password = this.getParameters().get(1);

        User userFromDb = this.getEntityManager()
                .getUsers()
                .stream()
                .filter(user -> user.getUsername().equals(username) && user.getPassword().equals(password))
                .findFirst()
                .orElse(null);

        this.consoleWriter.write(Constants.CONSOLE_FORUM_OUTPUT_PREFIX);

        if (userFromDb != null) {
            this.principal.signIn(userFromDb);
            this.consoleWriter.successLine(SuccessLoginMessage, username);
        } else {
            this.consoleWriter.errorLine(ErrorLoginMessage);
        }

        Thread.sleep(Constants.NOTIFICATION_DELAY);
    }
}
