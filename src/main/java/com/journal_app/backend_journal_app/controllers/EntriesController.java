package com.journal_app.backend_journal_app.controllers;

import com.journal_app.backend_journal_app.dto.EntriesDto;
import com.journal_app.backend_journal_app.services.impl.EntriesServiceImpl;
import com.journal_app.backend_journal_app.util.AuthUtil;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/entries")
public class EntriesController {

    private EntriesServiceImpl entriesService;
    private final AuthUtil authUtil;



    //Add entry rest api
    @PostMapping
    public ResponseEntity<EntriesDto> createEntries(@Valid @RequestBody EntriesDto entriesDto,
                                                    Authentication authentication){

        String personId = authUtil.getPersonId(authentication);

        EntriesDto savedEntries = entriesService.createEntries(entriesDto, personId);

        return new ResponseEntity<>(savedEntries, HttpStatus.CREATED);
    }

    //get entries
    @GetMapping
    public ResponseEntity<List<EntriesDto>> getEntries(Authentication authentication){


        String personId = authUtil.getPersonId(authentication);

        if (personId == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        List<EntriesDto> entriesDtos = entriesService.getAllEntries(personId);

        return ResponseEntity.ok(entriesDtos);
    }


    //delete
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEntries(@PathVariable("id")Long entryId,
                                                Authentication authentication){

        entriesService.deleteEntry(entryId);

        return ResponseEntity.ok("entry was sucessfully deleted.");
    }

    //update
    @PutMapping("{id}")
    public ResponseEntity<EntriesDto> updateEntry(@PathVariable("id") Long entryId,
                                                  @Valid @RequestBody EntriesDto updatedEntry,
                                                  Authentication authentication){


        EntriesDto entryDto = entriesService.updateEntry(entryId, updatedEntry);

        return ResponseEntity.ok(entryDto);
    }

    @GetMapping("/today")
    public ResponseEntity<List<EntriesDto>> getTodayEntries(Authentication authentication){


        String personId = authUtil.getPersonId(authentication);


        return ResponseEntity.ok(entriesService.getTodaysEntries(personId));
    }

    @GetMapping("/range")
    public ResponseEntity<List<EntriesDto>> getEntriesForRange(
            @RequestParam LocalDate start, @RequestParam LocalDate end, Authentication authentication) {


        String personId = authUtil.getPersonId(authentication);


        return ResponseEntity.ok(entriesService.getRangeOfEntries(personId, start, end));
    }

    @GetMapping("/weekly")
    public ResponseEntity<List<EntriesDto>> getEntriesWeekly(
            Authentication authentication) {

        String personId = authUtil.getPersonId(authentication);


        return ResponseEntity.ok(entriesService.getWeekEntries(personId));
    }

    @GetMapping("/by-date")
    public List<EntriesDto> getEntriesByDate(
            @RequestParam LocalDate date,
            Authentication authentication
    ) {

        String personId = authUtil.getPersonId(authentication);

        return entriesService.getEntriesByDate(personId, date);
    }




}
