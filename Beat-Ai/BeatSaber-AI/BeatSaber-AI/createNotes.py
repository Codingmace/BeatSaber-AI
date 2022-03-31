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

import matplotlib.pyplot as plt

y, sr = librosa.load("SevenNationArmy.egg")

tempo, beats = librosa.beat.beat_track(y=y, sr=sr)
'''
y_harm, y_perc = librosa.effects.hpss(y)
librosa.display.waveshow(y_harm, sr=sr, color='b', alpha=0.5, label='Harmonic')
librosa.display.waveshow(y_perc, sr=sr, color='r', alpha=0.5, label='Percussive')
'''
import matplotlib.pyplot as plt
hop_length = 512
onset_env = librosa.onset.onset_strength(y=y, sr=sr,
                                         aggregate=np.median)
times = librosa.times_like(onset_env, sr=sr, hop_length=hop_length)
M = librosa.feature.melspectrogram(y=y, sr=sr, hop_length=hop_length)

print(librosa.onset.onset_detect(y=y,sr=sr,onset_envelope=onset_env)) # Each thing is in frames so need frames to beats or something
plt.plot(times, librosa.util.normalize(onset_env),
         label='Onset strength')
plt.vlines(times[beats], 0, 1, alpha=0.5, color='r',
           linestyle='--', label='Beats')
plt.legend()

plt.show()
print(beats)
print(times)