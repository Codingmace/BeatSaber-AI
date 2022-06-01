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
    try:
        with sf.SoundFile(testAudioFile) as f:
            return f.frames / f.samplerate
    except:
        return 61
#        duration = f.frames / f.samplerate
#    return duration

# import ffmpeg
# Narrow down based on few criteria
def songLength(): # Cut by song length
    myCwd = "Z:\\BS Files\\BeastSaber\\"
    os.chdir(myCwd)
    g = open("Log_3.txt", "a")
#    os.chdir("BeastSaber Pt 3\\")
    #testAudioFile = "Z:\\BS Files\\BeastSaber\\BeatTest\\12e45\\song.egg"
    files = os.listdir() # All of the levels

    changePreview = False
    for levelFold in files:
        g.write("Record for id: " + levelFold + "\n")
        curFiles = os.listdir(levelFold)
        if "info.dat" not in curFiles:
            g.write("Renaming Info.dat to info.dat\n")
            os.rename(levelFold + "\\Info.dat", levelFold + "\\info.dat")
        changed =  False
        data = readInfo(levelFold + "\\info.dat")
        version = data['_version']
        if "2.0.0" not in version:
            g.write("Updating from version " + version + " to version 2.0.0\n")
            data['_version'] = "2.0.0"
            changed = True
        if changePreview:
            dur = data['_previewDuration']
            startTime = data['_previewStartTime']
            if startTime == 12 and dur == 10:
                print("Default Values, Need to change")
        songFileName = data['_songFilename']
        duration = getSongLength(levelFold + "\\" + songFileName)
        if not ("song.egg" in songFileName):
            kind = filetype.guess(levelFold + "\\" + songFileName)
    #        print(kind)
            if "ogg" in kind.EXTENSION:
                changed = True
                os.rename(levelFold + "\\" + songFileName, levelFold + "\\song.egg")
                g.write("Renaming song from " + songFileName + " to song.egg\n")
                data['_songFilename'] = "song.egg"
            else:
                g.write("Found song of filetype " + kind.EXTENSION + " which requires Conversion\n")
    #        print(songFileName)
        sx = data['_coverImageFilename']
        if "cover" not in sx:
            kind = filetype.guess(levelFold + "\\" + sx)
            if kind is not None:
                newName = "cover." + kind.EXTENSION
                os.rename(levelFold + "\\" + sx , levelFold + "\\" + newName)
                g.write("Changing file " + sx + " to " + newName + "\n")
                changed = True
                data['_coverImageFilename'] = newName
            else:
                g.write("Not able to deal with that file. Breaking program\n")
        if duration == 61:
            g.write("Duration Threw an error but deciding to keep the file. Can screw this up later\n")
        if duration < 60:
            g.write("Deleting because duration (" + str(int(duration)) + ") < 60\n")
            shutil.rmtree(levelFold)
        elif changed:
            writeJson(levelFold, data)
            g.write("Writing new info.dat file\n")

def ratings(): # Cut down by ratings
    myCwd = "Z:\\BS Files\\BeastSaber\\"
    os.chdir(myCwd)
    f = open("database_clean.csv", "r")
    g = open("database_revised.csv","w")
    h = open("logs.txt", "a")
    header = f.readline()
    g.write(header)
    g.flush()
    lines = f.readlines()
    os.chdir(myCwd)
    bs1 = os.listdir("./BeastSaber Pt 1")
    bs2 = os.listdir("./BeastSaber Pt 2")
    bs3 = os.listdir("./BeastSaber Pt 3")
    # print(len(bs1), len(bs2), len(bs3))
    for line in lines:
        split = line.split(",")
        curId = split[0]
        percentage = split[8]
        upvotes = split[9]
        downvotes = split[10]
        deletedFolder = False
        if int(upvotes) >= 25 or int(downvotes) >= 50:
            if float(percentage) <= .34:
                deletedFolder = True
                '''
                if curId in bs1:
                    h.write("Deleted BeatSaber Pt 1\\" + curId + " upVotes " + upvotes + " downvotes "+ downvotes + " percentage " + percentage +"\n")
                    h.flush()
                elif curId in bs2:
                    print("Found ID in Pt 2")
                elif curId in bs3:
                    print("Found ID in Pt 3")
                else:
                    deletedFolder = False
    #                print("DIDNT find the ID")
    '''
        elif int(upvotes) < 25:
            deletedFolder = True
            '''
            if curId in bs1:
                print("Found ID in Pt 1")
#                shutil.rmtree("BeatSaber Pt 1\\" + curId)
            elif curId in bs2:
                print("Found ID in Pt 2")
            elif curId in bs3:
                print("Found ID in Pt 3")
            else:
                deletedFolder = False
#                print("DIDNT find the ID")
'''
        if not deletedFolder:
            g.write(line)
            g.flush()
        else:
            if curId in bs1:
                try:
                    shutil.rmtree("BeastSaber Pt 1\\" + curId)
                    h.write("Deleted BeastSaber Pt 1\\" + curId + " upVotes " + upvotes + " downvotes "+ downvotes + " percentage " + percentage +"\n")
                except:
                    h.write("Could Not delete BeastSaber Pt 1\\" + curId + "\n")
            elif curId in bs2:
                try:
                    shutil.rmtree("BeastSaber Pt 2\\" + curId)
                    h.write("Deleted BeastSaber Pt 2\\" + curId + " upVotes " + upvotes + " downvotes "+ downvotes + " percentage " + percentage +"\n")
                except:
                    h.write("Could Not delete BeastSaber Pt 2\\" + curId + "\n")
            elif curId in bs3:
                try:
                    shutil.rmtree("BeastSaber Pt 3\\" + curId)
                    h.write("Deleted BeastSaber Pt 3\\" + curId + " upVotes " + upvotes + " downvotes "+ downvotes + " percentage " + percentage +"\n")
                except:
                    h.write("Could Not delete BeastSaber Pt 3\\" + curId + "\n")
            h.flush()
# ratings()
