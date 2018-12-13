package qubeCenimas.sharedBox_API.TestNg;


import org.testng.annotations.Test;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import qubeCenimas.sharedBox_API.Commons.Commons;
import qubeCenimas.sharedBox_API.Models.PutModel;
import qubeCenimas.sharedBox_API.Models.UploadModel;
import qubeCenimas.sharedBox_API.Models.config;
import qubeCenimas.sharedBox_API.Utilities.JsonMapperClass;

public class RejectOrAcceptSharedFile {
	
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
    
    private String getFileId(String json) {		
        return  new JsonPath(json).get("[0].fileId");	
	}
    
    
    @Test
    public void rejectOrAccept() throws ParseException {    	
    	Response r=new Commons().getFilesList(config_1.getTokenOfAccount2());    	
    	List<String> fileId=r.statusCode()==200?parse(r.asString()):null;
    	System.out.println("List of FileIds in shared box "+fileId);
    	PutModel um=new PutModel();
    	um.setFileId(fileId.get(1).toString());
    	um.setIsAccepted(false);   
        JSONParser parser = new JSONParser();
        JSONObject jsonObj = (JSONObject) parser.parse(new JsonMapperClass().convertJavaToJson(um));    	
		System.out.println(jsonObj);
    	if(fileId.size()!=0) { 
    		System.out.println(fileId.get(1).toString());
    		Response res=RestAssured.
        	    	given().header("Content-Type", "application/json").
        	    	queryParam("token",config_1.getTokenOfAccount1()).    	    	
        	    	body(jsonObj.toJSONString()).
        	    	put("/files").andReturn();  
    		if(res.getStatusCode()>=400){
        		System.out.println(res.asString()+" \n" +res.statusLine()+" \n"+res.prettyPrint());
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
    		System.out.println("No Files to share");
    	}
    	
     }
    
public  List<String> parse(String Json) throws ParseException {
    	List<String> fileIdlst=new ArrayList<String>();
    	JSONParser parser = new JSONParser();
    	org.json.simple.JSONArray jsonArray= (org.json.simple.JSONArray) parser.parse(Json);
    	if(jsonArray.size()!=0) {
        for(int i=0;i<jsonArray.size();i++) {
        	JSONObject obj = (JSONObject)jsonArray.get(i); // Exception happens here.
            fileIdlst.add(obj.get("fileId").toString());
        }
    	}else {
    		System.out.println("No Files/Objects Returned");
    	}
    	return fileIdlst;    	
    }
}
