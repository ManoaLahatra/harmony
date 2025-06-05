package com.matimi.domain.validation;

import com.matimi.domain.model.CrudConfig;

public class ConfigValidator {
    public static void validate(CrudConfig config) {
        if (config.className() == null || config.className().isEmpty() || config.className().isBlank()) {
            throw new IllegalArgumentException("Class name cannot be null or empty");
        }
        if (config.basePackage() == null || config.basePackage().isEmpty() || config.basePackage().isBlank()) {
            throw new IllegalArgumentException("Base package cannot be null or empty");
        }
        if (config.endpoint() == null || config.endpoint().isEmpty() || config.endpoint().isBlank()) {
            throw new IllegalArgumentException("Endpoint cannot be null or empty");
        }
        if (config.tableName() == null || config.tableName().isEmpty() || config.tableName().isBlank()) {
            throw new IllegalArgumentException("Table name cannot be null or empty");
        }
        if (config.fields() == null || config.fields().isEmpty()) {
            throw new IllegalArgumentException("Fields cannot be null or empty");
        }
    }
}
