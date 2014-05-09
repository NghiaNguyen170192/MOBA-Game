package Test;

import Server.Database.DbUtil;
import Server.Database.MessageDao;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;

/**
 * TestDatabaseSpring.java
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

@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration
@Transactional
public class TestDatabaseSpring {

    @Autowired
    private MessageDao dao;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private DbUtil dbUtil;

    @Before
    public void createDb() {
        dbUtil.createDb();
    }

    @Test
    public void testDaoConfigured() throws Exception {
        Assert.assertNotNull(dao);
    }

    @Test
    public void testLogin() {
        boolean s = dao.login("s3342137", "1234");
        Assert.assertEquals(true, s);
    }

}
