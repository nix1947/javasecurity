package symmetricCryptography;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.Security;
import java.util.Base64;
import java.util.Scanner;

public class IDEA {
    public static void main(String[] args) throws Exception {
        // Step 1: Add Bouncy Castle as a Security Provider
        Security.addProvider(new BouncyCastleProvider());

        // Step 2: Generate a random IDEA key
        KeyGenerator keyGenerator = KeyGenerator.getInstance("IDEA", "BC");
        keyGenerator.init(128);
        SecretKey secretKey = keyGenerator.generateKey();


        // Step 3: Create the cipher instance for IDEA encryption
        Cipher cipher = Cipher.getInstance("IDEA/ECB/PKCS5Padding", "BC");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);


        // Input data to encrypt
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a message to encrypt using IDEA: ");
        String userMessage = sc.nextLine();

        // Step 4: Encrypt the data
        byte [] encryptedData = cipher.doFinal(userMessage.getBytes());

        // Step 5: Encode encrypted data in Base64 for easier printing
        String cipherTextBase64 = Base64.getEncoder().encodeToString(encryptedData);
        System.out.println("Encrypted message is " + cipherTextBase64);

        // Step 6: Initialize the cipher for decryption
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte [] decodedOriginalData = cipher.doFinal(encryptedData);


        // Step 7: Decrypt the data

        // Step 8: Convert decrypted data to string using UTF-8 encoding
        System.out.println("The original message is " + new String(decodedOriginalData));

    }
}
