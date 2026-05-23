package com.example.demo.dto;

import java.util.Set;

public class FactionDTO {

    private Long id;

    private String name;

    private Long conflictId;

    private String conflictName;

    private Set<Long> supportingCountryIds;

    private Set<String> supportingCountryNames;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Long getConflictId() {
        return conflictId;
    }

    public void setConflictId(Long conflictId) {
        this.conflictId = conflictId;
    }


    public String getConflictName() {
        return conflictName;
    }

    public void setConflictName(String conflictName) {
        this.conflictName = conflictName;
    }


    public Set<Long> getSupportingCountryIds() {
        return supportingCountryIds;
    }

    public void setSupportingCountryIds(Set<Long> supportingCountryIds) {
        this.supportingCountryIds = supportingCountryIds;
    }


    public Set<String> getSupportingCountryNames() {
        return supportingCountryNames;
    }

    public void setSupportingCountryNames(Set<String> supportingCountryNames) {
        this.supportingCountryNames = supportingCountryNames;
    }

}
