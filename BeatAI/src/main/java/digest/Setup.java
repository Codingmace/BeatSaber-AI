package digest;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Setup {

	public static void main(String[] args) {
		String curFold = "src/main/resources/66e6/";
		String infoFilename = curFold + "info.dat";
		File infoFile = new File(infoFilename);
		Info x = new Info();
		boolean rewriteX = false;
		try {
			Gson g = new GsonBuilder().setLenient().create();
//			Gson g = new Gson();
			Scanner scan = new Scanner(infoFile);
			String jsonStr = scan.nextLine();
//			scan.close();
			x = g.fromJson(jsonStr, Info.class);
//			System.out.println(x);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ArrayList<String> keepFile = new ArrayList<String>();
		keepFile.add("info.dat");
//		keepFile.add(x.getSongFilename());
//		keepFile.add(x.getCoverImage());
		System.out.println("file 1 " + x.get_songFilename());
		System.out.println("File 2 " + x.get_coverImageFilename());
		keepFile.add(x.get_songFilename());
		keepFile.add(x.get_coverImageFilename());
		bms curSet[] = x.get_difficultyBeatmapSets();
		if (curSet.length == 1) {
			beatmap diffMaps[] = curSet[0].get_difficultyBeatmaps();
			for (int i = 0; i < diffMaps.length; i++) {
				beatmap curBM = diffMaps[i];
				String curDiff = curBM.get_Difficulty();
				String curDiffFile = curBM.get_BeatmapFilename();
				if ((curDiff + ".dat").equals(curDiffFile)) {
					System.out.println("they acytually did it right.");
					keepFile.add(curDiffFile);
				} else {
					System.out.println(" I guess I will have to fix it myself");
					File tempFile = new File(curFold + curDiffFile);
					if (tempFile.exists()) {
						if (tempFile.renameTo(new File(curFold + curDiff + ".dat"))) {
							System.out.println("Renamed file successfully");
							keepFile.add(curDiff + ".dat");
							x.get_difficultyBeatmapSets()[0].get_difficultyBeatmaps()[i]
									.setBeatmapFilename((curDiff + ".dat"));
							System.out.println("Now that I changed that I have to rewite the file");
							rewriteX = true;
						} else {
							System.out.println("Screwed up the rename. Dang it");
						}
					} else {
						System.out.println("How the fuck do you forget to add a file");
					}
					// Delete the file
					// write the file once created again
					// Grab json and do all that
				}
			}
		} else {
			System.out.println("That is a problem of more than one difficulty set");
		}
	}
}
