package com.repsrv.csweb.core.model.massupload;

public enum SystemTypeSchema {
    //INFOPRO
    ALLIED_P("CUFILE", SystemType.INFOPRO),
    ALLIED0D_D("CUFILE", SystemType.INFOPRO),
    SYS01_U("CUFILE", SystemType.INFOPRO),
    //CBS
    ALLIED01_P("CBDBFC", SystemType.CBS),
    ALLIED1R_U("CBDBFZ", SystemType.CBS),
    ALLIED1R_D("CBDBFY", SystemType.CBS);

    String schema;
    SystemType systemType;
    SystemTypeSchema(String schema, SystemType sysType){
        this.schema = schema;
        this.systemType = sysType;
    }

    public String getSchema(){
        return schema;
    }

    public static SystemTypeSchema findByName(String system){
        for(SystemTypeSchema typS : SystemTypeSchema.values()){
            if(typS.name().equals(system)){
                return typS;
            }
        }
        return null;
    }
}
