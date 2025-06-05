package com.matimi;

import com.matimi.application.CrudGenerator;
import com.matimi.application.strategy.InteractiveStrategy;
import com.matimi.application.strategy.JsonStrategy;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== CRUD Generator ===");
        System.out.println("1. Interactive mode");
        System.out.println("2. JSON mode");
        System.out.print("Choice: ");

        try {
            CrudGenerator generator = switch (scanner.nextLine()) {
                case "2" -> new CrudGenerator(new JsonStrategy(promptJsonPath(scanner)));
                default -> new CrudGenerator(new InteractiveStrategy());
            };
            generator.execute();
            System.out.println("✅ Generation successful!");
        } catch (Exception e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
    }

    private static String promptJsonPath(Scanner scanner) {
        System.out.print("JSON file path: ");
        return scanner.nextLine().trim();
    }
}
