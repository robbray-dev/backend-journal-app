package com.journal_app.backend_journal_app.controllers;

import com.journal_app.backend_journal_app.dto.EntriesDto;
import com.journal_app.backend_journal_app.services.impl.EntriesServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/entries")
public class EntriesController {

    private EntriesServiceImpl entriesService;

    //Add entry rest api
    @PostMapping
    public ResponseEntity<EntriesDto> createEntries(@RequestBody EntriesDto entriesDto){
        EntriesDto savedEntries = entriesService.createEntries(entriesDto);
        return new ResponseEntity<>(savedEntries, HttpStatus.CREATED);
    }
}
