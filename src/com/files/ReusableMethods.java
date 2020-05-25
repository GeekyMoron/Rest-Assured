package com.files;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ReusableMethods {
	public static JsonPath rawTojson(String res)
	{
		JsonPath j=new JsonPath(res);
		return j;
	}

}
