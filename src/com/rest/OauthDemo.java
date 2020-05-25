package com.rest;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.CourseInfo;
import pojo.Courses;
import pojo.Responsevalue;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import com.files.Payload;
import com.files.ReusableMethods;
public class OauthDemo {
	@Test
	public void oauthdemo() throws InterruptedException
	{   //Get Authorization code by ui automation through selenium
		/*
		 * System.setProperty("webdriver.chrome.driver",
		 * "F:\\Selenium_workspace\\Rest-Assured\\src\\Drviers\\chromedriver.exe");
		 * WebDriver driver = new ChromeDriver(); driver.manage().deleteAllCookies();
		 * driver.get(
		 * "https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php"
		 * ); WebElement
		 * username=driver.findElement(By.xpath("//*[@id=\'identifierId\']"));
		 * WebElement password=driver.findElement(By.xpath(
		 * "//*[@id=\"password\"]/div[1]/div/div[1]/input")); WebElement
		 * next=driver.findElement(By.xpath("//*[@id=\"passwordNext\"]/span/span"));
		 * username.sendKeys("nikhilkanpur1998@gmail.com"); next.click();
		 * Thread.sleep(3000); password.sendKeys("nikhil760"); next.click();
		 * Thread.sleep(4000); String url=driver.getCurrentUrl();
		 */
		String url="https://rahulshettyacademy.com/getCourse.php?code=4%2FzAFGtsL6YlZ4NZNRP_wTObRCvErNa2wmnCZkJHODNxhJeNyMam9jrdhFge0hR8NetZzWp4WbrX448rpuY3TILWM&scope=email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&authuser=0&prompt=none";
		String partial_code=url.split("code=")[1];
		String auth_code=partial_code.split("&scope")[0];
		System.out.println(auth_code);
		//Rest assured performs encoding on special char by default so we have to told Rest assured explicitly for not doing encoding.
		//Get Access token
		String res=given().urlEncodingEnabled(false).queryParams("code",auth_code).queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W").queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
		.queryParam("grant_type", "authorization_code").when().log().all().post("https://www.googleapis.com/oauth2/v4/token").then().assertThat().statusCode(200).extract()
		.response().asString();
		JsonPath j=ReusableMethods.rawTojson(res);//convert 
		String acess_code=j.getString("access_token");
		System.out.println(acess_code);
		//Get response(integrating it with POJO)
		//expect() method is used to give the response format expected
		Responsevalue resp=given().queryParams("access_token", acess_code).expect().defaultParser(Parser.JSON)
				.when().get("https://rahulshettyacademy.com/getCourse.php").as(Responsevalue.class);
		//System.out.println(resp);
		String ur=resp.getUrl();
		String serv=resp.getServices();
		String expertise=resp.getExpertise();
		String lkedin=resp.getlinkedIn();
		String instruct=resp.getInstructor();
		Courses c=resp.getCourses();
List<CourseInfo> webaut=c.getWebAutomation();
List<CourseInfo> api=c.getApi();
List<CourseInfo> mod=c.getMobile();
System.out.println(ur);
System.out.println(serv);
System.out.println(expertise);
System.out.println(lkedin);
System.out.println(instruct);
System.out.println("WEB AUTOMATION");
for(int i=0;i<webaut.size();i++)
{
	System.out.println("coursetitle: "+webaut.get(i).getCourseTitle());
	System.out.println("price: "+webaut.get(i).getPrice());
}
System.out.println("API");
for(int i=0;i<api.size();i++)
{
	System.out.println("coursetitle: "+api.get(i).getCourseTitle());
	System.out.println("price: "+api.get(i).getPrice());
}
System.out.println("MOBILE");
for(int i=0;i<mod.size();i++)
{
	System.out.println("coursetitle: "+mod.get(i).getCourseTitle());
	System.out.println("price: "+mod.get(i).getPrice());
}
	}

}
