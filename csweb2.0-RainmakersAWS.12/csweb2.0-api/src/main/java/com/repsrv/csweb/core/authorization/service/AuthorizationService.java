package com.repsrv.csweb.core.authorization.service;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.repsrv.csweb.core.authorization.dao.AuthorizationDao;
import com.repsrv.csweb.core.authorization.model.AuthResponse;
import com.repsrv.csweb.core.authorization.model.DivisionsInfo;
import com.repsrv.csweb.core.authorization.model.SuperUserPermissions;
import com.repsrv.csweb.core.model.UserAuthorization;
import com.repsrv.csweb.core.model.authorization.PointerAccess;
import com.repsrv.csweb.core.model.sp.StoredProcCallResult;
import com.repsrv.csweb.core.service.JsonResultService;

@Service
public class AuthorizationService extends JsonResultService{
	protected final Logger logger = LoggerFactory.getLogger(AuthorizationService.class);
	private static final ObjectMapper mapper = new ObjectMapper(); 

	@Autowired
	private AuthorizationDao authorizationDao;

	public List<UserAuthorization> getUserAuthorizations(AuthResponse authResponse) {
        List<UserAuthorization> auths = new ArrayList<>();
        auths.add(new UserAuthorization("USER"));

        List<String> userPointers = authResponse.getPointers();

        for (PointerAccess pa : PointerAccess.values()) {
            if (userPointers.contains(pa.pointer)) {
                auths.add(new UserAuthorization(pa.label));
            }
        }

        return auths;
    }

	public List<DivisionsInfo> getUserDivisions(AuthResponse authResponse) {
		return authResponse.getDivisions();
	}
	
	public AuthResponse getUserAuthDetail(String system, String username) {
        StoredProcCallResult result = new StoredProcCallResult();
        String jsonString = this.authorizationDao.getUserAuthDetail(system, username, result);
        return getJsonDataObject(jsonString, AuthResponse.class);
    }

	public SuperUserPermissions getSuperPerms(AuthResponse authResponse) {
        return authResponse.getSuper_perms();
    }
		
}

