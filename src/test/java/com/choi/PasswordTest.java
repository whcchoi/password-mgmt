/**
 * 
 */
package com.choi;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import com.choi.password.Password;

import junit.framework.TestCase;

/**
 * @author choi
 *
 */
public class PasswordTest extends TestCase {

    Password ks = new Password();
    /**
     * 
     * @param name
     */
    public PasswordTest(String name) {
       super(name);
    }
    public void testSuccessfulAuthentication() {
        try {
            assertTrue(ks.authenticate("me", "unbreakablepassword"));
            assertTrue(ks.authenticate("me", "unbreakablepassword"));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e1) {
            fail(e1.getMessage());
        }
    }
    public void testFailedAuthentication() {
        try {
            assertTrue(ks.authenticate("me", "unbreakablepassword"));
            assertFalse(ks.authenticate("me", "wrong password"));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            fail(e.getMessage());
        }
    }
}
