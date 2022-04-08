package com.gavinfitzgerald.socialNetworkTDD;
import com.gavinfitzgerald.socialNetworkTDD.DTOs.Message;
import com.gavinfitzgerald.socialNetworkTDD.DTOs.MessageTooLongException;
import com.gavinfitzgerald.socialNetworkTDD.Services.IUserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@WebMvcTest
class UserControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private IUserService userServiceMock;

    private final String USER_PARAM = "user";
    private final String MESSAGE_PARAM = "message";
    private final String USER_ONE = "Bob";
    private final String USER_TWO = "Alice";
    private final String FIRST_MESSAGE = "Message 1";
    private final String SECOND_MESSAGE = "Message 2";
    private final Message USER_ONE_FIRST_MESSAGE = new Message(USER_ONE, FIRST_MESSAGE);
    private final Message USER_ONE_SECOND_MESSAGE = new Message(USER_ONE, SECOND_MESSAGE);
    private final Message USER_TWO_FIRST_MESSAGE = new Message(USER_TWO, FIRST_MESSAGE);
    private final Message USER_TWO_SECOND_MESSAGE = new Message(USER_TWO, SECOND_MESSAGE);

    @Test
    public void testPostAMessageReturns200() throws Exception {
        //Act / Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/api/post")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"message\": \""+FIRST_MESSAGE+"\",\"user\": \""+USER_ONE+"\"}")
        ).andExpect(MockMvcResultMatchers.status().isOk());

        //Assert
        verify(userServiceMock).post(any(Message.class));
    }

    @Test
    public void testPostAMessageReturns400() throws Exception {
        doThrow(MessageTooLongException.class)
                .when(userServiceMock)
                .post(any(Message.class));


        mockMvc.perform(MockMvcRequestBuilders.post("/api/post")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"message\": \""+FIRST_MESSAGE+"\",\"user\": \""+USER_ONE+"\"}")
        ).andExpect(MockMvcResultMatchers.status().isBadRequest());

        verify(userServiceMock).post(any(Message.class));
    }

    @Test
    public void testPostAMessageReturns500() throws Exception {
        doThrow(RuntimeException.class)
                .when(userServiceMock)
                .post(any(Message.class));


        mockMvc.perform(MockMvcRequestBuilders.post("/api/post")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"message\": \""+FIRST_MESSAGE+"\",\"user\": \""+USER_ONE+"\"}")
        ).andExpect(MockMvcResultMatchers.status().isInternalServerError());

        verify(userServiceMock).post(any(Message.class));
    }

    @Test
    public void testPostAMessageWithMissingUserParamThrows400() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/post")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"message\": \""+FIRST_MESSAGE+"\"}")
        ).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testGetATimelineReturns200() throws Exception {
        //Arrange
        List<Message> messages = new ArrayList<Message>();
        messages.add(USER_ONE_FIRST_MESSAGE);
        Mockito.when(userServiceMock.getTimeline(anyString())).thenReturn(messages);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/timeline")
                .contentType(MediaType.APPLICATION_JSON)
                .param(USER_PARAM, USER_ONE)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$[0].user").value(USER_ONE))
                .andExpect(jsonPath("$[0].message").value(FIRST_MESSAGE));

        verify(userServiceMock).getTimeline(USER_ONE);
    }

    @Test
    public void testGetATimelineReturns500() throws Exception {
        doThrow(RuntimeException.class)
                .when(userServiceMock)
                .getTimeline(anyString());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/timeline")
                .contentType(MediaType.APPLICATION_JSON)
                .param(USER_PARAM, USER_ONE)
        ).andExpect(MockMvcResultMatchers.status().isInternalServerError());

        verify(userServiceMock).getTimeline(USER_ONE);
    }

    @Test
    public void testGetATimelineWithSubscriptionsReturns200() throws Exception {
        //Arrange
        List<Message> messages = new ArrayList<Message>();
        messages.add(USER_TWO_FIRST_MESSAGE);
        messages.add(USER_ONE_SECOND_MESSAGE);
        Mockito.when(userServiceMock.getTimelineWithSubscriptions(anyString(), any(List.class))).thenReturn(messages);

        //ACT/ASSERT
        mockMvc.perform(MockMvcRequestBuilders.get("/api/timelineWithSubscriptions")
                .contentType(MediaType.APPLICATION_JSON)
                .param(USER_PARAM, USER_ONE)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$[0].user").value(USER_TWO))
                .andExpect(jsonPath("$[0].message").value(FIRST_MESSAGE))
                .andExpect(jsonPath("$[1].user").value(USER_ONE))
                .andExpect(jsonPath("$[1].message").value(SECOND_MESSAGE));
        verify(userServiceMock).getTimelineWithSubscriptions(eq(USER_ONE), any(List.class));
    }

    @Test
    public void testGetATimelineWithSubscriptionsWhenUserHasNoSubscriptionsReturns200() throws Exception {
        //Arrange
        Mockito.when(userServiceMock.getSubscriptions(anyString())).thenReturn(null);

        //ACT/ASSERT
        mockMvc.perform(MockMvcRequestBuilders.get("/api/timelineWithSubscriptions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param(USER_PARAM, USER_ONE)
                ).andExpect(MockMvcResultMatchers.status().isOk());
        verify(userServiceMock).getTimeline(eq(USER_ONE));
    }

    @Test
    public void testGetATimelineWithSubscriptionsReturns500() throws Exception {
        doThrow(RuntimeException.class)
                .when(userServiceMock)
                .getTimelineWithSubscriptions(anyString(), any(List.class));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/timelineWithSubscriptions")
                .contentType(MediaType.APPLICATION_JSON)
                .param(USER_PARAM, USER_ONE)
        ).andExpect(MockMvcResultMatchers.status().isInternalServerError());

        verify(userServiceMock).getTimelineWithSubscriptions(eq(USER_ONE), any(List.class));
    }

    @Test
    public void testSubscribeToUsersReturns200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/subscribe")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"user\": \""+USER_ONE+"\",\"subscriptions\" : [ \""+USER_TWO+"\" ] }")
        ).andExpect(MockMvcResultMatchers.status().isOk());

        verify(userServiceMock).subscribes(any(List.class), eq(USER_ONE));
    }

    @Test
    public void testSubscribeToUsersWithMissingUserParamThrows400() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/subscribe")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"subscriptions\" : [ \""+USER_TWO+"\" ] }")
        ).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testSubscribeToUsersWithMissingSubscriptionsParamThrows400() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/subscribe")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"user\": \""+USER_ONE+"\" }")
        ).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testSubscribeToUsersThrows500() throws Exception {
        doThrow(RuntimeException.class)
                .when(userServiceMock)
                .subscribes(any(List.class), anyString());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/subscribe")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"user\": \""+USER_ONE+"\",\"subscriptions\" : [ \""+USER_TWO+"\" ] }")
        ).andExpect(MockMvcResultMatchers.status().isInternalServerError());
    }

    @Test
    public void testGetUserSubscriptionsReturns200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/getSubscriptions")
                .contentType(MediaType.APPLICATION_JSON)
                .param(USER_PARAM, USER_ONE)
        ).andExpect(MockMvcResultMatchers.status().isOk());

        verify(userServiceMock).getSubscriptions(USER_ONE);
    }

    @Test
    public void testGetUserSubscriptionsReturns500() throws Exception {
        doThrow(RuntimeException.class)
                .when(userServiceMock)
                .getSubscriptions(USER_ONE);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/getSubscriptions")
                .contentType(MediaType.APPLICATION_JSON)
                .param(USER_PARAM, USER_ONE)
        ).andExpect(MockMvcResultMatchers.status().isInternalServerError());

        verify(userServiceMock).getSubscriptions(USER_ONE);
    }
}