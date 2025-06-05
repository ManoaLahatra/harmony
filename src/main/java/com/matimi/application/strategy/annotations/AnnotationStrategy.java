package com.matimi.application.strategy.annotations;

import java.util.Scanner;

public interface AnnotationStrategy {
    boolean shouldApply(Scanner scanner);
    String generateAnnotation();
}
