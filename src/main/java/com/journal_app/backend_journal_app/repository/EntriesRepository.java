package com.journal_app.backend_journal_app.repository;

import com.journal_app.backend_journal_app.model.Entries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface EntriesRepository
        extends JpaRepository<Entries, Integer> {

    List<Entries> findByUsers(UUID users);


}
