package com.matimi;

import java.util.*;

public class CrudWizard {
    public static void start() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== 🚀 Spring Boot CRUD Generator ===");

        System.out.print("➡️ Entity name : ");
        String className = scanner.nextLine();

        System.out.print("➡️ Base package (ex: com.example.demo) : ");
        String basePackage = scanner.nextLine();

        System.out.print("➡️ REST endpoint name (ex: categories) : ");
        String endpoint = scanner.nextLine();

        System.out.print("➡️ SQL table name (ex: category) : ");
        String tableName = scanner.nextLine();

        List<FieldDefinition> fields = new ArrayList<>();

        while (true) {
            System.out.print("➕ Add field ? (y/n) : ");
            if (!scanner.nextLine().equalsIgnoreCase("y")) break;

            System.out.print("    📝 Field name : ");
            String name = scanner.nextLine();

            System.out.print("    🧬 Field type (ex: String, int, LocalDate) : ");
            String type = scanner.nextLine();

            List<String> annotations = new ArrayList<>();
            while (true) {
                System.out.print("    🔖 Annotation ? (ex: @NotNull, @Size(max=255)) (Enter for next) : ");
                String annotation = scanner.nextLine();
                if (annotation.isBlank()) break;
                annotations.add(annotation);
            }

            fields.add(new FieldDefinition(name, type, annotations));
        }

        TemplateEngine engine = new TemplateEngine("src/main/resources/templates");

        Map<String, String> templateData = Map.of(
                "ClassName", className,
                "basePackage", basePackage,
                "endpoint", endpoint,
                "tableName", tableName,
                "fields", TemplateEngine.generateFieldsFromDefinitions(fields)
        );

        engine.generate("Entity.tpl", "output/" + className + ".java", templateData);
        engine.generate("Repository.tpl", "output/" + className + "Repository.java", templateData);
        engine.generate("Service.tpl", "output/" + className + "Service.java", templateData);
        engine.generate("Controller.tpl", "output/" + className + "Controller.java", templateData);

        System.out.println("🎉 Generation finished successfully !");
    }

    public record FieldDefinition(String name, String type, List<String> annotations) {}
}
