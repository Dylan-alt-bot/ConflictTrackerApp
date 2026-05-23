package com.example.demo.mapper;

import com.example.demo.dto.FactionDTO;
import com.example.demo.model.Country;
import com.example.demo.model.Faction;

import java.util.stream.Collectors;

public class FactionMapper {

    public static FactionDTO toDTO(Faction faction) {

        FactionDTO dto = new FactionDTO();

        dto.setId(faction.getId());

        dto.setName(faction.getName());

        if (faction.getConflict() != null) {

            dto.setConflictId(faction.getConflict().getId());

            dto.setConflictName(faction.getConflict().getName());
        }

        dto.setSupportingCountryIds(faction.getSupportingCountries().stream().map(Country::getId).collect(Collectors.toSet()));

        dto.setSupportingCountryNames(faction.getSupportingCountries().stream().map(Country::getName).collect(Collectors.toSet()));

        return dto;
    }
}
