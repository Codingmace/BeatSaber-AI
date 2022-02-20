package api.beast;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLDownload {
	/*
	 * Going from oldest to newest for downloading.
	 */
	public static void main(String args[]) throws Exception {
		downloadHtml(2, 2);
		// Extract the keys from the HTML pages
		String file = "1.html";
		Scanner scan = new Scanner(new File(file));
		String str = "";
		while(scan.hasNextLine() ) {
			str += scan.nextLine();
		}
		findLinks(str);
		downloadZip("21ac2");
		// Extract the Zip
		// Remove the uneccessary files
		// Call the compare to find the duplicate files
		// Call the writer to write the information on each one.
	}

	private static void findLinks(String file) {
//		Pattern pattern = Pattern.compile("https://api.beatsaver.com/download/key/");
		Pattern pattern = Pattern.compile("zip\" href=\"https://api.beatsaver.com/download/key/[a-zA-Z0-9]+\"");

		Matcher matcher = pattern.matcher(file);
		
		    // Find all matches
		while (matcher.find()) {
		      // Get the matching string
			String match = matcher.group();
		    System.out.println(match);
		}
	}

	private static void downloadHtml(int i, int j) {
		String baseUrl = "https://bsaber.com/songs/new/page/";
		for(; i < j; i++) {
			String FILE_URL = baseUrl + i;
			String FILE_NAME = i + ".html";
			try {
				BufferedInputStream in = new BufferedInputStream(new URL(FILE_URL).openStream());
				FileOutputStream fileOutputStream = new FileOutputStream(FILE_NAME); 
				byte dataBuffer[] = new byte[1024];
				int bytesRead;
				while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
					fileOutputStream.write(dataBuffer, 0, bytesRead);
				}
			} catch (IOException e) {
					System.out.println("FUCK");
			}
			System.out.println("Done with page " + i);
		}
	}

	private static void downloadZip(String key) {
		String baseURL = "https://api.beatsaver.com/download/key/";
		String pageUrl = baseURL + key;
		String fold = "src/main/resources/BeastSaber/";
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
		}
		System.out.println("Done Writting " + filename);
	}
}
