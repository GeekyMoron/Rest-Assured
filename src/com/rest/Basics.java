package com.rest;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import com.files.Payload;
import com.files.ReusableMethods;
public class Basics {
	public static void main(String args[]) {
//Validating Add Place API
//Given- All Input details given for an api 
	//When - Submit the API on resource with post method(it contains http methods and resource)
	//Then - Response
		RestAssured.baseURI="https://rahulshettyacademy.com";
		String res=given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
		.body(Payload.Addplace()).when().post("/maps/api/place/add/json").then().assertThat().statusCode(200)
		        .body("scope",equalTo("APP")).header("Content-Length","194").extract().response().asString();
		//Add place Api >Update Place>get place
		//extract method is used to extract responce
		//extract().response().asString() - extracting responce as string
		//System.out.println(res);
		//for extracting anything from json we need to parse json(for which we use class JsonPath
		JsonPath j=ReusableMethods.rawTojson(res);//convert 
		String placeid=j.getString("place_id");
		String newaddress="70 Summer walk, USA";
		//System.out.println(placeid);
//update place
		given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json").body(Payload.Putplace(placeid,newaddress)).when().put("/maps/api/place/update/json").then().log().all().assertThat().statusCode(200)
		.body("msg",equalTo("Address successfully updated")).header("Server","Apache/2.4.18 (Ubuntu)").extract().response()
		.asString();
		
//Get Place API
		given().log().all().queryParam("key","qaclick123").queryParam("place_id", placeid).when().get("/maps/api/place/get/json").then().log().all().assertThat().statusCode(200)
		.body("address",equalTo(newaddress)).header("Server","Apache/2.4.18 (Ubuntu)").extract().response();
		
		//install testNg Jar
		//when ever u see [] in json then it is array.
		
		
		
	
	
	}	
}
