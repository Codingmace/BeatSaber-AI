from tkinter import *
 
def retrieve():
    print("X")
 
root = Tk()
root.geometry("200x150")
frame = Frame(root)
frame.pack()
 
 
RBttn = Radiobutton(frame, text = "Burger")
RBttn.pack(padx = 5, pady = 5)
 
RBttn2 = Radiobutton(frame, text = "Pizza")
RBttn2.pack(padx = 5, pady = 5)
 
Button = Button(frame, text = "Submit", command = retrieve)
Button.pack()
 
root.mainloop()