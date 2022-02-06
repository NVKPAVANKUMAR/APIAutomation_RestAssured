package com.spotify.oauth2.tests;

import com.aventstack.extentreports.Status;
import com.spotify.oauth2.util.listeners.AnnotationTransformer;
import com.spotify.oauth2.util.listeners.TestListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.spotify.oauth2.util.extentreport.ExtentTestManager.getTest;
import static com.spotify.oauth2.util.extentreport.ExtentTestManager.startTest;
import static io.restassured.RestAssured.given;

@Listeners({TestListener.class, AnnotationTransformer.class})
public class DeleteRequest extends BaseTest {

    @Test
    protected void test_DeleteUser(Method method) {
        startTest(method.getName(), "Delete Request for User Deletion");
        response = given()
                .when()
                .delete("/api/users/2");
        Assert.assertEquals(response.statusCode(), 204);
        getTest().log(Status.INFO, response.body().prettyPrint());
    }
}
