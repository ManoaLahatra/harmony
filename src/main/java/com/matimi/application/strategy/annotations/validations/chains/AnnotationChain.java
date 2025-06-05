package com.matimi.application.strategy.annotations.validations.chains;

import com.matimi.application.strategy.annotations.AnnotationStrategy;
import com.matimi.application.strategy.annotations.validations.context.AnnotationContext;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class AnnotationChain {
    private final List<AnnotationStrategy> strategies;

    public List<String> processAll(AnnotationContext context) {
        List<String> annotations = new ArrayList<>();
        strategies.forEach((strategy) -> {
            annotations.addAll(strategy.process(context));
        });
        return annotations;
    }
}
