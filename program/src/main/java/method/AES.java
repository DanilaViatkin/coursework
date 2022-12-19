package method;

import javax.crypto.*;

import javax.crypto.spec.IvParameterSpec;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class AES {
    private SecretKey secretKey;
    private IvParameterSpec iv;

    public AES(){
        try {
            secretKey = generateKey(256);
            iv = generateIv();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private void encryptOrDecrypt(int mode, InputStream is, OutputStream os) throws Exception {

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        if (mode == Cipher.ENCRYPT_MODE) {
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
            CipherInputStream cis = new CipherInputStream(is, cipher);
            doCopy(cis, os);
        } else if (mode == Cipher.DECRYPT_MODE) {
            cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
            CipherOutputStream cos = new CipherOutputStream(os, cipher);
            doCopy(is, cos);
        }
    }

    private void doCopy(InputStream is, OutputStream os) throws IOException {
        byte[] bytes = new byte[64];
        int numBytes;
        while ((numBytes = is.read(bytes)) != -1) {
            os.write(bytes, 0, numBytes);
        }
        os.flush();
        os.close();
        is.close();
    }

    public void encode(InputStream is, OutputStream os) throws Exception {
        encryptOrDecrypt(Cipher.ENCRYPT_MODE, is , os);
    }

    public void decode(InputStream is, OutputStream os) throws Exception {
        encryptOrDecrypt(Cipher.DECRYPT_MODE, is , os);
    }

    private SecretKey generateKey(int n) throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(n);
        SecretKey key = keyGenerator.generateKey();
        return key;
    }

    private IvParameterSpec generateIv() {
        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);
        return new IvParameterSpec(iv);
    }
}
