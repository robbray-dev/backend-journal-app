package com.journal_app.backend_journal_app.services.impl;

import com.journal_app.backend_journal_app.dto.EntriesDto;
import com.journal_app.backend_journal_app.mapper.EntriesMapper;
import com.journal_app.backend_journal_app.model.Entries;
import com.journal_app.backend_journal_app.repository.EntriesRepository;
import com.journal_app.backend_journal_app.services.IEntriesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
@AllArgsConstructor
public class EntriesServiceImpl implements IEntriesService {

    private EntriesRepository entriesRepository;

    @Override
    public EntriesDto createEntries(EntriesDto entriesDto, String personId) {

        //to store the entry into database, we need to convert the dto to entity
        Entries entries = EntriesMapper.mapToEntries(entriesDto, personId);
        Entries savedEntries = entriesRepository.save(entries);

        //will need to return the entries to the client, as a dto
        EntriesDto entriesDtoClient = EntriesMapper.mapToEntriesDto(savedEntries);

        return entriesDtoClient;
    }

    @Override
    public List<EntriesDto> getAllEntries(String personId) {

        System.out.println("Is it reaching here");
        //get the entries with this personId
        UUID userId = UUID.fromString(personId);

        List<Entries> entries = entriesRepository.findByUsers(userId);

        List<EntriesDto> entriesDtos = new ArrayList<>();
        for (Entries entry : entries) {
            entriesDtos.add(EntriesMapper.mapToEntriesDto(entry));
        }

        return entriesDtos;
    }
}
