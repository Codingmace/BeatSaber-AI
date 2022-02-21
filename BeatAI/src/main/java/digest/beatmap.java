package digest;

class beatmap {
	private String _difficulty;
	private String _difficultyRank;
	private String _beatmapFilename;
	private String _noteJumpMovementSpeed;
	private String _noteJumpStartBeatOffset;
	public CustomData _customData;
	
	public String getDifficulty() { 
		return _difficulty;
	}
	public void setDifficulty(String difficulty) {
		_difficulty = difficulty;
	}
	public String getDifficultyRank() {
		return _difficultyRank;
	}
	public void setDifficultyRank(String difficultyRank) {
		_difficultyRank = difficultyRank;
	}
	public String getBeatmapFilename() {
		return _beatmapFilename;
	}
	public void setBeatmapFilename(String beatmapFilename) {
		_beatmapFilename = beatmapFilename;
	}
	public String getNoteJumpMoveSpeed() {
		return _noteJumpMovementSpeed;
	}
	public void setNoteJumpMoveSpeed(String noteJumpSpeed) {
		_noteJumpMovementSpeed = noteJumpSpeed;
	}
	public String getNoteJumpStart() {
		return _noteJumpStartBeatOffset;
	}
	public void setNoteJumpStart(String offset) {
		_noteJumpStartBeatOffset = offset;
	}
}