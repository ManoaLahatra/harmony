package com.matimi.application.strategy.annotations.validations.chains;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AnnotationFlags {
    ID_PRESENT("@Id");

    private final String annotationName;

}
