package Server.Database;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;

import javax.sql.DataSource;

/**
 * DbUtil.java
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

public class DbUtil {

    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void createDb() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        try {
            System.out.println("before query");
            String msg = jdbcTemplate.queryForObject("SELECT * FROM user", String.class);
            System.out.println("schema found: " + msg);
            System.out.println("after query");
        } catch (Exception e) {
            //no db present, so create the tables
            System.out.println("No tables found, creating db");
            Resource script = new ClassPathResource("Server/Database/table.sql");
            JdbcTestUtils.executeSqlScript(jdbcTemplate, script, true);
        }
    }
}
