package com.repsrv.csweb.core.model.aae;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class QuoteNotes {

    private String noteLines;
    private String oldAccountStatus;
    private String newAccountStatus;
    private String createDate;
    private String createUser;
}