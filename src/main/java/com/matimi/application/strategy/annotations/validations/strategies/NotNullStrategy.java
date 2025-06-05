package com.matimi.application.strategy.annotations.validations.strategies;

import com.matimi.application.strategy.annotations.AnnotationStrategy;
import com.matimi.application.strategy.annotations.validations.context.AnnotationContext;
import com.matimi.application.strategy.annotations.validations.util.PromptUtils;
import com.matimi.application.strategy.annotations.validations.util.TypeUtils;

import java.util.List;

public class NotNullStrategy implements AnnotationStrategy {
    @Override
    public boolean isApplicable(String fieldType) {
        return !TypeUtils.isPrimitiveType(fieldType) || !TypeUtils.isBooleanType(fieldType);
    }

    @Override
    public List<String> process(AnnotationContext context) {
        if (!isApplicable(context.getFieldType())) {
            return List.of();
        }

        return PromptUtils.confirm(context.getScanner(), "Is the field required (not null)?") ?
            List.of("@NotNull") :
            List.of();
    }
}
