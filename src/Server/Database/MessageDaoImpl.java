package Server.Database;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * MessageDaoImpl.java
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

public class MessageDaoImpl implements MessageDao {

    private JdbcTemplate jdbcTemplate;

    // this is idiomatic spring/best practice to configure jdbcTemplates
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void queryDb() {
        List<String> messages = jdbcTemplate.query("SELECT * FROM user", new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getString(1);
            }
        });

        for (String s : messages) {
            System.out.println(s);
        }
    }

    @Override
    public void addSocket(String id, int socketHash) {
        jdbcTemplate.update("UPDATE user SET sockethash=? WHERE id=?", socketHash, id);
    }

    @Override
    public void delSocket(String id) {
        jdbcTemplate.update("UPDATE user SET sockethash='' WHERE id=?", id);
    }

    @Override
    public int getSocket(String id) {
        return jdbcTemplate.queryForInt("SELECT sockethash FROM user WHERE id=?", id);
    }

    @Override
    public void setStatus(String id, String status) {
        jdbcTemplate.update("UPDATE user SET status=? WHERE id=?", status, id);
    }

    @Override
    public String getStatus(String id) {
        List<String> s = jdbcTemplate.query("SELECT status FROM user WHERE id=?", new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet rs, int rowNum) throws SQLException {
                return rs.getString("status");
            }
        }, id);
        return s.get(0);
    }

    @Override
    public boolean login(String id, String pwd) {
        boolean s = jdbcTemplate.query("SELECT * FROM user WHERE id=? AND passwd=?", new ResultSetExtractor<Boolean>() {
            @Override
            public Boolean extractData(ResultSet rs) throws SQLException, DataAccessException {
                return rs.next();
            }
        }, id, pwd);
        return s;
    }

}
