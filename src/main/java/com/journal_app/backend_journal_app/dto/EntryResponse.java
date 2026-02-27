package com.journal_app.backend_journal_app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.OffsetDateTime;

@Getter
@AllArgsConstructor
public class EntryResponse {

    private Long id;
    private String title;
    private String whatDid;
    private String whatLearned;
    private OffsetDateTime createdAt;
}