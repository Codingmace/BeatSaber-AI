package digest;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import com.google.gson.Gson;

public class Setup {

	public static void main(String[] args) {
		String curFold = "src/main/resources/66e6/";
		String infoFile = curFold + "info.dat";
		Info x = new Info();
		try {
			Gson g = new Gson();
			Scanner scan = new Scanner(new File(infoFile));
			String jsonStr = scan.nextLine();
			x = g.fromJson(jsonStr, Info.class);
//			System.out.println(x);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ArrayList<String> keepFile = new ArrayList<String>();
		keepFile.add("info.dat");
//		keepFile.add(x.getSongFilename());
//		keepFile.add(x.getCoverImage());
		
		bms a = x._difficultyBeatmapSets[0];
		beatmap b[] = a._difficultyBeatmaps;
		for(int i = 0;i < b.length;i++) {
			keepFile.add(b[i].getBeatmapFilename());
		}
		System.out.println(Arrays.toString(keepFile.toArray()));
		// Remove files not in this list
		
	}
}
