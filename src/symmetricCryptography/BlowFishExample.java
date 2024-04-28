package symmetricCryptography;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Scanner;

public class BlowFishExample {
    public static void main(String[] args) throws Exception {

        // take input from keyboard
        Scanner in = new Scanner(System.in);
        String inputPlainText = in.nextLine();

        // Generate random secret key for encryption and decryption
        KeyGenerator keyGenerator = KeyGenerator.getInstance("Blowfish");

        SecretKey secretKey = keyGenerator.generateKey();

        // Generate a cipher instance for encryption and decryption
        Cipher cipher = Cipher.getInstance("Blowfish");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

       byte [] encryptedTextByteArray =  cipher.doFinal(inputPlainText.getBytes());

       String encryptedText = Base64.getEncoder().encodeToString(encryptedTextByteArray);

        System.out.println("Encryption text of original plainText is " + encryptedText);

        // Decryption process.
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        // Decode the base64 encoded data first and passed the byte array of encrypted data.
        byte [] decodedBytes = Base64.getDecoder().decode(encryptedText);

        byte [] originalPlainTextArray = cipher.doFinal(decodedBytes);

        System.out.println("Original text is " + new String(originalPlainTextArray));


    }
}
