package com.example.demo.controller.rest;

import com.example.demo.dto.CountryDTO;
import com.example.demo.dto.ConflictDTO;
import com.example.demo.service.CountryService;
import com.example.demo.service.ConflictService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/countries")
public class CountryController {

    private final CountryService countryService;
    private final ConflictService conflictService;

    public CountryController(CountryService countryService,
                             ConflictService conflictService) {
        this.countryService = countryService;
        this.conflictService = conflictService;
    }

    // CREATE COUNTRY (AIXÒ ET FALTAVA)
    @PostMapping
    public ResponseEntity<CountryDTO> create(@RequestBody CountryDTO dto) {
        return ResponseEntity.ok(countryService.create(dto));
    }

    // GET ALL COUNTRIES
    @GetMapping
    public ResponseEntity<List<CountryDTO>> getAll() {
        return ResponseEntity.ok(countryService.findAll());
    }

    // GET CONFLICTS BY COUNTRY CODE
    @GetMapping("/{code}/conflicts")
    public ResponseEntity<List<ConflictDTO>> getConflictsByCountry(
            @PathVariable String code) {

        return ResponseEntity.ok(
                conflictService.findByCountryCode(code)
        );
    }
}
