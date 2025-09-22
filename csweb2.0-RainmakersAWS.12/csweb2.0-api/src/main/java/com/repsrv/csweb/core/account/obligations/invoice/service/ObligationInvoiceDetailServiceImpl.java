package com.repsrv.csweb.core.account.obligations.invoice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.repsrv.csweb.core.account.obligations.invoice.dao.ObligationInvoiceDetailDao;
import com.repsrv.csweb.core.model.account.AccountInvoiceDetail;
import com.repsrv.csweb.core.model.account.AccountObligation;
import com.repsrv.csweb.core.model.sp.StoredProcCallResult;
import com.repsrv.csweb.core.service.JsonResultService;

@Service("obligationInvoiceDetailsService")
public class ObligationInvoiceDetailServiceImpl extends JsonResultService implements ObligationInvoiceDetailService {

	@Autowired
	private ObligationInvoiceDetailDao obligationInvoiceDetailDao;

	@Override
	public AccountObligation getAccountObligationInvoiceDetails(String company, String account, String obligationId) {

		StoredProcCallResult result = new StoredProcCallResult();
		String jsonString = this.obligationInvoiceDetailDao.getObligationInvoiceDetails(company, account, obligationId,
				result);

		List<AccountInvoiceDetail> details = getJsonDataObject(jsonString,
				new TypeReference<List<AccountInvoiceDetail>>() {
				});
		AccountObligation obligation = null;
		if (details != null && !details.isEmpty()) {
			obligation = new AccountObligation();

			AccountInvoiceDetail detail = details.get(0);// TODO: change once the SP has more efficient JSON structure
			obligation.setDate(detail.getObligationDate());
			obligation.setCompany(detail.getCompany());
			obligation.setAccount(detail.getAccount());
			obligation.setId(obligationId);
			obligation.setPoNumber(detail.getObligationReference());
			obligation.setOpenAmount(detail.getObligationOpen());
			obligation.setTotalAmount(detail.getObligationTotal());
			obligation.setInvoiceDetails(details);
		}

		return obligation;
	}

}
