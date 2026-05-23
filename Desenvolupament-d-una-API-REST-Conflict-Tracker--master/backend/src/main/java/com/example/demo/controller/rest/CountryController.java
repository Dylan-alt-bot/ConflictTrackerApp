package com.example.demo.controller.rest;

import com.example.demo.dto.ConflictDTO;
import com.example.demo.service.ConflictService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/countries")
public class CountryController {

    private final ConflictService conflictService;

    public CountryController(ConflictService conflictService) {
        this.conflictService = conflictService;
    }

    @GetMapping("/{code}/conflicts")
    public ResponseEntity<List<ConflictDTO>> getConflictsByCountry(@PathVariable("code") String code) {

        List<ConflictDTO> conflicts = conflictService.findByCountryCode(code);
        return ResponseEntity.ok(conflicts);
    }
}
