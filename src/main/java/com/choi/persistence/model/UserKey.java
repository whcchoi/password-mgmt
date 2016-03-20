/**
 * 
 */
package com.choi.persistence.model;

import java.io.Serializable;

/**
 * @author choi
 *
 */
@SuppressWarnings("serial")
public class UserKey implements Serializable {

    private String user;

    public UserKey(String user) {
        this.user = user;
    }
    /**
      * @return the user
      */
    public String getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }
    public String toString() {
        return "[UserKey: user=" + user + ']';
    }
}
