# TODO
# Look into select audio file more
from tkinter import *

window = Tk()
window.title('My BS Program')
window.geometry("500x500")
window.resizable(True, True)

# Select Zip file
def selectZipFile():
    filetypes = (('Compressed Files', '*.zip'),('All files', '*.*'))
    filename = fd.askopenfilename(title='Open a file',initialdir='/',filetypes=filetypes)
    return filename
#    showinfo(title='Selected File', message=filename)

# Select File Button
def fixProject():
    frame5 = Frame(window)
    # open button
    open_button = ttk.Button(frame5,text='Open a File',command=selectZipFile)
    open_button.pack(expand=True)
    # frame5.pack()
    
# Adding editable text
def getFrame1():
    frame1 = Frame(window)
    text1 = Text(frame1, height=8) # Need to fix to be static
    text1.insert('1.0', 'This is my program press to continue') # Have to make non editable
    text1.pack()
 #   frame1.pack()

 # Button Choices
def getFrame2():
    frame2 = Frame(window)
    Button1 = Button(frame2, text = "Fix Project", command = fixProject) # Goes to select zip file
    Button2 = Button(frame2,text = "Evaluate File") # Goes to Select zip or dat file
    Button3 = Button(frame2, text = "Download/convert song") # Goes to a url link download page 
    Button4 = Button(frame2, text = "Create Info File") # Goes to a select file page
    Button5 = Button(frame2, text = "Generate Full Song") # Goes to a multiple select page
    Button6 = Button(frame2, text= "Downgrading") # Goes to dat file selection
    Button7 = Button(frame2, text="Search Database") # Goes to table search
    Button1.pack()
    Button2.pack()
    Button3.pack()
    Button4.pack()
    Button5.pack()
    Button6.pack()
    Button7.pack()
    #frame2.pack()

# For multiple selections
def getFrame3():
    frame3 = Frame(window)
    yscrollbar = Scrollbar(window) # for Vertical Scrolling
    yscrollbar.pack(side = RIGHT, fill = Y)
    label = Label(frame3, text = "Select the languages below :  ", font = ("Times New Roman", 10), padx = 10, pady = 10)
    label.pack()
    list = Listbox(frame3, selectmode = "multiple", yscrollcommand = yscrollbar.set)
    list.pack(padx = 10, pady = 10, expand = YES, fill = "both")
    x =["Easy", "Normal", "Hard", "Expert", "Expert+"]
    for each_item in range(len(x)):
        list.insert(END, x[each_item])
        list.itemconfig(each_item, bg = "lime")  
    yscrollbar.config(command = list.yview) # Attach Listbox to Scroller
#    frame3.pack() # Listbox Frame

# Select file Button
def getFrame4():
    frame4 = Frame(window)
    # open button
    open_button = ttk.Button(frame4,text='Open a File',command=selectAudioFile)
    open_button.pack(expand=True)
    # frame4.pack()

# Select Audio
def selectAudioFile():
    filetypes = (('Audio Files', ['*.egg','*.ogg','*.mp3','*.m4a']),('All files', '*.*'))
    filename = fd.askopenfilename(title='Open a file',initialdir='/',filetypes=filetypes)
    showinfo(title='Selected File', message=filename)


# getFrame1()

window.mainloop()