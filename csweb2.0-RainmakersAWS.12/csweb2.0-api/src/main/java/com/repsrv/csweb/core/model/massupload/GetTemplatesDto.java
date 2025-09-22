package com.repsrv.csweb.core.model.massupload;

import java.util.List;

public class GetTemplatesDto {
    
    private List<MuTemplate> templates;
    private String changeReq;

    public List<MuTemplate> getTemplates() {
        return templates;
    }
    public void setTemplates(List<MuTemplate> templates) {
        this.templates = templates;
    }
    public String getChangeReq() {
        return changeReq;
    }
    public void setChangeReq(String changeReq) {
        this.changeReq = changeReq;
    }
}
