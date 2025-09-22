package com.repsrv.csweb.core.model.account.imports;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Clob;
import java.sql.SQLException;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.repsrv.csweb.core.util.JsonUtil;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL) 
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImportMetaData {
	private static final ObjectMapper mapper = new ObjectMapper();
	
	private String excel;
	private String userid;
	private String table;
	@Setter
	private String library;
	@Setter
	private String message;
	@Setter
	private Clob json;
	
	
	public void setTable(String table) {
		this.table = table;
	}
	
	@JsonIgnore
	public String getJson() {
		return JsonUtil.fastJson(this);
	}
	
	@JsonIgnore
	public String getResultMessage() {
		
		try {
			String jsonStr = getClobString(this.json);
			if(jsonStr == null)
				return null;
			
			ImportMetaData data = mapper.readValue(jsonStr, ImportMetaData.class);
			return data.getMessage();
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@JsonIgnore
	public static String getClobString(Clob clob) throws SQLException,
    IOException {
			if(clob == null)
				return null;
		
			BufferedReader stringReader = new BufferedReader(
			        clob.getCharacterStream());
			String singleLine = null;
			StringBuilder strBuff = new StringBuilder();
			while ((singleLine = stringReader.readLine()) != null) {
			    strBuff.append(singleLine);
			}
			return strBuff.toString();
}
}
