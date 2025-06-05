package com.matimi.application.strategy;

import com.matimi.domain.model.CrudConfig;

public interface CrudGenerationStrategy {
    CrudConfig generateConfig() throws Exception;
    void generateFiles(CrudConfig config) throws Exception;
}
