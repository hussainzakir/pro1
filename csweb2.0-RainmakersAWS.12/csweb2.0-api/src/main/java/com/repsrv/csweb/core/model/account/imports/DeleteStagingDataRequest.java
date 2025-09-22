package com.repsrv.csweb.core.model.account.imports;

import java.util.List;

import com.repsrv.csweb.core.model.sp.StoredProcCallResult;
import com.repsrv.csweb.core.util.JsonUtil;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class DeleteStagingDataRequest extends StoredProcCallResult{

	private ImportMetaData metadata;
	
	public String getJson() {
		return JsonUtil.fastJson(metadata);
	}
	
}
