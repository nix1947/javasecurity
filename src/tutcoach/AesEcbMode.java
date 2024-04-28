package tutcoach;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.bouncycastle.util.encoders.Hex;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.Security;


public class AesEcbMode {

    public static void main(String[] args) throws Exception {
        Security.addProvider(new BouncyCastleProvider());

        // Define the key and IV in hexadecimal format
        byte[] keyBytes = Hex.decode("000102030405060708090a0b0c0d0e0f");
        byte[] ivBytes = Hex.decode("9f741fdb5d8845bdb48a94394e84f8a3");

        // Create the SecretKeySpec and IvParameterSpec objects
        SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
        IvParameterSpec iv = new IvParameterSpec(ivBytes);

        // Initialize the Cipher instance for AES/CBC/NoPadding mode
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding", "BC");

        // Define the input data
        byte[] input = Hex.decode("a0a1a2a3a4a5a6a7a0a1a2a3a4a5a6a7"
                + "a0a1a2a3a4a5a6a7a0a1a2a3a4a5a6a7");

        // Print the input data in hexadecimal format
        System.out.println("Input: "  + Hex.toHexString(input));

        // Encrypt the input data
        cipher.init(Cipher.ENCRYPT_MODE, key, iv);
        byte[] encrypted = cipher.doFinal(input);

        // Print the encrypted data in hexadecimal format
        System.out.println("Encrypted: " + Hex.toHexString(encrypted));

        // Decrypt the encrypted data
        cipher.init(Cipher.DECRYPT_MODE, key, iv);
        byte[] decrypted = cipher.doFinal(encrypted);

        // Print the decrypted data in hexadecimal format
        System.out.println("Decrypted: " + Hex.toHexString(decrypted));
    }
}
