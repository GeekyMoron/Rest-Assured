package com.rest;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;
import io.restassured.path.json.JsonPath;
import pojo.AddEmployee;
import pojo.EmployeeOutput;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import io.restassured.RestAssured;

public class Employees {
	@Test
public void addemployee()
{
	RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
	AddEmployee a=new AddEmployee();
	//Add Employee
	a.setName("Nikhil Tiwari");
	a.setSalary("800000");
	a.setAge("23");
	EmployeeOutput emp=given().header("Content-Type","application/json").body(a).when().log().all().post("/create").then().log().all().assertThat().statusCode(200).extract().response().as(EmployeeOutput.class);
    assertEquals(emp.getStatus(),"success");
    int empid=emp.getData().getId();
    System.out.println(empid);
	//Update Employee
    AddEmployee b=new AddEmployee();
    b.setName("Nikhil Tiwari");
	b.setAge("28");
	b.setSalary("900000");
	String Res=given().header("Content-Type","application/json").body(b).when().log().all().put("/update/"+empid).then().log().all().assertThat().statusCode(200).extract().response().asString();
//	assertEquals(emp2.getData().getAge(),"22");
//	assertEquals(emp2.getData().getSalary(),"900000");
	System.out.println(Res);
}
}
