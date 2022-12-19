package method;

import java.util.Arrays;

public class Vigenere implements Cypher{
    private String key;

    public Vigenere(String key){
        this.key = key;
    }

    @Override
    public byte[] encode(byte[] message) throws Exception {
        char[] keyChars = key.toCharArray();
        byte[] encodedMessage = Arrays.copyOf(message, message.length);
        for (int i = 0; i < encodedMessage.length; i++) {
            int keyNR = keyChars[i % keyChars.length] - 32;
            int c = message[i] & 255;
            if ((c >= 32) && (c <= 127)) {
                int x = c - 32;
                x = (x + keyNR) % 96;
                encodedMessage[i] = (byte) (x + 32);
            }
        }
        return encodedMessage;
    }

    @Override
    public byte[] decode(byte[] encryptedMessage) throws Exception {
        char[] keyChars = key.toCharArray();
        byte[] decryptedMessage = Arrays.copyOf(encryptedMessage, encryptedMessage.length);
        for (int i = 0; i < encryptedMessage.length; i++) {
            int keyNR = keyChars[i % keyChars.length] - 32;
            int c = decryptedMessage[i] & 255;
            if ((c >= 32) && (c <= 127)) {
                int x = c - 32;
                x = (x - keyNR + 96) % 96;
                decryptedMessage[i] = (byte) (x + 32);
            }
        }
        return decryptedMessage;
    }
}
