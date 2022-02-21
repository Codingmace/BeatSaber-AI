package api.saver;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class Entry {
	private String id;
	private String name;
	private String description;
	private Uploader uploader;
	private MetaData metadata;
	private Stats stats;
	private String uploaded;
	private boolean automapper;
	private boolean ranked;
	private boolean qualified;
	private Versions versions[];
	private String createdAt;
	private String updatedAt;
	private String lastPublishedAt;
	private String[] tags;

	public String getId() {
		return id;
	}
	public String getUploaded() {
		return uploaded;
	}

	public void setUploaded(String uploaded) {
		this.uploaded = uploaded;
	}

	public String toCsvString() {
		return id + "," + name + "," + uploader.toCsvStr() + "," + metadata.toCsvStr() + ","
				+ stats.toCsvStr() + "," + uploaded + "," + automapper + "," + ranked + "," + qualified + ","
				+ createdAt + "," + updatedAt + "," + lastPublishedAt;
	}
	public Versions[] getVersions() {
		return versions;
	}
}

@Data
class Uploader {
	private int id;
	private String name;
	private String hash;//
	private String avatar;//
	private String type;

	public String toCsvStr() {
		return id + "," + name + "," + type;
	}
}

@Getter
@Setter
class MetaData {
	private double bpm;
	private int duration;
	private String songName;
	private String songSubName;
	private String songAuthorName;
	private String levelAuthorName;

	public String toCsvStr() {
		songName = songName.replaceAll(",", ";");
		songSubName = songSubName.replaceAll(",", ";");
		songAuthorName = songAuthorName.replaceAll(",", ";");
		levelAuthorName = levelAuthorName.replaceAll(",", ";");
		return bpm + "," + duration + "," + songName + "," + songSubName + "," + songAuthorName + "," + levelAuthorName;
	}
}

@Getter
@Setter
class Stats {
	private int plays;
	private int downloads;
	private int upvotes;
	private int downvotes;
	private double score;

	public String toCsvStr() {
		return plays + "," + downloads + "," + upvotes + "," + downvotes + "," + score;
	}
}

// Only for the JSON
@Getter
@Setter
class Versions {
	private String hash;//
	private String key;
	private String state;//
	private String createdAt;//
	private int sageScore;
	private Difficulties[] diffs;
	private String downloadURL;
	private String coverURL;
	private String previewURL;//
	
	public String getDownloadURL() {
		return downloadURL;
	}
}

// Adding to just the Json Files
@Getter
@Setter
class Difficulties {
	private double njs;
	private double offset;
	private int notes;
	private int bombs;
	private int obstacles;
	private double nps;
	private double length;
	private String characteristic;
	private String difficulty;
	private int events;
	private boolean chroma;
	private boolean me;
	private boolean ne;
	private boolean cinema;
	private double seconds;
	private Parity paritySummary;
}

@Getter
@Setter
class Parity {
	private int errors;
	private int warns;
	private int resets;
}