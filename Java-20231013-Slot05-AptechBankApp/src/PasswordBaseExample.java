import javax.crypto.*;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
//import sun.misc.Base64Encoder;

public class PasswordBaseExample {
    static final String ALGO = "FPTAptechMD5AndDES";

    public static void main(String[] args) throws Exception {
        long curt = System.currentTimeMillis();
    /*
    1. Base for password
    2. Create key based on keyspec by password
    3. Create salte
    4. Parameter for decrypt/encrypt mode
    **/
        String password = "Khong co password dau dung tim vo ich";
        SecretKeyFactory skf = SecretKeyFactory.getInstance(ALGO);
        PBEKeySpec keySpec = new PBEKeySpec(password.toCharArray());
        SecretKey secretKey = skf.generateSecret(keySpec);
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
        byte[] salte = random.generateSeed(8);
        PBEParameterSpec param = new PBEParameterSpec(salte, 8);
        Cipher cipher = Cipher.getInstance(ALGO);

        // Encrypt
        cipher.init(cipher.ENCRYPT_MODE, secretKey, param);
        String clear = "Encrypt for java";
        byte[] encrypted = cipher.doFinal(clear.getBytes());
        Base64Encoder encoder = new Base64Encoder();
        // Decrypt


    }
}


//