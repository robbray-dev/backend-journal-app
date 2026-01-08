package com.journal_app.backend_journal_app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EntriesDto {
    private Long id;
    private String title;
    private String what_did;
    private String what_learned;

    //may remove this from the dto
    private UUID user_id;
}
