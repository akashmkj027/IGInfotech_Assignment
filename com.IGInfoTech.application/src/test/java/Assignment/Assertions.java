package Assignment;

import io.restassured.response.Response;
import org.testng.Assert;
import java.util.HashMap;
import java.util.Map;
import io.restassured.path.json.JsonPath;

public class Assertions {
	public static Map<String, String> mEachEmployeeData = new HashMap<String, String>();
	
	/**
	 * Method to Assert response Status Code
	 * @param response is the response of the body
	 * @param iExpectedResponseCode is the expected Response code
	 * @throws Exception when Response code not identified
	 */
	public static void AssertRespStatusCode(Response response, int iExpectedResponseCode) throws Exception
	{
			Assert.assertTrue(response.getStatusCode()==iExpectedResponseCode);
			
	}
	
	
	/**
	 * Method to Assert Status From Response Body 
	 * @param response is the response of the body
	 * @param sExpectedValue is the expected Response value
	 * @throws Exception when Response is not parsable
	 */
	public static void AssertStatusFromResponseBody(Response response, String sExpectedValue) throws Exception
	{
		JsonPath jsonpath = WebServiceUtils.GetJsonPath(response);
		Assert.assertTrue(jsonpath.getString("status").equals(sExpectedValue));
	}
	
	/**
	 * Method to Assert Response Status Line From Response 
	 * @param response is the response of the body
	 * @param sExpectedResponseStatusLine is the expected Response Status Line
	 * @throws Exception when Response status Line is not found
	 */
	public static void AssertRespStatusLine(Response response, String sExpectedResponseStatusLine) throws Exception
	{
		Assert.assertTrue(response.getStatusLine().equals(sExpectedResponseStatusLine));
	}
	
	/**
	 * Method to assert response Headers
	 * @param mResponseHeaders Map of All Response Headers
	 * @param sHeader is header to assert
	 * @param sHeaderValue is the expected value of sHeader
	 */
	public static void AssertResponseHeader(Map<String, String> mResponseHeaders, String sHeader, String sHeaderValue)
	{
		if(mResponseHeaders.containsKey(sHeader))
		{
			Assert.assertTrue(mResponseHeaders.get(sHeader).equals(sHeaderValue));	
		}
		else
			Assert.fail("Header Not found in the response");
	}
	
	/**
	 * Method to Assert response Body
	 * @param mEmployeeData is Map of All EmployeeData
	 */
	public static void AssertResponseBody(Map<Integer, Map<String, String>> mEmployeeData)
	{
		mEachEmployeeData = mEmployeeData.get(0);
		Assert.assertTrue(mEachEmployeeData.get("id").equals("101209986"));
		Assert.assertTrue(mEachEmployeeData.get("age").equals("25"));
		Assert.assertTrue(mEachEmployeeData.get("name").equals("EmployeeName"));
		Assert.assertTrue(mEachEmployeeData.get("dob").equals("25-02-1994"));
		Assert.assertTrue(mEachEmployeeData.get("company").equals("IG Infotech"));
		Assert.assertTrue(mEachEmployeeData.get("role").equals("QA Automation Developer"));
			
	}
	
	/**
	 * Method to Assert Company Name from Responses Body 
	 * @param mEmployeeData is Map of All EmployeeData
	 */
	public static void AssertResponseBodyCompany(Map<Integer, Map<String, String>> mEmployeeData)
	{
		mEachEmployeeData = mEmployeeData.get(0);
		Assert.assertTrue(mEachEmployeeData.get("company").equals("ABC Infotech"), "Company Name didnt match, Expected: ABC Infotech, but Actual: " + mEachEmployeeData.get("company"));
	}
	

}
