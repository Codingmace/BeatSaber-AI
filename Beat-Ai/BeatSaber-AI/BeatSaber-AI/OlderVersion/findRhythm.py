from pychorus.helpers import find_and_output_chorus

inputFile = "gamble.egg"
clip_length = 12
foundClip = False
while not foundClip:
    chorus_start_sec = find_and_output_chorus(inputFile, clip_length)
    if chorus_start_sec == None:
        clip_length /= 2
    else:
        foundClip = True
    if clip_length == 1:
        foundClip
print(chorus_start_sec)