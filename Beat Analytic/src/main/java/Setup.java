import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class Setup {

	public static void main(String[] args) throws Exception {
		String curFold = "src/main/resources/2144/";
		String infoFile = curFold + "info.dat";
		// Will have to auto do this later.
		String easyFile = curFold + "Easy.dat";
		String normalFile =curFold + "Normal.dat";
		String hardFile = curFold + "Hard.dat";
		String expertFile = curFold + "Expert.dat";
		String expertPlusFile = curFold + "ExpertPlus.dat";
//		Scanner scan = new Scanner(new File(infoFile));
//		System.out.println(scan.nextLine());
		
        Object obj = new JSONParser().parse(new FileReader(infoFile));
          
        JSONObject jo = (JSONObject) obj;
        //System.out.println(jo.toJSONString());
        System.out.println("Information for the song");
        Long bpm = (Long) (jo.get("_beatsPerMinute"));
        String songName = (String) (jo.get("_songName"));
        String songWriter = (String) (jo.get("_songAuthorName"));
        System.out.println("BPM: " + bpm);
        System.out.println("Song Name: " + songName);
        System.out.println("Song Writer: " + songWriter);
        
        fileDigest(normalFile);
        fileDigest(hardFile);
        
//        JSONArray difficulties = (JSONArray) jo.get("_difficultyBeatmapSets");
//        System.out.println(difficulties.toString());
//        System.out.println(difficulties);

	}

	private static void fileDigest(String normalFile) throws Exception {
        Object obj = new JSONParser().parse(new FileReader(normalFile));
        JSONObject jo = (JSONObject) obj;
        
        /* Notes Report */
        JSONArray ja = (JSONArray) jo.get("_notes");
//        System.out.println(ja.size());
        Object notes[] = ja.toArray();
        notesFrequencyAnalysis(notes);
        int timeLength = Long2Int(((JSONObject)notes[notes.length - 1]).get("_time"));
        System.out.println(timeLength);
        String segments[] = segmentSong(timeLength, notes);
//        for(int i =0; i < 10; i++) {
//        	System.out.println(notes[notes.length - (i + 1)].toString());
//        }
        
        /* 
         * Obstacles Data
         * Type = 0 Full, 1 Duck
         * LineIndex = Column (0,1,2,3) Left -> Right
         * Width = # Of columns Take up (L -> R)
         */

        JSONArray jaObstacles = (JSONArray) jo.get("_obstacles");
        System.out.println("Found: " + jaObstacles.size() + " number of obstacles");
        /* 
         * Need to analyze Events
         */
        JSONArray jaEvents = (JSONArray) jo.get("_events");
        System.out.println("Found: " + jaEvents.size() + " number of Events");
        
		
	}

	private static String[] segmentSong(int timeLength, Object[] notes) {
		String red[] = new String[(int)(timeLength / 10) + 1];
		String blue[] = new String[(int)(timeLength / 10) + 1];
		String both[] = new String[(int) (timeLength / 10) + 1];
		for(int i =0 ; i < red.length;i++) {
			red[i] = "";
			blue[i] = "";
			both[i] = "";
		}
		// Ignoring Bombs
        /*
         * LineLayer = Row (0,1,2) Bottom -> Top
         * LineIndex = Column (0,1,2,3) Left -> Right
         * Type = Color (0 Red, 1 Blue, 3 Bomb)
         * Time = Beat Reaches Player
         * CutDirection 0 -> 8
         * 0 UP, 1 DOWN, 2 LEFT, 3 RIGHT, 4 UP LEFT
         * 5 UP RIGHT, 6 DOWN LEFT, 7 DOWN RIGHT
         * 8 ANY
         */
        for(int i=0; i < notes.length;i++) {
        	JSONObject curNote = (JSONObject) notes[i];
//        	System.out.println(curNote.toString());
        	Double time = Double.parseDouble((curNote.get("_time")).toString());
        	int pos =  time.intValue() / 10;
        	int row = Long2Int(curNote.get("_lineLayer"));
        	int column = Long2Int(curNote.get("_lineIndex"));
        	int color = Long2Int(curNote.get("_type"));
        	//double time = (Double) curNote.get("_time"); // Not necessary now
        	int direction = Long2Int(curNote.get("_cutDirection"));
        	int gridPosition = 4 * row + column;
        	/* 
        	 * Grid
        	 * 8	9	10	11
        	 * 4	5	6	7
        	 * 0	1	2	3
        	 */
        	int hashed = 12 * direction + gridPosition; // My frequency counter
        	if(color == 0) {// Red
        		red[pos] += hashed + " ";
        	} else if(color == 1) { // Blue
        		blue[pos] += hashed + " ";
        	}
        	both[pos] += hashed + " ";
        }
        
        System.out.println(Arrays.toString(red));
        System.out.println(Arrays.toString(blue));
		return both;
	}

	private static void notesFrequencyAnalysis(Object[] notes) {
		int redNoteFrequency[] = new int[108];
        int blueNoteFrequency[] = new int[108];
        int bombFrequency[] = new int[108];
        /*
         * LineLayer = Row (0,1,2) Bottom -> Top
         * LineIndex = Column (0,1,2,3) Left -> Right
         * Type = Color (0 Red, 1 Blue, 3 Bomb)
         * Time = Beat Reaches Player
         * CutDirection 0 -> 8
         * 0 UP, 1 DOWN, 2 LEFT, 3 RIGHT, 4 UP LEFT
         * 5 UP RIGHT, 6 DOWN LEFT, 7 DOWN RIGHT
         * 8 ANY
         */
        for(int i=0; i < notes.length;i++) {
        	JSONObject curNote = (JSONObject) notes[i];
//        	System.out.println(curNote.toString());
        	int row = Long2Int(curNote.get("_lineLayer"));
        	int column = Long2Int(curNote.get("_lineIndex"));
        	int color = Long2Int(curNote.get("_type"));
        	//double time = (Double) curNote.get("_time"); // Not necessary now
        	int direction = Long2Int(curNote.get("_cutDirection"));
        	int gridPosition = 4 * row + column;
        	/* 
        	 * Grid
        	 * 8	9	10	11
        	 * 4	5	6	7
        	 * 0	1	2	3
        	 */
        	int hashed = 12 * direction + gridPosition; // My frequency counter
        	if(color == 0) {// Red
        		redNoteFrequency[hashed] += 1;
        	} else if(color == 1) { // Blue
        		blueNoteFrequency[hashed] += 1;
        	} else if(color == 3) { // Bomb
        		bombFrequency[hashed] += 1;
        	} else {
        		System.out.println("FUCK");
        	}
        }
        // Prints out the mapping based on the type of note
        for(int i = 0; i < 8;i++) {
        	for(int r = 0; r < 3; r++) {
        		for(int c = 0; c < 4;c++) {
        			int index = i * 12 + 4 * r + c;
        			System.out.print(redNoteFrequency[index] + " (" + blueNoteFrequency[index] + ") ");
        		}
        		System.out.println();
        	}
        	System.out.println("\n");
        }
        System.out.println(Arrays.toString(redNoteFrequency));
        System.out.println(Arrays.toString(blueNoteFrequency));
        System.out.println(Arrays.toString(bombFrequency));
		
	}

	private static int Long2Int(Object object) {
		Long a = (Long) object;
		return a.intValue();
	}

}
