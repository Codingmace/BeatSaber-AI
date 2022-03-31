
import librosa
import os
import re

def getBpm(inputFile):
    '''
    baseFile = "Z:\\BS Files\\BeastSaber\\BeastSaber Pt 1\\1a0\\"
    filename = "runaway.egg"
#    baseFile = 'G:\\Git Projects\\BeatSaber-AI\\DDRtoBS\\DDRConversion\\DDRConversion\\_Mine\\'
#    filename = 'SevenNationArmy_Instruments.wav'
    filepath = baseFile + filename
    '''
    filepath = inputFile


    y, sr = librosa.load(filepath)
    ''' Not working
    onset_env = librosa.onset.onset_strength(y=y, sr=sr)
    tempo = librosa.beat.tempo(onset_envelope=onset_env, sr=sr)
    print(tempo)
    '''
    
#    y, sr = librosa.load(filepath)
    tempo, beats = librosa.beat.beat_track(y=y,sr=sr)
    print(tempo)
    first_beat_time, last_beat_time = librosa.frames_to_time((beats[0],beats[-1]),sr=sr)
    myBpm = (60/((last_beat_time-first_beat_time)/(len(beats)-1))) # Closer to the actual amount
    return myBpm
#    print("Hell Yeah")
    
    '''
    print('loading wave source...', end=' ')
    X, sr = librosa.load(args.input, sr=args.sr, mono = False, dtype=np.float32, res_type='kaiser_fast')
    basename = os.path.splitext(os.path.basename(args.input))[0]
#    print('done')

    if X.ndim == 1:
        # mono to stereo
        X = np.asarray([X, X])
        '''
#    print("Made it to the end")

if __name__ == '__main__':
    g = open("results.txt", "w")
    defaultPath = "Z:\\BS Files\\BeastSaber\\BeastSaber Pt 1\\"
    dirs = os.listdir(defaultPath)
    curDir = defaultPath + dirs[0]
#    print(dirs[0])
    infoFile = curDir + "\\info.dat"
    textfile = open(infoFile, 'r')
    filetext = textfile.read()
    textfile.close()
    print(filetext)
    patternBeat = "_beatsPerMinute\": (\d+),"
    match = re.findall(patternBeat, filetext)
    fileBpm = match[0]
    print(fileBpm)
    pattern = "_songFilename\": \"(\w+).(\w+)\""
    matches = re.findall(pattern, filetext)[0]
    songName = matches[0] + "." + matches[1]
    print(songName)
#    print(matches)
#    print(len(dirs))
    inputFile = curDir + "\\" + songName
    myBpm = getBpm(inputFile)
    if fileBpm > myBpm - 1.5 and fileBpm < myBpm + 1.5:
        print("In Range")
    else:
        g.write(curDir + "\n")



    # librosa
    # http://craffel.github.io/mir_eval/#module-mir_eval.beat
    # https://jams.readthedocs.io/en/stable/namespace.html