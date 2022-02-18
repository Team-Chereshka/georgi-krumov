package org.sanity.consoleForum.commands;

import org.sanity.consoleForum.commands.Attributes.*;
import org.sanity.consoleForum.common.Constants;
import org.sanity.consoleForum.core.Principal;
import org.sanity.consoleForum.database.EntityManager;
import org.sanity.consoleForum.io.InputReader;
import org.sanity.consoleForum.io.OutputWriter;
import org.sanity.consoleForum.models.Post;
import org.sanity.consoleForum.models.PostRating;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@UserCommand
@ModeratorCommand
@AdminCommand
@CommandHelper(help = "Views all created Posts from the database, ordered by rating.")
@CommandExample(example = "AllPosts")
public class AllPostsCommand implements Command {
    private static final String NoPostsMessage = "There are currently no Posts.";

    private static final String PostFormatOutput = "[%s] %s by %s";

    public InputReader consoleReader;

    public OutputWriter consoleWriter;

    public List<String> parameters;

    public Principal principal;

    public boolean resetsConsole;

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
    public EntityManager getEntityManager() {
        return this.entityManager;
    }

    @Override
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
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
    public void execute() throws IOException {
        {
            List<Post> postsFromDb = this.getEntityManager()
                    .getPosts()
                    .stream()
                    .filter(post -> !post.getIsDeleted())
                    .sorted((post1, post2) -> {
                        int result = Long.compare(
                                post2.getRatings().stream().filter(PostRating::getIsPositive).count(),
                                post1.getRatings().stream().filter(PostRating::getIsPositive).count());

                        if (result == 0) {
                            result = Long.compare(
                                    post1.getRatings().stream().filter(PostRating::getIsNegative).count(),
                                    post2.getRatings().stream().filter(PostRating::getIsNegative).count());
                        }
                        return result;
                    })
                    .collect(Collectors.toList());

            if (postsFromDb.isEmpty()) {
                this.consoleWriter.write(Constants.CONSOLE_FORUM_OUTPUT_PREFIX);
                this.consoleWriter.writeLine(NoPostsMessage);
            } else {
                for (int i = 0; i < 3 && i < postsFromDb.size(); i++) {
                    Post post = postsFromDb.get(i);
                    this.consoleWriter.write(Constants.CONSOLE_FORUM_OUTPUT_PREFIX);
                    this.consoleWriter.importantLine(PostFormatOutput, post.getId(), post.getTitle(), post.getUser().getUsername());
                }

                for (int i = 3; i < postsFromDb.size(); i++) {
                    Post post = postsFromDb.get(i);
                    this.consoleWriter.write(Constants.CONSOLE_FORUM_OUTPUT_PREFIX);
                    this.consoleWriter.writeLine(PostFormatOutput, post.getId(), post.getTitle());
                }
            }
        }
    }
}


