package com.matimi.application.strategy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.matimi.domain.model.CrudConfig;
import com.matimi.infrastructure.file.FileGenerator;
import java.nio.file.Path;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class JsonStrategy implements CrudGenerationStrategy {
    private final String jsonPath;
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public CrudConfig generateConfig() throws Exception {
        return mapper.readValue(Path.of(jsonPath).toFile(), CrudConfig.class);
    }

    @Override
    public void generateFiles(CrudConfig config) throws Exception {
        new FileGenerator().generateAll(config);
    }
}
