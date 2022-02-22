package api.saver;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.lingala.zip4j.ZipFile;

public class SaverDownload {

	/*
	 * Documentation https://api.beatsaver.com/docs/index.html?url=./swagger.json#/
	 * Grabs the Map based on the ID
	 */
	public static void main(String[] args) throws Exception {
		String writtingDirs = setupDirs();
		long ts = System.currentTimeMillis() / 1000;
		String eventFile = "src/main/resources/eventLogs/Call" + ts + ".txt";
		int con = 23826;
		String endl = "\n";
		FileWriter fw = new FileWriter(new File(eventFile));
		fw.write(writtingDirs + endl);
		fw.flush();
		while (con <= 2238032) {
			String baseUrl = "https://api.beatsaver.com/maps/id/";
			baseUrl += Integer.toHexString(con);
			String curFold = "src/main/resources/BeatSaver/"; // Where Putting the zip files
			fw.write("Grabbing ID: " + Integer.toHexString(con) + endl);
			Entry entity = new Entry();
			fw.flush();
			String FILE_URL = baseUrl;
			String FILE_NAME = "src/main/resources/Individual/" + Integer.toHexString(con) + ".json";
			con += 1;
			try {
				BufferedInputStream in = new BufferedInputStream(new URL(FILE_URL).openStream());
				FileOutputStream fileOutputStream = new FileOutputStream(FILE_NAME);
				byte dataBuffer[] = new byte[1024];
				int bytesRead;
				while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
//					jsonStr += new String(dataBuffer);
					fileOutputStream.write(dataBuffer, 0, bytesRead);
				}
				fileOutputStream.close();
				fw.write("Written all of " + con + ".json" + endl);
				fw.flush();
				Gson g = new GsonBuilder().setLenient().create();
				try {
					String veri = "";
					Scanner scan = new Scanner(new File(FILE_NAME));
					while (scan.hasNext()) {
						veri += scan.nextLine();
					}
					entity = g.fromJson(veri, Entry.class);
				} catch (Exception e) {
					fw.write(e.getMessage() + endl);
					continue;
				}
				String downloadUrl = entity.getVersions()[0].getDownloadURL();
				int index = downloadUrl.lastIndexOf("/") + 1;
				if (downloadZip(entity.getId(), downloadUrl.substring(index))) {
					if (unZip(entity.getId())) {
						if (deleteZip(entity.getId())) {
							fw.append(entity.getId() + " Downloaded, Unzip, and Deleted - "
									+ downloadUrl.substring(index) + endl);
						} else {
							fw.append("Failed to move Zip" + endl);
						}
					} else {
						fw.append("Failed to Unzip" + endl);
					}
				} else {
					fw.append("Failed to Download Zip" + endl);
				}
			} catch (Exception e) {
				fw.append(e.getMessage() + endl);
				System.out.println("FUCK");
			}
		}

	}

	/*
	 * Creates the default directories to make sure of no issues
	 */
	private static String setupDirs() {
		String results = "";
		String endl = "\n";
		String basePath = "src/main/resources/";
		File f1 = new File(basePath + "eventLogs");
		File f2 = new File(basePath + "BeatSaver");
		File f3 = new File(basePath + "Individual");
		File f4 = new File(basePath + "Delete");
		if (f1.exists()) {
			results += "eventLogs Folder Exists" + endl;
		} else {
			if (f1.mkdir()) {
				results += "creating eventLogs Folder" + endl;
			} else {
				results += "Problem creating eventLogs Folder" + endl;
			}
		}
		if (f2.exists()) {
			results += "BeatSaver Folder Exists" + endl;
		} else {
			if (f2.mkdir()) {
				results += "creating BeatSaver Folder" + endl;
			} else {
				results += "Problem creating BeatSaver Folder" + endl;
			}
		}
		if (f3.exists()) {
			results += "Individual Folder Exists" + endl;
		} else {
			if (f3.mkdir()) {
				results += "creating Individual Folder" + endl;
			} else {
				results += "Problem creating Individual Folder" + endl;
			}
		}
		if (f4.exists()) {
			results += "Delete Folder Exists" + endl;
		} else {
			if (f4.mkdir()) {
				results += "creating Delete Folder" + endl;
			} else {
				results += "Problem creating Delete Folder" + endl;
			}
		}
		return results;
	}

	private static boolean downloadZip(String key, String zipName) {
		String baseURL = "https://na.cdn.beatsaver.com/";
		String pageUrl = baseURL + zipName;
		String fold = "src/main/resources/BeatSaver/";
		String filename = fold + key + ".zip";
		try {
			BufferedInputStream in = new BufferedInputStream(new URL(pageUrl).openStream());
			FileOutputStream fileOutputStream = new FileOutputStream(filename);
			byte dataBuffer[] = new byte[1024];
			int bytesRead;
			while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
				fileOutputStream.write(dataBuffer, 0, bytesRead);
			}
			fileOutputStream.close();
			in.close();
		} catch (IOException e) {
			System.out.println("FUCK on key " + key);
			return false;
		}
		return true;

	}

	private static boolean unZip(String zipFile) {
		String baseFolder = "src/main/resources/BeatSaver/";
		String source = baseFolder + zipFile + ".zip";
		String target = baseFolder + zipFile;
		try {
			new ZipFile(source).extractAll(target);
		} catch (IOException e) {
			System.out.println("CRAP");
			return false;
		}
		return true;

	}

	@SuppressWarnings("unused")
	private static boolean moveZip(String zipFile) {
		String baseFolder = "src/main/resources/";
		Path source = Paths.get(baseFolder + "BeatSaver/" + zipFile + ".zip");
		Path dest = Paths.get(baseFolder + "Delete/" + zipFile + ".zip");
		try {
			if (Files.exists(dest)) {
				dest = Paths.get(baseFolder + "Delete/" + zipFile + "_1.zip");
			}
			Files.move(source, dest, REPLACE_EXISTING);
		} catch (IOException e) {
			System.out.println("DAMNIT");
			return false;
		}
		return true;
	}

	private static boolean deleteZip(String zipFile) {
		String baseFolder = "src/main/resources/BeatSaver/";
		Path source = Paths.get(baseFolder + zipFile + ".zip");
		try {
			Files.delete(source);
		} catch (IOException e) {
			System.out.println("DAMNIT");
			return false;
		}
		return true;
	}
}
