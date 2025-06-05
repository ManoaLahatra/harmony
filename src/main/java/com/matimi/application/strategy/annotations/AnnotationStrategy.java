package com.matimi.application.strategy.annotations;

import java.util.List;
import java.util.Scanner;

public interface AnnotationStrategy {
    boolean isApplicable(String fieldType);
    boolean shouldApply(Scanner scanner);
    String generateAnnotation();
    default List<String> getRequiredImports() {
        return List.of();
    }
}
