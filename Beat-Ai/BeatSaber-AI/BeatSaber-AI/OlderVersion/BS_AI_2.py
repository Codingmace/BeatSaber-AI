import librosa
import os
import re

def getBpm(inputFile):
    y, sr = librosa.load(inputFile)
    tempo, beats = librosa.beat.beat_track(y=y,sr=sr)
#    print(tempo)
    first_beat_time, last_beat_time = librosa.frames_to_time((beats[0],beats[-1]),sr=sr)
    myBpm = (60/((last_beat_time-first_beat_time)/(len(beats)-1))) # Closer to the actual amount
#    print(myBpm)
    return int(myBpm)
    
def locateInfo(curDir):
    if os.path.exists(curDir + "info.dat"):
        return curDir + "info.dat"
    elif os.path.exists(curDir + "Info.dat"):
        return curDir + "Info.dat"
    else:
        return curDir + "info"

def writingStats(text):
    text = ""


if __name__ == '__main__':
    g = open("results.csv", "a")
    g.write("Filepath,setBpm,myBpm,Fail Reason\n")
    defaultPath = "Z:\\BS Files\\BeastSaber\\BeastSaber Pt 1\\"
    dirs = os.listdir(defaultPath)
    tot = len(dirs)
#    i = 0
    for i in range(24, len(dirs)):
#    for a in dirs:
#        curDir = defaultPath + a + "\\"
        curDir = defaultPath + dirs[i] + "\\"
        infoFile = locateInfo(curDir)
#        infoFile = curDir + "info.dat"
        if "dat" in infoFile: # Get infoFile Location
#        if os.path.exists(infoFile):
            filetext = ""
            try:
                with open(infoFile, 'r') as f2:
                    filetext = f2.read()
            except Exception:
                g.write(curDir + ",0,0,File Reading Error\n")
    #        print(filetext)
            patternBeat = "_beatsPerMinute\": (\d+),"
            match = re.findall(patternBeat, filetext)
            fileBpm = 0
            if (len(match) > 0): # BPM found
                fileBpm = int(match[0])            
#            print(fileBpm)
            pattern = "_songFilename\": \"(\w+).(\w+)\""
            matches = re.findall(pattern, filetext)
            if(len(matches) == 0):
                g.write(curDir + "," + str(fileBpm) + ",0,No Audio File Found\n")
#                g.write(curDir + " manual Fix needed\n")
                continue
#                print(filetext)
#                input()
            else:
                matches = matches[0]
            songName = matches[0] + "." + matches[1]
    #        print(songName)
            inputFile = curDir + songName
            myBpm = 120
            try:
                myBpm = getBpm(inputFile)
            except Exception:
                g.write(curDir +"," + str(fileBpm) + "," + str(myBpm) + ",Exception thrown\n")
            if not (fileBpm > myBpm - 2 and fileBpm < myBpm + 2): # Check for measure Issue
                if fileBpm > myBpm:
                    myBpm *= 2
                elif myBpm > fileBpm:
                    myBpm /= 2
                if not (fileBpm > myBpm - 1 and fileBpm < myBpm + 1):
                    g.write(curDir + "," + str(fileBpm) + "," + str(myBpm) + ",Change measure Failed\n")
#            else: # Success
#                 g.write(curDir + "," + str(fileBpm) + "," + str(myBpm) + ",In Range\n")
            if ((i / tot) * 100) % 10 == 0:
                print("Current Percentage", (i/tot) * 100)
                break
        else:
            g.write(curDir + ",0,0,Info File not found\n")
