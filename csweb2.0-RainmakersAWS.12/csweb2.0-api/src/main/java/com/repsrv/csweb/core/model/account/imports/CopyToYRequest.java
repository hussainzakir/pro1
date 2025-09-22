package com.repsrv.csweb.core.model.account.imports;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CopyToYRequest {

    
    private String accts;
    private String fileName;
    private String aquiName;
    private String uploadType;
    
}
