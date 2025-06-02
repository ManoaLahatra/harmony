package com.matimi.domain;

import java.util.List;

public record FieldDefinition(String name, String type, List<String> annotations) {
}
