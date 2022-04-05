package com.gavinfitzgerald.socialNetworkTDD;

import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;

@SpringBootTest
public class BaseTestClass {

    final String DATE_PATTERN = "yyyy-MM-dd HH-mm";
    final String USER_ONE = "Bob";
    SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);
}
