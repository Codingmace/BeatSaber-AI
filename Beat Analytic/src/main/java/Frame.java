import java.util.ArrayList;

public class Frame {

	public ArrayList<Note> notes;
	public double time;
	public Frame() {
		notes = new ArrayList<Note>();
		time = -1;
	}
	
	public Frame(double time) {
		notes = new ArrayList<Note>();
		this.time = time;
	}
	
	public void addNote(Note note) {
		notes.add(note);
	}
	
	public void setTime(double time) {
		this.time = time;
	}
	
	public boolean removeNote(Note note) {
		for(int i =0; i < notes.size();i++) {
			if(sameNote(notes.get(i), note)) {
				notes.remove(i);
				return true;
			}
		}
		return false;
	}
	
	private boolean sameNote(Note note, Note note2) {
		if(note.col == note2.col && note.row == note2.row) {
			if(note.direction == note2.direction && note.color == note2.color) {
				return true;
			}
		}
		return false;
	}
}
