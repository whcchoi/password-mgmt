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
public class SingleStringValue implements Serializable {
    private String singleString;

    public SingleStringValue(String value) {
        this.singleString = value;
    }

    public String toString() {
        return "Unavalaible for security reason";
    }

    /**
     * @return the password
     */
    public String getSingleString() {
        return singleString;
    }
}
