package api.saver;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.net.URL;

public class SaverDownload {

	/*
	 * Documentation https://api.beatsaver.com/docs/index.html?url=./swagger.json#/
	 * Grabs the Map based on the ID
	 */
	public static void main(String[] args) throws Exception {
		String writtingDirs = "Not running that method";
		long ts = System.currentTimeMillis() / 1000;
		String eventFile = "src/main/resources/eventLogs/Call" + ts + ".txt";
//		int con = 129297;
		int con = 179013;
//		int con = 78118; // 76772
		int topLimit = 141545;
//		int topLimit = 2238032;
		String endl = "\n";
		FileWriter fw = new FileWriter(new File(eventFile));
		fw.write(writtingDirs + endl);
		fw.flush();
		while (con <= topLimit) {
			String baseUrl = "https://api.beatsaver.com/maps/id/";
			baseUrl += Integer.toHexString(con);
			fw.write("Grabbing ID: " + Integer.toHexString(con) + endl);
			fw.flush();
			String FILE_URL = baseUrl;
			String FILE_NAME = "G:\\DataStore\\Individual\\" + Integer.toHexString(con) + ".json";
			con += 1;
			try {
				BufferedInputStream in = new BufferedInputStream(new URL(FILE_URL).openStream());
				FileOutputStream fileOutputStream = new FileOutputStream(FILE_NAME);
				byte dataBuffer[] = new byte[1024];
				int bytesRead;
				while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
					fileOutputStream.write(dataBuffer, 0, bytesRead);
				}
				fileOutputStream.close();
				fw.write("Written all of " + con + ".json" + endl);
				fw.flush();
			} catch(Exception e) {
				System.out.println("something went wrong");
			}
		}
        try {
            Thread.sleep(10); // Sleep for 1 second between requests
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}
}
