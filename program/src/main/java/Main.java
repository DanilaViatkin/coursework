import method.*;

import java.io.*;
import java.nio.file.Files;
import java.util.*;

public class Main {
    private static String stringKey = "stringkey123";
    private static long[] intervals = new long[256];

    public static void main(String[] args) throws Exception {

        File picturePath = new File("D:\\Учеба\\7cem\\Курсач\\Новая папка\\plain\\plain image.jpg");
        File gifPath = new File ("D:\\Учеба\\7cem\\Курсач\\Новая папка\\plain\\plain gif.gif");
        File txtPath = new File ("D:\\Учеба\\7cem\\Курсач\\Новая папка\\plain\\plain text.txt");

        //doAes(picturePath, gifPath, txtPath);
        //doDes(picturePath, gifPath, txtPath);
        //doRC4(picturePath, gifPath,txtPath);
        //doRSA(txtPath);
        doVigenere(picturePath, gifPath, txtPath);

    }

    private static void doVigenere(File picturePath, File gifPath, File txtPath) throws Exception{
        byte[] encodedMessage = encryptAndDecryptFileVigenere(picturePath);
        outHist(getHist(encodedMessage), "D:\\Учеба\\7cem\\Курсач\\Новая папка\\hist\\Vigenere\\Vigenere hist picture.txt");
        doHi2(picturePath, encodedMessage.length);
        encodedMessage = encryptAndDecryptFileVigenere(gifPath);
        outHist(getHist(encodedMessage), "D:\\Учеба\\7cem\\Курсач\\Новая папка\\hist\\Vigenere\\Vigenere hist gif.txt");
        doHi2(gifPath, encodedMessage.length);
        encodedMessage = encryptAndDecryptFileVigenere(txtPath);
        outHist(getHist(encodedMessage), "D:\\Учеба\\7cem\\Курсач\\Новая папка\\hist\\Vigenere\\Vigenere hist text.txt");
        doHi2(txtPath, encodedMessage.length);
    }

    private static void doRSA(File txtPath) throws Exception{
        byte[] encodedMessage = encryptAndDecryptTextRSA(txtPath);
        outHist(getHist(encodedMessage), "D:\\Учеба\\7cem\\Курсач\\Новая папка\\hist\\RSA\\RSA hist text.txt");
        doHi2(txtPath, encodedMessage.length);
    }

    private static void doRC4(File picturePath,File gifPath, File txtPath) throws Exception{
        Random random = new Random();
        byte[] key = new byte[256];
        random.nextBytes(key);

        byte[] encodedMessage = encryptAndDecryptFileRC4(picturePath, key);
        outHist(getHist(encodedMessage), "D:\\Учеба\\7cem\\Курсач\\Новая папка\\hist\\RC4\\RC4 hist picture.txt");
        doHi2(picturePath, encodedMessage.length);

        encodedMessage = encryptAndDecryptFileRC4(gifPath, key);
        outHist(getHist(encodedMessage), "D:\\Учеба\\7cem\\Курсач\\Новая папка\\hist\\RC4\\RC4 hist gif.txt");
        doHi2(gifPath, encodedMessage.length);

        encodedMessage = encryptAndDecryptFileRC4(txtPath, key);
        outHist(getHist(encodedMessage), "D:\\Учеба\\7cem\\Курсач\\Новая папка\\hist\\RC4\\RC4 hist text.txt");
        doHi2(txtPath, encodedMessage.length);
    }

    private static void doAes(File picturePath,File gifPath,File txtPath) throws Exception{
        encryptAndDecryptPictureAES(picturePath);
        byte[] encodedMessage = Files.readAllBytes(
                new File("D:\\Учеба\\7cem\\Курсач\\Новая папка\\encrypted\\AES encrypted picture.txt")
                        .toPath());
        outHist(getHist(encodedMessage), "D:\\Учеба\\7cem\\Курсач\\Новая папка\\hist\\AES\\AES hist picture.txt");
        doHi2(picturePath, encodedMessage.length);

        encryptAndDecryptGifAES(gifPath);
        encodedMessage = Files.readAllBytes(
                new File("D:\\Учеба\\7cem\\Курсач\\Новая папка\\encrypted\\AES encrypted gif.txt")
                        .toPath());
        outHist(getHist(encodedMessage), "D:\\Учеба\\7cem\\Курсач\\Новая папка\\hist\\AES\\AES hist gif.txt");
        doHi2(gifPath, encodedMessage.length);

        encryptAndDecryptTextAES(txtPath);
        encodedMessage = Files.readAllBytes(
                new File("D:\\Учеба\\7cem\\Курсач\\Новая папка\\encrypted\\AES encrypted text.txt")
                        .toPath());
        outHist(getHist(encodedMessage), "D:\\Учеба\\7cem\\Курсач\\Новая папка\\hist\\AES\\AES hist text.txt");
        doHi2(txtPath, encodedMessage.length);
    }

