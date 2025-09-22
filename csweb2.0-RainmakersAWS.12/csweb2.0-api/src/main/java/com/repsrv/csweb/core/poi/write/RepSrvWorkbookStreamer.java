package com.repsrv.csweb.core.poi.write;

import java.io.IOException;
import java.io.OutputStream;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.StreamingOutput;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

@Service
public class RepSrvWorkbookStreamer {

	public StreamingOutput writeToStream(Workbook wkbk) {
		
		return new StreamingOutput() {
            @Override
            public void write(OutputStream output) throws IOException, WebApplicationException {
                try {
                	wkbk.write(output);
                	wkbk.close();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };
	}
}
