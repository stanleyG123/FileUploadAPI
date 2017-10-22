package com.api.demo.store;

import com.api.demo.Application;
import com.api.demo.model.FileInfo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Integration test for file Upload API
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class FileStorageMemDBTest {

    @Autowired
    private FileStorage fileStorage;

    static MockMultipartFile dummyFile = null;
    static FileInfo fi = null;
    static String jsonFile = "{ \"entryName\":\"stanTest3\",\"fileDescription\":\" Long and Windy file Description\",\"uploadUser\":\"stan\"}";

    @BeforeClass
    public static void setUp() throws IOException {
        FileInputStream fs = new FileInputStream("src/test/resources/testDummyFile.txt");
        dummyFile = new MockMultipartFile("fileInfo", "sampleJsonPayload.txt", "application/json", fs);
        ObjectMapper mapper = new ObjectMapper();
        fi = mapper.readValue(jsonFile, FileInfo.class);
    }

    @Test
    public void testRetrieveAll() throws IOException {

        FileInfo nwFi = new FileInfo();
        nwFi.setEntryName("tst");
        nwFi.setFileDescription("someFile");
        nwFi.setUploadUser("ii");
        fileStorage.store(dummyFile, nwFi);
        List<FileInfo> files = fileStorage.retrieveAllFiles();
        assertThat(files).isNotEmpty();
    }


    @Test
    public void testStoreFile() throws IOException {

        fileStorage.store(dummyFile, fi);
        List<FileInfo> files = fileStorage.retrieveAllFiles();
        for (FileInfo fileInfo : files) {
            File file = new File(fileInfo.getFilePath());
            assertTrue(file.exists());
        }
    }

    @Test(expected = org.springframework.dao.InvalidDataAccessApiUsageException.class)
    public void testStoreFile_emptyMultipartFile() throws IOException {
        fileStorage.store(null, fi);
    }

    @Test
    public void testRetrieveFileMetadata() throws IOException {

        FileInfo fileInfo = fileStorage.retrieveFileMetadata(1l);
        org.junit.Assert.assertNotNull(fileInfo);
        assertTrue(new File(fileInfo.getFilePath()).exists());

    }
}
