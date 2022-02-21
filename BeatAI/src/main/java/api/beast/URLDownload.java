package api.beast;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import static java.nio.file.StandardCopyOption.*;
import java.nio.file.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.lingala.zip4j.ZipFile;

public class URLDownload {
	/*
	 * Going from oldest to newest for downloading.
	 */
	public static void main(String args[]) throws Exception {
		long ts = System.currentTimeMillis() / 1000;
		String eventFile = "src/main/resources/eventLogs/Event" + ts + ".txt";
		FileWriter fw = new FileWriter(new File(eventFile));
		String endline = "\n";

		/* Download the HTML */
		int startPage = 562;
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
				fileOutputStream.close();
			} catch (IOException e) {
				System.out.println("FUCK");
			}
			ArrayList<String> links = findLinks(full); // Has all download links
			fw.write("Done downloading page " + startPage + endline);
			fw.flush();

			/*
			 * Extract Key Download, Unzip and Delete File
			 */
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
					deleteZip(curKey);
				} else {
					fw.write(" is not successful" + endline);
				}
				fw.flush();
			}
		}
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
		int con = 0;
		while (matcher.find()) {
			con += 1;
			// Get the matching string
			String match = matcher.group();
			String modString = match.substring(11); // Get rid beginning
			modString = modString.substring(0, modString.length() - 2); // End Quote
			if (!ar.contains(modString)) {
				ar.add(modString);
			}
		}
		System.out.println(con + " Number of times");
		return ar;
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

	@SuppressWarnings("unused")
	private static boolean moveZip(String zipFile) {
		String baseFolder = "src/main/resources/";
		Path source = Paths.get(baseFolder + "BeastSaber/" + zipFile + ".zip");
		Path dest = Paths.get(baseFolder + "Delete/" + zipFile + ".zip");
		try {
			Files.move(source, dest, REPLACE_EXISTING);
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
