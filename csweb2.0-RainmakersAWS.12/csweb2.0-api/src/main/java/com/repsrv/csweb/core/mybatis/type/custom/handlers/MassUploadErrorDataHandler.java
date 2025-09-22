package com.repsrv.csweb.core.mybatis.type.custom.handlers;

import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class MassUploadErrorDataHandler implements ResultHandler<Map<String, Object>> {
    private static final Logger log = LoggerFactory.getLogger(MassUploadErrorDataHandler.class);
    private List<List<String>> resultList;
    private static final SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd");

    public MassUploadErrorDataHandler() {
        resultList = new ArrayList<>();
    }

    public List<List<String>> getResultList() {
        return resultList;
    }

    @Override
    public void handleResult(ResultContext<? extends Map<String, Object>> resultContext) {
        log.debug("handling custom results from massupoad error data query");
        Map<String, Object> rowMap = resultContext.getResultObject();
        List<String> row = new ArrayList<>();

        for (Object value : rowMap.values()) {
            if(value == null){
                row.add("");
            } else if (value instanceof String) {
                row.add((String)value);
            } else if (value instanceof Integer) {
                Integer intValue = (Integer) value;
                row.add(String.valueOf(intValue));
            } else if (value instanceof Double) {
                Double doubleValue = (Double) value;
                row.add(String.valueOf(doubleValue));
            } else if (value instanceof BigDecimal) {
                BigDecimal bdValue = (BigDecimal) value;
                row.add(String.valueOf(bdValue));
            } else if (value instanceof java.sql.Date){
               row.add(sdf.format(value));
            } else {
                log.debug("Error datatype unidentified: {}  using object.toString", value.getClass());
                row.add(value.toString());
            }
        }
        resultList.add(row);
    }
}
