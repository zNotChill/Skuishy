package lol.aabss.skuishy.other;

import org.jetbrains.annotations.Nullable;

public class CaesarCipher {
    public static final String alpha = "abcdefghijklmnopqrstuvwxyz";

    public static String encrypt(String msg, @Nullable Integer shiftKey) {
        if (shiftKey == null){
            shiftKey = 7;
        }
        String message = msg;
        message = message.toLowerCase();
        StringBuilder cipherText = new StringBuilder();
        for (int ii = 0; ii < message.length(); ii++) {
            int charPosition = alpha.indexOf(message.charAt(ii));
            int keyVal = (shiftKey + charPosition) % 26;
            char replaceVal = alpha.charAt(keyVal);
            cipherText.append(replaceVal);
        }
        return cipherText.toString();
    }

    public static String decrypt(String msg, @Nullable Integer shiftKey) {
        if (shiftKey == null){
            shiftKey = 7;
        }
        String message = msg;
        message = message.toLowerCase();
        StringBuilder cipherText = new StringBuilder();
        for (int ii = 0; ii < message.length(); ii++) {
            int charPosition = alpha.indexOf(message.charAt(ii));
            int keyVal = (charPosition - shiftKey) % 26;
            if (keyVal < 0) {
                keyVal = alpha.length() + keyVal;
            }
            char replaceVal = alpha.charAt(keyVal);
            cipherText.append(replaceVal);
        }
        return cipherText.toString();
    }
}
