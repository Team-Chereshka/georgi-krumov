package org.sanity.consoleForum.common;

public final class Constants {
    private Constants() {
    }

    public static final String CONSOLE_FORUM_INTRO_BANNER =
            """
                    #####################################################################
                    ##                                                                 ##
                    ##         #####  #####  #   #  #####  #####  #      #####         ##
                    ##         #      #   #  ##  #  #      #   #  #      #             ##
                    ##         #      #   #  # # #  #####  #   #  #      #####         ##
                    ##         #      #   #  #  ##      #  #   #  #      #             ##
                    ##         #####  #####  #   #  #####  #####  #####  #####         ##
                    ##                                                                 ##
                    ##                #####  #####  #####  #   #  #   #                ##
                    ##                #      #   #  #   #  #   #  ## ##                ##
                    ##                #####  #   #  #####  #   #  # # #                ##
                    ##                #      #   #  # #    #   #  #   #                ##
                    ##                #      #####  #  ##   ###   #   #                ##
                    ##                                                                 ##
                    #####################################################################
                    """;

    public static final String CONSOLE_FORUM_ABOUT =
            """
                    #####################################################################
                    ##                                                                 ##
                    ## Welcome to the Console Forum!                                   ##
                    ##                                                                 ##
                    ## This is a simple Forum application.                             ##
                    ##                                                                 ##
                    ## Guests can Login or Register.                                   ##
                    ##                                                                 ##
                    ## Logged-In Users can create Posts.                               ##
                    ## Logged-In Users can create Comments on other Posts.             ##
                    ## Logged-In Users can Rate Posts by Liking or Disliking them.     ##
                    ##                                                                 ##
                    ## The Console Forum also has Moderation and Administration.       ##
                    ## Logged-In Moderators can do everything a normal User can do.    ##
                    ## Logged-In Moderators can Modify or Delete Posts and Comments.   ##
                    ## Logged-In Administartors can do everything a Moderator can do.  ##
                    ## Logged-In Administrators can Promote or Demote Users.           ##
                    ##                                                                 ##
                    ## Type in "help" if you need help with the commands.              ##
                    ## Console Forum wishes you a happy experience! :)                 ##
                    ##                                                                 ##
                    #####################################################################
                    """;


    public static final String ADMIN_INTRODUCTION_MESSAGE =
            """
                    ## Greetings, Administrator %s!%s ##\r
                    ##                                                                 ##
                    ## Type in "help" to view the available commands.                  ##
                    ##                                                                 ##
                    #####################################################################
                    """;

    public static final String MODERATOR_INTRODUCTION_MESSAGE =
            """
                    ## Welcome, Moderator %s!%s ##\r
                    ## Keep up the good work! :)                                       ##
                    ##                                                                 ##
                    ## Type in "help" to view the available commands.                  ##
                    ##                                                                 ##
                    #####################################################################
                    """;

    public static final String USER_INTRODUCTION_MESSAGE =
            """
                    ## Welcome, %s!%s ##\r
                    ## Console Forum wishes you a happy experience! :)                 ##
                    ##                                                                 ##
                    ## Type in "help" to view the available commands.                  ##
                    ##                                                                 ##
                    #####################################################################
                    """;

    public static final String GUEST_INTRODUCTION_MESSAGE =
            """
                    ## If you are not familiar with the application, type in "about"!  ##
                    ## This will give you a detailed summary of the Console Forum.     ##
                    ##                                                                 ##
                    ## You can also type in "help" to view the available commands.     ##
                    ##                                                                 ##
                    ## Console Forum wishes you a happy experience! :)                 ##
                    ##                                                                 ##
                    #####################################################################
                    """;

    public static final String CONSOLE_FORUM_OUTPUT_EMPTY_LINE =
            "##                                                                 ##";

    public static final String CONSOLE_FORUM_OUTPUT_PREFIX = "## ";

    public static final int NOTIFICATION_DELAY = 1000;

    public static final int EXIT_NOTIFICATION_DELAY = 2000;
}
