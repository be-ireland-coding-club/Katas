package com.gavinfitzgerald.socialNetworkTDD;

import com.gavinfitzgerald.socialNetworkTDD.DTOs.Message;
import com.gavinfitzgerald.socialNetworkTDD.DTOs.MessageTooLongException;
import com.gavinfitzgerald.socialNetworkTDD.Repositories.IFollowersRepository;
import com.gavinfitzgerald.socialNetworkTDD.Repositories.ITimeLineRepository;
import com.gavinfitzgerald.socialNetworkTDD.Services.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;

public class UserServiceTests extends BaseTestClass{

    @InjectMocks
    private UserService unitUnderTest;

    @Mock
    private ITimeLineRepository timeLineRepositoryMock;

    @Mock
    IFollowersRepository followersRepositoryMock;

    @Test
    public void testCanPublishMessageToPersonalTimeline() throws MessageTooLongException {
        //Arrange
        List<Message> messages = new ArrayList<Message>();
        messages.add(USER_ONE_FIRST_MESSAGE);
        Mockito.when(timeLineRepositoryMock.getTimeline(anyString())).thenReturn(messages);

        //Act
        unitUnderTest.post(USER_ONE_FIRST_MESSAGE);

        //Assert
        verify(timeLineRepositoryMock).addMessage(USER_ONE_FIRST_MESSAGE);
    }

    @Test
    public void testCanPublishMultilpleMessageToPersonalTimeline() throws MessageTooLongException{

        //Act
        unitUnderTest.post(USER_ONE_FIRST_MESSAGE);
        unitUnderTest.post(USER_ONE_SECOND_MESSAGE);
        unitUnderTest.post(USER_ONE_THIRD_MESSAGE);

        //Assert
        verify(timeLineRepositoryMock).addMessage(USER_ONE_FIRST_MESSAGE);
        verify(timeLineRepositoryMock).addMessage(USER_ONE_SECOND_MESSAGE);
        verify(timeLineRepositoryMock).addMessage(USER_ONE_THIRD_MESSAGE);
    }

    @Test
    public void testThrowMessageTooLongExceptionIfPostMessageLongerThan500Characters() throws MessageTooLongException{
        //Act / Assert
        MessageTooLongException thrown = assertThrows(
                MessageTooLongException.class,
                () -> unitUnderTest.post(USER_ONE_TOO_LONG_A_MESSAGE),
                "MessageTooLongException not thrown"
        );

        //Assert
        assertTrue(thrown.getMessage().contains(EXPECTED_MESSAGE_TOO_LONG_MESAGE));
    }

    @Test
    public void testCanReadFromAUsersTimeline() throws MessageTooLongException{
        //Arrange
        unitUnderTest.post(USER_ONE_FIRST_MESSAGE);

        //Act
        unitUnderTest.getTimeline(USER_ONE);

        //Assert
        verify(timeLineRepositoryMock).getTimeline(USER_ONE);
    }

    @Test
    public void testCanFollowAnotherUser() {
        //Arrange
        List<String> subscriptions = new ArrayList<String>(){{ add(USER_TWO); }};

        //Act
        unitUnderTest.subscribes(subscriptions, USER_ONE);

        //Assert
        verify(followersRepositoryMock).subscribe(subscriptions, USER_ONE);
    }

    @Test
    public void testCanFolowMultipleUsers(){
        //Arrange
        List<String> subscriptions = new ArrayList<String>(){{ add(USER_TWO); add(USER_THREE); }};

        //Act
        unitUnderTest.subscribes(subscriptions, USER_ONE);

        //Assert
        verify(followersRepositoryMock).subscribe(subscriptions, USER_ONE);
    }

    @Test
    public void testSubscriptionsOfUser(){
        //Act
        unitUnderTest.getSubscriptions(USER_ONE);

        //Assert
        verify(followersRepositoryMock).getSubscriptions(USER_ONE);
    }

    @Test
    public void testCanGetTimelineWithSubscriptions(){
        //Act
        List<String> subscriptions = new ArrayList<String>(){{add(USER_TWO); add(USER_THREE);}};
        unitUnderTest.getTimelineWithSubscriptions(USER_ONE, subscriptions);

        //Assert
        verify(timeLineRepositoryMock).getTimeline(USER_ONE, subscriptions);
    }


}
