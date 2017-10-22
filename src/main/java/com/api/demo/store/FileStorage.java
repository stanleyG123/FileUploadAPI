package com.api.demo.store;

import com.api.demo.model.FileInfo;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Interface for file storage API.
 *
 * @author stan
 */
@Service
public interface FileStorage {
    /**
     * Stores file in an underlying file store
     *
     * @param File     to store
     * @param FileInfo file submission metadata
     */
    public void store(MultipartFile mFile, FileInfo info) throws IOException;

    /**
     * Retrieves a list of all file
     *
     * @return array containing file metadata
     */
    public List<FileInfo> retrieveAllFiles();

    /**
     * Retrieve file metadata by id
     *
     * @return file metadata if found
     * @Param id of a file to retrieve
     */
    public FileInfo retrieveFileMetadata(Long id);
}