    private static void doDes(File picturePath,File gifPath,File txtPath) throws Exception{
        encryptAndDecryptPictureDES(picturePath);
        byte[] encodedMessage = Files.readAllBytes(
                new File("D:\\Учеба\\7cem\\Курсач\\Новая папка\\encrypted\\DES encrypted picture.txt")
                        .toPath());
        outHist(getHist(encodedMessage), "D:\\Учеба\\7cem\\Курсач\\Новая папка\\hist\\DES\\DES hist picture.txt");
        doHi2(picturePath, encodedMessage.length);

        encryptAndDecryptGifDES(gifPath);
        encodedMessage = Files.readAllBytes(
                new File("D:\\Учеба\\7cem\\Курсач\\Новая папка\\encrypted\\DES encrypted gif.txt")
                        .toPath());
        outHist(getHist(encodedMessage), "D:\\Учеба\\7cem\\Курсач\\Новая папка\\hist\\DES\\DES hist gif.txt");
        doHi2(gifPath, encodedMessage.length);

        encryptAndDecryptTextDES(txtPath);
        encodedMessage = Files.readAllBytes(
                new File("D:\\Учеба\\7cem\\Курсач\\Новая папка\\encrypted\\DES encrypted text.txt")
                        .toPath());
        outHist(getHist(encodedMessage), "D:\\Учеба\\7cem\\Курсач\\Новая папка\\hist\\DES\\DES hist text.txt");
        doHi2(txtPath, encodedMessage.length);
    }

    private static byte[] encryptAndDecryptFileVigenere(File path) throws Exception {
        Vigenere vigenere = new Vigenere(stringKey);
        byte[] pictureBytes = Files.readAllBytes(path.toPath());
        byte[] encodedMessage = vigenere.encode(pictureBytes);
        System.out.println("Encrypted message length: " + encodedMessage.length);
        byte[] decryptedMessage = vigenere.decode(encodedMessage);
        System.out.println("pictureBytes == decryptedMessage: " +
                Arrays.equals(pictureBytes, decryptedMessage));
        return encodedMessage;
    }

    private static void encryptAndDecryptPictureDES(File picturePath) throws Exception {
        DES des = new DES();
        FileInputStream fis = new FileInputStream(picturePath);
        FileOutputStream fos = new FileOutputStream("D:\\Учеба\\7cem\\Курсач\\Новая папка\\encrypted\\DES encrypted picture.txt");
        des.encode(stringKey, fis, fos);


        FileInputStream fis2 = new FileInputStream("D:\\Учеба\\7cem\\Курсач\\Новая папка\\encrypted\\DES encrypted picture.txt");
        FileOutputStream fos2 = new FileOutputStream("D:\\Учеба\\7cem\\Курсач\\Новая папка\\decrypted\\DES decrypted picture.jpg");
        des.decode(stringKey,fis2, fos2);
        fis.close();
        fis2.close();
        fos.close();
        fos2.close();
    }

    private static void encryptAndDecryptGifDES(File picturePath) throws Exception {
        DES des = new DES();
        FileInputStream fis = new FileInputStream(picturePath);
        FileOutputStream fos = new FileOutputStream("D:\\Учеба\\7cem\\Курсач\\Новая папка\\encrypted\\DES encrypted gif.txt");
        des.encode(stringKey, fis, fos);


        FileInputStream fis2 = new FileInputStream("D:\\Учеба\\7cem\\Курсач\\Новая папка\\encrypted\\DES encrypted gif.txt");
        FileOutputStream fos2 = new FileOutputStream("D:\\Учеба\\7cem\\Курсач\\Новая папка\\decrypted\\DES decrypted gif.gif");
        des.decode(stringKey,fis2, fos2);
        fis.close();
        fis2.close();
        fos.close();
        fos2.close();
    }

    private static void encryptAndDecryptTextDES(File picturePath) throws Exception {
        DES des = new DES();
        FileInputStream fis = new FileInputStream(picturePath);
        FileOutputStream fos = new FileOutputStream("D:\\Учеба\\7cem\\Курсач\\Новая папка\\encrypted\\DES encrypted text.txt");
        des.encode(stringKey, fis, fos);


        FileInputStream fis2 = new FileInputStream("D:\\Учеба\\7cem\\Курсач\\Новая папка\\encrypted\\DES encrypted text.txt");
        FileOutputStream fos2 = new FileOutputStream("D:\\Учеба\\7cem\\Курсач\\Новая папка\\decrypted\\DES decrypted text.txt");
        des.decode(stringKey,fis2, fos2);
        fis.close();
        fis2.close();
        fos.close();
        fos2.close();
    }

