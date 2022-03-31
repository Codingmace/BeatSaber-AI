# Enhancement
# Add advance Search
import os
from prettytable import PrettyTable

def getData(dataset):
    count = 0
    name = dataset[0]
    songAuthor = dataset[1]
    levelAuthor = dataset[2]
    uploader = dataset[3]
    ''' Note the database is based on entries not including beatsage in any way'''
    f = open("resources/songDatabase.csv" , "r")
    # Make into a table
    header = f.readline().strip().split(",")
    lines = f.readlines()
    f.close()
    print(header)
    myTable = PrettyTable(header)
    for line in lines:
        addRow = True
        curLine = line.strip().split(",")
        if name != "Name":
            if name not in curLine[1]:
                addRow = False
        if songAuthor != "Song Author":
            if songAuthor not in curLine[2]:
                addRow = False
        if levelAuthor != "Level Author":
            if levelAuthor not in curLine[3]:
                addRow = False
        if uploader != "Uploader":
            if uploader not in curLine[4]:
                addRow = False
        if addRow:
            myTable.add_row(line.strip().split(","))
            count += 1
    if count > 10:
        print(myTable[0:10])
        return myTable[0:10]
    else:
        print(myTable)
        return myTable