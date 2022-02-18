package org.sanity.consoleForum.commands;

import org.sanity.consoleForum.commands.Attributes.CommandExample;
import org.sanity.consoleForum.commands.Attributes.CommandHelper;
import org.sanity.consoleForum.common.Constants;
import org.sanity.consoleForum.common.Validator;
import org.sanity.consoleForum.core.Principal;
import org.sanity.consoleForum.database.EntityManager;
import org.sanity.consoleForum.io.InputReader;
import org.sanity.consoleForum.io.OutputWriter;
import org.sanity.consoleForum.models.User;
import org.sanity.consoleForum.models.enums.UserRole;

import java.io.IOException;
import java.util.List;

@CommandHelper(help = "Registers a User in the database by given username password and confirmPassword.")
@CommandExample(example = "Register|John|123|123")
public class RegisterCommand implements Command {
    private static final String SuccessRegisterMessage =
            "Successfully registed User - %s!";

    private static final String PasswordsDoNotMatchMessage =
            "Passwords do not match.";

    private static final String ExistentUsernameMessage =
            "Username already exists in the database.";

    private static final String UsernameAndPasswordMalformed =
            "Username and password must consist only of alphanumeric characters.";

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
        String confirmPassword = this.getParameters().get(2);

        this.consoleWriter.write(Constants.CONSOLE_FORUM_OUTPUT_PREFIX);

        if (Validator.isNullOrEmpty(username) || Validator.isNullOrEmpty(password)
                || !Validator.isAlphanumeric(username)
                || !Validator.isAlphanumeric(password)) {
            this.consoleWriter.errorLine(UsernameAndPasswordMalformed);
        } else if (!password.equals(confirmPassword)) {
            this.consoleWriter.errorLine(PasswordsDoNotMatchMessage);
        } else if (this.getEntityManager().getUsers().stream().anyMatch(user -> user.getUsername().equals(username))) {
            this.consoleWriter.errorLine(ExistentUsernameMessage);
        } else {
            this.getEntityManager().add(new User() {{
                setUsername(username);
                setPassword(password);
                setRole(UserRole.USER);
            }});

            this.consoleWriter.successLine(SuccessRegisterMessage, username);
        }

        Thread.sleep(Constants.NOTIFICATION_DELAY);
    }
}
