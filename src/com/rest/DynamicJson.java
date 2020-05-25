package com.rest;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.files.Payload;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class DynamicJson {
@Test(dataProvider = "bookdata")
 public void AddLib(String isbn,String aisle)//in this method we pass values as parameter because each time one row is flused from data provider
 {  //Dynamic Json is that in which some value is like primary key means it is unique so we have to change that value each time dynamicaly in our code
	RestAssured.baseURI="http://216.10.245.166";
	//so in response we will set unique parameter for json
	String res=given().header("Content-Type","application/json").body(Payload.AddLib(isbn,aisle)).when().post("/Library/Addbook.php").then().assertThat()
	.statusCode(200).extract().response().asString();
	JsonPath j= new JsonPath(res);
	String msg=j.getString("Msg");
	String id=j.get("ID");
	System.out.println(id);
	System.out.println(msg);
	Assert.assertEquals(msg,"successfully added");
	//we can also do parameterization with data provider annotation of testNG 
 }
//TestNg basically contains annotations
@DataProvider(name ="bookdata")
public Object[][] dataParameterization()//we create multi-dimentional array or hashMap to pick data
{
	return new Object[][]{{"erd","244"},{"eid","244"},{"efd","244"},{"efo","247"}};//each row is one round of test execution if n rows are there then n times test case is to be executed
	
}
//in testng , each annotation  have its associated parameters like name, thread etc. and one fuction is associated with each annotation
}
