package com.repsrv.csweb.core.obligations.service;

import java.util.List;

import com.repsrv.csweb.core.model.json.CSWebAction;
import com.repsrv.csweb.core.model.obligationsSearch.ObligationRegion;
import com.repsrv.csweb.core.model.obligationsSearch.Obligation;
import com.repsrv.csweb.core.obligations.json.ObligationsSearchRequest;
import com.repsrv.csweb.core.obligations.dao.ObligationsSearchDao;
import com.repsrv.csweb.core.support.exception.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("obligationsSearchService")
public class ObligationsSearchServiceImpl implements ObligationsSearchService {
    private static final Logger logger = LoggerFactory.getLogger(ObligationsSearchServiceImpl.class);

    @Autowired
    ObligationsSearchDao obligationsSearchDao;


    @Override
    public List<Obligation> getObligationsSearchResults(
            String regionCode,
            String obligationId,
            String accountingPeriodFrom,
            String accountingPeriodTo,
            String amountRangeFrom,
            String amountRangeTo) {

        ObligationsSearchRequest req = new ObligationsSearchRequest(SecurityUtils.getLoggedInUser(), CSWebAction.GET_ALL,
                regionCode, obligationId, accountingPeriodFrom, accountingPeriodTo, amountRangeFrom, amountRangeTo);

        logger.debug("request json: " + req.getJson());

        List<Obligation> results = this.obligationsSearchDao.getObligations(req);

        logger.debug("Results size {}", results == null ? "0" : String.valueOf(results.size()));
        logger.debug("Response Obligation-Search is: " + req.getReturnErrorMsg());
        req.validateResponse();
        return results;
    }

    @Override
    public List<ObligationRegion> getObligationRegionsList() {
        return this.obligationsSearchDao.getObligationRegions();
    }
}
