package com.gavinfitzgerald.socialNetworkTDD;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.verify;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;

public class UserControllerTests extends BaseTestClass{

    @InjectMocks
    private UserController unitUnderTest;

    @Mock
    private ITimeLineRepository timeLineRepositoryMock;

    @Test
    public void testUserControllerCanPublishMessageToPersonalTimeline(){
        //Arrange
        String message = "Hello World";
        List<Message> messages = new ArrayList<Message>();
        messages.add(new Message(USER_ONE, message, new Date()));
        Mockito.when(timeLineRepositoryMock.getTimeline(anyString())).thenReturn(messages);

        //Act
        unitUnderTest.post(USER_ONE, message);

        //Assert
        verify(timeLineRepositoryMock).addMessage(USER_ONE, message);
    }

    @Test
    public void testUserControllerCanPublishMultilpleMessageToPersonalTimeline(){
        //Arrange
        String user = "Bob";
        String firstMessage = "First Message";
        String secondMessage = "Second Message";
        String thirdMessage = "Third Message";

        //Act
        unitUnderTest.post(user, firstMessage);
        unitUnderTest.post(user, secondMessage);
        unitUnderTest.post(user, thirdMessage);

        //Assert
        verify(timeLineRepositoryMock).addMessage(USER_ONE, firstMessage);
        verify(timeLineRepositoryMock).addMessage(USER_ONE, secondMessage);
        verify(timeLineRepositoryMock).addMessage(USER_ONE, thirdMessage);
    }
}
