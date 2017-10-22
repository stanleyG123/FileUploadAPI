package com.api.demo.controller;

import com.api.demo.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.FileInputStream;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * Controller test for file upload API
 * Created by stan on 10/21/17.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class FileUploadControllerTest {

    @InjectMocks
    FileUploadController controller;

    @Autowired
    WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void initTests() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testFileUpload() throws Exception {

        FileInputStream fs = new FileInputStream("src/test/resources/testDummyFile.txt");
        FileInputStream jsonInputFs = new FileInputStream("src/test/resources/sampleJsonPayload.txt");
        MockMultipartFile multipartFile = new MockMultipartFile("incomingFile", "testDummyFile.txt", "text/plain", fs);
        MockMultipartFile multipartJson = new MockMultipartFile("fileInfo", "sampleJsonPayload.txt", "application/json", jsonInputFs);

        mockMvc.perform(MockMvcRequestBuilders.fileUpload("/uploadFile")
                .file(multipartFile)
                .file(multipartJson))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].entryName", is("stanTest3")));

    }

    @Test
    public void testReadAll() throws Exception {

        FileInputStream fs = new FileInputStream("src/test/resources/testDummyFile.txt");
        FileInputStream jsonInputFs = new FileInputStream("src/test/resources/sampleJsonPayload.txt");
        MockMultipartFile multipartFile = new MockMultipartFile("incomingFile", "testDummyFile.txt", "text/plain", fs);
        MockMultipartFile multipartJson = new MockMultipartFile("fileInfo", "sampleJsonPayload.txt", "application/json", jsonInputFs);

        mockMvc.perform(MockMvcRequestBuilders.fileUpload("/uploadFile")
                .file(multipartFile)
                .file(multipartJson));

        mockMvc.perform(MockMvcRequestBuilders.fileUpload("/uploadFile")
                .file(multipartFile)
                .file(multipartJson));

        mockMvc.perform(MockMvcRequestBuilders.get("/getAllFileInfo"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].id", is(2)));

    }

    @Test
    public void testGetById() throws Exception {

        FileInputStream fs = new FileInputStream("src/test/resources/testDummyFile.txt");
        FileInputStream jsonInputFs = new FileInputStream("src/test/resources/sampleJsonPayload.txt");
        MockMultipartFile multipartFile = new MockMultipartFile("incomingFile", "testDummyFile.txt", "text/plain", fs);
        MockMultipartFile multipartJson = new MockMultipartFile("fileInfo", "sampleJsonPayload.txt", "application/json", jsonInputFs);

        mockMvc.perform(MockMvcRequestBuilders.fileUpload("/uploadFile")
                .file(multipartFile)
                .file(multipartJson));


        mockMvc.perform(MockMvcRequestBuilders.get("/getAllFileInfo/1"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", is(1)));

    }
}


