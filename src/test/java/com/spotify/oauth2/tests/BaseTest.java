package com.spotify.oauth2.tests;

import com.spotify.oauth2.util.ConfigLoader;
import com.spotify.oauth2.util.XLSReader;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeSuite;

public class BaseTest {
    RequestSpecification request;
    Response response;

    @BeforeSuite
    public static void createSuite() {
        XLSReader suite = new XLSReader("src/test/resources/TestData.xlsx");
        suite.getTests("select * from TestCase where suite='Regression'");
        RestAssured.baseURI = ConfigLoader.getInstance().getBaseUrl();
    }
}
