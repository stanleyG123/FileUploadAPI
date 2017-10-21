package com.api.demo;

import java.io.File;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Database implementation of FileStorage interface
 * @author stan
 */
@Service
@Repository
@Transactional
public class FileStorageMemDB implements FileStorage{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void store(File file,FileInfo info) {
		entityManager.persist(info);
	}

	@Override
	public List<FileInfo> retrieveAllFiles() {
				
		return entityManager.createNamedQuery("query_find_all_records", FileInfo.class).getResultList();
	}

}
