package com.journal_app.backend_journal_app.services;

import com.journal_app.backend_journal_app.dto.EntriesDto;
import com.journal_app.backend_journal_app.model.Entries;
import org.apache.coyote.BadRequestException;

import java.time.LocalDate;
import java.util.List;

public interface IEntriesService {

    EntriesDto createEntries(EntriesDto entriesDto, String personId);

    List<EntriesDto> getAllEntries(String personId);

    void deleteEntry(Long id);

    EntriesDto updateEntry(Long id, EntriesDto entry);


    List<EntriesDto> getRangeOfEntries(String personId, LocalDate start, LocalDate end);

    List<EntriesDto> getWeekEntries(String personId);

    List<EntriesDto> getEntriesByDate(String personId, LocalDate date);




}
