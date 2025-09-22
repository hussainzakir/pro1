package com.repsrv.csweb.core.account.payments.service;

import java.util.List;

import com.repsrv.csweb.core.model.account.AccountPayment;

public interface AccountPaymentsService {

	List<AccountPayment> getAccountPayments(String company, String account);
}
