package com.matimi;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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

    public static String generateFields(Map<String, String> fields) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, String> entry : fields.entrySet()) {
            stringBuilder
                    .append("    private ")
                    .append(entry.getValue()).append(" ")
                    .append(entry.getKey()).append(";\n");
        }

        return stringBuilder.toString();
    }
}
