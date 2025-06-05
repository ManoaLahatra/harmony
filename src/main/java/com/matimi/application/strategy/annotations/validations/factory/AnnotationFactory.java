package com.matimi.application.strategy.annotations.validations.factory;

import com.matimi.application.strategy.annotations.validations.chains.AnnotationChain;
import com.matimi.application.strategy.annotations.validations.context.AnnotationContext;
import com.matimi.application.strategy.annotations.validations.strategies.GeneratedValueStrategy;
import com.matimi.application.strategy.annotations.validations.strategies.IdStrategy;
import com.matimi.application.strategy.annotations.validations.strategies.NotNullStrategy;
import com.matimi.application.strategy.annotations.validations.strategies.SizeStrategy;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AnnotationFactory {
    private static final AnnotationChain STRATEGIES = new AnnotationChain(
            List.of(new NotNullStrategy(), new SizeStrategy(), new IdStrategy(), new GeneratedValueStrategy()));

    public static List<String> generateAnnotations(Scanner scanner, String fieldType) {
        AnnotationContext context = new AnnotationContext(fieldType, scanner);

        return new ArrayList<>(STRATEGIES.processAll(context));
    }
}
