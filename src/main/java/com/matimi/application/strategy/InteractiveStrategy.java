package com.matimi.application.strategy;

import com.matimi.domain.model.CrudConfig;
import com.matimi.domain.model.FieldDefinition;
import com.matimi.infrastructure.file.FileGenerator;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InteractiveStrategy implements CrudGenerationStrategy {
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public CrudConfig generateConfig() {
        System.out.println("=== Interactive CRUD Generator ===");

        String className = promptNonEmpty("Entity name");
        String basePackage = promptNonEmpty("Base package (ex: com.example)");
        String endpoint = promptNonEmpty("Endpoint name");
        String tableName = promptNonEmpty("Database table name");

        return new CrudConfig(className, basePackage, endpoint, tableName, promptFields(),
                java.time.Instant.now().toString(), "1.0.0");
    }

    private List<FieldDefinition> promptFields() {
        List<FieldDefinition> fields = new ArrayList<>();
        do {
            String name = promptNonEmpty("Field name");
            String type = promptNonEmpty("Field type (ex: String, int)");
            fields.add(new FieldDefinition(name, type, promptAnnotations()));
        } while (confirm("Add filed"));
        return fields;
    }

    private List<String> promptAnnotations() {
        List<String> annotations = new ArrayList<>();
        while (confirm("Add annotation?")) {
            String annotation = promptNonEmpty("Annotation (ex: @NotNull, @Size(max=255))");
            if (!annotation.startsWith("@")) {
                System.err.println("⚠️ Annotation must start with @");
                continue;
            }
            annotations.add(annotation);
        }
        return annotations;
    }

    private boolean confirm(String message) {
        System.out.print(message + " (y/n): ");
        String input = scanner.nextLine().trim();
        return input.equalsIgnoreCase("y");
    }

    private String promptNonEmpty(String message) {
        while (true) {
            System.out.print(message + ": ");
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.err.println("⚠️ This field cannot be empty");
        }
    }

    @Override
    public void generateFiles(CrudConfig config) throws Exception {
        new FileGenerator().generateAll(config);
    }
}
