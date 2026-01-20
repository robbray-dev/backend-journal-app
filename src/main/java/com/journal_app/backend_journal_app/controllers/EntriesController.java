package com.journal_app.backend_journal_app.controllers;

import com.journal_app.backend_journal_app.dto.EntriesDto;
import com.journal_app.backend_journal_app.services.impl.EntriesServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;

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
        Map<String, String> principal = (Map<String, String>) authentication.getPrincipal();


        String personId = (String) principal.get("personId");

        EntriesDto savedEntries = entriesService.createEntries(entriesDto, personId);

        return new ResponseEntity<>(savedEntries, HttpStatus.CREATED);
    }
}
