package method;

public class RC4 implements Cypher{
    private final byte[] S = new byte[256];
    private final byte[] T = new byte[256];
    private final int keylen;

    public RC4(final byte[] key) {
        if (key.length < 1 || key.length > 256) {
            throw new IllegalArgumentException(
                    "key must be between 1 and 256 bytes");
        } else {
            keylen = key.length;
            for (int i = 0; i < 256; i++) {
                S[i] = (byte) i;
                T[i] = key[i % keylen];
            }
            int j = 0;
            byte tmp;
            for (int i = 0; i < 256; i++) {
                j = (j + S[i] + T[i]) & 0xFF;
                tmp = S[j];
                S[j] = S[i];
                S[i] = tmp;
            }
        }
    }

    @Override
    public byte[] encode(byte[] message) throws Exception {
        byte[] ciphertext = new byte[message.length];
        int i = 0, j = 0, k, t;
        byte tmp;
        for (int counter = 0; counter < message.length; counter++) {
            i = (i + 1) & 0xFF;
            j = (j + S[i]) & 0xFF;
            tmp = S[j];
            S[j] = S[i];
            S[i] = tmp;
            t = (S[i] + S[j]) & 0xFF;
            k = S[t];
            ciphertext[counter] = (byte) (message[counter] ^ k);
        }
        return ciphertext;
    }

    @Override
    public byte[] decode(byte[] encryptedMessage) throws Exception {
        return encode(encryptedMessage);
    }
}
