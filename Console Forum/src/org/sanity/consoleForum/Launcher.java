package org.sanity.consoleForum;

import org.sanity.consoleForum.commands.AboutCommand;
import org.sanity.consoleForum.commands.AllPostsCommand;
import org.sanity.consoleForum.commands.ClearCommand;
import org.sanity.consoleForum.commands.CommentCommand;
import org.sanity.consoleForum.commands.CreatePostCommand;
import org.sanity.consoleForum.commands.DeleteCommentCommand;
import org.sanity.consoleForum.commands.DeletePostCommand;
import org.sanity.consoleForum.commands.DemoteCommand;
import org.sanity.consoleForum.commands.DislikeCommand;
import org.sanity.consoleForum.commands.EditCommentCommand;
import org.sanity.consoleForum.commands.EditPostCommand;
import org.sanity.consoleForum.commands.ExitCommand;
import org.sanity.consoleForum.commands.HelpCommand;
import org.sanity.consoleForum.commands.LikeCommand;
import org.sanity.consoleForum.commands.LoginCommand;
import org.sanity.consoleForum.commands.LogoutCommand;
import org.sanity.consoleForum.commands.PromoteCommand;
import org.sanity.consoleForum.commands.ReadCommand;
import org.sanity.consoleForum.commands.RegisterCommand;
import org.sanity.consoleForum.core.CommandManager;
import org.sanity.consoleForum.core.ConsoleForumCommandManager;
import org.sanity.consoleForum.core.ConsoleForumEngine;
import org.sanity.consoleForum.database.EntityManager;
import org.sanity.consoleForum.io.ConsoleReader;
import org.sanity.consoleForum.io.ConsoleWriter;
import org.sanity.consoleForum.io.InputReader;
import org.sanity.consoleForum.io.OutputWriter;

public class Launcher {
    public static void main(String[] args) {
        // Hello friend. I am your good employer Prakash Perhunde.
        // I found app in some github: https://github.com/DCay/Console-Forum
        // I no know code
        // Please do in java
        // I tried to do but didn't
        // Please fix my code
        // and do other things from github

        InputReader consoleReader = new ConsoleReader();
        OutputWriter consoleWriter = new ConsoleWriter();
        EntityManager entityManager = new EntityManager();

        CommandManager commandManager = new ConsoleForumCommandManager(consoleReader, consoleWriter, entityManager );


        ConsoleForumEngine consoleForumEngine = new ConsoleForumEngine(commandManager, entityManager);


       consoleForumEngine.run();
    }
}
