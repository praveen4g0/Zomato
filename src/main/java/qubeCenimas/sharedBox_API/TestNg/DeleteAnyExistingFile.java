package qubeCenimas.sharedBox_API.TestNg;


import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import static org.hamcrest.Matchers.is;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.testng.Assert;

import org.testng.annotations.*;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import qubeCenimas.sharedBox_API.Commons.Commons;
import qubeCenimas.sharedBox_API.Models.config;
import qubeCenimas.sharedBox_API.Utilities.JsonMapperClass;

public class DeleteAnyExistingFile {
	
	public config config_1;
	
	
    @BeforeClass
    public void setup() throws FileNotFoundException, IOException {
    	config_1=new config();
    	RestAssured.useRelaxedHTTPSValidation();
    	RestAssured.baseURI=config_1.getAPIUrl();
    	System.out.println("---Setup process------");
    }
    
    @AfterClass
    public void TearDown() {
    	RestAssured.reset();
    	System.out.println("---TearDown process------");
    }
        
    @Test
    public void DeleteAnyExistingFileTest() throws JsonParseException, JsonMappingException, IOException, InterruptedException {
    	Response r=new Commons().getFilesList(config_1.getTokenOfAccount1());
    	String fileId=r.statusCode()==200?getFileId(r.asString()):null;
    	if(fileId==null) {
    		System.out.println("No Files to Delete");
    		AssertJUnit.fail();
    	}else {
    		System.out.println("FileId :"+fileId+" Ready to be Deleted");
    		Response res=RestAssured.
        	    	given().
        	    	queryParam("token",config_1.getTokenOfAccount1()).
        	    	formParam("fileId", fileId).
        	    	delete("/files").andReturn();
    		
    		if(res.getStatusCode()>=400){
	    		System.out.println(res.asString());
	    		Assert.assertEquals(res.getStatusCode(), 200);   	    		
	    	}else if(res.getStatusCode()==200) {
	    		try {
	    		String Json=new JsonMapperClass().getJsonPrettyFormat(res.asString());
	    		System.out.println(Json);
	    		}catch(Exception e) {
	    			System.out.println(res.asString());
	    		}
	    	}else {
	    		Assert.assertEquals(res.getStatusCode(), 200); 
	    	}   
    	}
    	
    	
    	Thread.sleep(5000);
    	
      Response res=RestAssured.
    	    	given().
    	    	queryParam("fileId",fileId).
    	    	queryParam("token",config_1.getTokenOfAccount1()).
    	    	accept(ContentType.JSON).get("/upload").
    	    	andReturn();
    	System.out.println("After Deletion \n"+res.asString());
    	if(res.asString()==null) {
    		System.out.println("FileId : "+fileId+" Not found in Directory");
    	}    	
    }
	private String getFileId(String json) {		
        return  new JsonPath(json).get("[0].fileId");	
	}
}
