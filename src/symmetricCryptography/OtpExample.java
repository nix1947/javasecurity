package symmetricCryptography;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
public class OtpExample {
    // Step 1: Define the key
    public static final String key = "SECRETKEY";

    public static void main(String[] args) {


        // Step 2: Input plaintext
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter plainText: ");
        String plainText = scanner.nextLine();

        // Step 3: Encrypt the plaintext

        String cipherText = encrypt("Hello world");

        // Step 4: Output the ciphertext
        String decodedString = encrypt(cipherText);

        // Step 5: Decrypt the ciphertext
        System.out.println("Ciphertext is " + cipherText);

        // Step 6: Output the decrypted text
        System.out.println("Decoded plaintext from cipherText is" + decodedString);
    }

 private static String encrypt(String plainText) {
        StringBuilder cipherText = new StringBuilder();

        for(int i = 0; i < plainText.length(); i++) {
            char plainChar = plainText.charAt(i);
            char keyChar = key.charAt(i % key.length());
           char cipherChar =  (char) (plainChar ^ keyChar);
           cipherText.append(cipherChar);

        }
        return cipherText.toString();

 }

 public static String decrypt(String cipherText) {
        StringBuilder decryptedText = new StringBuilder();

        for(int i = 0; i < cipherText.length(); i++) {
            char cipherChar = cipherText.charAt(i);
            char keyChar  = key.charAt(i % key.length());
            char decryptedChar = (char) (cipherChar ^ keyChar);
        }

        return decryptedText.toString();



 }
}
