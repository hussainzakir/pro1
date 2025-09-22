package com.repsrv.csweb.rest.health;

import com.repsrv.csweb.core.model.search.AccountSearchRequest;
import com.repsrv.csweb.core.search.dao.SearchDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.*;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
@Scope("request")
@Path("/health")
public class HealthCheckResource {
	 private static final Logger logger = LoggerFactory.getLogger(HealthCheckResource.class);

	@Autowired
	private Environment environment;

	@Autowired
	ConfigurableListableBeanFactory beanFactory;

	@Value("${health.check}")
	private String paramStoreCheck;

	@Value("${login.host}")
	private String loginHost;

	@Value("${secrets.health.check}")
	private String ssmCheck;

	@Autowired
	private SearchDao searchDao;

	@GET
    @Produces(MediaType.TEXT_PLAIN)
	public Response healthCheck() {
		logger.trace("Health Check Successful call");
		AccountSearchRequest req = new AccountSearchRequest();
		req.setAcctNum("1234");
		boolean searchWorking = false;
//		try {
//			searchDao.getSearchAccounts(req);
//		} catch(Exception e){
//			e.printStackTrace();
//			searchWorking = false;
//		}
        return Response.ok().entity(
				  "API:             success" +
				"\nParamStore:      " + paramStoreCheck +
				"\nSecrets Manager: " + ssmCheck +
				"\nLogin Host:      " + loginHost +
				"\nSearch :         " + (searchWorking?"success":"failed"))
				.build();
	}


}



