import tkinter as tk
from methods.searchDatabase import getData
from methods.writeInfo import writeInfoMain 
import os

# Initializing the program
window = tk.Tk()
window.title('My BS Program')
window.geometry("1200x1000")
window.resizable(True, True)

def updateDatabase():
    frame = tk.Frame(window)
    bottomframe = tk.Frame(window,height= 700, width=700)
    t= tk.Text(bottomframe) #Inside text widget we would put our table
    t.pack_configure(side= tk.TOP)
    def retrieve():
        dataset = [ my_entry1.get(), my_entry2.get(),my_entry3.get(),my_entry4.get()]
#        dataset = [my_entry0.get(), my_entry1.get(), my_entry2.get(),my_entry3.get(),my_entry4.get(),my_entry5.get(),my_entry6.get(),
#                   my_entry7.get(), my_entry8.get(), my_entry9.get(),my_entry10.get(),my_entry11.get(),my_entry12.get(), my_entry13.get()]
        x = getData(dataset)
        t.insert(tk.INSERT,x) 
#        bottomframe.update()
        bottomframe.pack()
        window.update()

    # Here update database View
    '''
    my_entry0 = tk.Entry(frame, width = 20)
    my_entry0.insert(0,'ID')
    my_entry0.pack(padx = 5, pady = 0, side=tk.LEFT)
    '''
    my_entry1 = tk.Entry(frame, width = 15)
    my_entry1.insert(1,'Name')
    my_entry1.pack(padx = 5, pady = 0, side = tk.LEFT)

    my_entry2 = tk.Entry(frame, width = 20)
    my_entry2.insert(2,'Song Author')
    my_entry2.pack(padx = 5, pady = 0, side=tk.LEFT)
 
    my_entry3 = tk.Entry(frame, width = 15)
    my_entry3.insert(3,'Level Author')
    my_entry3.pack(padx = 5, pady = 0, side = tk.LEFT)

    my_entry4 = tk.Entry(frame, width = 20)
    my_entry4.insert(4,'Uploader')
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
    frame.pack()

def downloadYoutubeVideo():
    print("This is where download youtube Video")

def createInfoFile():
    # NEED TO ADD SELECTING THE INPUT FILE
    if not os.path.exists("./tmp"):
        os.mkdir("./tmp")
    var1 = tk.IntVar()
    var2 = tk.IntVar()
    var3 = tk.IntVar()
    var4 = tk.IntVar()
    var5 = tk.IntVar()
    def print_selection():
        if var1.get() == 1:
            print("Going to do easy")
        if var2.get() == 1:
            print("Going to do Normal")
        if var3.get() == 1:
            print("Going to do Hard")
        if var4.get() == 1:
            print("Going to do expert")
        if var5.get() == 1:
            print("Going to do expert+")

    frame = tk.Frame(window)
    c1 = tk.Checkbutton(frame, text='Easy',variable=var1, onvalue=1, offvalue=0, command=print_selection)
    c1.pack()
    c2 = tk.Checkbutton(frame, text='Normal',variable=var2, onvalue=1, offvalue=0, command=print_selection)
    c2.pack()
    c3 = tk.Checkbutton(frame, text='Hard',variable=var3, onvalue=1, offvalue=0, command=print_selection)
    c3.pack()
    c4 = tk.Checkbutton(frame, text='Expert',variable=var4, onvalue=1, offvalue=0, command=print_selection)
    c4.pack()
    c5 = tk.Checkbutton(frame, text='ExpertPlus',variable=var5, onvalue=1, offvalue=0, command=print_selection)
    c5.pack()
    Button = tk.Button(frame, text = "Submit", command = print_selection)
    Button.pack(padx = 5, pady = 0, side = tk.RIGHT)
    frame.pack()
    window.update()
    print("Use information given to create an info file. Lots in the selection Options.")
    print("Use selection to write the file to temp folder")

def fixBrokenZip():
    print("Get a zip file and verify everything is correct and good for latest version")

def fixBrokenFile():
    print("Determine the file and fix the issues with it.")

def mapgui():
    print("This is the mapping part of the GUI and main meat of the program.")

def analyzeFolder():
    print("Analyze uploaded folder.")
    print("If it is zip, verify all info")
    print("If folders of folders than do reccursive thing")


frame1 = tk.Frame(window)
Button1 = tk.Button(frame1, text = "Search Database", command = updateDatabase)
Button1.pack()
Button2 = tk.Button(frame1, text = "Download Youtube Song", command = downloadYoutubeVideo)
Button2.pack()
Button3 = tk.Button(frame1, text = "Create info File" ,command = createInfoFile)
Button3.pack()
Button4 = tk.Button(frame1, text = "Fix Broken Package", command = fixBrokenZip)
Button4.pack()
Button5 = tk.Button(frame1, text = "Fix Broken File" , command = fixBrokenFile)
Button5.pack()
Button6 = tk.Button(frame1, text= "Create Mapping", command = mapgui)
Button6.pack()
Button7 = tk.Button(frame1, text = "Clean Package(s)" , command = analyzeFolder)
Button7.pack()
frame1.pack()
# Button2 = Button(frame1,text = "Evaluate File") # Goes to Select zip or dat file
# Button3 = Button(frame1, text = "Download/convert song") # Goes to a url link download page 
# Button4 = Button(frame1, text = "Create Info File") # Goes to a select file page
# Button5 = Button(frame1, text = "Generate Full Song") # Goes to a multiple select page
# Button6 = Button(frame1, text= "Downgrading") # Goes to dat file selection

window.mainloop()
