package utils;

import java.util.Random;

public class RandomUtils {

    private static final int LETTER_LENGTH = 5;

    public static String getRandomLetters() {
        String s = "qwertyuiopasdghjklzxcbnm";
        StringBuffer newUnic = new StringBuffer();

        for (int i = 0; i < LETTER_LENGTH; i++) {
            newUnic.append(s.charAt(new Random().nextInt(s.length())));
        }
        return newUnic.toString();
    }
}
