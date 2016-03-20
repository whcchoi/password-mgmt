/**
 * 
 */
package com.choi.persistence;

import java.io.File;

import com.sleepycat.je.Database;
import com.sleepycat.je.DatabaseConfig;
import com.sleepycat.je.DatabaseException;
import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;

/**
 * @author choi
 * http://www-rohan.sdsu.edu/doc/BerkeleyDB/ref/bdb_basic/intro.html
 */
public class BDBAccess {
    private Environment myDbEnvironment = null;

    private String dbFile;
    private static final String PASSWORD_STORE = "password_store";
    private static final String SALT_STORE = "salt_store";
    private static final String MAP_STORE = "map_store";

    private Database passwordDb;
    private Database saltDb;
    private Database mapDb;
    public BDBAccess(String databaseFile) {
        dbFile = databaseFile;
        try {
            // Open the environment, creating one if it does not exist
            EnvironmentConfig envConfig = new EnvironmentConfig();
            envConfig.setAllowCreate(true);
            myDbEnvironment = new Environment(new File(dbFile), envConfig);
 
            // Open the database, creating one if it does not exist
            DatabaseConfig dbConfig = new DatabaseConfig();
            dbConfig.setAllowCreate(true);
            passwordDb = myDbEnvironment.openDatabase(null,
                                             PASSWORD_STORE, dbConfig);
        } catch (DatabaseException dbe) {
            dbe.printStackTrace();
        }
    }
    public void close() {
         try {
            if (passwordDb != null) {
                passwordDb.close();
            }
            if (myDbEnvironment != null) {
                myDbEnvironment.close();
            }
         } catch (DatabaseException dbe) {
             dbe.printStackTrace();
         }
    }
}