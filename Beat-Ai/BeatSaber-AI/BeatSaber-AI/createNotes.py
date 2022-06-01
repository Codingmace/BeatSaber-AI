# TODO
# Read in the file and find beat
# Find the pitches
# Write the Notes corresponding
# Write for all difficulties.
# Placement is based on threshold and Sequence is still to be determined
# https://bsmg.wiki/mapping/basic-mapping.html#timing-notes

import librosa
import numpy as np
import librosa.display
import json

def readTemplate(templateName):
    # Opening JSON file
    with open(templateName, 'r') as openfile:
        # Reading from json file
        json_object = json.load(openfile)
    return json_object

def writeNotes(timing, double_hit):
    # The buffer note added is the first one
    baseNote = readTemplate("resources/noteTemplate.json")
    notes = []
    isRed = True
    for i in range(1, len(timing)):
        if double_hit[i]:
            isRed = False
            redNote = baseNote.copy()
            blueNote = baseNote.copy()
            redNote['_time'] = timing[i]
            blueNote['_time'] = timing[i]
            redNote['_lineLayer'] = 1
            blueNote['_type'] = 1
            redNote['_cutDirection'] = 8
            blueNote['_cutDirection'] = 8
            notes.append(redNote)
            notes.append(blueNote)
            # Write a double hit 
        else:
            if isRed:
                redNote = baseNote.copy()
                redNote['_time'] = timing[i]
                redNote['_lineLayer'] = 1
                redNote['_cutDirection'] = 8
                notes.append(redNote)
                # Write a red hit
            else:
                blueNote = baseNote.copy()
                blueNote['_time'] = timing[i]
                blueNote['_type'] = 1
                blueNote['_cutDirection'] = 8
                notes.append(blueNote)
                # Write a blue hit
            isRed = not isRed
    return notes        

def writeJson(curFold, json_data, filename):
    # Serialize the json
    json_object = json.dumps(json_data)
    with open(curFold + filename, "w") as outfile:
        outfile.write(json_object)
# import matplotlib.pyplot as plt

#y, sr = librosa.load("./tmp/whenIGrowUp.ogg")
# y, sr = librosa.load("./resources/shutup.egg")
# y, sr = librosa.load("./resources/TheNights.egg")
y, sr = librosa.load("./resources/legend.egg")

#y_percussive = librosa.effects.percussive(y)
tempo, beats = librosa.beat.beat_track(y=y, sr=sr, units= "time")
tempo = int(tempo)
print(tempo)
genre = ""
guessGenre = True
# Categorize it
if guessGenre:
    if tempo < 75:
        genre = "IDK"
    elif tempo < 85: # Slow dub/reggae: 75 BPM
        genre = "Slow Dub/raggae"
    elif tempo < 93:# Fast dub/reggae: 90 BPM
        genre = "Fast Dub/raggae"
    elif tempo < 105: # Deep house: 96 BPM
        genre = "Deep House"
    elif tempo < 118: # Slow house: 120 BPM
        genre = "Slow House"
    elif tempo < 130: # Electro house: 130 BPM
        genre = "Electro House"
    elif tempo < 137: # Trance: 135 BPM
        genre = "Trance"
    elif tempo < 140:# Dubstep: 140 BPM
        genre = "Dubstep"
    elif tempo < 145:# Hard house: 145 BPM
        genre = "Hard House"
    elif tempo < 160:# Jungle: 160 BPM
        genre = "Jungle"
    elif tempo < 175:# Drum&Bass: 175 BPM
        genre = "Drum and Bass"
    elif tempo < 190: # Gabber: 190 BPM
        genre = "Gabber"
    else:
        genre = "TOO FAST"
   
difficultySetting = 2 # Hard
'''
y_harm, y_perc = librosa.effects.hpss(y)
librosa.display.waveshow(y_harm, sr=sr, color='b', alpha=0.5, label='Harmonic')
librosa.display.waveshow(y_perc, sr=sr, color='r', alpha=0.5, label='Percussive')
'''
# import matplotlib.pyplot as plt
# hop_length = 256
hop_length = 512
onset_env = librosa.onset.onset_strength(y=y, sr=sr,
                                         aggregate=np.median)
# times = librosa.times_like(onset_env, sr=sr, hop_length=hop_length)
M = librosa.feature.melspectrogram(y=y, sr=sr, hop_length=hop_length)
onsets = librosa.onset.onset_detect(y=y,sr=sr,onset_envelope=onset_env) # Each thing is in frames so need frames to beats or something
onset_1 = librosa.onset.onset_detect(y=y,sr=sr,onset_envelope=onset_env,units="time") # Each thing is in frames so need frames to beats or something
# onsets_2 = librosa.onset.onset_detect(y=y,sr=sr) # try without
beat_1 = 60 / tempo # Seconds of a beat and assuming we have a 4/4 time signature
onset_convert = [0.0]
double_hit = [False]
myI = 0
# Turning into a beat track
for i in range(len(onset_1)):
    curBeat = onset_1[i] / beat_1
    decimal = curBeat % 1
    isDoubleHit = False
    if decimal > .48 and decimal < .52:
        curBeat = (curBeat / 1 ) - decimal + .5
        isDoubleHit = True
    elif decimal < .02:
        curBeat = curBeat - decimal
        isDoubleHit = True
    elif decimal > .98:
        curBeat = (curBeat / 1) - decimal + 1
        isDoubleHit = True
    else: # Verify Based on hard leveling or below. Expert to come
        if decimal < .24:
            curBeat = curBeat - decimal
        elif decimal < .41: 
            curBeat = curBeat - decimal + .33
        elif decimal < .58:
            curBeat = curBeat - decimal + .5
        elif decimal < .74:
            curBeat = curBeat - decimal + .66
        else:
            curBeat = curBeat - decimal + 1
    if onset_convert[myI] == curBeat: # Same note
        double_hit[myI] = True # Check if this is true before hand for a huge smash piece
    else:
        onset_convert.append(curBeat)
        double_hit.append(isDoubleHit)
        myI += 1

# Determine which beat is red and which one is blue
# Offset each beat to 5 seconds after start
'''
for i in range(0, len(onset_convert)):
    onset_convert[i] = onset_convert[i] + 5
'''

jsonObject = readTemplate('resources/levelTemplate.json')
jsonObject['_notes'] = writeNotes(onset_convert, double_hit)
writeJson('./tmp/', jsonObject,"Hard.dat")
print("At the end")
# Now that have all the beats, write the song with notes

# Turn both to seconds
# Get the list of what and can downscale or different threshold
# Round and compare with the true one.
# Change the threshold



#plt.plot(times, librosa.util.normalize(onset_env),
#         label='Onset strength')
'''
plt.vlines(times[beats], 0, 1, alpha=0.5, color='r',
           linestyle='--', label='Beats')
'''
#plt.legend()

#plt.show()
# print(beats)