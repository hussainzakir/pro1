package com.repsrv.csweb.core.aae.model;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;

import org.junit.Before;
import org.junit.Test;

import com.repsrv.csweb.core.model.aae.AaeExceptionReport;
import com.repsrv.csweb.core.model.json.CSWebAction;

public class AaeExceptionReportRequestTest {

    private AaeExceptionReportRequest request;
    private AaeExceptionReport params;

    @Before
    public void setUp() throws Exception {
        params = new AaeExceptionReport("2024-06-01", "2024-06-30");
        request = new AaeExceptionReportRequest(CSWebAction.GET_ALL, "SMITHBO", params);
    }
    
    @Test
    public void testConstructor() throws Exception {
        assertEquals("SMITHBO", request.getRequestedUser());
        assertEquals(CSWebAction.GET_ALL, request.getAction());

        Field paramsField = AaeExceptionReportRequest.class.getDeclaredField("exceptionReport");
        paramsField.setAccessible(true);
        AaeExceptionReport paramsValue = (AaeExceptionReport) paramsField.get(request);

        assertEquals(params, paramsValue);
    }

    @Test
    public void testGetRequestedObject() {
        assertEquals(params, request.getRequestedObject());
    }

    @Test
    public void testDropDownParamsField() throws Exception {
        Field paramsField = AaeExceptionReportRequest.class.getDeclaredField("exceptionReport");
        paramsField.setAccessible(true);
        AaeExceptionReport paramsValue = (AaeExceptionReport) paramsField.get(request);

        assertEquals(params, paramsValue);
    }
}
