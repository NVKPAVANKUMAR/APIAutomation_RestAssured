package com.spotify.oauth2.tests;

import com.aventstack.extentreports.Status;
import com.github.javafaker.Faker;
import com.spotify.oauth2.util.listeners.AnnotationTransformer;
import com.spotify.oauth2.util.listeners.TestListener;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static com.spotify.oauth2.util.extentreport.ExtentTestManager.getTest;
import static com.spotify.oauth2.util.extentreport.ExtentTestManager.startTest;

@Listeners({TestListener.class, AnnotationTransformer.class})
public class PostRequest extends BaseTest {
    @Test
    void test_CreateBookingTest(Method method) throws JSONException {
        startTest(method.getName(), "Post Request for Booking Request");
        Faker faker = new Faker();
        //Create Json body
        JSONObject body = new JSONObject();
        body.put("firstname", faker.name().firstName() );
        body.put("lastname", faker.name().lastName());
        body.put("totalprice", faker.random().nextInt(100,10000));
        body.put("depositpaid", faker.random().nextBoolean());
        JSONObject bookingdates = new JSONObject();
        bookingdates.put("checkin", "2018-01-01");
        bookingdates.put("checkout", "2019-01-01");
        body.put("bookingdates", bookingdates);
        body.put("additionalneeds", faker.food().dish());

        //Get response
        response = RestAssured.given()
                .contentType(ContentType.JSON)
                .body(body.toString())
                .post("https://restful-booker.herokuapp.com/booking");

        //Verification
        Assert.assertEquals(response.statusCode(), 200);
        getTest().log(Status.INFO, response.body().prettyPrint());
    }
}
