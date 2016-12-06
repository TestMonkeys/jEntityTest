package org.testmonkeys.jentitytest.utils;

public class StringUtils {

    private static final char[] controlChars = {'\n', '\t', '\r', '\0', '\b', '\f'};
    private static final String[] escControlChars = {"\\n", "\\t", "\\r", "\\0", "\\b", "\\f"};

    public static StringBuilder showControlChars(Object inputObj) {
        StringBuilder res = new StringBuilder(inputObj.toString());
        for (int i = 0; i < controlChars.length; i++) {
            int index = res.indexOf(String.valueOf(controlChars[i]));
            while(index != -1){
                res.replace(index, index + 1, escControlChars[i]);
                index = res.indexOf(String.valueOf(controlChars[i]));
            }
        }
        return res;
    }
}
