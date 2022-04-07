package com.gavinfitzgerald.socialNetworkTDD;

import com.gavinfitzgerald.socialNetworkTDD.DTOs.Message;
import com.gavinfitzgerald.socialNetworkTDD.Repositories.InMemoryTimeLineRepositoryImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
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

        //Act
        unitUnderTest.addMessage(USER_ONE_FIRST_MESSAGE);

        //Assert
        List<Message> actual = unitUnderTest.getTimeline(USER_ONE);
        //Take the timestamp from the actual message to make test deterministic
        String expected = USER_ONE+", "+dateFormat.format(actual.get(0).getTimestamp()).toString()+": "+FIRST_MESSAGE+"\n";
        assertEquals(expected, actual.get(0).toString(), "Timeline should return '"+expected+"' as only post");
    }

    @Test
    public void testCanAddMultipleMessageToTimeLine(){
        //Arrange
        InMemoryTimeLineRepositoryImpl unitUnderTest = new InMemoryTimeLineRepositoryImpl();

        //Act
        unitUnderTest.addMessage(USER_ONE_FIRST_MESSAGE);
        unitUnderTest.addMessage(USER_ONE_SECOND_MESSAGE);
        unitUnderTest.addMessage(USER_ONE_THIRD_MESSAGE);

        //Assert
        List<Message> actual = unitUnderTest.getTimeline(USER_ONE);
        String expectedFirstMessage = USER_ONE+", "+dateFormat.format(actual.get(0).getTimestamp()).toString()+": "+FIRST_MESSAGE+"\n";
        String expectedSecondMessage = USER_ONE+", "+dateFormat.format(actual.get(1).getTimestamp()).toString()+": "+SECOND_MESSAGE+"\n";
        String expectedThirdMessage = USER_ONE+", "+dateFormat.format(actual.get(2).getTimestamp()).toString()+": "+THIRD_MESSAGE+"\n";
        assertEquals(expectedFirstMessage, actual.get(0).toString(), "Timeline should return '"+expectedFirstMessage+"' as first post");
        assertEquals(expectedSecondMessage, actual.get(1).toString(), "Timeline should return '"+expectedSecondMessage+"' as second post");
        assertEquals(expectedThirdMessage, actual.get(2).toString(), "Timeline should return '"+expectedThirdMessage+"' as third post");
    }

    @Test
    public void testGetTimelineForNonExistantUserWillReturnEmptyList() {
        //Arrange
        InMemoryTimeLineRepositoryImpl unitUnderTest = new InMemoryTimeLineRepositoryImpl();

        //Act
        List<Message> actual = unitUnderTest.getTimeline(USER_ONE);

        //Assert
        assertTrue(actual.isEmpty());
    }

    @Test
    public void testCanReadTimelineWithSubscriptions(){
        //Arrange
        InMemoryTimeLineRepositoryImpl unitUnderTest = new InMemoryTimeLineRepositoryImpl();
        unitUnderTest.addMessage(USER_ONE_FIRST_MESSAGE);
        unitUnderTest.addMessage(USER_ONE_SECOND_MESSAGE);
        unitUnderTest.addMessage(USER_TWO_FIRST_MESSAGE);
        unitUnderTest.addMessage(USER_TWO_SECOND_MESSAGE);
        unitUnderTest.addMessage(USER_THREE_FIRST_MESSAGE);

        List<String> subscriptions = new ArrayList<String>(){{ add(USER_TWO); add(USER_THREE); }};

        //Act
        List<Message> actual = unitUnderTest.getTimeline(USER_ONE, subscriptions);

        //Assert
        assertEquals(USER_ONE_FIRST_MESSAGE.toString(), actual.get(0).toString());
        assertEquals(USER_ONE_SECOND_MESSAGE.toString(), actual.get(1).toString());
        assertEquals(USER_TWO_FIRST_MESSAGE.toString(), actual.get(2).toString());
        assertEquals(USER_TWO_SECOND_MESSAGE.toString(), actual.get(3).toString());
        assertEquals(USER_THREE_FIRST_MESSAGE.toString(), actual.get(4).toString());
    }

    @Test
    public void testTimelineWithSubscriptionsIsOrderedByTimestamp() throws InterruptedException {
        //Arrange
        InMemoryTimeLineRepositoryImpl unitUnderTest = new InMemoryTimeLineRepositoryImpl();

        //Order of Messages - Sleep added to keep tests deterministic
        unitUnderTest.addMessage(new Message(USER_ONE, FIRST_MESSAGE));
        Thread.sleep(1);
        unitUnderTest.addMessage(new Message(USER_TWO, FIRST_MESSAGE));
        Thread.sleep(1);
        unitUnderTest.addMessage(new Message(USER_THREE, FIRST_MESSAGE));
        Thread.sleep(1);
        unitUnderTest.addMessage(new Message(USER_TWO, SECOND_MESSAGE));
        Thread.sleep(1);
        unitUnderTest.addMessage(new Message(USER_ONE, SECOND_MESSAGE));

        List<String> subscriptions = new ArrayList<String>(){{ add(USER_TWO); add(USER_THREE); }};

        //Act
        List<Message> actual = unitUnderTest.getTimeline(USER_ONE, subscriptions);

        //Assert
        assertEquals(USER_ONE+FIRST_MESSAGE, actual.get(0).getUser()+actual.get(0).getMessage());
        assertEquals(USER_TWO+FIRST_MESSAGE, actual.get(1).getUser()+actual.get(1).getMessage());
        assertEquals(USER_THREE+FIRST_MESSAGE, actual.get(2).getUser()+actual.get(2).getMessage());
        assertEquals(USER_TWO+SECOND_MESSAGE, actual.get(3).getUser()+actual.get(3).getMessage());
        assertEquals(USER_ONE+SECOND_MESSAGE, actual.get(4).getUser()+actual.get(4).getMessage());
    }
}
