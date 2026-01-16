package com.journal_app.backend_journal_app.mapper;

import com.journal_app.backend_journal_app.dto.EntriesDto;
import com.journal_app.backend_journal_app.model.Entries;

import java.util.UUID;

public class EntriesMapper {
    public static EntriesDto mapToEntriesDto(Entries entry) {
        return new EntriesDto(
                entry.getTitle(),
                entry.getWhat_did(),
                entry.getWhat_learned()
        );
    }

    public static Entries mapToEntries(EntriesDto entriesDto, String personId) {
        UUID userId = UUID.fromString(personId);
        return new Entries(
                1,
                entriesDto.getTitle(),
                entriesDto.getWhat_did(),
                entriesDto.getWhat_learned(),
                userId
        );
    }
}
