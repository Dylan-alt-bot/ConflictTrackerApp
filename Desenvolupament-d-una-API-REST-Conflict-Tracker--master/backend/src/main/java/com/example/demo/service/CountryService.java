package com.example.demo.service;

import com.example.demo.dto.CountryDTO;

import java.util.List;

public interface CountryService {

    CountryDTO create(CountryDTO dto);

    List<CountryDTO> findAll();

    CountryDTO findById(Long id);

    void delete(Long id);
}
