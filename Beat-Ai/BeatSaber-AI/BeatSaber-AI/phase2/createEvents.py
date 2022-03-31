# TODO
# Have not even started one bit but easy once get the other part done


# https://bsmg.wiki/mapping/map-format.html#events-2

# Get the Melograph
# Translate the graph to the events
# Write the results to the file

# Onset Can create a idea of where to put the beats
# https://librosa.org/doc/latest/generated/librosa.beat.beat_track.html?msclkid=bd66166baebe11ecabf9d7d7420bed35

# Pitches used for the movement going through
# https://librosa.org/doc/latest/generated/librosa.decompose.nn_filter.html


# https://github.com/ItsOrius/LiteMapper#readme
'''
You may be wondering, how do we manage to incentivize more creative mapping? Rather than just placing events based on time and location, we run a multitude of different checks to decide on where to place our events.

Beats with a high pace (more than 1 block per beat) receive a red center light, beats with a medium pace (at least 2 block per two beats) receive a blue center light, and beats with a slow pace (one block or less per two beats) receive a fading blue center light.
A change in pace results in a ring zoom.
Timestamps with more than one block at a time results in a ring rotation.
Beats with more than one block per two beats receive a ring light every beat.
Any-direction blocks and bombs result in the back lights turning on and the center lights turning off.
The laser opposite of the last (starting on the left) will flash, but the other laser will deactivate.
Both lasers activate on double notes with two beats or more of padding
'''