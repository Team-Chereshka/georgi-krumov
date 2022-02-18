package org.sanity.consoleForum.commands;

import org.sanity.consoleForum.core.Principal;
import org.sanity.consoleForum.database.EntityManager;
import org.sanity.consoleForum.io.InputReader;
import org.sanity.consoleForum.io.OutputWriter;

import java.io.IOException;
import java.util.List;

public interface Command
{
    InputReader getConsoleReader();
    void setConsoleReader(InputReader inputReader);

    OutputWriter getConsoleWriter();
    void setConsoleWriter(OutputWriter outputWriter);

    List<String> getParameters();
    void setParameters(List<String> parameters);

    Principal getPrincipal();
    void setPrincipal(Principal principal);

    default EntityManager getEntityManager(){return null;}
    default void setEntityManager(EntityManager entityManager){}

    boolean getResetsConsole();
    void setResetsConsole(boolean resetsConsole);

    void execute() throws IOException, InterruptedException;
}
