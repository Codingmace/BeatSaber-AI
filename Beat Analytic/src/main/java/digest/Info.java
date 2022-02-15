package digest;


class Info {
	
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
	public Object _customData;
	public bms []_difficultyBeatmapSets;
	
	public String getVersion() {
		return _version;
	}
	public String getSong() {
		return _songName;
	}
	public String getSubSong() {
		return _songSubName;
	}
	public String getSongAuthor() {
		return _songAuthorName;
	}
	public String getLevelAuthor() {
		return _levelAuthorName;
	}
	public long getBPM() {
		return _beatsPerMinute;
	}
	public long getSongOffset() {
		return _songOffset;
	}
	public long getShuffle() {
		return _shuffle;
	}
	public double getShufflePeriod() {
		return _shufflePeriod;
	}
	public long getPreviewStartTime() {
		return _previewStartTime;
	}
	public long getPreviewDuration() {
		return _previewDuration;
	}
	public String getSongFilename() {
		return _songFilename;
	}
	public String getCoverImage() {
		return _coverImageFilename;
	}
	public String getEnviorment() {
		return _environmentName;
	}
	public String getSongTimeOffset() {
		return _songTimeOffset;
	}


}