package com.rest;

import org.testng.annotations.Test;

import com.files.Payload;

import io.restassured.path.json.JsonPath;


public class SumValidation {
	@Test
	public void sumvalidation() {
	JsonPath j=new JsonPath(Payload.coursePriceDummy());
	int purchaseAmount = j.getInt("dashboard.purchaseAmount");
	int count=j.getInt("courses.size()");
	int sum = 0;
	for(int i=0;i<count;i++)
	{
		int copies=j.getInt("courses[" +i+ "].copies");
	    int price=j.getInt("courses[" +i+ "].price");
	    sum=sum+(copies*price);
	}
	if(purchaseAmount==sum)
	System.out.println("Equal");
	else
	System.out.println("Not Equal");
	}
}
