package com.repsrv.csweb.core.poi;

import java.io.IOException;
import java.io.OutputStream;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.StreamingOutput;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

@Slf4j
public abstract class PoiExcelBuilder {

	protected abstract XSSFWorkbook getWorkbook();
	
	protected abstract OutputStream getOutputStream();
	
	public abstract XSSFWorkbook buildTemplate();
	
	protected String getMD5(byte[] contents){
		return DigestUtils.md5Hex(contents);
	}
	
	public StreamingOutput finalizeToNewStream() throws Exception{
		XSSFWorkbook wkbk = getWorkbook();

		return new StreamingOutput() {
				@Override
				public void write(OutputStream output) throws IOException, WebApplicationException {
					writeAndClose(wkbk, output);
				}
			};
	}


	public void finalizeToStream() throws Exception{
		writeAndClose(getWorkbook(), getOutputStream());
		log.debug("Finalized the excel workbook!");

	}

	private void writeAndClose(XSSFWorkbook wkbk, OutputStream outStream){
		try {
			wkbk.write(outStream);
			wkbk.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
}
