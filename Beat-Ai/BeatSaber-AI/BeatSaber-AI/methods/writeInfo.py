import json
import librosa
from pychorus.helpers import find_and_output_chorus

def readInfo():
    # Opening JSON file
    with open('info.json', 'r') as openfile:
        # Reading from json file
        json_object = json.load(openfile)
    return json_object

def getBpm(inputFile):
    y, sr = librosa.load(inputFile)
    tempo, beats = librosa.beat.beat_track(y=y,sr=sr)
    first_beat_time, last_beat_time = librosa.frames_to_time((beats[0],beats[-1]),sr=sr)
    myBpm = (60/((last_beat_time-first_beat_time)/(len(beats)-1))) # Closer to the actual amount
    return int(myBpm)

def refineDiff(difficulty, data):
    for i in range(0, len(difficulty)):
        if not difficulty[i]:
            data['_difficultyBeatmapSets'][0]['_difficultyBeatmaps'][i]
    return data

# Writes the JSON to info.dat
def writeJson(curFold, json_data):
    # Serialize the json
    json_object = json.dumps(json_data)
    with open(curFold + "info.dat", "w") as outfile:
        outfile.write(json_object)

def writeInfoMain():
    # Assuming the folder has song and already Written
    curFold = "./"
    data = readInfo()
    songFile = curFold + 'song.egg'
    editor = "myUsername"
    data['_songName'] = 'songName'
    data['_songAuthorName'] = 'songAuthorName'
    data['_levelAuthorName'] = editor
    data['_beatsPerMinute'] = getBpm(songFile)
    data['_shuffle'] = 0
    data['_shufflePeriod'] = 0.5
    # Finding the Chorus Part
    duration = 16
    foundClip = False
    startSecond = 0
    while not foundClip:
        chorus_start_sec = find_and_output_chorus(songFile, duration)
        if chorus_start_sec == None:
            duration /= 2
        else:
            startSecond = chorus_start_sec
            foundClip = True
        if duration == 1:
            foundClip = True
    data['_previewStartTime'] = startSecond
    data['_previewDuration'] = duration
    data['_songTimeOffset'] = 0 # Manually can set
    data['_customData']['_editors']['_lastEditedBy'] = editor # Verify this works
    diffInclude = [True,True,True,False,False]
    # Easy, Normal, Hard, Expert, ExpertPlus
    data = refineDiff(diffInclude, data)
    try:
        writeJson(curFold, data) # Writes the Info File
        print("Info file has been written")
    except Exception:
        print("Exception has happened writting info file")