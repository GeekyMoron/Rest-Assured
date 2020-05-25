package com.rest;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import pojo.Gorestinput;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import com.files.Payload;
import com.files.ReusableMethods;
public class GoRest {
@Test
public void gorest()
{
	RestAssured.baseURI="https://gorest.co.in/";
	//add new user
	Gorestinput g=new Gorestinput();
	g.setEmail("nik-900@gamil.com");
	g.setFirst_name("nikhil");
	g.setGender("male");
	g.setLast_name("Hero");
	String accesstoken="GVeoKpo7DlKuFNTbwo2XxO_Q_hihmWRkjOn0";
	String response=given().body(g).header("Content-Type","application/json").header(new Header("Authorization", "Bearer " + accesstoken)).when().log().all().post("public-api/users").then()
	.log().all().extract().response().asString();
	System.out.println(response);
	JsonPath j=new JsonPath(response);
	String success=j.getString("_meta.success");
	Assert.assertEquals("true", success);
	String firstname=j.getString("result.first_name");
	String id=j.getString("result.id");
	//System.out.println(success+firstname+id);
	//update data by put
	g.setEmail("nik89@gamil.com");
	g.setFirst_name("nikhil Tiwari");
	g.setGender("male");
	g.setLast_name("Hero");
	String response2=given().body(g).header("Content-Type","application/json").header(new Header("Authorization", "Bearer " + accesstoken)).when().log().all()
	.put("public-api/users/"+id).then().log().all().assertThat().statusCode(200).extract().response().asString();	
	JsonPath j2=ReusableMethods.rawTojson(response2);
	Assert.assertEquals(j2.getString("result.first_name"),"nikhil Tiwari");
	//delete
	String response3=given().body(g).header("Content-Type","application/json").header(new Header("Authorization", "Bearer " + accesstoken)).when().log().all()
	.delete("public-api/users/"+id).then().log().all().assertThat().statusCode(200).extract().response().asString();
	JsonPath j3=ReusableMethods.rawTojson(response3);
	Assert.assertEquals(j3.getString("_meta.success"),"true");

}
}
