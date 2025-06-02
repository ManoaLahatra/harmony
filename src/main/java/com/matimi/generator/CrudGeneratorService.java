package com.matimi.generator;

import com.matimi.config.CrudConfig;
import com.matimi.template.TemplateEngine;

import java.util.Map;

public class CrudGeneratorService {
    private final TemplateEngine templateEngine;

    public CrudGeneratorService(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public void generateCrud(CrudConfig config) {
        Map<String, String> templateData = createTemplateData(config);
        generateFiles(config.className(), templateData);
    }

    private Map<String, String> createTemplateData(CrudConfig config) {
        return Map.of(
                "ClassName", config.className(),
                "basePackage", config.basePackage(),
                "endpoint", config.endpoint(),
                "tableName", config.tableName(),
                "fields", TemplateEngine.generateFieldsFromDefinitions(config.fields())
        );
    }

    private void generateFiles(String className, Map<String, String> data) {
        templateEngine.generate("Entity.tpl", "output/" + className + ".java", data);
        templateEngine.generate("Repository.tpl", "output/" + className + "Repository.java", data);
        templateEngine.generate("Service.tpl", "output/" + className + "Service.java", data);
        templateEngine.generate("Controller.tpl", "output/" + className + "Controller.java", data);
    }
}
