package api.saver;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;



public class mainGrab {

	/*
	 * Documentation
	 * https://api.beatsaver.com/docs/index.html?url=./swagger.json#/
	 * Last one formatting YYYY-MM-DDTHH:MM:SSZ %3A for :
	 */
	public static void main(String[] args) throws Exception{
//		String testURL = "https://api.beatsaver.com/maps/latest?automapper=true&before=2022-02-14T21%3A56%3A39Z&sort=UPDATED";
		long ts = System.currentTimeMillis() / 1000;
		String eventFile = "src/main/resources/eventLogs/Call" + ts + ".txt";
		boolean notEnd = true;
		String beforeTime = "2022-02-17T21%3A56%3A39Z";
		System.out.println(beforeTime.substring(0, 10));
		System.out.println(beforeTime.substring(11,13));
		
		int con = 0;
		String endl = "\n";
		FileWriter fw = new FileWriter(new File(eventFile));
		String records = "src/main/resources/entries.csv";
		String writeType = "";
		FileWriter csvs = new FileWriter(new File("entries.csv"));
		if(!Files.exists(Paths.get(records))) {
			String pt1 = "id,name,Uploader id, Uploader name, Uploader type,bpm,duration,songName,songSubName,";
			String pt2 = "songAuthorName,levelAuthorName,plays,downloads,upvotes,downvotes,score,";
			String pt3 = "uploaded,automapper,ranked,qualified,createdAt,updatedAt,lastPublishedAt";
			csvs.append(pt1 + pt2 + pt3 + endl);
			fw.write("Writting the CSV file" + endl);
			// Have to write the top column
		} 
		csvs.flush();
		while(notEnd) {
//			String beforeTime = "2022-02-14T21%3A56%3A39Z";
			String baseUrl = "https://api.beatsaver.com/maps/latest?";
			baseUrl += "automapper=false&";
			baseUrl += "before=" + beforeTime + "&";
			baseUrl += "sort=UPDATED";
			String curFold = "src/main/resources/Individual/";
			fw.write("Grabbing Before " + beforeTime + endl);
//			String testFile = "grab.json";
			grab d = new grab();
			fw.flush();
			String jsonStr = "";
			String FILE_URL = baseUrl;
			String FILE_NAME = "BSJSON/" + con + ".json";
			con += 1;
			try {
				BufferedInputStream in = new BufferedInputStream(new URL(FILE_URL).openStream());
				FileOutputStream fileOutputStream = new FileOutputStream(FILE_NAME);
				byte dataBuffer[] = new byte[1024];
				int bytesRead;
				while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
					jsonStr += new String(dataBuffer);
					fileOutputStream.write(dataBuffer, 0, bytesRead);
				}
				fw.write("Written all of " + con + ".json" + endl);
				fw.flush();
				Gson g = new GsonBuilder().setLenient().create();
				try {
					d = g.fromJson(jsonStr, grab.class);
				} catch(Exception e) {
					fw.write(e.getMessage() + endl);
					// Set it earlier an hour
					int hour = Integer.parseInt(beforeTime.substring(11,13));
					hour -= 1;
					String newHour = "";
					if (hour <= 0) {
						fw.write("It broke" + endl);
						break;
					}
					else if(hour <= 9) {
						newHour = "0" + hour;
					} else {
						newHour = hour + "";
					}
					beforeTime = beforeTime.substring(0, 11) + newHour + "%3A00%3A00Z";
					continue;
				}
				int numbOfDocs = d.getDocs().length;
				Doc lastDoc = d.getDocs()[numbOfDocs - 1];
				String lastUpload = lastDoc.getUploaded();
				int index = lastUpload.lastIndexOf(".");
				lastUpload = lastUpload.substring(0, index) + "Z";
				lastUpload = lastUpload.replaceAll(":", "%3A");
				beforeTime = lastUpload;
				fw.write("Last known Entry: " + beforeTime + endl);
				fw.flush();
				
				// Write everything to JSON and Csv file
				Doc documents[] = d.getDocs();
				for (int i = 0 ; i < numbOfDocs;i++) {
					System.out.println(documents[i].getVersions()[0].getDownloadURL());
					String curFileName = documents[i].getId() + ".json";							
					csvs.append(documents[i].toCsvString() + endl);
					FileWriter curFw = new FileWriter(new File(curFold + curFileName));
					String curJson = g.toJson(documents[i], Doc.class);
					curFw.append(curJson);
					curFw.flush();
				}
				csvs.flush();
				
				// Download the File
				// Write all the information that is important in a file
			} catch (Exception e) {
				fw.append(e.getMessage() + endl);
//				e.printStackTrace();
				System.out.println("FUCK");
			}
	
/*			try {
				Gson g = new Gson();
				d = g.fromJson(jsonStr, grab.class);
				int numbOfDocs = d.getDocs().length;
				Doc lastDoc = d.getDocs()[numbOfDocs - 1];
				String lastUpload = lastDoc.getUploaded();
				int index = lastUpload.lastIndexOf(".");
				lastUpload = lastUpload.substring(0, index) + "Z";
				lastUpload = lastUpload.replaceAll(":", "%3A");
				beforeTime = lastUpload;
				fw.write("Last known);
//				System.out.println(lastUpload);
//				System.out.println("IT WORKS.");
//			System.out.println(d.getDocs());
//			System.out.println(d);
//			System.out.println(d.getDocs().length);
//			Doc curDoc = d.getDocs()[0];
//			System.out.println(curDoc);
//			String jsonStr = scan.nextLine();
//			System.out.println(x);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		*/
		// Try and write this all to a csv file most likely
		// If the file with the last date grabbed exists
		// Grab everything since
		// If not. Start over from the beginning
		// Grab from files only what is needed.
		
	}

}
}
