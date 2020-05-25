package com.rest;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.GoogleAPIPojo;
import pojo.LocationPOJO;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class SpecBuilderTest {
@Test
//we are using serialisation here
public void serialtest()
{
	//RestAssured.baseURI="https://rahulshettyacademy.com";
	LocationPOJO loc=new LocationPOJO();
	GoogleAPIPojo gap=new GoogleAPIPojo();
	ArrayList<String> types =new ArrayList<String>();
	loc.setLat(-38.383494);
	loc.setLng(45);
	gap.setAccuracy(50);
	gap.setAddress("29, side layout, cohen 09");
	gap.setLanguage("Hindi/English");
	gap.setLocation(loc);
	gap.setName("Nikhil Tiwari");
	gap.setPhone_number("(+91) 983 893 3937");
	gap.setWebsite("https://rahulshettyacademy.com");
	types.add("shoe park");
	types.add("shop");
	types.add("don");
	gap.setTypes(types);
	//requestSpecbuilder returns request specification object
	RequestSpecification res=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key","qaclick123").setContentType(ContentType.JSON).build();
	ResponseSpecification response=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
	RequestSpecification req=given().spec(res).body(gap);
	String resp=req.when().log().all()
	.post("/maps/api/place/add/json").then().spec(response).extract().response().asString();
	System.out.println(resp);
	
}
}
