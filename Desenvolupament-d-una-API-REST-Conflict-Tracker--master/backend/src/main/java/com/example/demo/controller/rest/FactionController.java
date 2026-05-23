package com.example.demo.controller.rest;

import com.example.demo.dto.CreateFactionDTO;
import com.example.demo.dto.FactionDTO;
import com.example.demo.service.FactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/factions")
public class FactionController {

    private final FactionService factionService;

    public FactionController(FactionService factionService) {
        this.factionService = factionService;
    }

    @GetMapping
    public ResponseEntity<List<FactionDTO>> getAll() {
        return ResponseEntity.ok(factionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FactionDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(factionService.findById(id));
    }

    @GetMapping("/conflict/{conflictId}")
    public ResponseEntity<List<FactionDTO>>
    findByConflict(@PathVariable Long conflictId) {
        return ResponseEntity.ok(factionService.findByConflict(conflictId));
    }

    @PostMapping
    public ResponseEntity<FactionDTO> create(@RequestBody CreateFactionDTO dto) {
        return ResponseEntity.status(201).body(factionService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FactionDTO> update(@PathVariable Long id, @RequestBody CreateFactionDTO dto) {
        return ResponseEntity.ok(factionService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        factionService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
