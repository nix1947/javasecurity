import java.security.MessageDigest;
import java.util.Scanner;

public class HashingDemoInJava {
    public static void main(String[] args) throws Exception {

        // Let's take some input from keyboard
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a message to Hash a message using SHA-256");

        String message = sc.nextLine();

        // Let's create a message digest object from java Security framework

        MessageDigest sha256 = MessageDigest.getInstance("sha-256");

        byte[]  sha256Digest = sha256.digest(message.getBytes());

        System.out.println("SHA-256 digest of a input message is " + bytesToHex(sha256Digest));




        // Hashing for MD5 algorithm
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] md5Digest = md5.digest(message.getBytes());
        System.out.println("Message digest of input message using MD5 algorithm is " + bytesToHex(md5Digest));


        // Hashing for MD2 algorithm
        MessageDigest md2 = MessageDigest.getInstance("MD2");
        byte[] md2Digest = md5.digest(message.getBytes());
        System.out.println("Message digest of input message using MD2 algorithm is " + bytesToHex(md2Digest));

        // Hashing and Salting Demo using SHA-256 SSHA-256

        String salt = "mySecretSalt";
        String saltAndMessage = message + salt;
        MessageDigest ssha256 = MessageDigest.getInstance("SHA-256");
        byte[] ssha256Digest = ssha256.digest(saltAndMessage.getBytes());
        System.out.println("Message digest using SHA-256 with salting value is " + bytesToHex(ssha256Digest));



    }


    private static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder(2 * bytes.length);
        for(byte b: bytes) {
            hexString.append(String.format("%02x", b & 0xff));
        }

        return  hexString.toString();

    }

}
