package com.repsrv.csweb.core.massuploads.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.reflect.Whitebox;

@RunWith(PowerMockRunner.class)
@PrepareForTest(MassUploadServiceImpl.class)

public class MassUploadServiceImplTest {
    @Test 
    public void testErrorPath() throws Exception{
        MassUploadServiceImpl service = new MassUploadServiceImpl();
        String path = Whitebox.invokeMethod(service, "errorPath");
        assertFalse(path.isEmpty());
    }

    @Test 
    public void testErrorPath_notFoundProperty() throws Exception{
        MassUploadServiceImpl service = new MassUploadServiceImpl();
        Properties props = PowerMockito.mock(Properties.class);
        PowerMockito.whenNew(Properties.class).withNoArguments().thenReturn(props);
        PowerMockito.doNothing().when(props).load(any(InputStream.class));
        PowerMockito.doReturn(null).when(props).getProperty(anyString());
        String path = Whitebox.invokeMethod(service, "errorPath");
        assertTrue(path.isEmpty());
    }

    @Test 
    public void testErrorPath_IOException() throws Exception{
        MassUploadServiceImpl service = new MassUploadServiceImpl();
        Properties props = PowerMockito.mock(Properties.class);
        PowerMockito.whenNew(Properties.class).withNoArguments().thenReturn(props);
        PowerMockito.doThrow(new IOException()).when(props).load(any(InputStream.class));
        String path = Whitebox.invokeMethod(service, "errorPath");
        assertTrue(path.isEmpty());
    }

    @Test (expected = IOException.class) 
    public void getErrorFile() throws IOException{
        MassUploadServiceImpl service = new MassUploadServiceImpl();
        service.getErrorFile("/file.xlsx");
    }

    @Test 
    public void getErrorFile_happyPath() throws IOException{
        PowerMockito.mockStatic(Paths.class);
        PowerMockito.mockStatic(Files.class);
        PowerMockito.when(Paths.get(anyString())).thenReturn(PowerMockito.mock(Path.class));
        PowerMockito.when(Files.readAllBytes(any(Path.class))).thenReturn(new byte[]{});
        MassUploadServiceImpl service = new MassUploadServiceImpl();
        byte [] output = service.getErrorFile("/NationalAccounts/dev/Uploads/Errors/file.xlsx");
        assertNotNull(output);
    }

}
