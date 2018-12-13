package qubeCenimas.sharedBox_API.Models;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({	
	"fileId","isAccepted"
})
public class PutModel {

@JsonProperty("fileId")
private String fileId;
@JsonProperty("isAccepted")
private Boolean isAccepted;

@JsonProperty("fileId")
public String getFileId() {
return fileId;
}

@JsonProperty("fileId")
public void setFileId(String fileId) {
this.fileId = fileId;
}

@JsonProperty("isAccepted")
public Boolean getIsAccepted() {
return isAccepted;
}

@JsonProperty("isAccepted")
public void setIsAccepted(Boolean isAccepted) {
this.isAccepted = isAccepted;
}


}
