package org.sanity.consoleForum.commands;

import org.sanity.consoleForum.commands.Attributes.*;
import org.sanity.consoleForum.common.Constants;
import org.sanity.consoleForum.common.Validator;
import org.sanity.consoleForum.core.Principal;
import org.sanity.consoleForum.database.EntityManager;
import org.sanity.consoleForum.io.InputReader;
import org.sanity.consoleForum.io.OutputWriter;
import org.sanity.consoleForum.models.Post;

import java.io.IOException;
import java.util.List;

@UserCommand
@ModeratorCommand
@AdminCommand
@CommandHelper(help = "Creates a Post by a given title and content.")
@CommandExample(example = "CreatePost|SomeTitle|SomeLongContent")
public class CreatePostCommand implements Command {
    private static final String SuccessCreatePostMessage = "Successfully created Post - %s!";

    private static final String TitleAndContentMalformed =
            "Title and Content must not be empty.";

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

        String title = this.getParameters().get(0);
        String content = this.getParameters().get(1);

        if (Validator.isNullOrEmpty(title) || Validator.isNullOrEmpty(content)) {
            this.consoleWriter.write(Constants.CONSOLE_FORUM_OUTPUT_PREFIX);
            this.consoleWriter.errorLine(TitleAndContentMalformed);
        }

        Post postForDb = new Post() {{
            setTitle(title);
            setContent(content);
            setUser(getPrincipal().getUser());
        }};

        entityManager.add(postForDb);
        this.principal.getUser().getPosts().add(postForDb);

        this.consoleWriter.write(Constants.CONSOLE_FORUM_OUTPUT_PREFIX);
        this.consoleWriter.successLine(SuccessCreatePostMessage, title);
        Thread.sleep(Constants.EXIT_NOTIFICATION_DELAY);
    }
}

