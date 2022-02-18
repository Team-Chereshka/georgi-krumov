package org.sanity.consoleForum.commands;

import org.sanity.consoleForum.commands.Attributes.AdminCommand;
import org.sanity.consoleForum.commands.Attributes.CommandExample;
import org.sanity.consoleForum.commands.Attributes.CommandHelper;
import org.sanity.consoleForum.common.Constants;
import org.sanity.consoleForum.core.Principal;
import org.sanity.consoleForum.database.EntityManager;
import org.sanity.consoleForum.io.InputReader;
import org.sanity.consoleForum.io.OutputWriter;
import org.sanity.consoleForum.models.User;
import org.sanity.consoleForum.models.enums.UserRole;

import java.io.IOException;
import java.util.List;
@AdminCommand
@CommandHelper(help = "Demotes the User with the given Id into a lower role.")
@CommandExample(example = "Demote|1")
public class DemoteCommand implements Command {
    private static final String NoRightsToDemote = "You have no right to demote this user.";

    private static final String NoSuchUserMessage = "There is no User with the given Id.";

    private static final String SuccessDemoteMessage = "Successfully Demoted User - %s";

    private static final String AlreadyUser = "This user has the lowest role.";

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
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void execute() throws IOException, InterruptedException {
        int userId = Integer.parseInt(this.getParameters().get(0));

        User userFromDb = entityManager.getUsers()
                .stream()
                .filter(user -> user.getId() == userId)
                .findFirst()
                .orElse(null);


        if (userFromDb == null || userFromDb.getIsDeleted()) {
            this.consoleWriter.write(Constants.CONSOLE_FORUM_OUTPUT_PREFIX);
            this.consoleWriter.errorLine(NoSuchUserMessage);
            Thread.sleep(Constants.NOTIFICATION_DELAY);

        } else {
            if (this.principal.getUser().getRole() == UserRole.ADMIN &&
                    userFromDb.getRole() == UserRole.MODERATOR) {
                userFromDb.setRole(UserRole.USER);
                this.consoleWriter.successLine(SuccessDemoteMessage, userId);

            } else if (this.principal.getUser().getRole() == UserRole.ADMIN &&
                    userFromDb.getRole() == UserRole.ADMIN) {
                userFromDb.setRole(UserRole.MODERATOR);
                this.consoleWriter.successLine(SuccessDemoteMessage, userId);
            }
            this.consoleWriter.write(Constants.CONSOLE_FORUM_OUTPUT_PREFIX);
            Thread.sleep(Constants.EXIT_NOTIFICATION_DELAY);
        }
    }
}
