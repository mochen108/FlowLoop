package com.kama.jchatmind.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kama.jchatmind.converter.ChatMessageConverter;
import com.kama.jchatmind.mapper.ChatMessageMapper;
import com.kama.jchatmind.mapper.ChatSessionMapper;
import com.kama.jchatmind.model.dto.ChatMessageDTO;
import com.kama.jchatmind.model.entity.ChatMessage;
import com.kama.jchatmind.model.entity.ChatSession;
import com.kama.jchatmind.service.impl.ChatMessageFacadeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ChatMessageFacadeServiceImplTest {

    @Mock
    private ChatMessageMapper chatMessageMapper;

    @Mock
    private ChatSessionMapper chatSessionMapper;

    @Mock
    private ApplicationEventPublisher publisher;

    @Spy
    private ChatMessageConverter chatMessageConverter = new ChatMessageConverter(new ObjectMapper());

    @Spy
    private ChatSessionTitleService chatSessionTitleService = new ChatSessionTitleService();

    @InjectMocks
    private ChatMessageFacadeServiceImpl chatMessageFacadeService;

    @Test
    void createChatMessage_shouldGenerateTitleForUntitledSession() {
        ChatSession chatSession = ChatSession.builder()
                .id("session-001")
                .agentId("agent-001")
                .title(null)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        when(chatMessageMapper.insert(any())).thenAnswer(invocation -> {
            ChatMessage entity = invocation.getArgument(0);
            entity.setId("message-001");
            return 1;
        });
        when(chatSessionMapper.selectById("session-001")).thenReturn(chatSession);

        chatMessageFacadeService.createChatMessage(ChatMessageDTO.builder()
                .sessionId("session-001")
                .role(ChatMessageDTO.RoleType.USER)
                .content("帮我梳理一下当前项目的模块职责和调用关系")
                .build());

        ArgumentCaptor<ChatSession> captor = ArgumentCaptor.forClass(ChatSession.class);
        verify(chatSessionMapper).updateById(captor.capture());
        assertEquals("帮我梳理一下当前项目的模块职责和调用关...", captor.getValue().getTitle());
    }

    @Test
    void createChatMessage_shouldNotOverwriteExistingTitle() {
        ChatSession chatSession = ChatSession.builder()
                .id("session-001")
                .agentId("agent-001")
                .title("Existing title")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        when(chatMessageMapper.insert(any())).thenAnswer(invocation -> {
            ChatMessage entity = invocation.getArgument(0);
            entity.setId("message-001");
            return 1;
        });
        when(chatSessionMapper.selectById("session-001")).thenReturn(chatSession);

        chatMessageFacadeService.createChatMessage(ChatMessageDTO.builder()
                .sessionId("session-001")
                .role(ChatMessageDTO.RoleType.USER)
                .content("新的提问内容")
                .build());

        verify(chatSessionMapper, never()).updateById(any());
    }
}

