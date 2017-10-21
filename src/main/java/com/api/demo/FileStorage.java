package com.api.demo;

import java.util.List;

/**
 * Interface for file storage API. 
 * @author stan
 *
 */
public interface FileStorage {
	/**
	 * Stores file in an underlying file store
	 * @param File to store
	 * @param FileInfo file submission metadata
	 */	
	public void store (java.io.File file,FileInfo info);
	
	/**
	 * Retrieves a list of all file
	 * @return array containing file metadata
	 */
	public List<FileInfo> retrieveAllFiles ();
}
