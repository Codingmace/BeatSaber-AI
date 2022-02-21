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

public class mainGrab {

	/*
	 * Documentation https://api.beatsaver.com/docs/index.html?url=./swagger.json#/
	 * Last one formatting YYYY-MM-DDTHH:MM:SSZ %3A for :
	 */
	public static void main(String[] args) throws Exception {
//		String testURL = "https://api.beatsaver.com/maps/latest?automapper=true&before=2022-02-14T21%3A56%3A39Z&sort=UPDATED";
		long ts = System.currentTimeMillis() / 1000;
		String eventFile = "src/main/resources/eventLogs/Call" + ts + ".txt";
		boolean notEnd = true;
//		String beforeTime = "2022-02-17T21%3A56%3A39Z";
//		System.out.println(beforeTime.substring(0, 10));
//		System.out.println(beforeTime.substring(11, 13));

		int con = 0;
		String endl = "\n";
		FileWriter fw = new FileWriter(new File(eventFile));
//		String records = "src/main/resources/entries.csv";
//		String writeType = "";
//		FileWriter csvs = new FileWriter(new File("entries.csv"));
/*
		if (!Files.exists(Paths.get(records))) {
			String pt1 = "id,name,Uploader id,Uploader name,Uploader type,bpm,duration,songName,songSubName,";
			String pt2 = "songAuthorName,levelAuthorName,plays,downloads,upvotes,downvotes,score,";
			String pt3 = "uploaded,automapper,ranked,qualified,createdAt,updatedAt,lastPublishedAt";
			csvs.append(pt1 + pt2 + pt3 + endl);
			fw.write("Writting the CSV file" + endl);
			// Have to write the top column
		}
		csvs.flush();
		*/
		while (con <= 2238032) {
//			String beforeTime = "2022-02-14T21%3A56%3A39Z";
			// https://api.beatsaver.com/maps/id/1
			String baseUrl = "https://api.beatsaver.com/maps/id/";
			baseUrl += Integer.toHexString(con);
//			baseUrl += "automapper=false&";
//			baseUrl += "before=" + beforeTime + "&";
//			baseUrl += "sort=UPDATED";
			String curFold = "src/main/resources/BeatSaver/"; // Where Putting the zip files
			fw.write("Grabbing ID: " + Integer.toHexString(con) + endl);
//			fw.write("Grabbing Before " + beforeTime + endl);
//			String testFile = "grab.json";
			Entry entity = new Entry();
			fw.flush();
//			String jsonStr = "";
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
				fw.write("Written all of " + con + ".json" + endl);
				fw.flush();
				Gson g = new GsonBuilder().setLenient().create();
				try {
					String veri = "";
					Scanner scan = new Scanner(new File(FILE_NAME));
					while (scan.hasNext()) {
						veri += scan.nextLine();
					}
					/*
					 * if(veri.equalsIgnoreCase(jsonStr)) {
					 * System.out.println("Theoretically it should work"); } else {
					 * System.out.println("Here is the problem"); }
					 */
					entity = g.fromJson(veri, Entry.class);
//					entity = g.fromJson(jsonStr, Entry.class);
				} catch (Exception e) {
					fw.write(e.getMessage() + endl);
					/*
					 * // Set it earlier an hour int hour =
					 * Integer.parseInt(beforeTime.substring(11,13)); hour -= 1; String newHour =
					 * ""; if (hour <= 0) { fw.write("It broke" + endl); break; } else if(hour <= 9)
					 * { newHour = "0" + hour; } else { newHour = hour + ""; } beforeTime =
					 * beforeTime.substring(0, 11) + newHour + "%3A00%3A00Z";
					 */
					continue;
				}
//				System.out.println(entity.getId());
//				System.out.println(entity.getVersions()[0].getDownloadURL()); // got download url
				String downloadUrl = entity.getVersions()[0].getDownloadURL();
				int index = downloadUrl.lastIndexOf("/") + 1;
//				fw.append("Dealing with zip" + endl);
				if(downloadZip(entity.getId(), downloadUrl.substring(index))) {
					if(unZip(entity.getId())) {
						if(moveZip(entity.getId())) {
							fw.append(entity.getId() + " Downloaded, Unzip, and Moved - " + downloadUrl.substring(index) + endl);
						} else {
							fw.append("Failed to move Zip" + endl);
						}
					} else {
						fw.append("Failed to Unzip" + endl);
					}
				} else {
					fw.append("Failed to Download Zip" + endl);
				}
//				System.out.println(downloadUrl.substring(index));
//				System.out.println("Breakdown");
				// Download this and uncompress like the other one
				/*
				 * int numbOfDocs = d.getDocs().length; Doc lastDoc = d.getDocs()[numbOfDocs -
				 * 1];
				 * 
				 * String lastUpload = lastDoc.getUploaded(); int index =
				 * lastUpload.lastIndexOf("."); lastUpload = lastUpload.substring(0, index) +
				 * "Z"; lastUpload = lastUpload.replaceAll(":", "%3A"); beforeTime = lastUpload;
				 * fw.write("Last known Entry: " + beforeTime + endl);
				 * 
				 * fw.flush();
				 * 
				 * // Write everything to JSON and Csv file Doc documents[] = d.getDocs(); for
				 * (int i = 0 ; i < numbOfDocs;i++) {
				 * System.out.println(documents[i].getVersions()[0].getDownloadURL()); String
				 * curFileName = documents[i].getId() + ".json";
				 * csvs.append(documents[i].toCsvString() + endl); FileWriter curFw = new
				 * FileWriter(new File(curFold + curFileName)); String curJson =
				 * g.toJson(documents[i], Doc.class); curFw.append(curJson); curFw.flush(); }
				 * csvs.flush();
				 */
				// Download the File
				// Write all the information that is important in a file
			} catch (Exception e) {
				fw.append(e.getMessage() + endl);
//				e.printStackTrace();
				System.out.println("FUCK");
			}

			/*
			 * try { Gson g = new Gson(); d = g.fromJson(jsonStr, grab.class); int
			 * numbOfDocs = d.getDocs().length; Doc lastDoc = d.getDocs()[numbOfDocs - 1];
			 * String lastUpload = lastDoc.getUploaded(); int index =
			 * lastUpload.lastIndexOf("."); lastUpload = lastUpload.substring(0, index) +
			 * "Z"; lastUpload = lastUpload.replaceAll(":", "%3A"); beforeTime = lastUpload;
			 * fw.write("Last known); // System.out.println(lastUpload); //
			 * System.out.println("IT WORKS."); // System.out.println(d.getDocs()); //
			 * System.out.println(d); // System.out.println(d.getDocs().length); // Doc
			 * curDoc = d.getDocs()[0]; // System.out.println(curDoc); // String jsonStr =
			 * scan.nextLine(); // System.out.println(x); } catch (Exception e) {
			 * e.printStackTrace(); } }
			 */
			// Try and write this all to a csv file most likely
			// If the file with the last date grabbed exists
			// Grab everything since
			// If not. Start over from the beginning
			// Grab from files only what is needed.

		}

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
//		System.out.println("Done Writting " + filename);
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

	private static boolean moveZip(String zipFile) {
		String baseFolder = "src/main/resources/";
		Path source = Paths.get(baseFolder + "BeatSaver/" + zipFile + ".zip");
		Path dest = Paths.get(baseFolder + "Delete/" + zipFile + ".zip");
		try {
			if(Files.exists(dest)) {
				dest = Paths.get(baseFolder + "Delete/" + zipFile + "_1.zip");
			}
			Files.move(source, dest, REPLACE_EXISTING);
//			Files.delete(source);
		} catch (IOException e) {
			System.out.println("DAMNIT");
			return false;
		}
		return true;

	}
}
