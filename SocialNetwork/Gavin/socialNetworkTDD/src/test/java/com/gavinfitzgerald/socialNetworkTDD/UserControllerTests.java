package com.gavinfitzgerald.socialNetworkTDD;
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

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;

@ExtendWith(SpringExtension.class)
@WebMvcTest
class UserControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private IUserService userServiceMock;

    @Test
    void testPostAMessageReturns200() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/post")
                .contentType(MediaType.APPLICATION_JSON)
                .param("user", "Bob")
                .param("message", "Testomg")
        ).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void testPostAMessageReturns400() throws Exception {
        doThrow(MessageTooLongException.class)
                .when(userServiceMock)
                .post(anyString(), anyString());


        mockMvc.perform(MockMvcRequestBuilders.post("/api/post")
                .contentType(MediaType.APPLICATION_JSON)
                .param("user", "Bob")
                .param("message", "TOO_LONG_A_MESSAGE")
        ).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void testPostAMessageReturns500() throws Exception {
        doThrow(RuntimeException.class)
                .when(userServiceMock)
                .post(anyString(), anyString());


        mockMvc.perform(MockMvcRequestBuilders.post("/api/post")
                .contentType(MediaType.APPLICATION_JSON)
                .param("user", "Bob")
                .param("message", "Will throw exception")
        ).andExpect(MockMvcResultMatchers.status().isInternalServerError());
    }
}