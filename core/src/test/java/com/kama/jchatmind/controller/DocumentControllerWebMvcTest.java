package com.kama.jchatmind.controller;

import com.kama.jchatmind.model.response.CreateDocumentResponse;
import com.kama.jchatmind.service.DocumentFacadeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DocumentController.class)
class DocumentControllerWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DocumentFacadeService documentFacadeService;

    @Test
    void uploadDocument_shouldReturnSuccessResponse() throws Exception {
        when(documentFacadeService.uploadDocument(eq("kb-001"), any()))
                .thenReturn(CreateDocumentResponse.builder().documentId("doc-001").build());

        MockMultipartFile file = new MockMultipartFile(
                "file",
                "note.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "test-content".getBytes()
        );

        mockMvc.perform(multipart("/api/documents/upload")
                        .file(file)
                        .param("kbId", "kb-001"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(200))
                .andExpect(jsonPath("$.data.documentId").value("doc-001"));

        verify(documentFacadeService).uploadDocument(eq("kb-001"), any());
    }
}

