import os
# import audioread
import soundfile as sf
import json
import shutil
import filetype

def readInfo(infoFile):
    # Opening JSON file
    with open(infoFile, 'r') as openfile:
        # Reading from json file
        json_object = json.load(openfile)
    return json_object

def writeJson(curFold, json_data):
    # Serialize the json
    json_object = json.dumps(json_data)
    with open(curFold + "\\info.dat", "w") as outfile:
        outfile.write(json_object)

def getSongLength(testAudioFile):
 #   duration = -1
#    print(os.getcwd())
    with sf.SoundFile(testAudioFile) as f:
        return f.frames / f.samplerate
#        duration = f.frames / f.samplerate
#    return duration

# import ffmpeg
# Narrow down based on few criteria
myCwd = "Z:\\BS Files\\BeastSaber\\BeatTest\\"
os.chdir(myCwd)
g = open("Log.txt", "a")
testAudioFile = "Z:\\BS Files\\BeastSaber\\BeatTest\\12e45\\song.egg"
files = os.listdir() # All of the levels
for levelFold in files:
    curFiles = os.listdir(levelFold)
    if "info.dat" in curFiles:
        print("info.dat found")
        # No need to rename
    else:
        print("renaming Info.dat")
        # Rename file
        os.rename(levelFold + "\\Info.dat", levelFold + "\\info.dat")
    changed =  False
    data = readInfo(levelFold + "\\info.dat")
    version = data['_version']
    if "2.0.0" not in version:
#        print("Updating Version")
        data['_version'] = "2.0.0"
        changed = True
    print(data['_previewDuration'])
    songFileName = data['_songFilename']
    duration = getSongLength(levelFold + "\\" + songFileName)
    if not ("song.egg" in songFileName):
        kind = filetype.guess(levelFold + "\\" + songFileName)
        print(kind)
        if "ogg" in kind.EXTENSION:
            changed = True
            os.rename(levelFold + "\\" + songFileName, levelFold + "\\song.egg")
            data['_songFilename'] = "song.egg"
        print(songFileName)
    sx = data['_coverImageFilename']
    if "cover" not in sx:
        kind = filetype.guess(levelFold + "\\" + sx)
        if kind is not None:
            newName = "cover." + kind.EXTENSION
#            print(newName)
            os.rename(levelFold + "\\" + sx , levelFold + "\\" + newName)
            changed = True
            data['_coverImageFilename'] = newName
            # Rename the file

    if duration < 60:
        print("That is going to be removed")
        print("length ", duration)
    if changed:
        writeJson(levelFold, data)
#        shutil.rmtree(levelFold)

#    testAudioFile
#    print(getSongLength(testAudioFile))
#    print(getSongLength(levelFold + "\\" + sx))
#    print(getSongLength())
# Search for files in index.files
# duration = 0
# with sf.SoundFile(testAudioFile) as f:
#    duration = f.frame / f.samplerate
# Check if matches parameter
# print(f)
# Check for the size
# sf.SoundFile(testAudioFile)
'''
with audioread.audio_open("Z:\\BS Files\\BeastSaber\\BeatTest\\12e45\\song.egg") as audio:
    print(audio.read_data())
    totsec = audio.duration
#    print('Total Duration: {}:{}:{}'.format(hours, mins, seconds))
    print(totsec)
'''

# print(ffmpeg.probe('12e45/song.egg')['format']['duration'])
