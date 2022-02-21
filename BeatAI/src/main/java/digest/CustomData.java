package digest;

class CustomData {
	private String _difficultyLabel;
	private long _editorOffset;
	private long _editorOldOffset;
	private String[] _warnings;
	private String[] _information;
	private String[] _suggestions;
	private String[] _requirements;
	
	public String getDifficultyLabel() {
		return _difficultyLabel;
	}
	public void setDifficultyLabel(String difficulty) {
		_difficultyLabel = difficulty;
	}
	public long getEditorOffset() {
		return _editorOffset;
	}
	public void setEditorOffset(long offset) {
		_editorOffset = offset;
	}
	public long getEditorOldOffset() {
		return _editorOldOffset;
	}
	public void setEditorOldOffset(long offset) {
		_editorOldOffset = offset;
	}
	public String[] getWarnings() {
		return _warnings;
	}
	public void setWarnings(String warnings[]) {
		_warnings = warnings;
	}
	public String[] getInformation() {
		return _information;
	}
	public void setInformation(String[] information) {
		_information = information;
	}
	public String[] getSuggestions() {
		return _suggestions;
	}
	public void setSuggestions(String[] suggestions) {
		_suggestions = suggestions;
	}
	public String[] getRequirements() {
		return _requirements;
	}
	public void setRequirements(String[] requirement) {
		_requirements = requirement;
	}
}