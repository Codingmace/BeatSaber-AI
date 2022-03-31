'''
Description
From all the data grabbed, it verifies that it is good to be able to process later on
'''
import os
import re
import shutil

def locateInfo(curDir):
    if os.path.exists(curDir + "info.dat"):
        return curDir + "info.dat"
    elif os.path.exists(curDir + "Info.dat"):
        return curDir + "Info.dat"
    else:
        return curDir + "info"


if __name__ == "__main__":
    g = open("results.csv", "w")
    g.write("Filepath,setBpm,Fail Reason\n")
    defaultPath = "Z:\\BS Files\\BeastSaber\\BeastSaber Pt 3\\"
    dirs = os.listdir(defaultPath)
    tot = len(dirs)
    print("Going to read " + str(tot) + " entries")
    for i in range(0, len(dirs)):
        deleteFolder = False
        curDir = defaultPath + dirs[i] + "\\"
        infoFile = locateInfo(curDir)
        if "dat" in infoFile:  # Get infoFile Location
            filetext = ""
            try:
                with open(infoFile, "r") as f2:
                    filetext = f2.read()
            except Exception:
                g.write(curDir + ",0,File Reading Error\n")
                deleteFolder = True
            patternBeat = '_beatsPerMinute": (\d+),'  # Could be a decimal
            match = re.findall(patternBeat, filetext)
            fileBpm = 0
            if len(match) > 0:  # BPM found
                fileBpm = int(match[0])
            else:
                g.write(curDir + ",0,No BPM in File\n")
                deleteFolder = True
            pattern = '_songFilename":[\s]+"([A-Za-z0-9 ]+.\w+)"'
            matches = re.findall(pattern, filetext)
            if len(matches) == 0:  # Can try again without the space
                g.write(curDir + "," + str(fileBpm) + ",0,No Audio File Found\n")
                deleteFolder = True
            if i % 1000 == 0:
                print("Current Percentage", (i / tot) * 100)
            if not deleteFolder:
                g.write(curDir + "," + str(fileBpm) + ",In Range\n")
        else:
            g.write(curDir + ",0,Info File not found\n")
            deleteFolder = True
        if deleteFolder:
            try:
                shutil.rmtree(curDir, ignore_errors=True)
            except Exception:
                print("DELETING " + curDir + " is UNSUCCESSFUL")
