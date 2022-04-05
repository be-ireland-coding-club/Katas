package com.gavinfitzgerald.socialNetworkTDD;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
    public void testCanPublishMessageToPersonalTimeline() throws MessageTooLongException {
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
    public void testCanPublishMultilpleMessageToPersonalTimeline() throws MessageTooLongException{
        //Arrange
        String firstMessage = "First Message";
        String secondMessage = "Second Message";
        String thirdMessage = "Third Message";

        //Act
        unitUnderTest.post(USER_ONE, firstMessage);
        unitUnderTest.post(USER_ONE, secondMessage);
        unitUnderTest.post(USER_ONE, thirdMessage);

        //Assert
        verify(timeLineRepositoryMock).addMessage(USER_ONE, firstMessage);
        verify(timeLineRepositoryMock).addMessage(USER_ONE, secondMessage);
        verify(timeLineRepositoryMock).addMessage(USER_ONE, thirdMessage);
    }

    @Test
    public void testThrowMessageTooLongExceptionIfPostMessageLongerThan500Characters() throws MessageTooLongException{
        //Act / Assert
        MessageTooLongException thrown = assertThrows(
                MessageTooLongException.class,
                () -> unitUnderTest.post(USER_ONE, TOO_LONG_A_MESSAGE),
                "MessageTooLongException not thrown"
        );

        //Assert
        assertTrue(thrown.getMessage().contains(EXPECTED_MESSAGE_TOO_LONG_MESAGE));
    }
}
