package com.api.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

@Entity
@Table(name = "FILE_INFO")
@NamedQueries({
	@NamedQuery(name="query_find_all_records", query="SELECT e FROM FileInfo e")          
})
public class FileInfo {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

	@Size(min = 1, max = 20)
	private String entryName;
	@Size(min = 1, max = 100)
	private String fileDescription;
	@Size(min = 1, max = 300)
	private String filePath;
	@Size(min = 1, max = 200)
	private String uploadUser;
	
    public FileInfo() {
    }

    @JsonCreator
    public FileInfo(
    		@JsonProperty("entryName") String entryName,
    		@JsonProperty("fileDescription")  String fileDescription,
    		@JsonProperty("uploadUser") String uploadUser) {
    	this.entryName = entryName;
    	this.fileDescription = fileDescription;
    	this.uploadUser = uploadUser;
    }
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEntryName() {
		return entryName;
	}
	public void setEntryName(String entryName) {
		this.entryName = entryName;
	}
	public String getFileDescription() {
		return fileDescription;
	}
	public void setFileDescription(String fileDescription) {
		this.fileDescription = fileDescription;
	}

	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public String getUploadUser() {
		return uploadUser;
	}
	public void setUploadUser(String uploadUser) {
		this.uploadUser = uploadUser;
	}
	
	
}
