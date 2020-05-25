package com.rest;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import pojo.GoogleAPIPojo;
import pojo.LocationPOJO;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class Serialisedtest {
@Test
//we are using serialisation here
public void serialtest()
{
	RestAssured.baseURI="https://rahulshettyacademy.com";
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
	String Response=given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json").body(gap).when().log().all()
	.post("/maps/api/place/add/json").then().assertThat().statusCode(200).extract().response().asString();
	System.out.println(Response);
	
}
}
