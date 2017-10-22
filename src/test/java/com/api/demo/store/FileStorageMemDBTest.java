package com.api.demo.store;

import com.api.demo.Application;
import com.api.demo.model.FileInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
//@SpringBootTest
public class FileStorageMemDBTest {
	
	@Autowired
    private TestEntityManager entityManager;
	
	@Autowired
	private FileStorage fileStorage;

	 @Test
	 public void testFindByLastName() {
		FileInfo fileInfo = new FileInfo ("fileOne","GoodFile.txt","Stan");
		
		entityManager.persist(fileInfo);
	//	fileStorage.retrieveAllFiles();
		 
	 }
}
