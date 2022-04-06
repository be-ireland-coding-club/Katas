package com.gavinfitzgerald.socialNetworkTDD;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;

public class InMemoryTimelineRepositoryImplTests extends BaseTestClass{

    @AfterEach
    public void cleanUpEach(){
        InMemoryTimeLineRepositoryImpl.deleteTimelines();
    }

    @Test
    public void testCanAddMessageToTimeLine(){
        //Arrange
        InMemoryTimeLineRepositoryImpl unitUnderTest = new InMemoryTimeLineRepositoryImpl();
        String message = "Hello World";

        //Act
        unitUnderTest.addMessage(USER_ONE, message);

        //Assert
        List<Message> actual = unitUnderTest.getTimeline(USER_ONE);
        //Take the timestamp from the actual message to make test deterministic
        String expected = USER_ONE+", "+dateFormat.format(actual.get(0).getTimestamp()).toString()+": "+message+"\n";
        assertEquals(expected, actual.get(0).toString(), "Timeline should return '"+expected+"' as only post");
    }

    @Test
    public void testCanAddMultipleMessageToTimeLine(){
        //Arrange
        InMemoryTimeLineRepositoryImpl unitUnderTest = new InMemoryTimeLineRepositoryImpl();
        String firstMessage = "First Message";
        String secondMessage = "Second Message";
        String thirdMessage = "Third Message";

        //Act
        unitUnderTest.addMessage(USER_ONE, firstMessage);
        unitUnderTest.addMessage(USER_ONE, secondMessage);
        unitUnderTest.addMessage(USER_ONE, thirdMessage);

        //Assert
        List<Message> actual = unitUnderTest.getTimeline(USER_ONE);
        String expectedFirstMessage = USER_ONE+", "+dateFormat.format(actual.get(0).getTimestamp()).toString()+": "+firstMessage+"\n";
        String expectedSecondMessage = USER_ONE+", "+dateFormat.format(actual.get(1).getTimestamp()).toString()+": "+secondMessage+"\n";
        String expectedThirdMessage = USER_ONE+", "+dateFormat.format(actual.get(2).getTimestamp()).toString()+": "+thirdMessage+"\n";
        assertEquals(expectedFirstMessage, actual.get(0).toString(), "Timeline should return '"+expectedFirstMessage+"' as first post");
        assertEquals(expectedSecondMessage, actual.get(1).toString(), "Timeline should return '"+expectedSecondMessage+"' as second post");
        assertEquals(expectedThirdMessage, actual.get(2).toString(), "Timeline should return '"+expectedThirdMessage+"' as third post");
    }

    @Test
    //TODO: Does this test add any value?
    public void testGetTimelineForNonExistantUserWillReturnNull() {
        //Arrange
        InMemoryTimeLineRepositoryImpl unitUnderTest = new InMemoryTimeLineRepositoryImpl();

        //Act
        List<Message> actual = unitUnderTest.getTimeline(USER_ONE);

        //Assert
        assertNull(actual);
    }

    @Test
    public void testCanReadTimelineWithSubscriptions(){
        //Arrange
        InMemoryTimeLineRepositoryImpl unitUnderTest = new InMemoryTimeLineRepositoryImpl();
        String u1text1 = "Bob's first post";
        Message u1message1Expected = new Message(USER_ONE, u1text1, new Date());
        String u1text2 = "Bob's second post";
        Message u1message2Expected = new Message(USER_ONE, u1text2, new Date());
        unitUnderTest.addMessage(USER_ONE, u1text1);
        unitUnderTest.addMessage(USER_ONE, u1text2);

        String u2text1 = "Alice's first post";
        Message u2message1Expected = new Message(USER_TWO, u2text1, new Date());
        String u2text2 = "Alice's second post";
        Message u2message2Expected = new Message(USER_TWO, u2text2, new Date());
        unitUnderTest.addMessage(USER_TWO, u2text1);
        unitUnderTest.addMessage(USER_TWO, u2text2);

        String u3text1 = "Trudy's first post";
        Message u3message1Expected = new Message(USER_THREE, u3text1, new Date());
        unitUnderTest.addMessage(USER_THREE, u3text1);

        List<String> subscriptions = new ArrayList<String>(){{ add(USER_TWO); add(USER_THREE); }};

        //Act
        List<Message> actual = unitUnderTest.getTimeline(USER_ONE, subscriptions);

        //Assert
        assertEquals(u1message1Expected.toString(), actual.get(0).toString());
        assertEquals(u1message2Expected.toString(), actual.get(1).toString());
        assertEquals(u2message1Expected.toString(), actual.get(2).toString());
        assertEquals(u2message2Expected.toString(), actual.get(3).toString());
        assertEquals(u3message1Expected.toString(), actual.get(4).toString());
    }
}
