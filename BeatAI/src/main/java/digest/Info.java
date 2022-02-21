package digest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Info {
	private String _version;
	private String _songName;
	private String _songSubName;
	private String _songAuthorName;
	private String _levelAuthorName;
	private long _beatsPerMinute;
	private long _songOffset;
	private long _shuffle;
	private double _shufflePeriod;
	private long _previewStartTime;
	private long _previewDuration;
	private String _songFilename;
	private String _coverImageFilename;
	private String _environmentName;
	private String _songTimeOffset;
	private Object _customData;
	private bms[] _difficultyBeatmapSets;

	public String get_version() {
		return _version;
	}

	public void set_version(String _version) {
		this._version = _version;
	}

	public String get_songName() {
		return _songName;
	}

	public void set_songName(String _songName) {
		this._songName = _songName;
	}

	public String get_songSubName() {
		return _songSubName;
	}

	public void set_songSubName(String _songSubName) {
		this._songSubName = _songSubName;
	}

	public String get_songAuthorName() {
		return _songAuthorName;
	}

	public void set_songAuthorName(String _songAuthorName) {
		this._songAuthorName = _songAuthorName;
	}

	public String get_levelAuthorName() {
		return _levelAuthorName;
	}

	public void set_levelAuthorName(String _levelAuthorName) {
		this._levelAuthorName = _levelAuthorName;
	}

	public long get_beatsPerMinute() {
		return _beatsPerMinute;
	}

	public void set_beatsPerMinute(long _beatsPerMinute) {
		this._beatsPerMinute = _beatsPerMinute;
	}

	public long get_songOffset() {
		return _songOffset;
	}

	public void set_songOffset(long _songOffset) {
		this._songOffset = _songOffset;
	}

	public long get_shuffle() {
		return _shuffle;
	}

	public void set_shuffle(long _shuffle) {
		this._shuffle = _shuffle;
	}

	public double get_shufflePeriod() {
		return _shufflePeriod;
	}

	public void set_shufflePeriod(double _shufflePeriod) {
		this._shufflePeriod = _shufflePeriod;
	}

	public long get_previewStartTime() {
		return _previewStartTime;
	}

	public void set_previewStartTime(long _previewStartTime) {
		this._previewStartTime = _previewStartTime;
	}

	public long get_previewDuration() {
		return _previewDuration;
	}

	public void set_previewDuration(long _previewDuration) {
		this._previewDuration = _previewDuration;
	}

	public String get_songFilename() {
		return _songFilename;
	}

	public void set_songFilename(String _songFilename) {
		this._songFilename = _songFilename;
	}

	public String get_coverImageFilename() {
		return _coverImageFilename;
	}

	public void set_coverImageFilename(String _coverImageFilename) {
		this._coverImageFilename = _coverImageFilename;
	}

	public String get_environmentName() {
		return _environmentName;
	}

	public void set_environmentName(String _environmentName) {
		this._environmentName = _environmentName;
	}

	public String get_songTimeOffset() {
		return _songTimeOffset;
	}

	public void set_songTimeOffset(String _songTimeOffset) {
		this._songTimeOffset = _songTimeOffset;
	}

	public Object get_customData() {
		return _customData;
	}

	public void set_customData(Object _customData) {
		this._customData = _customData;
	}

	public bms[] get_difficultyBeatmapSets() {
		return _difficultyBeatmapSets;
	}

	public void set_difficultyBeatmapSets(bms[] _difficultyBeatmapSets) {
		this._difficultyBeatmapSets = _difficultyBeatmapSets;
	}
}

@Getter
@Setter
class bms {
	private String _beatmapCharacteristicName;
	private beatmap[] _difficultyBeatmaps;

	public String getMapCharacteristicName() {
		return _beatmapCharacteristicName;
	}

	public void setMapCharacteristicName(String charName) {
		_beatmapCharacteristicName = charName;
	}

	public beatmap[] get_difficultyBeatmaps() {
		return _difficultyBeatmaps;
	}

	public void set_difficultyBeatmaps(beatmap[] _difficultyBeatmaps) {
		this._difficultyBeatmaps = _difficultyBeatmaps;
	}
}

@Getter
@Setter
class beatmap {
	private String _difficulty;
	private String _difficultyRank;
	private String _beatmapFilename;
	private String _noteJumpMovementSpeed;
	private String _noteJumpStartBeatOffset;
	private CustomData _customData;

	public String get_Difficulty() {
		return _difficulty;
	}

	public void set_Difficulty(String difficulty) {
		_difficulty = difficulty;
	}

	public String getDifficultyRank() {
		return _difficultyRank;
	}

	public void setDifficultyRank(String difficultyRank) {
		_difficultyRank = difficultyRank;
	}

	public String get_BeatmapFilename() {
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

	public CustomData get_customData() {
		return _customData;
	}

	public void set_customData(CustomData _customData) {
		this._customData = _customData;
	}
}

@Getter
@Setter
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