package Tools;

import java.security.SecureRandom;
import java.sql.Time;
import java.time.LocalDateTime;

public class HelperFunctions{
    public static byte[] generateSessionKeyOrNonce(){
        SecureRandom sr = new SecureRandom();
        byte[] sessionKey = new byte[16];
        sr.nextBytes(sessionKey);
        return sessionKey;
    }

    public static String generateLifetime(){
        return LocalDateTime.now().plusHours(1).toString();
    }

    public static LocalDateTime generateTimestamp(){
        return LocalDateTime.now();
    }
}

/*
generateSessionKeyOrNonce():

This function generates a random session key or nonce.
It uses SecureRandom to generate a secure random byte array of length 16 (128 bits).
The generated byte array is returned as the session key or nonce.
generateLifetime():

This function generates a lifetime value.
It uses LocalDateTime.now() to get the current date and time.
It adds one hour to the current time using plusHours(1) to define the expiration time.
The expiration time is converted to a string representation and returned as the lifetime value.
generateTimestamp():

This function generates a timestamp.
It uses LocalDateTime.now() to get the current date and time.
The current date and time are returned as a LocalDateTime object representing the timestamp.
 */