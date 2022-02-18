package org.sanity.consoleForum.common;

public final class Validator {
    public static boolean isNull(Object obj) {
        return obj == null;
    }

    public static boolean isNullOrEmpty(String text) {
        return text == null || text.isBlank();
    }

    public static boolean isAlphanumeric(String text) {
        return text.matches("[a-zA-Z0-9]*");
    }
}
