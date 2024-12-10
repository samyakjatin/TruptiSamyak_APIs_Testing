   package restassuredtut01;
 
import org.testng.annotations.Test;
import static io. restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

 
 public class Test02{
	
	 @Test
	public void test_02() {
		
            baseURI=("https://reqres.in/api/");
	

		given().
		 get("users?page=2").
		then().
		 statusCode(200).
		body("data[4].first_name", equalTo("George")).
		body("data.first_name" , hasItems("George"));
		
		
	}
	 @Test
	public void testPost() {
		 
		 Map<String,Object>map= new HashMap<String,Object>();
		 
		 map.put("name","Raghav");
		 map.put("job","Teacher");
		 
		 System.out.println(map);
		 
		
	}

}
