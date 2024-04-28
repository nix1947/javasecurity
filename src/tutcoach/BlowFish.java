package tutcoach;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class BlowFish {
    private static final String ALGORITHM = "Blowfish";

    public static void main(String[] args) throws Exception {
        // Generate a random secret key for encryption and decryption
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
        SecretKey secretKey = keyGenerator.generateKey();

        // Generate a cipher instance for encryption and decryption
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        String plainText = "Hello, this is a test message for Blowfish encryption.";
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());

        // Encode the encrypted bytes using Base64 for better readability
        String encryptedText = Base64.getEncoder().encodeToString(encryptedBytes);
        System.out.println("Encrypted Text: " + encryptedText);

        // Initialize the cipher for decryption
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        // Decode the Base64 encoded encrypted text and decrypt it
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedText);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);

        String decryptedText = new String(decryptedBytes);
        System.out.println("Decrypted Text: " + decryptedText);
    }
}
