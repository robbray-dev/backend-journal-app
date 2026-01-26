package com.journal_app.backend_journal_app.services.impl;

import com.journal_app.backend_journal_app.dto.EntriesDto;
import com.journal_app.backend_journal_app.mapper.EntriesMapper;
import com.journal_app.backend_journal_app.model.Entries;
import com.journal_app.backend_journal_app.repository.EntriesRepository;
import com.journal_app.backend_journal_app.services.IEntriesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
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

        //get the entries with this personId
        UUID userId = UUID.fromString(personId);

        List<Entries> entries = entriesRepository.findByUsers(userId);

        List<EntriesDto> entriesDtos = new ArrayList<>();
        for (Entries entry : entries) {
            entriesDtos.add(EntriesMapper.mapToEntriesDto(entry));
        }

        return entriesDtos;
    }


    //delete
    @Override
    public void deleteEntry(Long id) {

        //delete an entry by the id

        entriesRepository.deleteById(id);


    }

    @Override
    public EntriesDto updateEntry(Long id, EntriesDto entry) {


        Entries entryToUpdate = entriesRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("invalid id given : " + id)
        );

        entryToUpdate.setTitle(entry.getTitle());
        entryToUpdate.setWhat_learned(entry.getWhat_learned());
        entryToUpdate.setWhat_did(entry.getWhat_did());


        Entries updatedEntry = entriesRepository.save(entryToUpdate);


        return EntriesMapper.mapToEntriesDto(updatedEntry);

    }

    @Override
    public List<EntriesDto> getTodaysEntries(String personId) {
        UUID userId = UUID.fromString(personId);
        Instant start = LocalDate.now(ZoneOffset.UTC)
                .atStartOfDay()
                .toInstant(ZoneOffset.UTC);

        Instant end = start.plus(1, ChronoUnit.DAYS);

        List<Entries> entries = entriesRepository.findByUsersAndCreatedAtBetweenOrderByCreatedAtDesc(
                userId, start, end
        );

        List<EntriesDto> entriesDtos = new ArrayList<>();

        for(Entries entry: entries){
            entriesDtos.add(EntriesMapper.mapToEntriesDto(entry));
        }

        return entriesDtos;
    }

    @Override
    public List<EntriesDto> getRangeOfEntries(String personId, LocalDate startDate, LocalDate endDate) {
        UUID userId = UUID.fromString(personId);
        Instant start = startDate
                .atStartOfDay()
                .toInstant(ZoneOffset.UTC);

        Instant end = endDate
                .plusDays(1)   // inclusive end date
                .atStartOfDay()
                .toInstant(ZoneOffset.UTC);

        List<Entries> entries = entriesRepository.findByUsersAndCreatedAtBetweenOrderByCreatedAtDesc(
                userId, start, end
        );

        List<EntriesDto> entriesDtos = new ArrayList<>();

        for (Entries entry : entries) {
            entriesDtos.add(EntriesMapper.mapToEntriesDto(entry));
        }
        return entriesDtos;
    }

    @Override
    public List<EntriesDto> getWeekEntries(String personId) {
        UUID userId = UUID.fromString(personId);
        LocalDate today = LocalDate.now(ZoneOffset.UTC);

        LocalDate monday = today.with(DayOfWeek.MONDAY);
        LocalDate nextMonday = monday.plusWeeks(1);

        Instant start = monday.atStartOfDay().toInstant(ZoneOffset.UTC);
        Instant end = nextMonday.atStartOfDay().toInstant(ZoneOffset.UTC);

        List<EntriesDto> entriesDtos = new ArrayList<>();

        List<Entries>  entries = entriesRepository.findByUsersAndCreatedAtBetweenOrderByCreatedAtDesc(
                userId, start, end
        );

        for(Entries entry: entries) {
            entriesDtos.add(EntriesMapper.mapToEntriesDto(entry));
        }

        return entriesDtos;
    }


}
