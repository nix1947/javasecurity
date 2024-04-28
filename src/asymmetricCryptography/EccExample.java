package asymmetricCryptography;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.ECGenParameterSpec;
import java.util.Scanner;

public class EccExample {
    public static void main(String[] args) throws Exception {
        Security.addProvider(new BouncyCastleProvider());

        // STEP 1: Ask for user input from keyboard
        Scanner in = new Scanner(System.in);
        System.out.println("Enter a message to encrypt using ECC:");
        String userMessage = in.nextLine();

        // STEP 2: Generate an ECC keypair
        KeyPair keypair = generateKeyPair();
        PrivateKey privateKey = keypair.getPrivate();
        PublicKey publicKey = keypair.getPublic();

        // Step 3: Encryption on our message

        byte [] cipherText = encrypt(userMessage, publicKey);
        System.out.println("Original message is: " + userMessage);
        System.out.println("Encrypted message using EC is " + new String(cipherText));

        // Step 4: Decryption
        String originalText = decrypt(cipherText, privateKey);

        System.out.println("The decoded information using private key is " + originalText);
    }

    public static KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC", "BC");
        ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp256k1");
        keyPairGenerator.initialize(ecSpec);
        return keyPairGenerator.generateKeyPair();
    }

    public static byte[] encrypt(String message, PublicKey publicKey) throws Exception {
        Cipher cipher = Cipher.getInstance("ECIES", "BC");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(message.getBytes());

    }

    public static String decrypt(byte [] cipherText, PrivateKey privateKey) throws  Exception {
        Cipher cipher = Cipher.getInstance("ECIES", "BC");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return new String(cipher.doFinal(cipherText));
    }
}
