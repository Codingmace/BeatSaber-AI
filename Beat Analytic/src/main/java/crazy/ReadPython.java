package crazy;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
public class ReadPython {
	public static void main(String[] args) throws IOException, InterruptedException {
		String path = "./src/main/java/crazy/script.py";
		ProcessBuilder pb = new ProcessBuilder("python",path).inheritIO();
		Process p = pb.start();
		p.waitFor();
		BufferedReader bfr = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line = "";
		while ((line = bfr.readLine()) != null) {
			System.out.println(line);
		}
	}
}