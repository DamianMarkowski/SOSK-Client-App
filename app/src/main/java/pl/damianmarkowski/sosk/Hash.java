package pl.damianmarkowski.sosk;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {

    public static String encodeMd5(String text){
        return Hash.getHash(text, "MD5");
    }

    private static String getHash(String text, String hashType) {
        try{
            MessageDigest instance = MessageDigest.getInstance(hashType);
            instance.update(text.getBytes(), 0, text.length());;

            BigInteger bigInteger = new BigInteger(1, instance.digest());
            return String.format("%1$032x", bigInteger);

        }catch(NoSuchAlgorithmException e){
            //error
            e.printStackTrace();

        }
        return null;

    }
}