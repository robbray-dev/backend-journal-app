package com.journal_app.backend_journal_app.services;

import com.journal_app.backend_journal_app.dto.EntriesDto;
import com.journal_app.backend_journal_app.model.Entries;

import java.util.List;

public interface IEntriesService {

    EntriesDto createEntries(EntriesDto entriesDto, String personId);


}
