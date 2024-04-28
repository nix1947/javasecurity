package dsa;

import java.security.*;
import java.util.Base64;
import java.util.Scanner;

public class DsaExample {
    public static void main(String[] args) throws Exception {
        // Ask for user input
        Scanner in = new Scanner(System.in);
        System.out.println("Enter a message to sign digitally using DSA algorithm");
        String userMessage = in.nextLine();

        // Step 2: Generate a key pair using DSA algorithm
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA");
        KeyPair keypair = keyPairGenerator.generateKeyPair();

        PrivateKey privateKey = keypair.getPrivate();
        PublicKey publicKey = keypair.getPublic();

        // Step 3: create a signature object for signing and verifying a message
        Signature signature = Signature.getInstance("SHA256withDSA");

        // initialize the signature object using private key
        signature.initSign(privateKey);

        // Pass data to signature object to sign by private key
        signature.update(userMessage.getBytes());

        // Generate a digital signature of userMessage
        byte [] digitalSignature = signature.sign();

        System.out.println("Digital signature is " + Base64.getEncoder().encodeToString(digitalSignature));

        // Step 4: verify the signature using public key
        signature.initVerify(publicKey);

        signature.update(userMessage.getBytes());

        boolean verified = signature.verify(digitalSignature);

        if(verified) {
            System.out.println("Signature for the input message is verified");

        } else {
            System.out.println("Cannot verify the authenticity of a user input message");
        }













    }
}
