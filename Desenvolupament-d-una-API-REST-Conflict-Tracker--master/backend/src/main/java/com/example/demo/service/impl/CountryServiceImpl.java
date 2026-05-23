package com.example.demo.service.impl;

import com.example.demo.dto.CountryDTO;
import com.example.demo.model.Country;
import com.example.demo.repository.CountryRepository;
import com.example.demo.service.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public CountryDTO create(CountryDTO dto) {
        Country country = new Country();
        country.setName(dto.getName());
        country.setCode(dto.getCode());

        Country saved = countryRepository.save(country);

        return toDTO(saved);
    }

    @Override
    public List<CountryDTO> findAll() {
        return countryRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CountryDTO findById(Long id) {
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Country not found"));

        return toDTO(country);
    }

    @Override
    public void delete(Long id) {
        countryRepository.deleteById(id);
    }

    private CountryDTO toDTO(Country country) {
        CountryDTO dto = new CountryDTO();
        dto.setId(country.getId());
        dto.setName(country.getName());
        dto.setCode(country.getCode());
        return dto;
    }
}
