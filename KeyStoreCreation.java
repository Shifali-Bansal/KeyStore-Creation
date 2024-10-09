import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.KeyStore;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.security.cert.Certificate;
import java.security.cert.CertificateEncodingException;
import javax.security.auth.x500.X500Principal;
import java.util.Calendar;
import java.math.BigInteger;

public class KeyStoreCreation {

    public static void main(String[] args) throws Exception {
        // Generate a key pair
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair keyPair = keyGen.generateKeyPair();

        // Create a self-signed certificate
        X500Principal principal = new X500Principal("CN=MyCert");
        long now = System.currentTimeMillis();
        Date startDate = new Date(now);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, 1); // Valid for 1 year
        Date endDate = calendar.getTime();
        BigInteger serialNumber = BigInteger.valueOf(now);
        X509Certificate cert = generateSelfSignedCertificate(principal, keyPair, serialNumber, startDate, endDate);

        // Create a keystore and store the key pair and certificate
        KeyStore keyStore = KeyStore.getInstance("JKS");
        keyStore.load(null, null); // Initialize an empty keystore
        keyStore.setKeyEntry("myKeyAlias", keyPair.getPrivate(), "password".toCharArray(), new Certificate[]{cert});

        // Save the keystore to a file
        try (java.io.FileOutputStream fos = new java.io.FileOutputStream("myKeystore.jks")) {
            keyStore.store(fos, "password".toCharArray());
        }
        
        System.out.println("Keystore created successfully!");
    }

    private static X509Certificate generateSelfSignedCertificate(X500Principal principal, KeyPair keyPair, BigInteger serial, Date start, Date end) throws CertificateEncodingException {
        // Implementation of certificate generation goes here
        // You can use a library like BouncyCastle to create a self-signed certificate
        return null; // Placeholder
    }
}
