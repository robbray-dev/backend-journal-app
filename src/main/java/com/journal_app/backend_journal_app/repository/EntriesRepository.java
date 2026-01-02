package com.journal_app.backend_journal_app.repository;

import com.journal_app.backend_journal_app.model.Entries;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntriesRepository
        extends JpaRepository<Entries, Integer> {

    

}
