package Tools;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Arrays;

public class AESImpl{
    private SecretKeySpec secretKeySpec;

    public byte[] decrypt(byte[] msg){
        try{
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
            return cipher.doFinal(msg);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public byte[] encrypt(byte[] msg){
        try{
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
            return cipher.doFinal(msg);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void setTheKey(byte[] key){
        try{
            MessageDigest sha = MessageDigest.getInstance("SHA-256");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKeySpec = new SecretKeySpec(key, "AES");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}

/*

AES (Advanced Encryption Standard) is a widely used symmetric encryption algorithm. It is a block cipher that operates
on fixed-size blocks of data, where the block size is typically 128 bits.

AES uses a secret key to perform encryption and decryption operations. The key length can be 128 bits, 192 bits,
or 256 bits. The longer the key, the stronger the encryption.
 */