package ru.axkip.ectocontrol.utils;

public class StringUtils {
    private StringUtils() {
    }

    public static String clearString(String input) {
        if (input == null) {
            return null;
        }
        //строка без пробелов
        var noSpaces = input.trim();

        //удалить кавычки
        if (noSpaces.startsWith("\"") && noSpaces.endsWith("\"")) {
            return noSpaces.substring(1, noSpaces.length() - 1);
        } else {
            return noSpaces;
        }
    }
}
