package com.matimi.application.strategy.annotations.validations;

import com.matimi.application.strategy.annotations.AnnotationStrategy;
import java.util.Scanner;

public class NotNullStrategy implements AnnotationStrategy {
    @Override
    public boolean shouldApply(Scanner scanner) {
        System.out.print("Is the field required (not null)? (y/n): ");
        return scanner.nextLine().trim().equalsIgnoreCase("n");
    }

    @Override
    public String generateAnnotation() {
        return "@NotNull";
    }
}
