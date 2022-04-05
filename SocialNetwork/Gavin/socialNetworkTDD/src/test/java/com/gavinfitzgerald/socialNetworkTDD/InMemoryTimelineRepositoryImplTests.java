package com.gavinfitzgerald.socialNetworkTDD;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;

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
}
