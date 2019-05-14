package com.Remigiusz.BookLiblary;

import static  io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;


public class RestAssuredApiTest {

    @Test
    public void testBooksRequestByIsbn() {
        get(Endpoints.CONTEXT_PATH+"/book/9788324677610").then().statusCode(200).log().body();

    }

    @Test
    public void testBooksRequestByCategory() {
        get(Endpoints.CONTEXT_PATH+"/books/Computers").then().statusCode(200).log().body();

    }



}
