package com.repsrv.csweb.core.mybatis.type.handlers;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



//@MappedJdbcTypes(value={JdbcType.DATE,JdbcType.TIMESTAMP, JdbcType.TIME}, includeNullJdbcType=false)
public class DateTypeHandler implements TypeHandler<String> {
	private static final Logger logger =  LoggerFactory.getLogger(DateTypeHandler.class);  
	DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy"); 
	
  @Override
  public void setParameter(PreparedStatement ps, int i, String parameter,
      JdbcType jdbcType) throws SQLException {
    ps.setString(i, trim(parameter));
  }

  @Override
  public String getResult(ResultSet rs, String columnName) throws SQLException {
	  String colVal = rs.getString(columnName);
	  if("0001-01-01".equals(colVal) || "9999-12-31".equals(colVal) || StringUtils.isBlank(colVal))
			  return null;

	  try {
		  Date date = rs.getDate(columnName);
		  String dateString = dateFormat.format(date);
		  if("01/01/0001".equals(dateString) || "12/31/9999".equals(dateString))
			  return null;
		  else 
			  return dateString;
	  }catch(Exception e) {
		  //maybe this is a string formatted date column
		  String dateFormatted = null;
		  if(colVal != null) {
			  String [] parts = colVal.split("-");
			  if(parts.length == 3) {
				  dateFormatted = parts[1] + "/" + parts[2] + "/" + parts[0];
				  if("01/01/0001".equals(dateFormatted) || "12/31/9999".equals(dateFormatted))
					  return null;
				  else 
					  return dateFormatted;
			  }
		  }
		  
		  return rs.getString(columnName);
	  }
	  
	  
//	  String [] parts = colVal.split("-");
//	  if(parts.length ==3)
//		  return parts[1] + "/" + parts[2] + "/" + parts[0];
//	  else
//		  return null;
  }

  @Override
  public String getResult(ResultSet rs, int columnIndex) throws SQLException {
	  logger.info("***** CONVERTING DATE! {}"+columnIndex);
    return trim(rs.getString(columnIndex));
  }

  @Override
  public String getResult(CallableStatement cs, int columnIndex)
      throws SQLException {
	  logger.info("***** CONVERTING DATE! {}"+columnIndex);
    return trim(cs.getString(columnIndex));
  }

  
private String trim(String s) {
    if (s == null) {
      return null;
    } else {
      return s.trim();
    }
  }
}
