package qubeCenimas.sharedBox_API.TestNg;

import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.annotations.*;
import qubeCenimas.sharedBox_API.Commons.Commons;
import qubeCenimas.sharedBox_API.Commons.Hooks;
import qubeCenimas.sharedBox_API.Models.config;
import qubeCenimas.sharedBox_API.Models.convertedPojo.qubeCenimas.sharedBox_API.Models.Category;
import qubeCenimas.sharedBox_API.Models.convertedPojo.qubeCenimas.sharedBox_API.Models.GetCategories;
import qubeCenimas.sharedBox_API.Utilities.ExtentReports.ExtentTestManager;
import qubeCenimas.sharedBox_API.Utilities.JsonMapperClass;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.lessThan;

@Listeners(qubeCenimas.sharedBox_API.Utilities.Listener.class)
public class Zomato_Api_TestNg extends Hooks {
    public GetCategories getCategories;
    public List<Category> categoryList;
    public Commons commons;

    @Test(groups = {"positive"})
    public void getCategories() {
        ExtentTestManager.getTest().setDescription("get All Categories of Zomato (Positive)\n");
        Header h1 = new Header("Accept", "application/json");
        Header h2 = new Header("user-key", config.getApiKey());
        List<Header> list = new ArrayList<Header>();
        list.add(h1);
        list.add(h2);
        Headers header = new Headers(list);
        Response res = RestAssured.
                given().
                headers(header).
                accept(ContentType.JSON).get("/categories").andReturn();
        if (res.getStatusCode() >= 400) {
            System.out.println(res.asString());
            ExtentTestManager.getTest().setDescription(res.asString() + "\n");
            Assert.assertEquals(res.getStatusCode(), 200);
        } else if (res.getStatusCode() == 200) {
            try {
                ExtentTestManager.getTest().setDescription("status Line : " + res.getStatusLine() + "\n");
                System.out.println("status Line : " + res.getStatusLine());
                ExtentTestManager.getTest().setDescription(res.getStatusLine() + "\n  " + "Categories List in Zomato " + new JsonMapperClass().getJsonPrettyFormat(res.asString()) + "\n");
                System.out.println(res.getStatusLine() + "\n" + "Categories List in Zomato " + new JsonMapperClass().getJsonPrettyFormat(res.asString()));
            } catch (Exception e) {
                System.out.println(res.asString());
                ExtentTestManager.getTest().setDescription(res.asString() + "\n");
            }
        } else {
            Assert.assertEquals(res.getStatusCode(), is(200));
        }
    }

    @Test(groups = {"negative"})
    public void getCategoriesWithoutUserKeyHeader() {
        ExtentTestManager.getTest().setDescription("get All Categories with Invalid or No ApiKey (Negative) \n");
        Header h1 = new Header("Accept", "application/json");
        List<Header> list = new ArrayList<Header>();
        list.add(h1);
        Headers header = new Headers(list);
        Response res = RestAssured.
                given().
                headers(header).
                accept(ContentType.JSON).get("/categories").andReturn();
        if (res.getStatusCode() >= 400) {
            System.out.println(res.asString());
            ExtentTestManager.getTest().setDescription(res.asString() + "\n");
            Assert.assertNotEquals(res.getStatusCode(), 200);
        } else if (res.getStatusCode() == 200) {
            try {
                System.out.println("status Line : " + res.getStatusLine());
                ExtentTestManager.getTest().setDescription("statue Line : " + res.getStatusLine() + "\n");
                System.out.println(res.getStatusLine() + "\n" + "Categories List in Zomato " + new JsonMapperClass().getJsonPrettyFormat(res.asString()));
                ExtentTestManager.getTest().setDescription(res.getStatusLine() + "\n" + "Categories List in Zomato " + new JsonMapperClass().getJsonPrettyFormat(res.asString()) + "\n");
            } catch (Exception e) {
                System.out.println(res.asString());
                ExtentTestManager.getTest().setDescription(res.asString() + "\n");
            }
        } else {
            Assert.assertEquals(res.getStatusCode(), is(200));
        }
    }

