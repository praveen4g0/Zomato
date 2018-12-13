package qubeCenimas.sharedBox_API.Commons;

import io.restassured.RestAssured;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import qubeCenimas.sharedBox_API.Models.config;

import java.io.IOException;

public class Hooks {

    public config config;
    @BeforeTest(groups = {"positive","negative","per"})
    public void setup() throws IOException {
        config = new config();
        RestAssured.useRelaxedHTTPSValidation();
        RestAssured.baseURI = config.getAPIUrl();
        System.out.println("---Setup process------");
    }

    @AfterTest(groups = {"positive","negative"})
    public void TearDown() {
        RestAssured.reset();
        System.out.println("---TearDown process------");
    }

}
