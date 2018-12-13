package qubeCenimas.sharedBox_API.Models;

import org.codehaus.jackson.annotate.JsonPropertyOrder;

import com.fasterxml.jackson.annotation.JsonProperty;

@JsonPropertyOrder(value={"status","name","hash","createdOn","bytesCompleted","size","fileId"})
public class UploadModel {
	
	public String status;
	public String name;
	
	public String hash ;
	public String createdOn;
	public String bytesCompleted ;
	public String size;
	public String fileId;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	public String getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
	public String getBytesCompleted() {
		return bytesCompleted;
	}
	public void setBytesCompleted(String bytesCompleted) {
		this.bytesCompleted = bytesCompleted;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public UploadModel(String status, String name, String fileHash, String createdOn, String bytesCompleted,
			String size, String fileId) {
		super();
		this.status = status;
		this.name = name;
		this.hash = fileHash;
		this.createdOn = createdOn;
		this.bytesCompleted = bytesCompleted;
		this.size = size;
		this.fileId = fileId;
	}
	@Override
	public String toString() {
		return "UploadModel [status=" + status + ", name=" + name + ", fileHash=" + hash + ", createdOn="
				+ createdOn + ", bytesCompleted=" + bytesCompleted + ", size=" + size + ", fileId=" + fileId + "]";
	}
	
	


}
