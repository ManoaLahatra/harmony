package com.matimi;

import java.util.*;

public class CrudWizard {

    public static void start() {
        Scanner scanner = new Scanner(System.in);

        printWelcomeMessage();

        String className = prompt(scanner, "➡️ Entity name");
        String basePackage = prompt(scanner, "➡️ Base package (ex: com.example.demo)");
        String endpoint = prompt(scanner, "➡️ REST endpoint name (ex: categories)");
        String tableName = prompt(scanner, "➡️ SQL table name (ex: category)");

        List<FieldDefinition> fields = promptFieldDefinitions(scanner);

        Map<String, String> templateData = Map.of(
                "ClassName", className,
                "basePackage", basePackage,
                "endpoint", endpoint,
                "tableName", tableName,
                "fields", TemplateEngine.generateFieldsFromDefinitions(fields)
        );

        TemplateEngine engine = new TemplateEngine("src/main/resources/templates");

        generateFiles(engine, className, templateData);

        System.out.println("🎉 Generation finished successfully !");
    }

    private static void printWelcomeMessage() {
        System.out.println("=== 🚀 Spring Boot CRUD Generator ===");
    }

    private static String prompt(Scanner scanner, String message) {
        System.out.print(message + " : ");
        return scanner.nextLine().trim();
    }

    private static List<FieldDefinition> promptFieldDefinitions(Scanner scanner) {
        List<FieldDefinition> fields = new ArrayList<>();

        while (true) {
            System.out.print("➕ Add field ? (y/n) : ");
            String choice = scanner.nextLine().trim();
            if (!choice.equalsIgnoreCase("y")) {
                break;
            }

            String name = prompt(scanner, "    📝 Field name");
            String type = prompt(scanner, "    🧬 Field type (ex: String, int, LocalDate)");

            List<String> annotations = new ArrayList<>();
            while (true) {
                String annotation = prompt(
                        scanner,
                        "    🔖 Annotation ? (ex: @NotNull, @Size(max=255)) (Enter for next)");
                if (annotation.isBlank()) {
                    break;
                }
                annotations.add(annotation);
            }

            fields.add(new FieldDefinition(name, type, annotations));
        }

        return fields;
    }

    private static void generateFiles(TemplateEngine engine, String className, Map<String, String> data) {
        engine.generate("Entity.tpl", "output/" + className + ".java", data);
        engine.generate("Repository.tpl", "output/" + className + "Repository.java", data);
        engine.generate("Service.tpl", "output/" + className + "Service.java", data);
        engine.generate("Controller.tpl", "output/" + className + "Controller.java", data);
    }

    public record FieldDefinition(String name, String type, List<String> annotations) {}
}
