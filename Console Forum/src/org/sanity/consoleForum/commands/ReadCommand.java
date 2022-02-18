package org.sanity.consoleForum.commands;

import org.sanity.consoleForum.commands.Attributes.*;
import org.sanity.consoleForum.common.Constants;
import org.sanity.consoleForum.core.Principal;
import org.sanity.consoleForum.database.EntityManager;
import org.sanity.consoleForum.io.InputReader;
import org.sanity.consoleForum.io.OutputWriter;
import org.sanity.consoleForum.models.Comment;
import org.sanity.consoleForum.models.Post;
import org.sanity.consoleForum.models.PostRating;
import org.sanity.consoleForum.models.enums.UserRole;

import java.io.IOException;
import java.util.List;

@UserCommand
@ModeratorCommand
@AdminCommand
@CommandHelper(help = "Reads a Post, by given Id, viewing detailed info about it.")
@CommandExample(example = "Read|1")
public class ReadCommand implements Command {
    private static final String NoSuchPostMessage = "There is no Post with the given Id.";

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
        int postId = Integer.parseInt(this.getParameters().get(0));

        Post postFromDb = getEntityManager().getPosts().stream()
                .filter(post -> post.getId() == postId)
                .findFirst()
                .orElse(null);

        if (postFromDb == null || postFromDb.getIsDeleted()) {
            this.consoleWriter.write(Constants.CONSOLE_FORUM_OUTPUT_PREFIX);
            this.consoleWriter.errorLine(NoSuchPostMessage);
            this.resetsConsole = true;
            Thread.sleep(Constants.NOTIFICATION_DELAY);
        } else {
            this.consoleWriter.write(Constants.CONSOLE_FORUM_OUTPUT_PREFIX);
            this.consoleWriter.importantLine("Post - " + postFromDb.getTitle());
            this.consoleWriter.writeLine(Constants.CONSOLE_FORUM_OUTPUT_PREFIX);
            this.consoleWriter.write(Constants.CONSOLE_FORUM_OUTPUT_PREFIX);
            this.consoleWriter.writeLine(postFromDb.getContent());
            this.consoleWriter.writeLine(Constants.CONSOLE_FORUM_OUTPUT_PREFIX);
            this.consoleWriter.write(Constants.CONSOLE_FORUM_OUTPUT_PREFIX);

            String postIdString = String.valueOf(postFromDb.getId());
            String postUsernameString = postFromDb.getUser().getUsername();

            String emptySpace = repeatString(60 - postIdString.length() - postUsernameString.length());
            //postId, " ", by postUsername
            this.consoleWriter.writeLine("[%s]%s by %s",
                    postIdString, emptySpace, postUsernameString);

            // RATINGS LABEL
            this.consoleWriter.writeLine(Constants.CONSOLE_FORUM_OUTPUT_PREFIX);
            this.consoleWriter.writeLine("#".repeat(69));
            this.consoleWriter.writeLine(Constants.CONSOLE_FORUM_OUTPUT_EMPTY_LINE);

            String likesString = postFromDb.getRatings()
                    .stream()
                    .filter(PostRating::getIsPositive)
                    .count() + "";

            String dislikesString = postFromDb.getRatings()
                    .stream()
                    .filter(PostRating::getIsNegative)
                    .count() + "";

            String spacing = " ".repeat(46 - likesString.length() - dislikesString.length());

            this.consoleWriter.writeLine("## Likes: %s%sDislikes: %s ##", likesString,
                    spacing, dislikesString);

            this.consoleWriter.writeLine(Constants.CONSOLE_FORUM_OUTPUT_EMPTY_LINE);
            this.consoleWriter.writeLine("#".repeat(69));
            this.consoleWriter.writeLine(Constants.CONSOLE_FORUM_OUTPUT_PREFIX);

            // COMMENTS LABEL
            this.consoleWriter.writeLine(Constants.CONSOLE_FORUM_OUTPUT_PREFIX);
            this.consoleWriter.writeLine("#".repeat(69));
            this.consoleWriter.writeLine(Constants.CONSOLE_FORUM_OUTPUT_EMPTY_LINE);

            String spacingString = " ".repeat(28) + "Comments" + " ".repeat(29);

            this.consoleWriter.writeLine("##%s##", spacingString);
            this.consoleWriter.writeLine(Constants.CONSOLE_FORUM_OUTPUT_EMPTY_LINE);
            this.consoleWriter.writeLine("#".repeat(69));
            this.consoleWriter.writeLine(Constants.CONSOLE_FORUM_OUTPUT_PREFIX);

            if (!postFromDb.getComments().isEmpty()) {
                List<Comment> postComment = postFromDb.getComments()
                        .stream()
                        .filter(c -> !c.getIsDeleted()).toList();

                for (Comment comment : postComment) {
                    this.consoleWriter.write(Constants.CONSOLE_FORUM_OUTPUT_PREFIX);

                    String userRoleString =
                            comment.getUser().getRole() == UserRole.MODERATOR
                                    || comment.getUser().getRole() == UserRole.ADMIN
                                    ? "[" + comment.getUser().getRole() + "]"
                                    : "";

                    this.consoleWriter.writeLine("(%d) (%s%s): %s",
                            comment.getId(), userRoleString, comment.getUser().getUsername(), comment.getContent());
                    this.consoleWriter.writeLine(Constants.CONSOLE_FORUM_OUTPUT_PREFIX);
                }
            }
        }
    }
    private String repeatString(int n) {
        // print whitespaces n times
        return String.valueOf(" ").repeat(Math.max(0, n));
    }
}

