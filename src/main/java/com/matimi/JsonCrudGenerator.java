/* (C)2025 */
package com.matimi;

import static com.matimi.TemplateEngine.generateFieldsFromDefinitions;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public class JsonCrudGenerator {
    private final TemplateEngine templateEngine;
    private final ObjectMapper objectMapper;

    public JsonCrudGenerator(String templatePath) {
        this.templateEngine = new TemplateEngine(templatePath);
        this.objectMapper = new ObjectMapper();
    }

    public void generateFromJson(String jsonFilePath) throws IOException {
        CrudConfig config = objectMapper.readValue(Path.of(jsonFilePath).toFile(), CrudConfig.class);

        Map<String, String> templateData = Map.of("ClassName", config.className(), "basePackage", config.basePackage(),
                "endpoint", config.endpoint(), "tableName", config.tableName(), "fields",
                generateFieldsFromDefinitions(config.fields()));

        generateFiles(config.className(), templateData);
    }

    private void generateFiles(String className, Map<String, String> data) {
        templateEngine.generate("Entity.tpl", "output/" + className + ".java", data);
        templateEngine.generate("Repository.tpl", "output/" + className + "Repository.java", data);
        templateEngine.generate("Service.tpl", "output/" + className + "Service.java", data);
        templateEngine.generate("Controller.tpl", "output/" + className + "Controller.java", data);
    }
}