    private static void encryptAndDecryptPictureAES(File picturePath) throws Exception {
        AES aes = new AES();
        FileInputStream fis = new FileInputStream(picturePath);
        FileOutputStream fos =
                new FileOutputStream("D:\\Учеба\\7cem\\Курсач\\Новая папка\\encrypted\\AES encrypted picture.txt");
        aes.encode(fis, fos);


        FileInputStream fis2 = new FileInputStream("D:\\Учеба\\7cem\\Курсач\\Новая папка\\encrypted\\AES encrypted picture.txt");
        FileOutputStream fos2 = new FileOutputStream("D:\\Учеба\\7cem\\Курсач\\Новая папка\\decrypted\\AES decrypted picture.jpg");
        aes.decode(fis2, fos2);
        fis.close();
        fis2.close();
        fos.close();
        fos2.close();
    }

    private static void encryptAndDecryptGifAES(File gifPath) throws Exception {
        AES aes = new AES();
        FileInputStream fis = new FileInputStream(gifPath);
        FileOutputStream fos =
                new FileOutputStream("D:\\Учеба\\7cem\\Курсач\\Новая папка\\encrypted\\AES encrypted gif.txt");
        aes.encode(fis, fos);


        FileInputStream fis2 = new FileInputStream("D:\\Учеба\\7cem\\Курсач\\Новая папка\\encrypted\\AES encrypted gif.txt");
        FileOutputStream fos2 = new FileOutputStream("D:\\Учеба\\7cem\\Курсач\\Новая папка\\decrypted\\AES decrypted gif.gif");
        aes.decode(fis2, fos2);
        fis.close();
        fis2.close();
        fos.close();
        fos2.close();
    }

    private static void encryptAndDecryptTextAES(File gifPath) throws Exception {
        AES aes = new AES();
        FileInputStream fis = new FileInputStream(gifPath);
        FileOutputStream fos =
                new FileOutputStream("D:\\Учеба\\7cem\\Курсач\\Новая папка\\encrypted\\AES encrypted text.txt");
        aes.encode(fis, fos);


        FileInputStream fis2 = new FileInputStream("D:\\Учеба\\7cem\\Курсач\\Новая папка\\encrypted\\AES encrypted text.txt");
        FileOutputStream fos2 = new FileOutputStream("D:\\Учеба\\7cem\\Курсач\\Новая папка\\decrypted\\AES decrypted text.txt");
        aes.decode(fis2, fos2);
        fis.close();
        fis2.close();
        fos.close();
        fos2.close();
    }

    private static byte[] encryptAndDecryptTextRSA(File picturePath) throws Exception {
        byte[] pictureBytes = Files.readAllBytes(picturePath.toPath());
        if ( pictureBytes.length < 245){
            RSA rsa = new RSA();
            byte[] encryptedPictureBytes = rsa.encode(pictureBytes);

            System.out.println("Picture size: " + pictureBytes.length);
            System.out.println("Length of encrypted message bytes: " + encryptedPictureBytes.length);

            byte[] decryptedPictureBytes = rsa.decode(encryptedPictureBytes);
            System.out.println("pictureBytes == decryptedMessage: " +
                    Arrays.equals(pictureBytes, decryptedPictureBytes));
            return encryptedPictureBytes;
        } else {
            System.out.println("Data must not be longer than 245 bytes.");
        }
        return null;
    }

    private static byte[] encryptAndDecryptFileRC4(File picturePath, byte[] key) throws Exception {
        RC4 method = new RC4(key);
        byte[] pictureBytes = Files.readAllBytes(picturePath.toPath());
        //System.out.println("File size: " + pictureBytes.length);
        byte[] encryptedMessage = method.encode(pictureBytes);
        //System.out.println("Length of encrypted message bytes: " + encryptedMessage.length);
        RC4 rc4 = new RC4(key);
        byte[] decryptedMessage = rc4.decode(encryptedMessage);
        System.out.println("fileBytes == decryptedMessage: " +
                Arrays.equals(pictureBytes, decryptedMessage));
        return encryptedMessage;
    }

    private static int[] getHist(byte[] bytes){
        int[] hist = new int[bytes.length];
        for (int i = 0; i < bytes.length; i++){
            int value = bytes[i] & 0xff;
            hist[i] = value;
            intervals[value]++;
        }
        return hist;
    }

    private static void outHist(int[] bytes, String filename) throws Exception{
        BufferedWriter ow = null;
        ow = new BufferedWriter(new FileWriter(filename));
        for (int i = 0; i < bytes.length; i++) {

            ow.write(bytes[i]+"");
            ow.newLine();
        }
        ow.flush();
        ow.close();
    }


    private static void doHi2(File path, double sum) {
        System.out.println();
        double sumOf8 = intervals[0];
        double nii = 0.03125*sum;
        double finalSum = 0;
        double ni = 0;
        intervals[0] = 0;
        for(int i = 1; i < intervals.length; i++){
            if(i % 8 == 0){
                ni = sumOf8;
                finalSum += (ni-nii)*(ni-nii)/nii;
                sumOf8 = 0;
            }
            sumOf8 += intervals[i];
            intervals[i] = 0;
        }
        ni = sumOf8;
        finalSum += (ni-nii)*(ni-nii)/nii;
        System.out.println();
        System.out.println(path.toString());
        System.out.println("Final sum: " + finalSum);
    }
}

