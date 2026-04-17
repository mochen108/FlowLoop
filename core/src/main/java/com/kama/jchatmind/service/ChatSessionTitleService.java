package com.kama.jchatmind.service;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ChatSessionTitleService {

    private static final int MAX_TITLE_LENGTH = 24;

    public String generateTitle(String content) {
        if (!StringUtils.hasText(content)) {
            return "新对话";
        }

        String normalized = content
                .replaceAll("\\s+", " ")
                .trim();

        if (!StringUtils.hasText(normalized)) {
            return "新对话";
        }

        int preferredBreak = findPreferredBreak(normalized);
        String title = preferredBreak > 0
                ? normalized.substring(0, preferredBreak).trim()
                : normalized;

        if (title.length() <= MAX_TITLE_LENGTH) {
            return title;
        }

        return title.substring(0, MAX_TITLE_LENGTH).trim() + "...";
    }

    private int findPreferredBreak(String content) {
        int maxIndex = Math.min(content.length(), MAX_TITLE_LENGTH);
        String breakChars = "。！？!?.,，；;：:";

        for (int i = 0; i < maxIndex; i++) {
            if (breakChars.indexOf(content.charAt(i)) >= 0) {
                return i;
            }
        }

        return -1;
    }
}

