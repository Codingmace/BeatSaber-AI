

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.*;

/* Grab before adding all the extra stuff */
@Data
public class grabs {
	private Doc docs[];
}

class Doc {
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
}

@Data
class Uploader {
	private int id;
	private String name;
	private String hash;
	private String avatar;
	private String type;
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
}

@Getter
@Setter
class Stats {
	private int plays;
	private int downloads;
	private int upvotes;
	private int downvotes;
	private double score;
}

@Getter
@Setter
class Versions {
	private String hash;
	private String state;
	private String createdAt;
	private int sageScore;
	private Difficulties[] diffs;
	private String downloadURL;
	private String coverURL;
	private String previewURL;
}

@Getter
@Setter
class Difficulties {
	private int njs;
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
	private boolean ne ;
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