    @Test(groups = {"positive"}, dependsOnMethods = {"getCategories"})
    public void getCategoriesWithSchemaCheck() {
        ExtentTestManager.getTest().setDescription("get All Categories of  Zomato and Perform  Json Schema Validation (Positive) \n");
        Header h1 = new Header("Accept", "application/json");
        Header h2 = new Header("user-key", config.getApiKey());
        List<Header> list = new ArrayList<Header>();
        list.add(h1);
        list.add(h2);
        commons = new Commons();
        Headers header = new Headers(list);
        Response res = RestAssured.
                given().
                headers(header).
                accept(ContentType.JSON).get("/categories").andReturn();
        if (res.getStatusCode() >= 400) {
            System.out.println(res.asString());
            Assert.assertEquals(res.getStatusCode(), 200);
        } else if (res.getStatusCode() == 200) {
            try {
                System.out.println("status Line : " + res.getStatusLine());
                ExtentTestManager.getTest().setDescription("status Line : " + res.getStatusLine() + "\n");
                try {
                    getCategories = new JsonMapperClass().ConvertJsonToJava(res.asString(), GetCategories.class);
                } catch (Exception e) {
                    System.out.println("Schema Didn't Match Exactly caannot parse to Pojo Class " + e.getLocalizedMessage());
                    ExtentTestManager.getTest().setDescription("Schema Didn't Match Exactly caannot parse to Pojo Class " + e.getLocalizedMessage() + "\n");
                    Assert.fail("Schema Validation failed");
                }
                categoryList = getCategories.getCategories();
                ExtentTestManager.getTest().setDescription(res.asString());
                for (Category category : categoryList) {
                    System.out.println("--------------------------------------------------------------------");
                    System.out.println("Category Id : " + category.getCategories().getId() + " Category Name : " + category.getCategories().getName());
                    System.out.println("--------------------------------------------------------------------");
                    System.out.println();
                }
            } catch (Exception e) {
                System.out.println(res.asString());
                ExtentTestManager.getTest().setDescription(res.asString() + "\n");
            }
        } else {
            Assert.assertEquals(res.getStatusCode(), is(200));
        }
    }

    @Test(groups = {"positive", "performance"})
    public void PerformanceOfResponseTime() {
        ExtentTestManager.getTest().setDescription("get All Categories of  Zomato and Perform  Json Schema Validation (Positive) \n");
        Header h1 = new Header("Accept", "application/json");
        Header h2 = new Header("user-key", config.getApiKey());
        List<Header> list = new ArrayList<Header>();
        list.add(h1);
        list.add(h2);
        commons = new Commons();
        Headers header = new Headers(list);
        long time = RestAssured.
                given().
                headers(header).
                accept(ContentType.JSON).get("/categories").time();
        System.out.println("Response Time default(ms) :" + time);
        ExtentTestManager.getTest().setDescription("Response Time default(ms) :" + time);
    }

    @Test(groups = {"positive", "performance"})
    public void PerformanceOfResponseTimeInUnits() {
        ExtentTestManager.getTest().setDescription("get All Categories of  Zomato and Perform  Json Schema Validation (Positive) \n");
        Header h1 = new Header("Accept", "application/json");
        Header h2 = new Header("user-key", config.getApiKey());
        List<Header> list = new ArrayList<Header>();
        list.add(h1);
        list.add(h2);
        commons = new Commons();
        Headers header = new Headers(list);
        long time = RestAssured.
                given().
                headers(header).
                accept(ContentType.JSON).get("/categories").timeIn(TimeUnit.SECONDS);
        System.out.println("Response Time (Seconds) :" + time);
        ExtentTestManager.getTest().setDescription("Response Time (Seconds) :" + time);
    }

    @Test(groups = {"positive", "performance"})
    public void PerformanceOfResponseTimeAssert() {
        ExtentTestManager.getTest().setDescription("get All Categories of  Zomato and Perform  Json Schema Validation (Positive) \n");
        Header h1 = new Header("Accept", "application/json");
        Header h2 = new Header("user-key", config.getApiKey());
        List<Header> list = new ArrayList<Header>();
        list.add(h1);
        list.add(h2);
        commons = new Commons();
        Headers header = new Headers(list);
        RestAssured.
                given().
                headers(header).
                accept(ContentType.JSON).get("/categories").then().time(lessThan(500L));
    }


}
