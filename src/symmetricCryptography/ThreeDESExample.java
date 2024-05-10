package symmetricCryptography;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.util.Base64;

public class ThreeDESExample  {
    public static void main(String[] args) throws  Exception {

        // Step 1: Define the secret key for Triple DES encryption
        String secretKey = "MySecretKey1234567890";
        byte [] secretKeyBytes = secretKey.getBytes("UTF-32");

        // Step 2: Generate a Triple DES key using the secret key bytes
        DESedeKeySpec spec = new DESedeKeySpec(secretKeyBytes);
        SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("DESede");
        SecretKey secretKey1 = secretKeyFactory.generateSecret(spec);

        // Step 3: Initialize the Cipher for encryption
        Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey1);


        // Step 4: Encrypt the plaintext using Triple DES
        String plainText = "Hello world";
        byte [] cipherTextByteArray = cipher.doFinal(plainText.getBytes());

        // Step 5: Encode the encrypted bytes to Base64 for easy display
        String cipherText = Base64.getEncoder().encodeToString(cipherTextByteArray);
        System.out.println("Cipher text is " + cipherText);

        // Step 6: Initialize the Cipher for decryption
        cipher.init(Cipher.DECRYPT_MODE, secretKey1);

        // Step 7: Decode the Base64 encrypted text
        byte [] cipherBytesDecoded = Base64.getDecoder().decode(cipherText);
        byte [] originalPlainText = cipher.doFinal(cipherBytesDecoded );

        // Step 8: Decrypt the encoded bytes using Triple DES
        System.out.println("Original decoded data is " + new String(originalPlainText));
    }

}
