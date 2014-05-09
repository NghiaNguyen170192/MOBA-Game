package Server.Database;

/**
 * MessageDao.java
 * MOBA Turn-based Online Game
 * Assignment 1, COSC2440 Software Architecture: Design and Implementation
 * RMIT International University Vietnam
 * -
 * Copyright 2013 Vuong Do Thanh Huy      (s3342135)
 * Nguyen Quoc Trong Nghia (s3343711)
 * Kieu Hoang Anh          (s3275058)
 * -
 * Refer to the NOTICE.txt file in the root of the source tree for
 * acknowledgements of third party works used in this software.
 * -
 * Date created: 13/03/2013
 * Date last modified: 05/05/2013
 */

public interface MessageDao {

    void queryDb();

    void addSocket(String id, int socketHash);

    void delSocket(String id);

    int getSocket(String id);

    void setStatus(String id, String status);

    String getStatus(String id);

    boolean login(String id, String pwd);

}
