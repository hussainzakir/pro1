package com.repsrv.csweb.core.massuploads.service;

import com.repsrv.csweb.core.model.massupload.MuHistory;

public interface ErrorFileService {
    void generateErrorFile(String logId, String chgTicket, String system);

    byte[] getErrorFile(MuHistory record);
}
