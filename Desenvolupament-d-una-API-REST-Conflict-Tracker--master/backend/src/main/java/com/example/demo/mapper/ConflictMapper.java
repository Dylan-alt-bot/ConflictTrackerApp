package com.example.demo.mapper;

import com.example.demo.dto.ConflictDTO;
import com.example.demo.dto.CreateConflictDTO;
import com.example.demo.dto.CountryDTO;
import com.example.demo.model.Conflict;
import com.example.demo.model.Country;

import java.util.List;
import java.util.stream.Collectors;

public class ConflictMapper {

    private ConflictMapper() {
    }

    public static ConflictDTO toDTO(Conflict conflict){

        ConflictDTO dto = new ConflictDTO();

        dto.setId(conflict.getId());
        dto.setName(conflict.getName());
        dto.setDescription(conflict.getDescription());

        dto.setStatus(conflict.getStatus());

        dto.setStartDate(conflict.getStartDate());


        dto.setCountries(conflict.getCountries().stream().map(country -> {

            CountryDTO c = new CountryDTO();

            c.setId(country.getId());

            c.setName(country.getName());

            c.setCode(country.getCode());

            return c;

        }).toList()

        );
        return dto;
    }

    public static Conflict fromCreateDTO(CreateConflictDTO dto) {
        if (dto == null) return null;

        Conflict conflict = new Conflict();
        conflict.setName(dto.getName());
        conflict.setStartDate(dto.getStartDate());
        conflict.setStatus(dto.getStatus());
        conflict.setDescription(dto.getDescription());
        return conflict;
    }

    private static CountryDTO toCountryDTO(Country country) {
        CountryDTO dto = new CountryDTO();
        dto.setId(country.getId());
        dto.setName(country.getName());
        dto.setCode(country.getCode());
        return dto;
    }
}
