package com.matimi.application.strategy.annotations.validations.strategies;

import com.matimi.application.strategy.annotations.AnnotationStrategy;
import com.matimi.application.strategy.annotations.validations.chains.AnnotationFlags;
import com.matimi.application.strategy.annotations.validations.context.AnnotationContext;
import com.matimi.application.strategy.annotations.validations.util.PromptUtils;
import com.matimi.application.strategy.annotations.validations.util.TypeUtils;

import java.util.List;

public class IdStrategy implements AnnotationStrategy {
    @Override
    public boolean isApplicable(String fieldType) {
        return TypeUtils.isNumericType(fieldType) || TypeUtils.isUUIDType(fieldType);
    }

    @Override
    public List<String> process(AnnotationContext context) {
        if (!isApplicable(context.getFieldType())) {
            return List.of();
        }

        if (PromptUtils.confirm(context.getScanner(), "Is this field the primary key?")) {
            context.markAsPresent(AnnotationFlags.ID_PRESENT);
            return List.of(AnnotationFlags.ID_PRESENT.getAnnotationName());
        }
        return List.of();
    }
}
