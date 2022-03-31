
import json
import array
import numpy as np

class Event:
    def __init__(self, ti, ty, va):
        self.eTime = ti
        self.eType = ty
        self.eValue = va

class Note:

    def __init__(self, ti, li, ll, ty, cud):
        self.nTime = ti
        self.nLineIndex = li
        self.nLineLayer = ll
        self.nType = ty
        self.nCutDirection = cud

    def __init__(self, data):
        self.nTime = data['_time']
        self.nLineIndex = data['_lineIndex']
        self.nLineLayer = data['_lineLayer']
        self.nType = data['_type']
        self.nCutDirection = data['_cutDirection']



# Used again
def readInfo(filename):
    # Opening JSON file
    with open(filename, 'r') as openfile:
        # Reading from json file
        json_object = json.load(openfile)
    return json_object

def main():
    filename = "Expert.dat"
    data = readInfo(filename)
    version = data['_version']
    eventData = data['_events']
    noteData = data['_notes']
    events = np.empty(len(eventData), dtype=object)
    for i in range(0, len(events)):
        curEvent = eventData[i]
        e = Event(curEvent['_time'], curEvent['_type'], curEvent['_value']) # FIx Later
        events[i] = e
    notes = np.empty(len(noteData), dtype=object)
    for i in range(0, len(notes)):
        curNote = noteData[i]
        n = Note(curNote)
        notes[i] = n
#        np.append(events, e)
#    print(events)


main()