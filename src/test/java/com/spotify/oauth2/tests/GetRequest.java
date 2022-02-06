package com.spotify.oauth2.tests;

import com.aventstack.extentreports.Status;
import com.spotify.oauth2.util.listeners.AnnotationTransformer;
import com.spotify.oauth2.util.listeners.TestListener;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.spotify.oauth2.util.extentreport.ExtentTestManager.getTest;
import static com.spotify.oauth2.util.extentreport.ExtentTestManager.startTest;

@Listeners({TestListener.class, AnnotationTransformer.class})
public class GetRequest extends BaseTest {
    @Test
    void test_GetAllUsersRequest(Method method) {
        startTest(method.getName(), "Get All Users Request");
        response = RestAssured.get("/api/users");
        Assert.assertEquals(200, response.statusCode());
        getTest().log(Status.INFO, response.body().prettyPrint());
    }
}
