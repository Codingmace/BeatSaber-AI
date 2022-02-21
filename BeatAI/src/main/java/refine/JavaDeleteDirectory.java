package refine;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.io.File;
import java.io.IOException;

public class JavaDeleteDirectory {

	public static void main(String[] args) throws Exception {
		int threshold = 2000000; // 2MB
		File dir = new File("src/test/resources/BeatSaber");
		System.out.println(dir.toString());
		File listMaps[] = dir.listFiles();
		System.out.println(Arrays.toString(listMaps));
		boolean deleteFolder[] = new boolean[listMaps.length];
		for(int i = 0;i < listMaps.length;i++) {
			if(isLarger(listMaps[i].listFiles(), threshold)) {
				System.out.println("That is a valid mapping so far or threw an error");
				// Remove files not needed
				// Rename files that are shit named
			} else { // Not larger so Delete
				System.out.println("That doesn't work");
				deleteFolder[i] = true;
			}
			// If are remove all the files that are not needed
			// In addition rename files that are named stupidly.
		}
	}
	private static boolean isLarger(File files[], int threshold) {
		for(File curFile: files) {
			Path curPath = Paths.get(curFile.getAbsolutePath());
			try {
				BasicFileAttributes attr = Files.readAttributes(curPath, BasicFileAttributes.class);
				long bytes = attr.size();
				if(bytes > threshold) {
					return true;
				}
			}catch(IOException e) {
				System.out.println(e.getMessage());
				return true;
			}
		}
		return false;
	}
	
	private static boolean deleteFolder(File fold) {
		return true;
	}
}