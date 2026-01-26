package com.journal_app.backend_journal_app.services;

import com.journal_app.backend_journal_app.dto.EntriesDto;
import com.journal_app.backend_journal_app.model.Entries;

import java.time.LocalDate;
import java.util.List;

public interface IEntriesService {

    EntriesDto createEntries(EntriesDto entriesDto, String personId);

    List<EntriesDto> getAllEntries(String personId);

    void deleteEntry(Long id);

    EntriesDto updateEntry(Long id, EntriesDto entry);

    List<EntriesDto> getTodaysEntries(String personId);

    List<EntriesDto> getRangeOfEntries(String personId, LocalDate start, LocalDate end);

    List<EntriesDto> getWeekEntries(String personId);



}
