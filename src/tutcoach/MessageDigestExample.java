package tutcoach;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.Strings;
import org.bouncycastle.util.encoders.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;


public class MessageDigestExample
{
    public static void main(String[] args)
            throws Exception
    {
        Security.addProvider(new BouncyCastleProvider());

        System.out.println(
                Hex.toHexString(
                        calculateDigest("SHA-256", Strings.toByteArray("Hello World!"))));
    }

    public static byte[] calculateDigest(String digestName, byte[] data)
            throws NoSuchProviderException, NoSuchAlgorithmException
    {
        MessageDigest digest = MessageDigest.getInstance(digestName, "BC");

        digest.update(data);

        return digest.digest();
    }
}
