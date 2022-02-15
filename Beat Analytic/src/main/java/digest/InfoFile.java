package digest;

import java.io.File;
import java.util.Scanner;

import com.google.gson.Gson;

public class InfoFile {

	public static void main(String[] args) {
		try {
			Gson g = new Gson();
			Scanner scan = new Scanner(new File("test.json"));
			String jsonStr = scan.nextLine();
			Info x = g.fromJson(jsonStr, Info.class);
			System.out.println(x);
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}