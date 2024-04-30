package asymmetricCryptography;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import java.security.*;
import java.util.Base64;
import java.util.Scanner;

public class ElGamalExample {
    public static void main(String[] args) throws Exception {

        // STEP 1: Take user Input
        Scanner in = new Scanner(System.in);
        System.out.println("Enter a message to encrypt using elgamal algorithm");
        String userInput = in.nextLine();

        // STEP 2:  Add Bouncy Castle as a security provider for
        Security.addProvider(new BouncyCastleProvider());

        // STEP 3:  Generate ElGamal key pair using Bouncy Castle
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("ElGamal", "BC");
        keyPairGenerator.initialize(2048);
        KeyPair keypair = keyPairGenerator.generateKeyPair();
        PublicKey publicKey = keypair.getPublic();
        PrivateKey privateKey = keypair.getPrivate();

        // STEP 4:  Encryption
        Cipher cipher = Cipher.getInstance("ElGamal/None/NoPadding", "BC");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte [] cipherText = cipher.doFinal(userInput.getBytes());


        // STEP  5: Print user message
        System.out.println("The ciphertext is " + Base64.getEncoder().encodeToString(cipherText));

        // STEP 6: Decryption
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte [] originalMessage = cipher.doFinal(cipherText);
        System.out.println("The original message is " + new String(originalMessage));


        // STEP 6: Print the decoded message
    }
}
