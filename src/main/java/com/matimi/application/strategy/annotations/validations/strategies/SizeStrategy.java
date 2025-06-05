package com.matimi.application.strategy.annotations.validations.strategies;

import com.matimi.application.strategy.annotations.AnnotationStrategy;
import com.matimi.application.strategy.annotations.validations.context.AnnotationContext;
import com.matimi.application.strategy.annotations.validations.util.PromptUtils;
import com.matimi.application.strategy.annotations.validations.util.TypeUtils;
import java.util.List;

public class SizeStrategy implements AnnotationStrategy {

    @Override
    public boolean isApplicable(String fieldType) {
        return TypeUtils.isTextType(fieldType) || TypeUtils.isCollectionType(fieldType);
    }

    @Override
    public List<String> process(AnnotationContext context) {
        if (!isApplicable(context.getFieldType())) {
            return List.of();
        }

        if (PromptUtils.confirm(context.getScanner(), "Add @Size constraint?")) {
            System.out.print("Enter min,max (ex: 2,50): ");
            String[] parts = context.getScanner().nextLine().split(",");
            return List.of(String.format("@Size(min=%s, max=%s)", parts[0], parts[1]));
        }
        return List.of();
    }
}
