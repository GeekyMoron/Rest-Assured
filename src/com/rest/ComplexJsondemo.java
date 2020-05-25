package com.rest;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import com.files.Payload;
public class ComplexJsondemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JsonPath j=new JsonPath(Payload.coursePriceDummy());
		int no_of_courses = j.getInt("courses.size()");//size() for array size.
		System.out.println(no_of_courses);
		int purchaseAmount = j.getInt("dashboard.purchaseAmount");
		System.out.println(purchaseAmount);
		String ftitle = j.getString("courses[0].title");
		System.out.println(ftitle);
		//iterating over all elements of json array
		int count=j.getInt("courses.size()");
		for(int i=0;i<count;i++)
		{
			System.out.println(j.get("courses["+i+"].title")+" "+j.get("courses["+i+"].price"));
		}
		//conditional logic
		for(int i=0;i<count;i++)
		{
			String coursetitle = j.get("courses["+i+"].title");
			if(coursetitle.equalsIgnoreCase("RPA"))
			{
				System.out.println("no of copies sold for RPA"+" "+ j.get("courses["+i+"].copies"));
				break;
			}
		}   
	}

}
