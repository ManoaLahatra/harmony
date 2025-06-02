package com.matimi.config;

import com.matimi.domain.FieldDefinition;

import java.util.List;

public record CrudConfig(String className, String basePackage, String endpoint, String tableName,
                         List<FieldDefinition> fields, String generatedAt, String version) {
}
