package com.matimi;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    System.out.println("=== 🚀 Spring Boot CRUD Generator ===");
    System.out.println("1. Interactive mode");
    System.out.println("2. Generate from JSON file");
    System.out.print("Choose mode: ");

    String choice = scanner.nextLine().trim();

    if ("2".equals(choice)) {
      System.out.print("Enter JSON file path: ");
      String jsonPath = scanner.nextLine().trim();

      try {
        new JsonCrudGenerator("src/main/resources/templates").generateFromJson(jsonPath);
        System.out.println("🎉 Generation finished successfully from JSON!");
      } catch (Exception e) {
        System.err.println("❌ Error processing JSON file: " + e.getMessage());
      }
    } else {
      CrudWizard.start();
    }
  }
}
