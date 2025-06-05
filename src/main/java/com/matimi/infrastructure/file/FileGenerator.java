package com.matimi.infrastructure.file;

import com.matimi.domain.model.CrudConfig;
import com.matimi.domain.model.FieldDefinition;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FileGenerator {
    private final TemplateEngine templateEngine = new TemplateEngine("src/main/resources/templates");

    public void generateAll(CrudConfig config) throws IOException {
        Map<String, String> templateData = Map.of("ClassName", config.className(), "basePackage", config.basePackage(),
                "tableName", config.tableName(), "endpoint", config.endpoint(), "fields",
                generateFieldsString(config.fields()));
        generateFile("Entity.tpl", config.className() + ".java", templateData);
        generateFile("Repository.tpl", config.className() + "Repository.java", templateData);
        generateFile("Service.tpl", config.className() + "Service.java", templateData);
        generateFile("Controller.tpl", config.className() + "Controller.java", templateData);
    }

    private String generateFieldsString(List<FieldDefinition> fields) {
        return fields.stream().map(FieldDefinition::toCodeString).collect(Collectors.joining("\n\n    "));
    }

    private void generateFile(String template, String output, Map<String, String> data) throws IOException {
        try {
            String content = templateEngine.render(template, data);
            Path outputPath = Path.of("output", output);
            Files.createDirectories(outputPath.getParent());
            Files.writeString(outputPath, content);
        } catch (IOException e) {
            throw new IOException("Failed to generate file: " + output, e);
        }
    }

}
