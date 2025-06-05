package com.matimi.application.strategy.annotations.validations.util;

import java.util.Map;
import java.util.Scanner;

public class PromptUtils {
    public static boolean confirm(Scanner scanner, String message) {
        System.out.print(message + " (y/n): ");
        return scanner.nextLine().equalsIgnoreCase("y");
    }

    public static String chooseOption(Scanner scanner, String message, Map<String, String> options) {
        System.out.println(message);
        options.forEach((key, value) -> System.out.println(key + ") " + value));
        return options.getOrDefault(scanner.nextLine(), options.values().iterator().next());
    }
}
