
public class Note {

	public int color; // 0 = Red, 1 = Blue, 2 = Obstacle, 3 = Bomb
	public int direction; // Same as setup
	public int row;
	public int col;
	
	public Note(int color, int direction, int row, int col) {
		this.color = color;
		this.direction = direction;
		this.row = row;
		this.col = col;
	}
	public void setNote(int color) {
		this.color = color;
	}
	public void setDirection(int direction) {
		this.direction = direction;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public void setColumn(int col) {
		this.col = col;
	}
}
