package br.com.brn.feedrapi.application.utils;

import java.util.Random;

public class Utils {

    public static String getRandomHexString(int length) {
        Random r = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        while (stringBuilder.length() < length) {
            stringBuilder.append(Integer.toHexString(r.nextInt()));
        }
        return stringBuilder.toString().substring(0, length);
    }


}
