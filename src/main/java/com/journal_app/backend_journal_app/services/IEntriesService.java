package com.journal_app.backend_journal_app.services;

import com.journal_app.backend_journal_app.model.Entries;

import java.util.List;

public interface IEntriesService {

    Entries saveEntry();
    void deleteEntry();
    List<Entries> getEntriesByUser();
    List<Entries> getEntriesByDate();


}
