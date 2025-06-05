package com.matimi.application.strategy.annotations.validations.context;

import com.matimi.application.strategy.annotations.validations.chains.AnnotationFlags;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AnnotationContext {
    private final Map<AnnotationFlags, Boolean> flags = new HashMap<>();
    @Getter
    private final String fieldType;
    @Getter
    private final Scanner scanner;

    public AnnotationContext(String fieldType, Scanner scanner) {
        this.fieldType = fieldType;
        this.scanner = scanner;
    }

    public boolean hasFlag(AnnotationFlags flag) {
        return flags.getOrDefault(flag, false);
    }

    public void markAsPresent(AnnotationFlags flag) {
        flags.put(flag, true);
    }
}
