package org.sanity.consoleForum.io;

public class ConsoleWriter implements OutputWriter {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";

    @Override
    public void write(Object output) {
        System.out.print(output);
    }

    @Override
    public void write(Object output, Object... parameters) {
        System.out.print(String.format(output.toString(), parameters));
    }

    @Override
    public void writeLine(Object output) {
        System.out.println(output);
    }

    @Override
    public void writeLine(Object output, Object... parameters) {
        System.out.println(String.format(output.toString(), parameters));
    }

    @Override
    public void errorLine(Object output, Object... parameters) {
        System.out.println(ANSI_RED + String.format(output.toString(), parameters) + ANSI_RESET);
    }

    @Override
    public void successLine(Object output, Object... parameters) {
        System.out.println(ANSI_GREEN + String.format(output.toString(), parameters) + ANSI_RESET);
    }

    @Override
    public void importantLine(Object output, Object... parameters) {
        System.out.println(ANSI_CYAN + String.format(output.toString(), parameters) + ANSI_RESET);
    }
}
