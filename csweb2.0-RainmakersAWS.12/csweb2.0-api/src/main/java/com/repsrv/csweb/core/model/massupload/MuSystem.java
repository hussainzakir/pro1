package com.repsrv.csweb.core.model.massupload;

public enum MuSystem {

    ALLIED0D(false),
    ALLIED1R(true),
    SYS01(false),
    ALLIED(false),
    ALLIED01(true);

    private boolean cbs = false;

    MuSystem(boolean isCbs){ this.cbs = isCbs; }

    public boolean isCbs() { return this.cbs; }

    public static MuSystem getSystem(String sysName) {
        for(MuSystem sys : MuSystem.values()) {
            if(sys.name().equalsIgnoreCase(sysName))
                return sys;
        }
        return null;
    }
}
