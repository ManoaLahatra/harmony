package com.matimi.application.strategy.annotations.validations.strategies;

import com.matimi.application.strategy.annotations.AnnotationStrategy;
import com.matimi.application.strategy.annotations.validations.chains.AnnotationFlags;
import com.matimi.application.strategy.annotations.validations.context.AnnotationContext;
import com.matimi.application.strategy.annotations.validations.util.PromptUtils;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class GeneratedValueStrategy implements AnnotationStrategy {
    private static final Map<String, String> STRATEGIES = Map.of(
            "1", "AUTO",
            "2", "IDENTITY",
            "3", "SEQUENCE",
            "4", "TABLE"
    );

    @Override
    public boolean isApplicable(String fieldType) {
        return true;
    }

    @Override
    public List<String> process(AnnotationContext context) {
        if (!context.hasFlag(AnnotationFlags.ID_PRESENT)) { return List.of(); }

        return PromptUtils.confirm(context.getScanner(), "Add @GeneratedValue?")
                ? List.of(generateAnnotation(context.getScanner()))
                : List.of();
    }

    private String generateAnnotation(Scanner scanner) {
        String strategy = PromptUtils.chooseOption(
                scanner,
                "Choose strategy:",
                STRATEGIES
        );
        return "@GeneratedValue(strategy = GenerationType." + strategy + ")";
    }
}
