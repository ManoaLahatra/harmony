package com.matimi;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class JsonCrudGenerator {
    private final TemplateEngine templateEngine;
    private final ObjectMapper objectMapper;

    public JsonCrudGenerator(String templatePath) {
        this.templateEngine = new TemplateEngine(templatePath);
        this.objectMapper = new ObjectMapper();
    }

    public void generateFromJson(String jsonFilePath) throws IOException {
        CrudConfig config = objectMapper.readValue(
                Path.of(jsonFilePath).toFile(),
                CrudConfig.class
        );

        Map<String, String> templateData = Map.of(
                "ClassName", config.className(),
                "basePackage", config.basePackage(),
                "endpoint", config.endpoint(),
                "tableName", config.tableName(),
                "fields", generateFieldsFromDefinitions(config.fields())
        );

        generateFiles(config.className(), templateData);
    }

    private String generateFieldsFromDefinitions(List<CrudConfig.FieldDefinition> fields) {
        StringBuilder builder = new StringBuilder();
        for (CrudConfig.FieldDefinition field : fields) {
            for (String annotation : field.annotations()) {
                builder.append("    ").append(annotation).append("\n");
            }
            builder.append("    private ")
                    .append(field.type())
                    .append(" ")
                    .append(field.name())
                    .append(";\n\n");
        }
        return builder.toString().trim();
    }

    private void generateFiles(String className, Map<String, String> data) {
        templateEngine.generate("Entity.tpl", "output/" + className + ".java", data);
        templateEngine.generate("Repository.tpl", "output/" + className + "Repository.java", data);
        templateEngine.generate("Service.tpl", "output/" + className + "Service.java", data);
        templateEngine.generate("Controller.tpl", "output/" + className + "Controller.java", data);
    }
}