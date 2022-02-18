package org.sanity.consoleForum.commands;

import org.sanity.consoleForum.commands.Attributes.*;
import org.sanity.consoleForum.core.Principal;
import org.sanity.consoleForum.io.InputReader;
import org.sanity.consoleForum.io.OutputWriter;
import org.sanity.consoleForum.models.enums.UserRole;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@CommandHelper(help = "Displays information about each command and its functionality.")
@CommandExample(example = "Help")
public class HelpCommand implements Command {


    private Set<Class<?>> commandClasses = new HashSet<>() {{
        add(AboutCommand.class);
        add(AllPostsCommand.class);
        add(ClearCommand.class);
        add(CommentCommand.class);
        add(CreatePostCommand.class);
        add(DeleteCommentCommand.class);
        add(DeletePostCommand.class);
        add(DemoteCommand.class);
        add(DislikeCommand.class);
        add(EditCommentCommand.class);
        add(EditPostCommand.class);
        add(ExitCommand.class);
        add(HelpCommand.class);
        add(LikeCommand.class);
        add(LoginCommand.class);
        add(LogoutCommand.class);
        add(PromoteCommand.class);
        add(ReadCommand.class);
        add(RegisterCommand.class);
    }};

    public InputReader consoleReader;

    public OutputWriter consoleWriter;

    public List<String> parameters;

    public Principal principal;

    public boolean resetsConsole = false;

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
    public void execute() {
        // REFLECTION TIME!

        for (Class<?> commandClass : commandClasses) {
            if ((this.getPrincipal().getUser() == null && !commandClass.isAnnotationPresent(GuestCommand.class)) ||
                            (this.getPrincipal().getUser() != null && this.getPrincipal().getUser().getRole() == UserRole.USER
                                    && !commandClass.isAnnotationPresent(UserCommand.class)) ||
                            (this.getPrincipal().getUser() != null && this.getPrincipal().getUser().getRole() == UserRole.MODERATOR
                                    && !commandClass.isAnnotationPresent(ModeratorCommand.class)) ||
                            (this.getPrincipal().getUser() != null && this.getPrincipal().getUser().getRole() == UserRole.ADMIN
                                    && !commandClass.isAnnotationPresent(AdminCommand.class))) {
                continue;
            }

            String commandString = String.format("[%s] <=> %s <=> %s",
                    commandClass.getSimpleName().replace("Command", ""),
                    commandClass.getAnnotation(CommandHelper.class).help(),
                    commandClass.getAnnotation(CommandExample.class).example());

            System.out.println(commandString);
        }
    }
}

