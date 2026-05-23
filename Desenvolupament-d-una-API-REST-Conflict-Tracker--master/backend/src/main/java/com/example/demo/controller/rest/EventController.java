package com.example.demo.controller.rest;

import com.example.demo.dto.CreateEventDTO;
import com.example.demo.dto.EventDTO;
import com.example.demo.service.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public ResponseEntity<List<EventDTO>> getAll() {
        return ResponseEntity.ok(eventService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.findById(id));
    }

    @GetMapping("/conflict/{conflictId}")
    public ResponseEntity<List<EventDTO>>
    findByConflict(@PathVariable Long conflictId) {
        return ResponseEntity.ok(eventService.findByConflict(conflictId));
    }


    @PostMapping
    public ResponseEntity<EventDTO> create(@RequestBody CreateEventDTO dto) {
        return ResponseEntity.status(201).body(eventService.create(dto));
    }


    @PutMapping("/{id}")
    public ResponseEntity<EventDTO> update(@PathVariable Long id, @RequestBody CreateEventDTO dto) {
        return ResponseEntity.ok(eventService.update(id, dto));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        eventService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
