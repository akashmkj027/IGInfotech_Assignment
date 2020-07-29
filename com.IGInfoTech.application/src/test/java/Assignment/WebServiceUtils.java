package Assignment;

import java.util.HashMap;
import java.util.Map;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class WebServiceUtils {

	public static Map<String, String> mHeaders = new HashMap<String, String>();
	public static Map<Integer, Map<String, String>> mEmployeeData = new HashMap<Integer, Map<String, String>>();
	public static Map<String, String> mEachEmployeeData = new HashMap<String, String>();
	
	/**
	 * Method to fetch all headers of the response
	 * @param response is the response
	 * @return Map of Headers from response
	 * @throws Exception when response is invalid
	 */
	public static Map<String,String> GetAllHeaders(Response response) throws Exception
	{
		mHeaders.put("Date", response.getHeader("Date"));
		mHeaders.put("X-Cloud-Trace-Context", response.getHeader("X-Cloud-Trace-Context"));
		mHeaders.put("Server", response.getHeader("Server"));
		mHeaders.put("Content-Length", response.getHeader("Content-Length"));
		mHeaders.put("Content-Type", response.getHeader("Content-Type"));
		mHeaders.put("Connection", response.getHeader("Connection"));
		return mHeaders;
	}
	
	/**
	 * Method to get All Employee Data
	 * @param response is the JSON response
	 * @return Map of All Employee Data
	 * @throws Exception when response is not parsable
	 */
	public static Map<Integer, Map<String, String>> GetAllEmployeeData(Response response) throws Exception
	{
		JsonPath jsonPath = new JsonPath(response.asString());
		for(int i=0; i<jsonPath.getInt("employeeData.size()"); i++)
		{
			mEachEmployeeData.put("id", jsonPath.getString("employeeData[" + Integer.toString(i) + "].id"));
			mEachEmployeeData.put("age", jsonPath.getString("employeeData[" + Integer.toString(i) + "].age"));
			mEachEmployeeData.put("name", jsonPath.getString("employeeData[" + Integer.toString(i) + "].name"));
			mEachEmployeeData.put("dob", jsonPath.getString("employeeData[" + Integer.toString(i) + "].dob"));
			mEachEmployeeData.put("company", jsonPath.getString("employeeData[" + Integer.toString(i) + "].company"));
			mEachEmployeeData.put("role", jsonPath.getString("employeeData[" + Integer.toString(i) + "].role"));
			
			mEmployeeData.put(i, mEachEmployeeData);
		}
		return mEmployeeData;
	}
	
	/**
	 * Method to get JSONPath of response
	 * @param response is the JSON response
	 * @return JSONPath of the response
	 */
	public static JsonPath GetJsonPath(Response response)
	{
		return new JsonPath(response.asString());
	}
}
