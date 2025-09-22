package com.repsrv.csweb.rest.account;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.codehaus.jettison.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.repsrv.csweb.core.account.charges.service.AccountChargesService;
import com.repsrv.csweb.core.account.container.model.HaulerCostInformation.HaulerInfo;
import com.repsrv.csweb.core.account.container.model.HcmtRatesDto;
import com.repsrv.csweb.core.account.container.service.ContainerService;
import com.repsrv.csweb.core.account.obligations.invoice.service.ObligationInvoiceDetailService;
import com.repsrv.csweb.core.account.obligations.service.AccountObligationsService;
import com.repsrv.csweb.core.account.payments.service.AccountPaymentsService;
import com.repsrv.csweb.core.account.service.AccountService;
import com.repsrv.csweb.core.account.service.AccountServiceImpl;
import com.repsrv.csweb.core.model.account.AccountBalance;
import com.repsrv.csweb.core.model.account.AccountCharge;
import com.repsrv.csweb.core.model.account.AccountContactEdit;
import com.repsrv.csweb.core.model.account.AccountDetailEdit;
import com.repsrv.csweb.core.model.account.AccountObligation;
import com.repsrv.csweb.core.model.account.AccountPayment;
import com.repsrv.csweb.core.model.account.AccountReage;
import com.repsrv.csweb.core.model.account.AccountReconciliation;
import com.repsrv.csweb.core.model.account.AccountSiteContactEdit;
import com.repsrv.csweb.core.model.account.SiteDetailEdit;
import com.repsrv.csweb.core.model.container.ContainerGroupHauler;
import com.repsrv.csweb.core.model.container.ContainerGroupRate;
import com.repsrv.csweb.core.model.search.AccountDetail;
import com.repsrv.csweb.rest.BaseResource;

import flexjson.JSONSerializer;

@Component
@Scope("request")
@Path("/account")
public class AccountResource extends BaseResource {

	private static final String CLASS = "*.class";

	@Autowired
	private AccountService accountService;

	@Autowired
	private AccountPaymentsService accountPaymentService;

	@Autowired
	private AccountObligationsService accountObligationsService;

	@Autowired
	private AccountChargesService accountChargesService;

	@Autowired
	private ObligationInvoiceDetailService obligationInvoiceDetailService;
	
	@Autowired
	private ContainerService containerService;
	
	@Autowired
	private AccountServiceImpl accountServiceImpl;


	@GET
	@Path("/{company}/{account}/{site}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAccountDetail(@PathParam("company") String company, @PathParam("account") String account,
			@PathParam("site") String site) {

		AccountDetail detail = this.accountService.getAccountDetailAllSites(company, account, site);

		logger.debug("Sites for this detail? {}",
				this.serializerFactory.getSerializer("account-detail").serialize(detail));
		if (detail == null)
			return Response.status(Status.NOT_FOUND).entity("Account not found").build();

		return Response.ok().entity(this.serializerFactory.getSerializer("account-detail").serialize(detail)).build();
	}
	
	@GET
	@Path("/{company}/{account}/{site}/rates")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getContainerRates(@PathParam("company") String company, 
			@PathParam("account") String account,
			@PathParam("site") String site) throws JSONException {

		Map<String, Set<ContainerGroupRate>> containers = this.containerService.getContainerRates(company, account, site);

		String jsonString = new JSONSerializer().exclude(CLASS).deepSerialize(containers);
		return Response.ok().entity(jsonString).build();
	}

	@GET
	@Path("/{company}/{account}/{site}/haulers")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getContainerHaulers(@PathParam("company") String company, 
			@PathParam("account") String account,
			@PathParam("site") String site) {

		Map<String, Set<ContainerGroupHauler>> containers = this.containerService.getHaulerDetail(company, account, site);

		String jsonString = new JSONSerializer().exclude(CLASS).deepSerialize(containers);
		return Response.ok().entity(jsonString).build();
	}

	
	@GET
	@Path("/{company}/{account}/obligations/open")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getOpenObligations(@PathParam("company") String company, @PathParam("account") String account)
			throws JSONException {

		List<AccountObligation> obs = this.accountObligationsService.getOpenObligations(company, account);

		return Response.ok().entity(this.serializerFactory.getSerializer("account-obligations-get").serialize(obs))
				.build();
	}

	@GET
	@Path("/{company}/{account}/obligations/closed")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getClosedObligations(@PathParam("company") String company, @PathParam("account") String account)
			throws JSONException {

		List<AccountObligation> obs = this.accountObligationsService.getClosedObligations(company, account);

		return Response.ok().entity(this.serializerFactory.getSerializer("account-obligations-get").serialize(obs))
				.build();
	}

	@GET
	@Path("/{company}/{account}/balance")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAccountBalance(@PathParam("company") String company, @PathParam("account") String account)
			throws JSONException {

		AccountBalance balance = this.accountService.getAccountBalance(company, account);

		if (balance == null)
			return Response.status(Status.NOT_FOUND).entity("Account not found").build();

		return Response.ok().entity(this.serializerFactory.getSerializer("account-balance").serialize(balance)).build();
	}

