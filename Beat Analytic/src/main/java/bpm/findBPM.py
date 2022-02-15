import argparse
import os

import librosa
import numpy as np
import soundfile as sf
import torch
from tqdm import tqdm

from lib import dataset
from lib import nets
from lib import spec_utils
from lib import utils


def main():
    p = argparse.ArgumentParser()
    p.add_argument('--input', '-i', required=True)
    p.add_argument('--sr', '-r', type=int, default=44100)
    args = p.parse_args()
    

    y, sr = librosa.load(args.input)
    onset_env = librosa.onset.onset_strength(y=y, sr=sr)
    tempo = librosa.beat.tempo(onset_envelope=onset_env, sr=sr)
    print(tempo)
    
    y, sr = librosa.load(args.input)
    tempo, beats = librosa.beat.beat_track(y=y,sr=args.sr)
    print(tempo)
    first_beat_time, last_beat_time = librosa.frames_to_time((beats[0],beats[-1]),sr=sr)
    print(60/((last_beat_time-first_beat_time)/(len(beats)-1))) # Closer to the actual amount
    print("Hell Yeah")
    
    print('loading wave source...', end=' ')
    X, sr = librosa.load(args.input, sr=args.sr, mono = False, dtype=np.float32, res_type='kaiser_fast')
    basename = os.path.splitext(os.path.basename(args.input))[0]
#    print('done')

    if X.ndim == 1:
        # mono to stereo
        X = np.asarray([X, X])

    print("Made it to the end")

if __name__ == '__main__':
    main()
