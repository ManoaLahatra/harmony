package com.matimi;

import picocli.CommandLine;

public class Main {
    public static void main(String[] args) {
        int exitCode = new CommandLine(new CrudCommand()).execute(args);
        System.exit(exitCode);
    }
}
