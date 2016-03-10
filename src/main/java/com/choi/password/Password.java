/**
 * 
 */
package com.choi.password;

import java.util.HashMap;
import java.util.Map;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import java.math.BigInteger;
/**
 * @author choi
 * TODO: Thread safe
 */
public class Password {
    private final int ITERATIONS = 1000;
    private final int KEY_LENGTH = 192; // bits
    private UserSalt userSalt = new UserSalt();
    Map<String, String> userPassword = new HashMap<>();

    public Password() {
    }
    public Password(Map<String, String> password) {
        userPassword.putAll(password);
    }
    public boolean authenticate(String user, String attemptedPassword)
                throws NoSuchAlgorithmException, InvalidKeySpecException {
        String encryptedAttemptedPassword;
        if (userPassword.containsKey(user)) {
             //existing user authentication
            encryptedAttemptedPassword = hashPassword(attemptedPassword, userSalt.getSalt(user));
            return userPassword.get(user).equals(encryptedAttemptedPassword);
        } else {
            //create the first time user registration
            userPassword.put(user, hashPassword(attemptedPassword, userSalt.getSalt(user)));
            return true;
        }
     }

    public String hashPassword(String password, byte[] salt)
             throws NoSuchAlgorithmException, InvalidKeySpecException{
        char[] passwordChars = password.toCharArray();

        PBEKeySpec spec = new PBEKeySpec(
                 passwordChars,
                 salt,
                 ITERATIONS,
                 KEY_LENGTH
                 );
        SecretKeyFactory key = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hashedPassword = key.generateSecret(spec).getEncoded();
        return String.format("%x", new BigInteger(hashedPassword));
    }
    public Map<String, String> getPasswordMap() {
        //always return a copy
        return new HashMap<String, String>(userPassword);
    }
}
