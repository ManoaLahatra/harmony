package com.matimi;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class TemplateEngine {
    private final String templateDir;

    public TemplateEngine(String templatePath) {
        this.templateDir = templatePath;
    }

    public void generate(String templateName, String outputPath, Map<String, String> values) {
        try {
            String content = Files.readString(Path.of(templateDir, templateName));

            for (Map.Entry<String, String> entry : values.entrySet()) {
                content = content.replace("{{" + entry.getKey() + "}}", entry.getValue());
            }

            Path path = Path.of(outputPath);
            Files.createDirectories(path.getParent());
            Files.writeString(path, content);
            System.out.println("✅ File generated : " + outputPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String generateFieldsFromDefinitions(List<CrudWizard.FieldDefinition> fields) {
        StringBuilder builder = new StringBuilder();
        for (CrudWizard.FieldDefinition field : fields) {
            for (String annotation : field.annotations()) {
                builder.append("    ").append(annotation).append("\n");
            }
            builder.append("    private ").append(field.type()).append(" ").append(field.name()).append(";\n\n");
        }
        return builder.toString().trim();
    }

}
