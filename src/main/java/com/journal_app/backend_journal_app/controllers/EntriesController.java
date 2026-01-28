package com.journal_app.backend_journal_app.controllers;

import com.journal_app.backend_journal_app.dto.EntriesDto;
import com.journal_app.backend_journal_app.services.impl.EntriesServiceImpl;
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


    //Add entry rest api
    @PostMapping
    public ResponseEntity<EntriesDto> createEntries(@RequestBody EntriesDto entriesDto,
                                                    Authentication authentication){
        // Edge Case: Check if authentication exists and user is not 'anonymous'
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Map<String, String> principal = (Map<String, String>) authentication.getPrincipal();


        String personId = (String) principal.get("personId");


        if (personId == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        if(!StringUtils.hasText(entriesDto.getTitle()) || !StringUtils.hasText(entriesDto.getWhat_learned()) || !StringUtils.hasText(entriesDto.getWhat_did())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        EntriesDto savedEntries = entriesService.createEntries(entriesDto, personId);

        return new ResponseEntity<>(savedEntries, HttpStatus.CREATED);
    }

    //get entries
    @GetMapping
    public ResponseEntity<List<EntriesDto>> getEntries(Authentication authentication){
        // Edge Case: Check if authentication exists and user is not 'anonymous'
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Map<String, String> principal = (Map<String, String>) authentication.getPrincipal();

        String personId = (String) principal.get("personId");

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
        // Edge Case: Check if authentication exists and user is not 'anonymous'
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        entriesService.deleteEntry(entryId);

        return ResponseEntity.ok("entry was sucessfully deleted.");
    }

    //update
    @PutMapping("{id}")
    public ResponseEntity<EntriesDto> updateEntry(@PathVariable("id") Long entryId,
                                                  @RequestBody EntriesDto updatedEntry,
                                                  Authentication authentication){
        // Edge Case: Check if authentication exists and user is not 'anonymous'
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if(!StringUtils.hasText(updatedEntry.getTitle()) || !StringUtils.hasText(updatedEntry.getWhat_learned()) || !StringUtils.hasText(updatedEntry.getWhat_did())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        EntriesDto entryDto = entriesService.updateEntry(entryId, updatedEntry);

        return ResponseEntity.ok(entryDto);
    }

    @GetMapping("/today")
    public ResponseEntity<List<EntriesDto>> getTodayEntries(Authentication authentication){

        // Edge Case: Check if authentication exists and user is not 'anonymous'
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Map<String, String> principal = (Map<String, String>) authentication.getPrincipal();

        String personId = (String) principal.get("personId");

        if (personId == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return ResponseEntity.ok(entriesService.getTodaysEntries(personId));
    }

    @GetMapping("/range")
    public ResponseEntity<List<EntriesDto>> getEntriesForRange(
            @RequestParam LocalDate start, @RequestParam LocalDate end, Authentication authentication) {
        // Edge Case: Check if authentication exists and user is not 'anonymous'
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Map<String, String> principal = (Map<String, String>) authentication.getPrincipal();
        String personId = (String) principal.get("personId");

        if (personId == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return ResponseEntity.ok(entriesService.getRangeOfEntries(personId, start, end));
    }

    @GetMapping("/weekly")
    public ResponseEntity<List<EntriesDto>> getEntriesWeekly(
            Authentication authentication) {
        // Edge Case: Check if authentication exists and user is not 'anonymous'
        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        Map<String, String> principal = (Map<String, String>) authentication.getPrincipal();

        String personId = (String) principal.get("personId");

        if (personId == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return ResponseEntity.ok(entriesService.getWeekEntries(personId));
    }




}
