package com.repsrv.csweb.core.model.massupload;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * ALLIED (PROD), ALLIED0d (ITG) and SYS01 (QA/UAT) is InfoPro
 * ALLIED01 (PROD), ALLIED1R (ITG/QUA) is CBS
 */
public enum SystemType {
    INFOPRO(new String[]{"ALLIED","ALLIED0D","SYS01"}),
    CBS(new String[]{"ALLIED01","ALLIED1R"});

    private Map<String, String> systemNames;
    SystemType(String [] systems){
        systemNames = Arrays.stream(systems)
                .collect(Collectors.toMap(sys -> sys, sys -> ""));

    }

    public static SystemType getSystemType(String sysName){
        if(CBS.systemNames.containsKey(sysName))
            return CBS;
        else if(INFOPRO.systemNames.containsKey(sysName))
            return INFOPRO;
        else
            return null;
    }
}
