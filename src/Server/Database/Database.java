package Server.Database;

import java.sql.*;

/**
 * Database.java
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

public class Database {

    private static String DB_URL = "jdbc:hsqldb:file:/database/sadi_game_db";
    private static String DB_CLASS = "org.hsqldb.jdbc.JDBCDriver";
    private static Database db;
    private static Connection c;
    private static Statement stm;

    private Database() {
    }

    public static Database createDatabase() {
        if (db == null) {
            db = new Database();
        }
        return db;
    }

    public void initDatabase() throws SQLException, ClassNotFoundException {
        Class.forName(DB_CLASS);
        c = DriverManager.getConnection(DB_URL, "SA", "");
        stm = c.createStatement();
        System.out.println("DB initialized!");
    }

    /*
    Example database:
    [user]
    id | passwd | fullname | status | sockethash
    ---
    s3342135 | 1234 | Huy   | null | null
    s3343174 | 1234 | Nghia | null | null
    s3342133 | 1234 | San   | null | null
    s3342137 | 1234 | Hung  | null | null
     */
    public void exampleDb() throws SQLException {
        stm.execute("SET DATABASE DEFAULT TABLE TYPE CACHED");
        stm.execute("SET AUTOCOMMIT TRUE");
        stm.execute("CREATE TABLE user(" +
                "id varchar(8) not null," +
                "passwd varchar(100) not null," +
                "fullname varchar(50)," +
                "status varchar(3)," +
                "sockethash int)");

        stm.execute("INSERT INTO user(id,passwd,fullname) " +
                "VALUES('s3342135','1234','Huy')");
        stm.execute("INSERT INTO user(id,passwd,fullname) " +
                "VALUES('s3342174','1234','Nghia')");
        stm.execute("INSERT INTO user(id,passwd,fullname) " +
                "VALUES('s3342133','1234','San')");
        stm.execute("INSERT INTO user(id,passwd,fullname) " +
                "VALUES('s3342137','1234','Hung')");
    }

    public void queryDb() throws SQLException {
        ResultSet s = stm.executeQuery("SELECT * FROM user");
        int count = 0;
        while (s.next()) {
            count += 1;
            String id = s.getString("id");
            String passwd = s.getString("passwd");
            String name = s.getString("fullname");
            System.out.println("Row #:" + count);
            System.out.print("ID: " + id + " ");
            System.out.print("PASSWD: " + passwd + " ");
            System.out.print("NAME: " + name);
            System.out.println();

        }
    }

    public void addSocket(String id, int socketHash) throws SQLException {
        stm.execute("UPDATE user SET sockethash=" + socketHash + " WHERE id='" + id + "'");
    }

    public void delSocket(String id) throws SQLException {
        stm.execute("UPDATE user SET sockethash='' WHERE id='" + id + "'");
    }

    public int getSocket(String id) throws SQLException {
        ResultSet rs = stm.executeQuery("SELECT sockethash FROM user WHERE id='" + id + "'");
        rs.next();
        return Integer.parseInt(rs.getString("sockethash"));
    }

    public void setStatus(String id, String status) throws SQLException {
        stm.execute("UPDATE user SET status='" + status + "' WHERE id='" + id + "'");
    }

    public String getStatus(String id) throws SQLException {
        ResultSet t = stm.executeQuery("SELECT status FROM user WHERE id='" + id + "'");
        t.next();
        return t.getString("status");
    }

    public boolean login(String id, String pwd) throws SQLException {
        ResultSet t = stm.executeQuery("SELECT * FROM user WHERE id='" + id + "'" + " AND passwd='" + pwd + "'");
        return t.next();
    }

    public void shutdownDatabase() throws SQLException {
        c.commit();
        c.close();
        c = DriverManager.getConnection(DB_URL + ";shutdown=true", "SA", "");
        System.out.println("DB shutdown!");
    }
}
