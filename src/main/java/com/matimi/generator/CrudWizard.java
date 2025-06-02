package com.matimi.generator;

import com.matimi.config.CrudConfig;
import com.matimi.domain.FieldDefinition;
import com.matimi.template.TemplateEngine;

import java.util.*;

public class CrudWizard {
    private final CrudGeneratorService generatorService;
    private final Scanner scanner;

    public CrudWizard(TemplateEngine templateEngine) {
        this.generatorService = new CrudGeneratorService(templateEngine);
        this.scanner = new Scanner(System.in);
    }

    public static void start() {
        TemplateEngine engine = new TemplateEngine("src/main/resources/templates");
        new CrudWizard(engine).startWizard();
    }

    private void startWizard() {
        printWelcomeMessage();

        String className = prompt(scanner, "➡️ Entity name");
        String basePackage = prompt(scanner, "➡️ Base package (ex: com.example.demo)");
        String endpoint = prompt(scanner, "➡️ REST endpoint name (ex: categories)");
        String tableName = prompt(scanner, "➡️ SQL table name (ex: category)");

        List<FieldDefinition> fields = promptFieldDefinitions(scanner);

        CrudConfig config = new CrudConfig(
                className,
                basePackage,
                endpoint,
                tableName,
                fields,
                java.time.LocalDateTime.now().toString(),
                "1.0"
        );

        generatorService.generateCrud(config);
        System.out.println("🎉 Generation finished successfully!");
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
                String annotation = prompt(scanner,
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
}
