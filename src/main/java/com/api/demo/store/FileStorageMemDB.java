package com.api.demo.store;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.api.demo.model.FileInfo;
import com.api.demo.store.FileStorage;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * Database implementation of FileStorage interface
 * @author stan
 */
@Transactional
@Service
public class FileStorageMemDB implements FileStorage {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void store(MultipartFile incomingFile, FileInfo info) throws IOException {

		File tempFile = File.createTempFile(incomingFile.getName(), ".tmp");
		Files.copy(incomingFile.getInputStream(),
				Paths.get(tempFile.getAbsolutePath()),
				StandardCopyOption.REPLACE_EXISTING);


        info.setFilePath(tempFile.getAbsolutePath());
        // should probbly come from JWT , ot some SAML token
        info.setUploadUser("Stan");

		entityManager.persist(info);
	}

	@Override
	public List<FileInfo> retrieveAllFiles() {
				
		return entityManager.createNamedQuery("query_find_all_records", FileInfo.class).getResultList();
	}


    @Override
    public FileInfo retrieveFileMetadata(Long id) {
          return entityManager.find(FileInfo.class,id);
    }


}
