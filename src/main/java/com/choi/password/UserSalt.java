/**
 * 
 */
package com.choi.password;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

/**
 * @author choi
 *
 * TODO: Thread safe
 */
public class UserSalt {
    private final String RANDOM_ALGO = "SHA1PRNG";
    /**
     * Key is the user name
     * Value is the salt string
     */
    Map<String, String> saltMap = new HashMap<>();
    public UserSalt() {
    }
    public UserSalt(Map<String, String> salt) {
        saltMap.putAll(salt);
    }
    public byte[] getSalt(String user) throws NoSuchAlgorithmException {
        if (saltMap.containsKey(user)) {
            return saltMap.get(user).getBytes();
        }
        // VERY important to use SecureRandom instead of just Random
        SecureRandom random = SecureRandom.getInstance(RANDOM_ALGO);
        // Generate a 8 byte (64 bit) salt as recommended by RSA PKCS5
        byte[] salt = new byte[8];
        random.nextBytes(salt);
        //add to user salt map
        saltMap.put(user, String.format("%x", new BigInteger(salt)));
        //read from the map because the conversion may have different encoding.
        return saltMap.get(user).getBytes();
    }
    public Map<String, String> getSaltMap() {
        //always return a copy
        return new HashMap<String, String>(saltMap);
    }
}
