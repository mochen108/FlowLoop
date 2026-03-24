package com.kama.jchatmind.controller;

import com.kama.jchatmind.model.response.CreateChatSessionResponse;
import com.kama.jchatmind.service.ChatSessionFacadeService;
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

@WebMvcTest(ChatSessionController.class)
class ChatSessionControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ChatSessionFacadeService chatSessionFacadeService;

    @Test
    void createChatSession_shouldReturnSuccessResponse() throws Exception {
        when(chatSessionFacadeService.createChatSession(any()))
                .thenReturn(CreateChatSessionResponse.builder().chatSessionId("session-001").build());

        String requestBody = """
                {
                  "agentId": "agent-001",
                  "title": "Integration Test Session"
                }
                """;

        mockMvc.perform(post("/api/chat-sessions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data.chatSessionId").value("session-001"));

        verify(chatSessionFacadeService).createChatSession(any());
    }
}

