package com.donus.challenge.api.account.management.util;

/**
 * @author andreia
 *
 */
public class NumberGeberatorUtil {

    public static String accountNumberGenerator() {
        String s = "";
        double d;
        for (int i = 1; i <= 10; i++) {
            d = Math.random() * 10;
            s = s + ((int) d);
        }
        return s;
    }
}
