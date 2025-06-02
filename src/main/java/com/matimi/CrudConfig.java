package com.matimi;

import java.util.List;

public record CrudConfig(
    String className,
    String basePackage,
    String endpoint,
    String tableName,
    List<FieldDefinition> fields,
    String generatedAt,
    String version
) {
    public record FieldDefinition(
            String name,
            String type,
            List<String> annotations
    ) {}
}
