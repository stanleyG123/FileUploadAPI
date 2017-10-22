package com.api.demo.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import com.api.demo.model.FileInfo;
import com.api.demo.store.FileStorage;
import io.swagger.annotations.ApiParam;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.ApiOperation;

/**
 * Controller class for File Upload API
 * @author stan
 */
@RestController
public class FileUploadController {

	@Autowired
    private FileStorage storageSvc;
	
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
     * Retrieves a file by id
     * @return a particular file with a cooresponding id
     * @throws IOException
     */
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/getAllFileInfo/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "Get a single file", notes = "A valid ID must be specified")
    public @ResponseBody FileInfo getFileInfo(@ApiParam(value = "uploaded file id", required = true)
                                                  @PathVariable("id") Long id) throws IOException {
        return storageSvc.retrieveFileMetadata(id);
    }

	/**
	 * Accepts file upload submissions and metadata about the files
	 * @param incomingFile file to save
	 * @param fileInfo metadata about the file
	 * @return List of files already persisted
	 * @throws IOException
	 */
    @RequestMapping(value = "/uploadFile",method = RequestMethod.POST,consumes = "multipart/form-data")
    @ApiOperation(value="post a new file", response = List.class)
    public @ResponseBody List<FileInfo>  uploadFile (
    		@RequestPart("incomingFile") MultipartFile incomingFile,
            @RequestPart("fileInfo")  FileInfo fileInfo
    	) throws IOException {
    	storageSvc.store(incomingFile,fileInfo);
    	return storageSvc.retrieveAllFiles();
    }



}
