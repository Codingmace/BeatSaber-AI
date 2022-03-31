
# Python program demonstrating Multiple selection
# in Listbox widget with a scrollbar
from tkinter import *

def printx():
    print("Printing X")
window = Tk()
window.title('Multiple selection')
  
frame1 = Frame(window)

# for scrolling vertically
yscrollbar = Scrollbar(window)
yscrollbar.pack(side = RIGHT, fill = Y)
  
label = Label(frame1, text = "Select the languages below :  ", font = ("Times New Roman", 10), padx = 10, pady = 10)
label.pack()
list = Listbox(frame1, selectmode = "multiple", yscrollcommand = yscrollbar.set)
  
# Widget expands horizontally and 
# vertically by assigning both to
# fill option
list.pack(padx = 10, pady = 10, expand = YES, fill = "both")
  
x =["Easy", "Normal", "Hard", "Expert", "Expert+"]
  
for each_item in range(len(x)):
    list.insert(END, x[each_item])
    list.itemconfig(each_item, bg = "lime")
  
# Attach listbox to vertical scrollbar
yscrollbar.config(command = list.yview)
#frame1.pack() # Listbox Frame
frame2 = Frame(window)
Button1 = Button(frame2, text = "Fix Project") # Goes to select zip file
Button2 = Button(frame2,text = "Evaluate File") # Goes to Select zip or dat file
Button3 = Button(frame2, text = "Download/convert song") # Goes to a url link download page 
Button4 = Button(frame2, text = "Create Info File") # Goes to a select file page
Button5 = Button(frame2, text = "Generate Full Song") # Goes to a multiple select page
Button1.pack()
Button2.pack()
Button3.pack()
Button4.pack()
#Button5.pack()
frame2.pack()
window.mainloop()