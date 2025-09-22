package com.repsrv.csweb.core.account.obligations.invoice.service;

import com.repsrv.csweb.core.model.account.AccountObligation;

public interface ObligationInvoiceDetailService {

	AccountObligation getAccountObligationInvoiceDetails(String company, String account, String obligationId);
}
