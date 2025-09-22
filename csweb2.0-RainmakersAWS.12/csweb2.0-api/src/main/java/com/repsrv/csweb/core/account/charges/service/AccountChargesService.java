package com.repsrv.csweb.core.account.charges.service;

import java.util.List;

import com.repsrv.csweb.core.model.account.AccountCharge;

public interface AccountChargesService {

	List<AccountCharge> getAccountCharges(String company, String account);
}
