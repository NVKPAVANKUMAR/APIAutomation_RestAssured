package com.spotify.oauth2.tests;

import com.aventstack.extentreports.Status;
import com.spotify.oauth2.util.listeners.AnnotationTransformer;
import com.spotify.oauth2.util.listeners.TestListener;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.core.Is;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.spotify.oauth2.util.extentreport.ExtentTestManager.getTest;
import static com.spotify.oauth2.util.extentreport.ExtentTestManager.startTest;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@Listeners({TestListener.class, AnnotationTransformer.class})
public class PatchRequestTest extends BaseTest{
    @Test
    public void test_PatchUserDetails(Method method) {
        startTest(method.getName(), "Patch Request for User Details Update");
        JSONObject req = new JSONObject();
        req.put("name", "morpheus");
        req.put("job", "leader");

        response = given().
                body(req.toJSONString()).
                contentType(ContentType.JSON).
                accept("application/JSON").
                when().
                patch("/api/users/2");
        Assert.assertEquals(response.statusCode(), 200);
        getTest().log(Status.INFO, response.body().prettyPrint());
    }
}
