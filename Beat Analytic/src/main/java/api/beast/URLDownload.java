package api.beast;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import static java.nio.file.StandardCopyOption.*;
import java.nio.file.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.lingala.zip4j.ZipFile;

public class URLDownload {
	/*
	 * Going from oldest to newest for downloading.
	 */
	public static void main(String args[]) throws Exception {
//		String filenametest = "zip\" href=\"https://api.beatsaver.com/download/key/21ff6\"";
//		filenametest = filenametest.substring(10, filenametest.length()-1);
//		System.out.println(getKey(filenametest));
		long ts = System.currentTimeMillis() / 1000;
		String eventFile = "src/main/resources/eventLogs/Event" + ts + ".txt";
		FileWriter fw = new FileWriter(new File(eventFile));
//		Scanner ins = new Scanner(System.in);
//		String q = ins.nextLine();
		String endline = "\n";

		/* Download the HTML */
		int startPage = 55;
		int endPage = 2897;
		String baseUrl = "https://bsaber.com/songs/new/page/";
		fw.write("Downloading From page " + startPage + " to " + endPage + endline);
		for (; startPage < endPage; startPage++) {
			String full = "";
			String FILE_URL = baseUrl + startPage;
			String FILE_NAME = "html/" + startPage + ".html";
			try {
				BufferedInputStream in = new BufferedInputStream(new URL(FILE_URL).openStream());
				FileOutputStream fileOutputStream = new FileOutputStream(FILE_NAME);
				byte dataBuffer[] = new byte[1024];
				int bytesRead;
				while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
					full += new String(dataBuffer);
					fileOutputStream.write(dataBuffer, 0, bytesRead);
				}
			} catch (IOException e) {
				System.out.println("FUCK");
			}
			ArrayList<String> links = findLinks(full); // Has all download links
			fw.write("Done downloading page " + startPage + endline);
			fw.flush();
			// System.out.println("Done with page " + startPage);
			for (int i = 0; i < links.size(); i++) {
				String curKey = getKey(links.get(i));
				fw.write("Key: " + curKey + " download");
				if (downloadZip(curKey)) {
					fw.write(" is successful" + endline);
				} else {
					fw.write(" is not successful" + endline);
				}
				fw.write("Key: " + curKey + " unzip");
				if (unZip(curKey)) {
					fw.write(" is successful" + endline);
					/* Move file if successful */
					moveZip(curKey);
				} else {
					fw.write(" is not successful" + endline);
				}
				fw.flush();
			}
		}

		/*
		 * String file = "1.html"; downloadHtml(1, 2); // Extract the keys from the HTML
		 * pages Scanner scan = new Scanner(new File(file)); String str = ""; while
		 * (scan.hasNext()) { str += scan.nextLine(); }
		 * System.out.println("Finding links"); // System.out.println(str); //
		 * findLinks(str); // downloadZip("21ac2");
		 */
		// Extract the Zip
		// Remove the uneccessary files
		// Call the compare to find the duplicate files
		// Call the writer to write the information on each one.
	}

	private static String getKey(String url) {
		int index = url.lastIndexOf("/");
		System.out.println(url.substring(index + 1));
		return url.substring(index + 1);
	}

	private static ArrayList<String> findLinks(String file) {
		Pattern pattern = Pattern.compile("zip\" href=\"https://api.beatsaver.com/download/key/[a-zA-Z0-9]+\">");

		Matcher matcher = pattern.matcher(file);
		ArrayList<String> ar = new ArrayList<String>();
//		System.out.println(matcher.groupCount());
		int con = 0;
		while (matcher.find()) {
			con += 1;
			// Get the matching string
			String match = matcher.group();
			String modString = match.substring(11); // Get rid beginning
			modString = modString.substring(0, modString.length() - 2); // End Quote
			if(!ar.contains(modString)) {
				ar.add(modString);
			}
//			ar.add(match);
//		    System.out.println(match);
		}
		System.out.println(con + " Number of times");
		return ar;
	}

	private static void downloadHtml(int i, int j) {
		String baseUrl = "https://bsaber.com/songs/new/page/";
		String full = "";
		for (; i < j; i++) {
			String FILE_URL = baseUrl + i;
			String FILE_NAME = i + ".html";
			try {
				BufferedInputStream in = new BufferedInputStream(new URL(FILE_URL).openStream());
				FileOutputStream fileOutputStream = new FileOutputStream(FILE_NAME);
				byte dataBuffer[] = new byte[1024];
				int bytesRead;
				while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
					full += new String(dataBuffer);
					fileOutputStream.write(dataBuffer, 0, bytesRead);
				}
			} catch (IOException e) {
				System.out.println("FUCK");
			}
			System.out.println("Done with page " + i);
		}
		ArrayList<String> links = findLinks(full);
//		System.out.println(full);
	}

	private static boolean downloadZip(String key) {
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
			return false;
		}
		return true;
//		System.out.println("Done Writting " + filename);
	}

	private static boolean unZip(String zipFile) {
		String baseFolder = "src/main/resources/BeastSaber/";
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
		Path source = Paths.get(baseFolder + "BeastSaber/" + zipFile + ".zip");
		Path dest = Paths.get(baseFolder + "Delete/" + zipFile + ".zip");
		try {
			Files.move(source, dest, REPLACE_EXISTING);
//			Files.delete(source);
		} catch (IOException e) {
			System.out.println("DAMNIT");
			return false;
		}
		return true;

	}

	private static boolean deleteZip(String zipFile) {
		String baseFolder = "src/main/resources/BeastSaber/";
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
