package com.kama.jchatmind.controller;

import com.kama.jchatmind.model.response.CreateChatMessageResponse;
import com.kama.jchatmind.service.ChatMessageFacadeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ChatMessageController.class)
class ChatMessageControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ChatMessageFacadeService chatMessageFacadeService;

    @Test
    void createChatMessage_shouldReturnSuccessResponse() throws Exception {
        when(chatMessageFacadeService.createChatMessage(any()))
                .thenReturn(CreateChatMessageResponse.builder().chatMessageId("message-001").build());

        String requestBody = """
                {
                  "agentId": "agent-001",
                  "sessionId": "session-001",
                  "role": "user",
                  "content": "hello"
                }
                """;

        mockMvc.perform(post("/api/chat-messages")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data.chatMessageId").value("message-001"));

        verify(chatMessageFacadeService).createChatMessage(any());
    }
}

