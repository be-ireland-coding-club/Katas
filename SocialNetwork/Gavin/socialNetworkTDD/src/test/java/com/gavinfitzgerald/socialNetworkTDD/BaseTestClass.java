package com.gavinfitzgerald.socialNetworkTDD;

import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;

@SpringBootTest
public class BaseTestClass {

    final String DATE_PATTERN = "yyyy-MM-dd HH-mm";
    final String USER_ONE = "Bob";
    final String TOO_LONG_A_MESSAGE = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut euismod efficitur diam, eu tincidunt nisl. Suspendisse vel ante non velit vulputate interdum. Aenean bibendum lorem orci, ac malesuada elit posuere eget. Nunc sodales condimentum lectus, non fermentum velit commodo et. Nulla dictum nisl non elit mattis ultricies. Nullam egestas augue a ligula dignissim, non rhoncus nunc fermentum. Phasellus tincidunt lorem ac diam cursus cursus. Pellentesque et velit luctus eros vulputate egestas. Vestib";
    final String EXPECTED_MESSAGE_TOO_LONG_MESAGE = "Message too long. 500 is the maximum number of characters.";
    SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);
}
