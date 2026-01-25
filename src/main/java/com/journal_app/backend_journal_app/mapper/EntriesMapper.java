package com.journal_app.backend_journal_app.mapper;

import com.journal_app.backend_journal_app.dto.EntriesDto;
import com.journal_app.backend_journal_app.model.Entries;

import java.util.UUID;

public class EntriesMapper {
    public static EntriesDto mapToEntriesDto(Entries entry) {
        return new EntriesDto(
                entry.getId(),
                entry.getTitle(),
                entry.getWhat_did(),
                entry.getWhat_learned()
        );
    }

    public static Entries mapToEntries(EntriesDto entriesDto, String personId) {
        UUID userId = UUID.fromString(personId);
        Entries entry = new Entries();

        if(entriesDto.getId() != null){
            entry.setId(entriesDto.getId());
        }

        entry.setTitle(entriesDto.getTitle());
        entry.setWhat_did(entriesDto.getWhat_did());
        entry.setWhat_learned(entriesDto.getWhat_learned());
        entry.setUsers(userId);

        return entry;
    }
}
