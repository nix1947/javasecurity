package asymmetricCryptography;

import java.math.BigInteger;
import java.security.SecureRandom;

public class DeffieHellmanAlgorithm {
    private static final BigInteger P = new BigInteger("23");
    private static final BigInteger G = new BigInteger("5");
    public static void main(String[] args) {


        // K = (B^a mod P): secret key computed by Alice
        // K = (A^b mod P): secret key computed by Bob
        //  B = G^b mod P
        // A = G^a  mod P
        //B: bob public key, a: Alice private key, P: Prime number

        BigInteger alicePrivateKey = generatePrivateKey(); // a
        BigInteger bobPrivateKey = generatePrivateKey(); // b
        BigInteger alicePublicKey = generatePublicKey(alicePrivateKey); // A
        BigInteger bobPublicKey = generatePublicKey(bobPrivateKey); // B

        // Generate a secret key

            BigInteger aliceSharedKey = generateSecretSharedKey(alicePrivateKey, bobPublicKey);
            BigInteger bobSharedKey = generateSecretSharedKey(bobPrivateKey, alicePublicKey);

         System.out.println("Key by bob is " + bobSharedKey);
        System.out.println("key by alice is" + aliceSharedKey);



    }

    private static BigInteger generatePrivateKey() {

        SecureRandom random = new SecureRandom();
        return  new BigInteger(32, random);
    }

    private static BigInteger generatePublicKey(BigInteger privateKey) {
        return G.modPow(privateKey, P);
    }

   private static BigInteger generateSecretSharedKey(BigInteger privateKey, BigInteger publicKey) {
        return publicKey.modPow(privateKey, P);

    }
}
