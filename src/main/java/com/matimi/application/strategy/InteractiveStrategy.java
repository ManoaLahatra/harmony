package com.matimi.application.strategy;

import com.matimi.application.strategy.annotations.validations.factory.AnnotationFactory;
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
            fields.add(new FieldDefinition(name, type, promptAnnotations(type)));
        } while (confirm());
        return fields;
    }

    private List<String> promptAnnotations(String fieldType) {
        return AnnotationFactory.generateAnnotations(scanner, fieldType);
    }

    private boolean confirm() {
        System.out.print("Add field" + " (y/n): ");
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
