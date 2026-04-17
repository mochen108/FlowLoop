package com.kama.jchatmind.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChatSessionTitleServiceTest {

    private final ChatSessionTitleService chatSessionTitleService = new ChatSessionTitleService();

    @Test
    void generateTitle_shouldNormalizeWhitespaceAndTrimLength() {
        String title = chatSessionTitleService.generateTitle("  帮我总结一下   这个项目的技术架构  和部署方式  ");

        assertEquals("帮我总结一下 这个项目的技术架构 和...", title);
    }

    @Test
    void generateTitle_shouldFallbackWhenContentIsBlank() {
        String title = chatSessionTitleService.generateTitle("   ");

        assertEquals("新对话", title);
    }
}

