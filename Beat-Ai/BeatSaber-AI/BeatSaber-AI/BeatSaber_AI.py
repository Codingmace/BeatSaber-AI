''' 
Decription
Deep clean for the input files
Checks to verify that my BPM is matching theirs
Can either be by halftime, doubletime, or exact.
Also gets rid of files that can't read
'''
import librosa
import os
import re
import shutil

def getBpm(inputFile):
    y, sr = librosa.load(inputFile)
    tempo, beats = librosa.beat.beat_track(y=y,sr=sr)
    first_beat_time, last_beat_time = librosa.frames_to_time((beats[0],beats[-1]),sr=sr)
    myBpm = (60/((last_beat_time-first_beat_time)/(len(beats)-1))) # Closer to the actual amount
    return int(myBpm)
    
# When graphing the beats
# https://librosa.org/doc/latest/generated/librosa.beat.beat_track.html?msclkid=bd66166baebe11ecabf9d7d7420bed35
def locateInfo(curDir):
    if os.path.exists(curDir + "info.dat"):
        return curDir + "info.dat"
    elif os.path.exists(curDir + "Info.dat"):
        return curDir + "Info.dat"
    else:
        return curDir + "info"

if __name__ == '__main__':
    g = open("results.csv", "w")
    g.write("Filepath,setBpm,myBpm,Fail Reason\n")
    defaultPath = "Z:\\BS Files\\BeastSaber\\BeastSaber Pt 1\\"
    dirs = os.listdir(defaultPath)
    tot = len(dirs)
    print("Going to read " + str(tot) + " entries")
    deleteFolder = False
    for i in range(0, len(dirs)):
        curDir = defaultPath + dirs[i] + "\\"
        infoFile = locateInfo(curDir)
        if "dat" in infoFile: # Get infoFile Location
            filetext = ""
            try:
                with open(infoFile, 'r') as f2:
                    filetext = f2.read()
            except Exception:
                g.write(curDir + ",0,0,File Reading Error\n")
                deleteFolder = True
            patternBeat = "_beatsPerMinute\": (\d+)," # Could be a decimal
            match = re.findall(patternBeat, filetext)
            fileBpm = 0
            if (len(match) > 0): # BPM found
                fileBpm = int(match[0])
            pattern = "_songFilename\": \"(\w+).(\w+)\""
            matches = re.findall(pattern, filetext)
            if(len(matches) == 0): # Can try again without the space
                g.write(curDir + "," + str(fileBpm) + ",0,No Audio File Found\n")
                # deleteFolder = True
                continue
            else:
                matches = matches[0]
            songName = matches[0] + "." + matches[1]
            inputFile = curDir + songName
            myBpm = 120
            try:
                myBpm = getBpm(inputFile)
            except Exception:
                g.write(curDir +"," + str(fileBpm) + "," + str(myBpm) + ",Exception thrown\n")
                deleteFolder = True
            if not (fileBpm > myBpm - 2 and fileBpm < myBpm + 2): # Check for measure Issue
                if fileBpm > myBpm:
                    myBpm *= 2
                elif myBpm > fileBpm:
                    myBpm /= 2
                if not (fileBpm > myBpm - 2 and fileBpm < myBpm + 2):
                    g.write(curDir + "," + str(fileBpm) + "," + str(myBpm) + ",Change measure Failed\n")
                    # deleteFolder = True
            if i % 1000 == 0:
                print("Current Percentage", (i/tot) * 100)
            if not deleteFolder:
                g.write(curDir + "," + str(fileBpm) + "," + str(myBpm) + ",In Range\n")
        else:
            g.write(curDir + ",0,0,Info File not found\n")
            deleteFolder = True
        if deleteFolder:
            try:
                shutil.rmtree(curDir, ignore_errors=True)
            except Exception:
                print("DELETING " + curDir + " is UNSUCCESSFUL")
            