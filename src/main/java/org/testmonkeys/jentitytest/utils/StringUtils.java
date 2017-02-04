package org.testmonkeys.jentitytest.utils;

public final class StringUtils {

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

    public static String charArrayToString(char[] array){
        if (array == null || array.length == 0)
            return null;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++){
            sb.append(showControlChars(array[i]).append(", "));
        }
        return sb.deleteCharAt(sb.lastIndexOf(",")).toString();
    }
}
