package qubeCenimas.sharedBox_API.Commons;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import qubeCenimas.sharedBox_API.Utilities.JsonMapperClass;

public class Commons {
	
	public Response getListOfFilesWithFileId(String FileId,String TokenId) throws JsonParseException, JsonMappingException, IOException{    	
    	Response res=RestAssured.
    	given().
    	queryParam("getSharedFiles", FileId).
    	queryParam("token",TokenId).
    	accept(ContentType.JSON).get("/files").andReturn();    	     	
        return res;	
    }

    public Boolean ChechSchemaForGivenResponse(Response res,String Path){
		try {
			assertThat(res.asString(), matchesJsonSchemaInClasspath(Path));
		}catch (Exception e){
			return false;
		}
		return true;
	}
	
	  public Response getFileWithFileId(String FileId,String TokenId) throws JsonParseException, JsonMappingException, IOException{
	    	Response res=RestAssured.
	    	given().
	    	queryParam("fileId", FileId).
	    	queryParam("token",TokenId).
	    	accept(ContentType.JSON).get("/upload").andReturn();    	 
	    	return res;		
	    }
	  
	  /*Generic Functions*/   
	   public Response getFilesList(String TokeId) {    	
	    	Response res= RestAssured.
	    	given().
	    	queryParam("token",TokeId).
	    	accept(ContentType.JSON).get("/files").andReturn();  
	    	return res;
	    }

}
