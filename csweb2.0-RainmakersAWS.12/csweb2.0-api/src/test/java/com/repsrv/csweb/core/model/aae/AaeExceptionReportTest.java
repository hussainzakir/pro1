package com.repsrv.csweb.core.model.aae;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class AaeExceptionReportTest {

    private AaeExceptionReport aaeExceptionReport;

    @Before
    public void setUp() {
        aaeExceptionReport = new AaeExceptionReport("2024-06-01", "2024-06-30");
    }

    @Test
    public void testGetReportFromDate() {
        assertEquals("2024-06-01", aaeExceptionReport.getReportFromDate());
    }

    @Test
    public void testSetReportFromDate() {
        aaeExceptionReport.setReportFromDate("2025-01-01");
        assertEquals("2025-01-01", aaeExceptionReport.getReportFromDate());
    }

    @Test
    public void testGetReportToDate() {
        assertEquals("2024-06-30", aaeExceptionReport.getReportToDate());
    }

    @Test
    public void testSetReportToDate() {
        aaeExceptionReport.setReportToDate("2025-12-31");
        assertEquals("2025-12-31", aaeExceptionReport.getReportToDate());
    }
}