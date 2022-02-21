package csv;

import lombok.Getter;
import lombok.Setter;

/* Goal is to get good stats on the parts of each map */
@Getter
@Setter
public class SongVar {
	private String Name;
	private String Artist;
	private String bpm;
	private long length; // In seconds
	private String enviroment;
	private long events;
	private long obstacles;
	private long redCount;
	private long blueCount;
	private long bombCount;
	private long na0; // Note angles
	private long na1;
	private long na2;
	private long na3;
	private long na4;
	private long na5;
	private long na6;
	private long na7;
	private long na8;
	private long na0p; // Note angles Percent
	private long na1p;
	private long na2p;
	private long na3p;
	private long na4p;
	private long na5p;
	private long na6p;
	private long na7p;
	private long na8p;
	private long np0; // Note Placement
	private long np1;
	private long np2;
	private long np3;
	private long np4;
	private long np5;
	private long np6;
	private long np7;
	private long np8;
	private long np9;
	private long np10;
	private long np11;
	private long upvote;
	private long downvote;
	private double rating;
	private long myBpm;

}
