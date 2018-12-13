package qubeCenimas.sharedBox_API.Models;

public class ErrorModel {
  private String error;

public String getError() {
	return error;
}

public void setError(String error) {
	this.error = error;
}

public ErrorModel(String error) {
	super();
	this.error = error;
}
  
}
