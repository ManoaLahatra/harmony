package com.matimi.domain.model;

import java.util.List;
import java.util.Objects;

public record FieldDefinition(String name, String type, List<String> annotations) {
    public FieldDefinition {
        Objects.requireNonNull(name);
        Objects.requireNonNull(type);
        annotations = List.copyOf(Objects.requireNonNullElse(annotations, List.of()));
    }

    /**
     * Generates a string representation of the field definition suitable for Java
     * code. This includes the field's annotations, type, and name.
     *
     * @return A string representation of the field definition.
     */
    public String toCodeString() {
        StringBuilder sb = new StringBuilder();
        annotations.forEach(ann -> sb.append(ann).append("\n    "));
        sb.append("private ").append(type).append(" ").append(name).append(";");
        return sb.toString();
    }
}
