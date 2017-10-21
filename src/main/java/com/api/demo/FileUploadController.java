package com.api.demo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Controller class for File Upload API
 * @author stan
 */
@RestController
public class FileUploadController {

	@Autowired
    private  FileStorage storageSvc;
	
	/**
	 * Retrieves all file entries. 
	 * @return a list of all file entries
	 * @throws IOException
	 */
	@RequestMapping(value = "/getAllFileInfo", method = RequestMethod.GET)
	@ApiOperation(value="view all available files", response = List.class)
    public @ResponseBody List<FileInfo> listAllUploadedFiles() throws IOException {
        
        return storageSvc.retrieveAllFiles();
    }
    
	/**
	 * Accepts file upload submissions and metadata about the files
	 * @param incomingFile file to save
	 * @param fileMetadata metadata about the file
	 * @return List of files already persisted
	 * @throws IOException
	 */
    @RequestMapping(value = "/uploadFile",method = RequestMethod.POST)
    @ApiOperation(value="post a new file", response = List.class)
    public @ResponseBody List<FileInfo>  uploadFile (
    		@RequestParam("incomingFile") MultipartFile incomingFile,
    		@RequestParam("fileInfo")String fileMetadata) throws IOException {
        
    	File tempFile = File.createTempFile(incomingFile.getName(), ".tmp");    	
    	Files.copy(incomingFile.getInputStream(), 
    			Paths.get(tempFile.getAbsolutePath()),
    			StandardCopyOption.REPLACE_EXISTING);
    	
    	//TODO : figure out automatic way to deal with JSON this
    	FileInfo fileInfo = new FileInfo();
    	try {
			JSONObject jsonObj = new JSONObject(fileMetadata);			
			fileInfo.setEntryName(jsonObj.getString("entryName"));
			fileInfo.setFileDescription(jsonObj.getString("fileDescription"));
			fileInfo.setUploadUser(jsonObj.getString("uploadUser"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}
    	
    	
    	//ObjectMapper mapper = new ObjectMapper();
    	//mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        //FileInfo fileInfo = mapper.convertValue(fileMetadata, FileInfo.class);
        
        fileInfo.setFilePath(tempFile.getAbsolutePath());
        fileInfo.setUploadUser("Stan");
    	
    	storageSvc.store(tempFile, fileInfo);
    	return storageSvc.retrieveAllFiles();
    	
    }

}
