package com.example.demo.mapper;

import com.example.demo.dto.EventDTO;
import com.example.demo.model.Event;
import com.example.demo.model.Conflict;

public class EventMapper {

    private EventMapper() {}

    public static EventDTO toDTO(Event event) {

        if (event == null) return null;

        EventDTO dto = new EventDTO();

        dto.setId(event.getId());
        dto.setEventDate(event.getEventDate());
        dto.setLocation(event.getLocation());
        dto.setDescription(event.getDescription());

        Conflict conflict = event.getConflict();

        if (conflict != null) {
            dto.setConflictId(conflict.getId());
            dto.setConflictName(conflict.getName() != null ? conflict.getName() : null);
        }
        return dto;
    }
}
