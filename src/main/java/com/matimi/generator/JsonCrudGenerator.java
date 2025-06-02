package com.matimi.generator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.matimi.config.CrudConfig;
import com.matimi.template.TemplateEngine;

import java.io.IOException;
import java.nio.file.Path;

public class JsonCrudGenerator {
    private final CrudGeneratorService generatorService;
    private final ObjectMapper objectMapper;

    public JsonCrudGenerator(TemplateEngine templateEngine) {
        this.generatorService = new CrudGeneratorService(templateEngine);
        this.objectMapper = new ObjectMapper();
    }

    public void generateFromJson(String jsonFilePath) throws IOException {
        CrudConfig config = objectMapper.readValue(Path.of(jsonFilePath).toFile(), CrudConfig.class);
        generatorService.generateCrud(config);
    }
}
