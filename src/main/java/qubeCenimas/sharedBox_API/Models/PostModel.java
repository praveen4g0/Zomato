package qubeCenimas.sharedBox_API.Models;

import org.codehaus.jackson.annotate.JsonPropertyOrder;

@JsonPropertyOrder(value={"secretKey","accessKey","sessionToken","fileId"})
public class PostModel {
	
	public String fileId  ;
	public String accessKey ;
	public String secretKey ;
	public String sessionToken ;
	public PostModel(String fileId, String accessKey, String secretKey, String sessionToken) {
		super();
		this.fileId = fileId;
		this.accessKey = accessKey;
		this.secretKey = secretKey;
		this.sessionToken = sessionToken;
	}
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public String getAccessKey() {
		return accessKey;
	}
	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}
	public String getSecretKey() {
		return secretKey;
	}
	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}
	public String getSessionToken() {
		return sessionToken;
	}
	public void setSessionToken(String sessionToken) {
		this.sessionToken = sessionToken;
	}
	@Override
	public String toString() {
		return "PostModel [fileId=" + fileId + ", accessKey=" + accessKey + ", secretKey=" + secretKey
				+ ", sessionToken=" + sessionToken + "]";
	}

	
	

}
