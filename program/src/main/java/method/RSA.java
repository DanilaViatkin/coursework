package method;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.io.*;
import java.nio.file.Files;
import java.security.*;
import java.security.spec.EncodedKeySpec;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;

public class RSA{
    private static Cipher cipher;
    private static final String ALGORITHM = "RSA";
    private static final File PUBLIC_KEY_FILE = new File("public.key");
    private static final File PRIVATE_KEY_FILE = new File("private.key");
    private static PublicKey publicKey;
    private static PrivateKey privateKey;

    static {
        try {
            cipher = Cipher.getInstance(ALGORITHM);

            byte[] publicKeyBytes = Files.readAllBytes(PUBLIC_KEY_FILE.toPath());
            byte[] privateKeyBytes = Files.readAllBytes(PRIVATE_KEY_FILE.toPath());



            if (publicKeyBytes.length != 0 && privateKeyBytes.length != 0) {
                //TODO delete
                System.out.println("Hash of public key: " + Arrays.hashCode(publicKeyBytes));
                System.out.println("Hash of private key: " + Arrays.hashCode(privateKeyBytes));

                KeyFactory keyFactory = KeyFactory.getInstance("RSA");

                EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);
                EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);

                publicKey = keyFactory.generatePublic(publicKeySpec);
                privateKey = keyFactory.generatePrivate(privateKeySpec);

            } else {
                KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);

                KeyPair keyPair = keyPairGenerator.generateKeyPair();
                privateKey = keyPair.getPrivate();
                publicKey = keyPair.getPublic();

                FileOutputStream fosPublicKey = new FileOutputStream(PUBLIC_KEY_FILE);
                FileOutputStream fosPrivateKey = new FileOutputStream(PRIVATE_KEY_FILE);

                fosPublicKey.write(publicKey.getEncoded());
                fosPrivateKey.write(privateKey.getEncoded());

                fosPrivateKey.close();
                fosPublicKey.close();
            }
        } catch (IOException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
    }

    public byte[] encode(byte[] message) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(message);
    }

    public byte[] decode(byte[] encodeMessage) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(encodeMessage);
    }

}
