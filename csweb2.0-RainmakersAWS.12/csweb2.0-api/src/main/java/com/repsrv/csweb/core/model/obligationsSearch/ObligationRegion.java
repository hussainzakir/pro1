package com.repsrv.csweb.core.model.obligationsSearch;

import com.fasterxml.jackson.annotation.JsonProperty;


public class ObligationRegion {
    @JsonProperty("Region")
    private String region;
    @JsonProperty("Description")
    private String description;
    
    public ObligationRegion() {}
    
    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
