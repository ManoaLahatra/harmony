package com.matimi.domain.model;

import java.util.List;
import java.util.Objects;

public record CrudConfig(String className, String basePackage, String endpoint, String tableName,
        List<FieldDefinition> fields, String generatedAt, String version) {
    public CrudConfig {
        Objects.requireNonNull(className);
        Objects.requireNonNull(fields);
        fields = List.copyOf(fields);
    }
}
