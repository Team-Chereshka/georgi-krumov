package org.sanity.consoleForum.commands;

import org.sanity.consoleForum.commands.Attributes.AdminCommand;
import org.sanity.consoleForum.commands.Attributes.CommandExample;
import org.sanity.consoleForum.commands.Attributes.CommandHelper;
import org.sanity.consoleForum.commands.Attributes.ModeratorCommand;
import org.sanity.consoleForum.common.Constants;
import org.sanity.consoleForum.core.Principal;
import org.sanity.consoleForum.database.EntityManager;
import org.sanity.consoleForum.io.InputReader;
import org.sanity.consoleForum.io.OutputWriter;
import org.sanity.consoleForum.models.Post;
import org.sanity.consoleForum.models.enums.UserRole;

import java.io.IOException;
import java.util.List;

@ModeratorCommand
@AdminCommand
@CommandHelper(help = "Deletes a Post by given Id.")
@CommandExample(example = "DeletePost|1")
public class DeletePostCommand implements Command {
    private static final String NoSuchPostMessage = "There is no Post with the given Id.";

    private static final String NO_RIGHTS_MESSAGE = "You have NO permission to delete this post!!";


    private static final String SuccessDeleteMessage = "Successfully Deleted Post - %s!";

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

        int postId = Integer.parseInt(this.parameters.get(0));

        Post postFromDb = this.entityManager.getPosts()
                .stream()
                .filter(post -> post.getId() == postId)
                .findFirst()
                .orElse(null);

        if (postFromDb == null || postFromDb.getIsDeleted()) {
            this.consoleWriter.write(Constants.CONSOLE_FORUM_OUTPUT_PREFIX);
            this.consoleWriter.errorLine(NoSuchPostMessage);
            Thread.sleep(Constants.NOTIFICATION_DELAY);
        } else {
            if (postFromDb.getUser().getId() == this.principal.getUser().getId() ||
                    this.principal.getUser().getRole() == UserRole.MODERATOR ||
                    this.principal.getUser().getRole() == UserRole.ADMIN) {
                postFromDb.setIsDeleted(true);
                this.consoleWriter.write(Constants.CONSOLE_FORUM_OUTPUT_PREFIX);
                this.consoleWriter.successLine(SuccessDeleteMessage, postId);
                Thread.sleep(Constants.NOTIFICATION_DELAY);
            } else {
                this.consoleWriter.errorLine(NO_RIGHTS_MESSAGE);
            }
        }
    }
}
