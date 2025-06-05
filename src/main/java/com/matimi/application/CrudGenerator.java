package com.matimi.application;

import com.matimi.application.strategy.CrudGenerationStrategy;
import com.matimi.domain.validation.ConfigValidator;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CrudGenerator {
    private final CrudGenerationStrategy strategy;

    public void execute() throws Exception {
        var config = strategy.generateConfig();
        ConfigValidator.validate(config);
        strategy.generateFiles(config);
    }
}
