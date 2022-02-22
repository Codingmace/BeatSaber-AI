package refine;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;

public class DeleteSmallDirectory {

	public static void main(String[] args) throws Exception {
		int bottomThreshold = 2000000; // 2MB
		int topThreshold = 25000000; // 25MB

		File dir = new File("Z:\\BS Files\\BeatSaver\\");
		System.out.println(dir.toString());
		File listMaps[] = dir.listFiles(); // individual maps
		System.out.println(Arrays.toString(listMaps));
		boolean deleteFolder[] = new boolean[listMaps.length];
		for (int i = 0; i < listMaps.length; i++) {
			if (isLarger(listMaps[i].listFiles(), bottomThreshold)) {
				int axe = 0;
//				System.out.println("That is a valid mapping so far or threw an error");
				// Remove files not needed
				// Rename files that are shit named
			} else { // Not larger so Delete
				//System.out.println("That doesn't work");
				deleteFolder[i] = true;
			}
			// If are remove all the files that are not needed
			// In addition rename files that are named stupidly.
		}

		/* check that the verify folder exists */
		File a1 = new File("src/test/resources/verify");
		if (a1.exists()) {
			System.out.println("No need to create the folder");
		} else {
			System.out.println("Creating the folder");
			if (a1.mkdir()) {
				System.out.println("Successfully created the folder");
			} else {
				System.out.println("Can't create the folder. We have a big issue.");
			}
		}
		FileWriter fw = new FileWriter(new File("my file.txt"));
		for (int i = 0; i < deleteFolder.length; i++) {
			if (deleteFolder[i]) {
				fw.write("delete the folder " + listMaps[i].getName());
				fw.flush();
//				System.out.println("delete the folder " + listMaps[i].getName());
				try {
					deleteFold(listMaps[i]);
				} catch(Exception e ) {
					System.out.println(e.getMessage());
				}
			//} else {
				//System.out.println("delete unwanted files");
				//System.out.println("Move only wanted ???");
				// Part of the other program
			}
		}
	}

	private static void deleteFold(File file) throws IOException {
		File files[] = file.listFiles();
		for (File curFile : files) {
			if(curFile.isFile()) {
				Files.delete(Paths.get(curFile.toURI()));
			} else { // Is directory
				deleteFold(curFile);
			}
		}
		Files.delete(Paths.get(file.toURI()));
	}

	private static boolean isLarger(File files[], int threshold) {
		if(files == null) {
			return false;
		}
		for (File curFile : files) {
			Path curPath = Paths.get(curFile.getAbsolutePath());
			try {
				BasicFileAttributes attr = Files.readAttributes(curPath, BasicFileAttributes.class);
				long bytes = attr.size();
				if (bytes > threshold) {
					return true;
				}
			} catch (IOException e) {
				System.out.println(e.getMessage());
				return true;
			}
		}
		return false;
	}
}