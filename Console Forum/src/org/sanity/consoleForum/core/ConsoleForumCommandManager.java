package org.sanity.consoleForum.core;

import org.sanity.consoleForum.commands.Command;
import org.sanity.consoleForum.common.Constants;
import org.sanity.consoleForum.database.EntityManager;
import org.sanity.consoleForum.io.InputReader;
import org.sanity.consoleForum.io.OutputWriter;
import org.sanity.consoleForum.models.enums.UserRole;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ConsoleForumCommandManager implements CommandManager {
    private final InputReader consoleReader;

    private final OutputWriter consoleWriter;

    private final EntityManager entityManager;

    private Principal currentlyLoggedInUser;


    public ConsoleForumCommandManager(InputReader consoleReader, OutputWriter consoleWriter, EntityManager entityManager) {
        this.consoleReader = consoleReader;
        this.consoleWriter = consoleWriter;
        this.currentlyLoggedInUser = new PrincipalImpl();
        this.entityManager = entityManager;
    }

    private boolean isLoggedIn() {
        if (currentlyLoggedInUser.getUser() != null) {
            return true;
        }
        return false;
    }

    private boolean isAdmin() {
        if (this.isLoggedIn() &&
                this.currentlyLoggedInUser.getUser().getRole().equals(UserRole.ADMIN)) {
            return true;
        }
        return false;
    }

    private boolean isModerator() {
        if (this.isLoggedIn() &&
                this.currentlyLoggedInUser.getUser().getRole().equals(UserRole.MODERATOR)) {
            return true;
        }
        return false;
    }

    private String getAppropriateHeader() {

        if (this.isAdmin()) {
            //not sure if I should get the length of the username for the second parameter..
            return String.format(Constants.ADMIN_INTRODUCTION_MESSAGE,
                    this.currentlyLoggedInUser.getUser().getUsername(),
                    repeatString(37 - this.currentlyLoggedInUser.getUser().getUsername().length()));

        }
        if (this.isModerator()) {
            return String.format(Constants.MODERATOR_INTRODUCTION_MESSAGE,
                    this.currentlyLoggedInUser.getUser().getUsername(),
                    repeatString(47 - this.currentlyLoggedInUser.getUser().getUsername().length()));
        }
        if (this.isLoggedIn()) {
            return String.format(Constants.USER_INTRODUCTION_MESSAGE,
                    this.currentlyLoggedInUser.getUser().getUsername(),
                    repeatString(53 - this.currentlyLoggedInUser.getUser().getUsername().length()));
        }
        return Constants.GUEST_INTRODUCTION_MESSAGE;
    }

    private void clearConsole() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (IOException | InterruptedException ignored) {
        }
    }

    private void printIntro() {
        try {
            this.consoleWriter.writeLine(Constants.CONSOLE_FORUM_INTRO_BANNER);
            this.consoleWriter.writeLine(Constants.CONSOLE_FORUM_OUTPUT_EMPTY_LINE);
            this.consoleWriter.writeLine(this.getAppropriateHeader());
            this.consoleWriter.writeLine(Constants.CONSOLE_FORUM_OUTPUT_PREFIX);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // THIS CREATES A COMMAND OBJECT FROM A CLASS BASED ON INPUT
    private Command instanceCommand(String input) {
        List<String> inputParams = Arrays.stream(input.split("\\|"))
                .collect(Collectors.toList());
        String commandName = inputParams.get(0);
        inputParams.remove(0);

        Class commandType = null;

        try {

            commandType = Class.forName("org.sanity.consoleForum.commands." + commandName + "Command");
            if (commandType != null) {
                Command command = null;

                command = (Command) commandType.getDeclaredConstructor().newInstance();
                command.setConsoleReader(this.consoleReader);
                command.setConsoleWriter(this.consoleWriter);
                command.setParameters(inputParams);
                command.setPrincipal(this.currentlyLoggedInUser);
                command.setEntityManager(this.entityManager);

                return command;
            }

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }

        return null;
    }

    private boolean isAuthorized(Command command) {
        // TODO: CHECK IF COMMAND HAS AUTHORIZATION ANNOTATIONS
        //       THEN CHECK IF USER ROLE EQUALS ONE OF THE REQUIRED
        //       ROLES IN THE AUTHORIZATION ANNOTATION
        //       COMMAND ANNOTATIONS ARE ADMINCOMMAND, MODERATORCOMMAND, USERCOMMAND, GUESTCOMMAND
        //       RESEPCTIVELY YOU ARE LOOKING FOR A USER WITH ADMIN ROLE, MODERATOR ROLE, LOGGED IN USER WITH USER ROLE
        //       GUEST = LOGGED OUT USER (NO USER IN SYSTEM)

        return true;
    }

    public void reset() {
        this.clearConsole();
        this.printIntro();
    }

    public void handleInput() throws IOException, InterruptedException {
        this.consoleWriter.write(Constants.CONSOLE_FORUM_OUTPUT_PREFIX);
        String input = this.consoleReader.readLine();

        Command command = this.instanceCommand(input);

        if (command == null) {
            this.consoleWriter.write(Constants.CONSOLE_FORUM_OUTPUT_PREFIX);
            this.consoleWriter.errorLine("Unsupported command...", null);
            Thread.sleep(Constants.NOTIFICATION_DELAY);
            this.reset();
        } else {
            if (!this.isAuthorized(command)) {
                this.consoleWriter.write(Constants.CONSOLE_FORUM_OUTPUT_PREFIX);
                this.consoleWriter.errorLine("You have no access to this command.", null);
                Thread.sleep(Constants.NOTIFICATION_DELAY);
                this.reset();
            } else {
                command.execute();
                if (command.getResetsConsole()) this.reset();
            }
        }
    }


    private String repeatString(int n) {
        // print whitespaces n times

        return String.valueOf(" ").repeat(Math.max(0, n));
    }

    private String capitalizeCommand(String path) {
        return path.substring(0, 1).toUpperCase() + path.substring(1).toLowerCase();
    }
}
