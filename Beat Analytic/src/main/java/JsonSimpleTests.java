
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonSimpleTests {
	/**
	 * How to create an JSON object and write it to a file
	 */
	public static void createJSONObject() {
		System.out.println("Write an JSON Object");
		/* Create simple object */
		JSONObject object = new JSONObject();
		object.put("id", new Integer(1001));
		object.put("name", "John Doe");
		
		System.out.println("Simple object: " + object);
		
		/* json array added manualy */
		JSONArray array = new JSONArray();
		array.add("product 1");
		array.add("product 2");
		
		/* json array added from an ArrayList object */
		ArrayList<String> list = new ArrayList<String>();
		list.add("list element 1");
		list.add("list element 2");
		list.add("list element 3");
		array.addAll(list);
		
		System.out.println("Array object: " + array);
		
		/* add the list to initial object */
		object.put("list", array);
		
		System.out.println("Complex object:" + object);
		
		/* write the complex object to a file */
		try {
			FileWriter file = new FileWriter("test1.json");
			file.write(object.toString());
			file.flush();
			file.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * How to read an json object from a file and display it on the console
	 * 
	 */
	public static void readJSONObject() {
		JSONParser parser = new JSONParser();
		try {
			/* Get the file content into the JSONObject */
			Object obj = parser.parse(new FileReader("test1.json"));
			JSONObject jsonObject = (JSONObject)obj;
			
			System.out.println("Object from file:" + jsonObject);
			
			/* Read simple value */
			Long id = (Long)jsonObject.get("id");
			System.out.println("id:"+id);

			/* Read a list */
			ArrayList<String> list = (ArrayList<String>)jsonObject.get("list");
			for (int i = 0; i < list.size(); i++) {
				System.out.println(i+ ": " + list.get(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		System.out.println("json-simple examples");
		System.out.println();
		
		createJSONObject();
		
		System.out.println();
		
		readJSONObject();
	}
	
}