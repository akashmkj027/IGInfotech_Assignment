package Assignment;

import static io.restassured.RestAssured.given;
import java.util.HashMap;
import java.util.Map;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;


public class TestCase {

public static Response response = null;
public static Map<String, String> mAllHeaders = new HashMap<String, String>();
public static Map<Integer, Map<String, String>> mAllEmployeeData = new HashMap<Integer, Map<String, String>>();

	@Test(testName ="Assertion-1", description="Validate Response Status code as 200")
	public void Assertion1()
	{
		RestAssured.baseURI=AppConstants.BASE_URI;
		try {
			Response response =
			given().
					log().all().and().
					basePath(AppConstants.BASE_PATH).and().
					header(AppConstants.CONTENT_TYPE,AppConstants.REQ_CONTENT_TYPE).and().
			when().
					get().
			then().
					extract().response();	
			System.out.println(response.prettyPrint());
			Assertions.AssertRespStatusCode(response, 200);
			
		}
		catch(Exception ex)
		{
			System.out.println("Exception Caused due to: "+ ex.getMessage());
			ex.printStackTrace();
		}
	}
	
	@Test(testName ="Assertion-2", description="Validate Response Header for JSON response")
	public void Assertion2()
	{
		RestAssured.baseURI=AppConstants.BASE_URI;
		try {
			Response response =
			given().
					log().all().and().
					basePath(AppConstants.BASE_PATH).and().
					header(AppConstants.CONTENT_TYPE,AppConstants.REQ_CONTENT_TYPE).and().
			when().
					get().
			then().
					extract().response();	
			mAllHeaders = WebServiceUtils.GetAllHeaders(response);
			Assertions.AssertResponseHeader(mAllHeaders, "Content-Type", "application/json; charset=UTF-8");
			Assertions.AssertResponseHeader(mAllHeaders, "Server", "Google Frontend");
			Assertions.AssertResponseHeader(mAllHeaders, "Connection", "keep-alive");
			
		}
		catch(Exception ex)
		{
			System.out.println("Exception Caused due to: "+ ex.getMessage());
			ex.printStackTrace();
		}
	}
	
	@Test(testName ="Assertion-3", description="Validate Response body with specified data ")
	public void Assertion3()
	{
		RestAssured.baseURI=AppConstants.BASE_URI;
		try {
			Response response =
			given().
					log().all().and().
					basePath(AppConstants.BASE_PATH).and().
					header(AppConstants.CONTENT_TYPE,AppConstants.REQ_CONTENT_TYPE).and().
			when().
					get().
			then().
					extract().response();	
			mAllEmployeeData = WebServiceUtils.GetAllEmployeeData(response);
			Assertions.AssertResponseBody(mAllEmployeeData);
			
		}
		catch(Exception ex)
		{
			System.out.println("Exception Caused due to: "+ ex.getMessage());
			ex.printStackTrace();
		}
	}
	
	@Test(testName ="Assertion-4", description="Validate Company Name as ABC InfoTech in Response Body")
	public void Assertion4()
	{
		RestAssured.baseURI=AppConstants.BASE_URI;
		try {
			Response response =
			given().
					log().all().and().
					basePath(AppConstants.BASE_PATH).and().
					header(AppConstants.CONTENT_TYPE,AppConstants.REQ_CONTENT_TYPE).and().
			when().
					get().
			then().
					extract().response();	
			mAllEmployeeData = WebServiceUtils.GetAllEmployeeData(response);
			Assertions.AssertResponseBodyCompany(mAllEmployeeData);
		}
		catch(Exception ex)
		{
			System.out.println("Exception Caused due to: "+ ex.getMessage());
			ex.printStackTrace();
		}
	}
}
