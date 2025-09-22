package com.repsrv.csweb.core.obligations.service;

import com.repsrv.csweb.core.model.obligationsSearch.ObligationRegion;
import com.repsrv.csweb.core.model.obligationsSearch.Obligation;

import java.util.List;

public interface ObligationsSearchService {
    List<Obligation> getObligationsSearchResults(
            String regionCode,
            String obligationId,
            String accountingPeriodFrom,
            String accountingPeriodTo,
            String amountRangeFrom,
            String amountRangeTo);

    List<ObligationRegion> getObligationRegionsList();

}
