package qubeCenimas.sharedBox_API.TestNg;


import org.testng.annotations.Test;
import static org.hamcrest.Matchers.is;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import qubeCenimas.sharedBox_API.Commons.Commons;
import qubeCenimas.sharedBox_API.Models.config;
import qubeCenimas.sharedBox_API.Utilities.JsonMapperClass;

public class UploadFileUsingFormData {
	
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
	
//@Test	
    public void uploadFileToServerUsingJsonBody() throws JsonParseException, JsonMappingException, IOException, InterruptedException {
    	JSONObject requestParams = new JSONObject();
		requestParams.put("name", "Test123"); // Cast
		requestParams.put("size", "156");
		requestParams.put("hash", "NO");
    	Response res=RestAssured.
    	    	given().header("Content-Type", "application/json").
    	    	queryParam("token",config_1.getTokenOfAccount1()).
    	    	body(requestParams.toJSONString()).post("/upload").andReturn();    	    	
    	    	if(res.getStatusCode()>=400){
    	    		System.out.println(res.asString());
    	    		Assert.assertEquals(res.getStatusCode(), 200); 	    		
    	    	}else if(res.getStatusCode()==200) {    	    		
    	    		System.out.println(new JsonMapperClass().getJsonPrettyFormat(res.asString()));    	    		
		    		System.out.println("Uploaded file Id : "+new JsonPath(res.asString()).get("fileId"));
		    		Thread.sleep(5000);
		    		Response r=new Commons().getFileWithFileId((String)new JsonPath(res.asString()).get("fileId").toString(),config_1.getTokenOfAccount1());
		    		if(r.getStatusCode()==200) {
		    			System.out.println("File Found in SharedBox:  /n"+new JsonMapperClass().getJsonPrettyFormat(r.asString()));
		    		}
    	    	}else {
    	    		Assert.assertEquals(res.getStatusCode(), 200);
    	    	}   

    }
    

@Test	
public void uploadFileToServerWithFormParameters() throws JsonParseException, JsonMappingException, IOException, InterruptedException {
	Response res=RestAssured.
	    	given().
	    	formParam("name", "Test123"). // Cast
	    	formParam("size", "156").
	    	formParam("hash", "NO").	    	
	    	queryParam("token",config_1.getTokenOfAccount1()).
	    	post("/upload").andReturn();    	    	
	    	if(res.getStatusCode()>=400){
	    		System.out.println(res.asString());
	    		Assert.assertEquals(res.getStatusCode(), 200);    	    		
	    	}else if(res.getStatusCode()==200) {    	    		
	    		System.out.println(new JsonMapperClass().getJsonPrettyFormat(res.asString()));    	    		
	    		System.out.println("Uploaded file Id : "+new JsonPath(res.asString()).get("fileId"));
	    		Thread.sleep(5000);
	    		Response r=new Commons().getFileWithFileId((String)new JsonPath(res.asString()).get("fileId").toString(),config_1.getTokenOfAccount1());
	    		if(r.getStatusCode()==200) {
	    			System.out.println("File Found in SharedBox:  /n"+new JsonMapperClass().getJsonPrettyFormat(r.asString()));
	    		}
	    	}else {
	    		Assert.assertEquals(res.getStatusCode(), 200);
	    	}  

}

   
}
