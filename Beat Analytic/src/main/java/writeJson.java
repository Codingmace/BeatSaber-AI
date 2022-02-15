

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class writeJson {

	public static void main(String[] args) {
		/* Create simple object */
		JSONObject object = new JSONObject();
		object.put("id", new Integer(1001));
		object.put("name", "John Doe");
		
		/* json array added manually */
		JSONArray array = new JSONArray();
		array.add("product 1");
		array.add("product 2");
			
		/* json array added from an ArrayList object */
		ArrayList<String> list = new ArrayList<String>();
		list.add("list element 1");
		list.add("list element 2");
		list.add("list element 3");
		array.addAll(list);
		
		/* add the list to initial object */
		object.put("list", array);
		
		/* write the complex object to a file */
		try {
			FileWriter file = new FileWriter("./test.json");
			file.write(object.toString());
			file.flush();
			file.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
