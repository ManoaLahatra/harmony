package com.matimi.application.strategy.annotations.validations.util;

public class TypeUtils {
    public static boolean isTextType(String type) {
        return
                type.equals("String") ||
                type.equals("Text") ||
                type.equals("Character") ||
                type.equals("CharSequence");
    }

    public static boolean isPrimitiveType(String type) {
        return type.equals("int") ||
                type.equals("long") ||
                type.equals("double") ||
                type.equals("float") ||
                type.equals("boolean") ||
                type.equals("char");
    }

    public static boolean isCollectionType(String type) {
        return
                type.equals("List") ||
                type.equals("Set") ||
                type.equals("Map") ||
                type.equals("Collection") ||
                type.equals("String[]") ||
                type.equals("Text[]") ||
                type.equals("List[]") ||
                type.equals("Set[]") ||
                type.equals("Map[]") ||
                type.equals("Character[]") ||
                type.equals("CharSequence[]") ||
                type.equals("Collection[]");
    }

    public static boolean isNumericType(String type) {
        return
                type.equals("Integer") ||
                type.equals("int") ||
                type.equals("Long") ||
                type.equals("long") ||
                type.equals("Double") ||
                type.equals("double") ||
                type.equals("Float") ||
                type.equals("float") ||
                type.equals("BigDecimal");
    }

    public static boolean isBooleanType(String type) {
        return
                type.equals("Boolean") ||
                type.equals("boolean");
    }

    public static boolean isUUIDType(String type) {
        return type.equals("UUID");
    }
}
