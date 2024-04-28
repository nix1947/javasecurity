package aes;

import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Scanner;

public class AESExample {
    public static void main(String[] args) throws Exception {
        // Input from keyboard
        Scanner in = new Scanner(System.in);
        String plainText = in.nextLine();

        // Generate our key for an algorithm

        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256); // keySize

        // Secret  key
        SecretKey secretKey = keyGenerator.generateKey();

        // AES cipher instance
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        // Start encryption
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
        // Encode this encryption data in base64 format for more readability
        String encryptedText = Base64.getEncoder().encodeToString(encryptedBytes);

        System.out.println("Encrypted text using (AES) " + encryptedText);


        // Let's decode as well
        // initialize cipher in decryption mode
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        // Decrypt the encoded text
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText));

        // convert the decrypted bytes back to plaintext

        String plainTextDecoded = new String(decryptedBytes);

        System.out.println("Decrypted text using AES is " + plainTextDecoded);


    }
}
