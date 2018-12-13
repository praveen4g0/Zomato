package qubeCenimas.sharedBox_API.TestNg;

import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.AssertJUnit;
import static org.hamcrest.Matchers.is;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import qubeCenimas.sharedBox_API.Commons.Commons;
import qubeCenimas.sharedBox_API.Models.config;
import qubeCenimas.sharedBox_API.Utilities.JsonMapperClass;

public class ShareFileToOtherUser {
	
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
    public void shareFileToOtherUserTest() {
    	    	
    	Response r=new Commons().getFilesList(config_1.getTokenOfAccount1());    	
    	String fileId=r.statusCode()==200?getFileId(r.asString()):null;    	
    	if(fileId!=null) {
    	System.out.println("FileId :"+fileId+" Ready to be shared from : \n "+config_1.getAccount1()+" to: \n"+config_1.getAccount2());
    	JSONObject requestParams = new JSONObject();
		requestParams.put("fileId", fileId); // Cast
		requestParams.put("shareTo",config_1.getAccount2());
    	Response res=RestAssured.
    	    	given().header("Content-Type", "application/json").
    	    	queryParam("token",config_1.getTokenOfAccount1()).
    	    	body(requestParams.toJSONString()).post("/files").andReturn();       	
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
    	
    	}else {
    		System.out.println("No files to Share "+r.asString());
    		AssertJUnit.fail();
    	}
    }
    
    private String getFileId(String json) {		
        return  new JsonPath(json).get("[0].fileId");	
	}


}
