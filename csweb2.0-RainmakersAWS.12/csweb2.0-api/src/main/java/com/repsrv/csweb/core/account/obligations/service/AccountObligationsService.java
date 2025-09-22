	package com.repsrv.csweb.core.account.obligations.service;

import java.util.List;

import com.repsrv.csweb.core.model.account.AccountObligation;
import com.repsrv.csweb.core.model.account.AccountReconciliation;

public interface AccountObligationsService {

	List<AccountObligation> getOpenObligations(String company, String account);

	List<AccountObligation> getClosedObligations(String company, String account);

	List<AccountReconciliation> getOpenReconciliations(String company, String account);

	List<AccountReconciliation> getAllReconciliations(String company, String account);

}
