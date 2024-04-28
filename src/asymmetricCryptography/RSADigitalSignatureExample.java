package asymmetricCryptography;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.*;

public class RSADigitalSignatureExample {

    public static void main(String[] args) throws Exception {

        // STEP 1: Generates an RSA key pair (public key and private key).
        KeyPair keyPair = generateRSAKeyPair();
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();


        // STEP 2:  Saves the private key to a file (private_key.pem)
        // and the public key to another file (public_key.pem).

        savePrivateKeyToFile(privateKey, "private_key.pem");
        savePublicKeyToFile(publicKey, "public_key.pem");



        // STEP 3: Reads an image file (image_to_sign.png) to be signed.
        File imageFile = new File("image_to_sign.jpeg");
        byte [] imageData = readImageData(imageFile);


        // STEP 4: Signs the image using the private key and generates a digital signature.
        byte [] digitalSignature = signImage(imageData, privateKey);



        // STEP 5: Saves the digital signature to a file (digital_signature.bin).
         saveDigitalSignatureTofile(digitalSignature, "digital_signature.bin");


        // STEP 6: Verifies the signature using the public key and prints the verification result.
        boolean isVerified = verifySignature(imageData, digitalSignature, publicKey);

        System.out.println("Signature  verified is: " + isVerified);

    }

    public static KeyPair generateRSAKeyPair() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        return keyPairGenerator.generateKeyPair();
    }

    public static void savePrivateKeyToFile(PrivateKey privateKey, String filePath) throws Exception {
        FileOutputStream fos = new FileOutputStream(filePath);
        fos.write(privateKey.getEncoded());
        fos.close();


    }


    public static void savePublicKeyToFile(PublicKey publicKey, String filePath) throws Exception {
        FileOutputStream fos = new FileOutputStream(filePath);
        fos.write(publicKey.getEncoded());
        fos.close();

    }

    private static byte[] readImageData(File imageFile) throws Exception {
        FileInputStream fis = new FileInputStream(imageFile);
        byte [] imageData = new byte[ (int) imageFile.length()];
        int read = fis.read(imageData);
        fis.close();
        return imageData;

    }

    private static byte[] signImage(byte [] imageData, PrivateKey privateKey) throws Exception {
        Signature signature = Signature.getInstance("SHA256withRSA");
        signature.initSign(privateKey);
        signature.update(imageData);
        return signature.sign();

    }

    private static void saveDigitalSignatureTofile(byte [] digitalSignature, String filePath) throws Exception {
        FileOutputStream fos = new FileOutputStream(filePath);
        fos.write(digitalSignature);
        fos.close();
    }

    public static boolean verifySignature(byte [] imageData, byte [] digitalSignature, PublicKey publicKey) throws  Exception  {
       Signature signature = Signature.getInstance("SHA256withRSA");
       signature.initVerify(publicKey);
       signature.update(imageData);
       return signature.verify(digitalSignature);
    }


}
