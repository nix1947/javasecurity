package asymmetricCryptography;

import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Scanner;

public class RsaExample {
    public static void main(String[] args) throws Exception {
        // Ask for the user input
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a message to encrypt");
        String userMessage = input.nextLine();

        // Second step: Generate a rsa key pair
        KeyPair keyPair = generateRsaKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        // Step 3: Implement an encryption
        byte [] cipherText = encrypt(userMessage, publicKey);

        // Step 4: Print the cipher Text
        System.out.println("Original Text is:  " + userMessage);
        System.out.println("Cipher text is: " + new String(cipherText));

        // Step 4: Implement decryption as well
        String originalMessage = decryption(cipherText, privateKey);
        System.out.println("Decrypted text from cipher text is " + originalMessage);



    }

    public static KeyPair generateRsaKeyPair() throws Exception {
       KeyPairGenerator keyPairGenerator =    KeyPairGenerator.getInstance("RSA");
       // Define the size of a key
        keyPairGenerator.initialize(2048);
        return keyPairGenerator.generateKeyPair();

    }

    public static byte [] encrypt(String message, PublicKey publicKey) throws Exception{
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(message.getBytes());

    }

    public static  String decryption(byte [] cipherText, PrivateKey privateKey) throws  Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte [] decryptedOriginalArray = cipher.doFinal(cipherText);
        return new String(decryptedOriginalArray);

    }




}
