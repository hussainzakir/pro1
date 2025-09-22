package com.repsrv.csweb.core.model.aae;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.repsrv.csweb.core.model.listData.ListDataDisposalCodeValues;

public class ListDataDisposalCodeValuesTest {

    private ListDataDisposalCodeValues disposalCodeValues;

    @Before
    public void setUp() {
        disposalCodeValues = new ListDataDisposalCodeValues();
    }

    @Test
    public void testDisposalCode() {
        String disposalCode = "AR";
        disposalCodeValues.setDisposalCode(disposalCode);
        assertEquals(disposalCode, disposalCodeValues.getDisposalCode());
    }

    @Test
    public void testDisposalPriceCode() {
        String disposalPriceCode = "99";
        disposalCodeValues.setDisposalPriceCode(disposalPriceCode);
        assertEquals(disposalPriceCode, disposalCodeValues.getDisposalPriceCode());
    }

    @Test
    public void testDescription() {
        String description = "AR 01 AMERICAN RECYCLE-ATL";
        disposalCodeValues.setDescription(description);
        assertEquals(description, disposalCodeValues.getDescription());
    }
}
