import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;



public class messingWithFiles {
	public static void main(String args[]) throws Exception {
		String file = "src/test/resources/21ee2/Back in Black.egg";
		BasicFileAttributes attr = Files.readAttributes(Paths.get(file), BasicFileAttributes.class);
		
		System.out.println("creationTime: " + attr.creationTime());
		System.out.println("lastAccessTime: " + attr.lastAccessTime());
		System.out.println("lastModifiedTime: " + attr.lastModifiedTime());
	
		System.out.println("isDirectory: " + attr.isDirectory());
		System.out.println("isOther: " + attr.isOther());
		System.out.println("isRegularFile: " + attr.isRegularFile());
		System.out.println("isSymbolicLink: " + attr.isSymbolicLink());
		System.out.println("size: " + attr.size());
		//		1000000
		
	}

}