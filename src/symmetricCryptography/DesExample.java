package symmetricCryptography;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.util.Arrays;
import java.util.Base64;
import java.util.Scanner;

public class DesExample {
    public static void main(String[] args) throws Exception {

        // Take user input
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a message to encode using DES");
        String userInput = sc.nextLine();

        // Step 1: Generate a DES Key
        String secretKey = "SecretKey";
        DESKeySpec desKeySpec = new DESKeySpec(secretKey.getBytes());
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey1 = secretKeyFactory.generateSecret(desKeySpec);



        // Step 2: Create a Cipher instance for encryption and decryption
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");

        // Step 3: Initialize the Cipher for encryption
        cipher.init(Cipher.ENCRYPT_MODE, secretKey1);

        // Step 4: Encrypt the plaintext
        byte [] cipherTextArray = cipher.doFinal(userInput.getBytes());
       String cipherTextString =  Base64.getEncoder().encodeToString(cipherTextArray);
        System.out.println("Cipher text of user Input data is " + cipherTextString);




        // Step 6: Initialize the Cipher for decryption
        cipher.init(Cipher.DECRYPT_MODE, secretKey1);
        byte [] decodedData = cipher.doFinal(Base64.getDecoder().decode(cipherTextString));

        // Step 7: Decrypt the ciphertext
        System.out.println("The decoded data is " + new String(decodedData));


    }
}
