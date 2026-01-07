package com.journal_app.backend_journal_app.repository;

import com.journal_app.backend_journal_app.model.Entries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface EntriesRepository
        extends JpaRepository<Entries, Integer> {

    @Query(value = "SELECT id, title, entry_date, what_did, what_learned FROM entries WHERE user_id:=user_id", nativeQuery = true)
    List<Entries> getEntriesByUserId(@Param("user_id")UUID user_id);


}
