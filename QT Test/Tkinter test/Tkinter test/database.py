from prettytable import PrettyTable
# from tkinter import *
import tkinter as tk
def retrieve():
    print(my_entry0.get(), my_entry1.get(), my_entry2.get(),my_entry3.get(),my_entry4.get(),my_entry5.get(),my_entry6.get())
    print(my_entry7.get(), my_entry8.get(), my_entry9.get(),my_entry10.get(),my_entry11.get(),my_entry12.get(), my_entry13.get())
 

window = tk.Tk()
frame = tk.Frame(window)
bottomframe = tk.Frame(window)
bottomframe.pack(side=tk.BOTTOM)
window.geometry("1000x500")

#  Adding all the entries
my_entry0 = tk.Entry(frame, width = 20)
my_entry0.insert(0,'Filename')
my_entry0.pack(padx = 5, pady = 0, side=tk.LEFT)
 
my_entry1 = tk.Entry(frame, width = 15)
my_entry1.insert(1,'Filepath')
my_entry1.pack(padx = 5, pady = 0, side = tk.LEFT)

my_entry2 = tk.Entry(frame, width = 20)
my_entry2.insert(2,'File Type')
my_entry2.pack(padx = 5, pady = 0, side=tk.LEFT)
 
my_entry3 = tk.Entry(frame, width = 15)
my_entry3.insert(3,'Genre')
my_entry3.pack(padx = 5, pady = 0, side = tk.LEFT)

my_entry4 = tk.Entry(frame, width = 20)
my_entry4.insert(4,'Valid')
my_entry4.pack(padx = 5, pady = 0, side=tk.LEFT)

'''
my_entry5 = tk.Entry(frame, width = 15)
my_entry5.insert(5,'BPM')
my_entry5.pack(padx = 5, pady = 0, side = tk.LEFT)

my_entry6 = tk.Entry(frame, width = 20)
my_entry6.insert(6,'Duration')
my_entry6.pack(padx = 5, pady = 0, side=tk.LEFT)
 
my_entry7 = tk.Entry(frame, width = 15)
my_entry7.insert(7,'Score')
my_entry7.pack(padx = 5, pady = 0, side = tk.LEFT)

my_entry8 = tk.Entry(frame, width = 20)
my_entry8.insert(8,'Percentage')
my_entry8.pack(padx = 5, pady = 0, side=tk.LEFT)
 
my_entry9 = tk.Entry(frame, width = 15)
my_entry9.insert(9,'Upvotes')
my_entry9.pack(padx = 5, pady = 0, side = tk.LEFT)
 
my_entry10 = tk.Entry(frame, width = 15)
my_entry10.insert(10,'Downvotes')
my_entry10.pack(padx = 5, pady = 0, side = tk.LEFT)

my_entry11 = tk.Entry(frame, width = 15)
my_entry11.insert(11,'SageScore')
my_entry11.pack(padx = 5, pady = 0, side = tk.LEFT)

my_entry12 = tk.Entry(frame, width = 15)
my_entry12.insert(12,'Level Count')
my_entry12.pack(padx = 5, pady = 0, side = tk.LEFT)

my_entry13 = tk.Entry(frame, width = 15)
my_entry13.insert(13,'URL??')
my_entry13.pack(padx = 5, pady = 0, side = tk.LEFT)
'''


Button = tk.Button(frame, text = "Submit", command = retrieve)
Button.pack(padx = 5, pady = 0, side = tk.RIGHT)

t= tk.Text(bottomframe) #Inside text widget we would put our table
#header =  ['id', 'name', 'songAuthorName', 'levelAuthorName', 'uploader', 'bpm', 'duration', 'score', 'percent', 'upvotes', 'downvotes', 'sageScore', 'levelCount', 'downloadURL (https://na.cdn.beatsaver.com/)']
header = ["City name", "Area", "Population", "Annual Rainfall"]
x=PrettyTable(header)
# x.field_names = ["City name", "Area", "Population", "Annual Rainfall"]
x.add_row(["Adelaide", 1295, 1158259, 600.5])
x.add_row(["Brisbane", 5905, 1857594, 1146.4])
x.add_row(["Darwin", 112, 120900, 1714.7])
x.add_row(["Hobart", 1357, 205556, 619.5])
x.add_row(["Sydney", 2058, 4336374, 1214.8])
x.add_row(["Melbourne", 1566, 3806092, 646.9])
x.add_row(["Perth", 5386, 1554769, 869.4])

t.insert(tk.INSERT,x)
# t.insert(INSERT,x)#Inserting table in text widget
t.pack_configure(side= tk.TOP)
# t.pack(side = tk.BOTTOM)
frame.pack()
# bottomframe.pack()
window.mainloop()
