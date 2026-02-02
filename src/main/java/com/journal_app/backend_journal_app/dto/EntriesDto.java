package com.journal_app.backend_journal_app.dto;

import jakarta.validation.constraints.NotBlank;
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

    @NotBlank
    private String title;

    @NotBlank
    private String what_did;

    @NotBlank
    private String what_learned;
}
