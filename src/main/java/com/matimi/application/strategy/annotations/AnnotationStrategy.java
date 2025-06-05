package com.matimi.application.strategy.annotations;

import com.matimi.application.strategy.annotations.validations.context.AnnotationContext;

import java.util.List;

public interface AnnotationStrategy {
    boolean isApplicable(String fieldType);
    List<String> process(AnnotationContext context);
}
