package com.journal_app.backend_journal_app.services;

import com.journal_app.backend_journal_app.model.Entries;
import com.journal_app.backend_journal_app.repository.EntriesRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EntriesService {
    private final EntriesRepository EntriesRepository;

    public EntriesService(EntriesRepository EntriesRepository){
        this.EntriesRepository = EntriesRepository;
    }

    public List<Entries> getEntries(){
        return EntriesRepository.findAll();
    }
}
