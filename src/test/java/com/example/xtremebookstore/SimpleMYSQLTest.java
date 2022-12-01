package com.example.xtremebookstore;

import org.junit.Rule;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testcontainers.containers.MySQLContainer;

public class SimpleMYSQLTest {
    private static final Logger logger = LoggerFactory.getLogger(SimpleMYSQLTest.class);
    @Rule
    public MySQLContainer mysql = new MySQLContainer();
    @Test
    public String testData(){
        return null;
    }


}