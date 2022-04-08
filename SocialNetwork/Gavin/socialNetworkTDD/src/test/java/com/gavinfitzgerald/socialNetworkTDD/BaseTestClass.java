package com.gavinfitzgerald.socialNetworkTDD;

import com.gavinfitzgerald.socialNetworkTDD.DTOs.Message;
import org.springframework.boot.test.context.SpringBootTest;
import java.text.SimpleDateFormat;

@SpringBootTest
public class BaseTestClass {

    final String DATE_PATTERN = "yyyy-MM-dd HH-mm";
    final String USER_ONE = "Bob";
    final String USER_TWO = "Alice";
    final String USER_THREE = "Trudy";
    final String FIRST_MESSAGE = "First Message";
    final String SECOND_MESSAGE = "Second Message";
    final String THIRD_MESSAGE = "Third Message";
    final String TOO_LONG_A_MESSAGE = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Ut euismod efficitur diam, eu tincidunt nisl. Suspendisse vel ante non velit vulputate interdum. Aenean bibendum lorem orci, ac malesuada elit posuere eget. Nunc sodales condimentum lectus, non fermentum velit commodo et. Nulla dictum nisl non elit mattis ultricies. Nullam egestas augue a ligula dignissim, non rhoncus nunc fermentum. Phasellus tincidunt lorem ac diam cursus cursus. Pellentesque et velit luctus eros vulputate egestas. Vestib";
    final String EXPECTED_MESSAGE_TOO_LONG_MESAGE = "Message too long. 500 is the maximum number of characters.";
    final Message USER_ONE_FIRST_MESSAGE = new Message(USER_ONE, FIRST_MESSAGE);
    final Message USER_ONE_SECOND_MESSAGE = new Message(USER_ONE, SECOND_MESSAGE);
    final Message USER_ONE_THIRD_MESSAGE = new Message(USER_ONE, THIRD_MESSAGE);
    final Message USER_TWO_FIRST_MESSAGE = new Message(USER_TWO, FIRST_MESSAGE);
    final Message USER_TWO_SECOND_MESSAGE = new Message(USER_TWO, SECOND_MESSAGE);
    final Message USER_TWO_THIRD_MESSAGE = new Message(USER_TWO, THIRD_MESSAGE);
    final Message USER_THREE_FIRST_MESSAGE = new Message(USER_THREE, FIRST_MESSAGE);
    final Message USER_THREE_SECOND_MESSAGE = new Message(USER_THREE, SECOND_MESSAGE);
    final Message USER_THREE_THIRD_MESSAGE = new Message(USER_THREE, THIRD_MESSAGE);
    final Message USER_ONE_TOO_LONG_A_MESSAGE = new Message(USER_ONE, TOO_LONG_A_MESSAGE);
    SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_PATTERN);
}