	@GET
	@Path("/{company}/{account}/payments")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAccountPayments(@PathParam("company") String company, @PathParam("account") String account)
			throws JSONException {

		List<AccountPayment> payments = this.accountPaymentService.getAccountPayments(company, account);

		return Response.ok().entity(this.serializerFactory.getSerializer("account-payment").serialize(payments))
				.build();
	}

	@GET
	@Path("/{company}/{account}/reconciliations/open")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getOpenReconciliations(@PathParam("company") String company, @PathParam("account") String account)
			throws JSONException {

		List<AccountReconciliation> obs = this.accountObligationsService.getOpenReconciliations(company, account);

		return Response.ok().entity(this.serializerFactory.getSerializer("account-reconciliations-get").serialize(obs))
				.build();
	}

	@GET
	@Path("/{company}/{account}/reconciliations/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllReconciliations(@PathParam("company") String company, @PathParam("account") String account) {

		List<AccountReconciliation> obs = this.accountObligationsService.getAllReconciliations(company, account);

		return Response.ok().entity(this.serializerFactory.getSerializer("account-reconciliations-get").serialize(obs))
				.build();
	}

	@GET
	@Path("/{company}/{account}/charges")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAccountCharges(@PathParam("company") String company, @PathParam("account") String account)
			throws JSONException {

		List<AccountCharge> charges = this.accountChargesService.getAccountCharges(company, account);

		return Response.ok().entity(this.serializerFactory.getSerializer("account-charges-get").serialize(charges))
				.build();
	}

	// obligationInvoiceDetailsService
	@GET
	@Path("/{company}/{account}/obligation/{obligationId}/invoiceDetails")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getObligationInvoiceDetails(@PathParam("company") String company,
			@PathParam("account") String account, @PathParam("obligationId") String obligationId) throws JSONException {

		AccountObligation obligation = this.obligationInvoiceDetailService.getAccountObligationInvoiceDetails(company, account, obligationId);
		
		return Response.ok().entity(this.serializerFactory.getSerializer("obligation-invoice-details-get").serialize(obligation))
				.build();
	}
	
	@GET
	@Path("/{company}/{account}/reage")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAccountReage(@PathParam("company") String company, @PathParam("account") String account){

		AccountReage reage = this.accountService.getAccountReage(company, account);

		if (reage == null)
			return Response.status(Status.NOT_FOUND).entity("Account not found").build();

		return Response.ok().entity(this.serializerFactory.getSerializer("account-reage").serialize(reage)).build();
  }
	
	@POST
	@Path("/{company}/{account}/details")
	@Produces(MediaType.APPLICATION_JSON)
	public Response accountDetailsUpdate(@PathParam("company") String company, @PathParam("account") String account, 
			AccountDetailEdit detail) {
		
		detail.setAccount(account);
		detail.setCompany(company);
		
		this.accountServiceImpl.updateAccountDetail(detail);
		
		return Response.ok().build();
	}
	
	@POST
	@Path("/{company}/{account}/contacts")
	@Produces(MediaType.APPLICATION_JSON)
	public Response accountContactsUpdate(@PathParam("company") String company, @PathParam("account") String account, 
		 AccountContactEdit contact) {
		
		contact.setAccount(account);
		contact.setCompany(company);
		contact.setSite("");
		
		this.accountServiceImpl.updateAccountContacts(contact);
	
		return Response.ok().build();
		
	}
	
	@POST
	@Path("/{company}/{account}/{site}/sitecontacts")
	@Produces(MediaType.APPLICATION_JSON)
	public Response accountSiteContactsUpdate(@PathParam("company") String company, @PathParam("account") String account, 
			@PathParam("site") String site, AccountSiteContactEdit contact) {
		
		contact.setAccount(account);
		contact.setCompany(company);
		contact.setSite(site);
		
		this.accountServiceImpl.updateSiteContacts(contact);
	
		return Response.ok().build();
		
	}
	
	@POST
	@Path("/{company}/{account}/{site}/sites")
	@Produces(MediaType.APPLICATION_JSON)
	public Response accountSitesUpdate(@PathParam("company") String company, @PathParam("account") String account, 
			@PathParam("site") String site, SiteDetailEdit detailEdit) {
		
		detailEdit.setAccount(account);
		detailEdit.setCompany(company);
		detailEdit.setSite(site);
		
		this.accountServiceImpl.updateSiteDetail(detailEdit);
	
		return Response.ok().build();
		
	}
	
	@GET
	@Path("/{company}/{account}/{site}/hcmt/{vendorNumber}/{vendorSubtype}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getHcmtRates(@PathParam("company") String company, 
			@PathParam("account") String account,
			@PathParam("site") String site,
			@PathParam("vendorNumber") String vendorNumber,
			@PathParam("vendorSubtype") String vendorSubtype) {
				
		HaulerInfo haulerInfo = new HaulerInfo(company, account, site, "1", vendorNumber, vendorSubtype);
		List <HcmtRatesDto> hcmtRates = this.containerService.getHcmtRates(haulerInfo);

		String jsonString = new JSONSerializer().exclude(CLASS).deepSerialize(hcmtRates);
		return Response.ok().entity(jsonString).build();
	}
}
