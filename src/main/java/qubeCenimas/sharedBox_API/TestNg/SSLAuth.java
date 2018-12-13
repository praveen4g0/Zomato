package qubeCenimas.sharedBox_API.TestNg;


import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.authentication.CertificateAuthSettings;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import qubeCenimas.sharedBox_API.Models.ErrorModel;
import qubeCenimas.sharedBox_API.Models.PostModel;
import qubeCenimas.sharedBox_API.Models.UploadModel;
import qubeCenimas.sharedBox_API.Models.config;
import qubeCenimas.sharedBox_API.Utilities.JsonMapperClass;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.util.JSONPObject;
import org.hamcrest.core.Is;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.util.List;

import static io.restassured.RestAssured.get;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;

public class SSLAuth  {
	
    
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

	
   /* @Test(expected = SSLException.class)
    public void throwsSSLExceptionWhenHostnameInCertDoesntMatch() throws Exception {
        RestAssured.get("/files");
    }*/

   

    //@Test(expected = SSLHandshakeException.class)
    public void whenEnablingAllowAllHostNamesVerifierWithoutActivatingAKeyStore() throws Exception {
        RestAssured.config = RestAssuredConfig.config().sslConfig(SSLConfig.sslConfig().allowAllHostnames());
        try {
           // RestAssured.get("/files").then().spec(helloWorldSpec());
        } finally {
            RestAssured.reset();
        }
    }

    

    /*@Test(expected = SSLException.class)
    public void usingStaticallyConfiguredCertificateAuthenticationWithIllegalHostNameInCertDoesntWork() throws Exception {
        RestAssured.authentication = RestAssured.certificate("truststore_mjvmobile.jks", "test4321");
        try {
            RestAssured.get("/files").then().body(containsString("eurosport"));
        } finally {
            RestAssured.reset();
        }
    }*/

    

  

    //@Test 
    public void
    relaxed_https_validation_works_using_instance_dsl() {
        RestAssured.given().relaxedHTTPSValidation().when().get("https://bunny.cloudamqp.com/api/").then().statusCode(200);
    }

    //@Test
    public void
    relaxed_https_validation_works_when_defined_statically() {
        RestAssured.useRelaxedHTTPSValidation();

        try {
            RestAssured.get("https://bunny.cloudamqp.com/api/").then().statusCode(200);
        } finally {
            RestAssured.reset();
        }
    }

    //@Test 
    public void
    relaxed_https_validation_works_when_defined_statically_with_base_uri() {
        RestAssured.useRelaxedHTTPSValidation();
        try {
            RestAssured.get("/api/").then().statusCode(200);
        } finally {
            RestAssured.reset();
        }
    }
    
	
    
    /*@Test
    public void contentTypeSpecification() throws Exception {
        final RequestSpecification requestSpecification = RestAssured.given().contentType(ContentType.TEXT).with().parameters("firstName", "John", "lastName", "Doe");
        final ResponseSpecification responseSpecification = RestAssured.expect().contentType(ContentType.JSON).and().body("greeting", equalTo("Greetings John Doe"));
        RestAssured.given(requestSpecification, responseSpecification).get("/greet");
    }*/
    
    @Test
    public void getListOfFiles() throws JsonParseException, JsonMappingException, IOException{
    	
    	Response res=RestAssured.
    	given().
    	queryParam("token",config_1.getTokenOfAccount1()).
    	accept(ContentType.JSON).get("/files").andReturn();    	     	
    	if(res.getStatusCode()>=400){
    		System.out.println(res.asString());
    	    //assertThat(res.getStatusCode(),is(200));
    		Assert.assertEquals(res.getStatusCode(), 200);
    	}else if(res.getStatusCode()==200) {
    		try {    			
	    		System.out.println(res.getStatusLine()+"\n"+" ListOfFiles in shared Box : \n"+new JsonMapperClass().getJsonPrettyFormat(res.asString()));	    		
	    		}catch(Exception e) {
	    			System.out.println(res.asString());
	    		}  
    	}else {
    		Assert.assertEquals(res.getStatusCode(),is(200));   
    	}
    	
    
    }
 
    
    
    
    
    

    
    
    
    
   
}
