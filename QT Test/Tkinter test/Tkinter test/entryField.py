from tkinter import *
 # https://coderslegacy.com/python/python-gui/python-tkinter-entry/#:~:text=Python%20Tkinter%20Entry%20widget%20is%20used%20to%20take,tkinter%20import%20%2A%20Entry%20%3D%20Entry%20%28master%2C%20option.........%29?msclkid=537edd8dafa411ec92f271f3d33b6cb8
def retrieve():
    print(my_entry.get())
    print(my_entry2.get())
 
root = Tk()
root.geometry("200x150")
 
frame = Frame(root)
frame.pack()
 
my_entry = Entry(frame, width = 20)
my_entry.insert(0,'Username')
my_entry.pack(padx = 5, pady = 5)
 
my_entry2 = Entry(frame, width = 15)
my_entry2.insert(0,'password')
my_entry2.pack(padx = 5, pady = 5)
 
Button = Button(frame, text = "Submit", command = retrieve)
Button.pack(padx = 5, pady = 5)
 
root.mainloop()
