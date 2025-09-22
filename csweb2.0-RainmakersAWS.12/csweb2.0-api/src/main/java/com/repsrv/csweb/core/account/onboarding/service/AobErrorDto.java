package com.repsrv.csweb.core.account.onboarding.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.poiji.annotation.ExcelCell;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter 
@AllArgsConstructor

public class AobErrorDto {

    @JsonProperty("identifier")
    @ExcelCell(1)
    private String identifier; 

    @JsonProperty("workbookId")
    @ExcelCell(0)
    private String rowId;

    @JsonProperty("value")
    @ExcelCell(2)
    private String value; 

    @JsonProperty("error")
    @ExcelCell(3)
    private String errorDescription;
}
