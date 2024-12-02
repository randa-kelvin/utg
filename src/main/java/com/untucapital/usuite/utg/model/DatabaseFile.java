package com.untucapital.usuite.utg.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "DataFiles")
public class DatabaseFile extends AbstractEntity{
	//@Id
	//@GeneratedValue(generator = "uuid")
	//@GenericGenerator(name = "uuid", strategy = "uuid2")
	//private String id;

	@Column(name = "file_name")
	private String fileName;

	@Column(name = "file_type")
	private String fileType;

	@Column(name = "file_description")
	private String fileDescription;

	@NotNull
	@JoinColumn(name = "user_id", nullable = false)
	private String userId;

	@JoinColumn(name = "loan_id")
	private String loanId;

	@Lob
	private byte[] data;

	public DatabaseFile() {

	}

	public DatabaseFile(String fileName, String fileType,String fileDescription,String userId, String loanId, byte[] data) {
		this.fileName = fileName;
		this.fileType = fileType;
		this.fileDescription = fileDescription;
		this.userId = userId;
		this.loanId = loanId;
		this.data = data;
	}

	//public String getId() {
		//return id;
	//}

	public String getFileName() {
		return fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public String getFileDescription() {
		return fileDescription;
	}
	public byte[] getData() {
		return data;
	}

	//public void setId(String id) {
		//this.id = id;
	//}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public void setFileDescription(String fileDescription) {
		this.fileDescription = fileDescription;
	}

	public String getLoanId() {
		return loanId;
	}

	public void setLoanId(String loanId) {
		this.loanId = loanId;
	}
}
