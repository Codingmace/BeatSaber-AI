# Regex Search
# Objective is to find the phrases most used in the namning convention

import os
import re
def simplify(file1, file2):
	os.chdir("C:\\Users\\Maste\\Downloads\\out")
	f = open(file1, "r", encoding="UTF-8")
	g = open(file2, "w")
	lines = f.readlines()
	for line in lines:
		smaller = line.replace("├──", "").replace("│   ", "")
		try:
			g.write(smaller)
		except:
			print(smaller)
		g.flush()
	g.close()

#simplify("astana1.txt", "astana_clean.txt")
#simplify("nohr", "nohr_clean.txt")
#simplify("CX", "CX_clean.txt")
def smartReplace(a, b): # Removing A from b if a is present
	a = a.lower()
	c = b.lower()
	if a in c:
		startIndex = c.index(a)
		endIndex = startIndex + len(a)
		return b[0:startIndex] + b[endIndex:]
	else:
		return b

logging = open("logs.txt", "a")
def metaFill(filename):
	os.chdir("C:\\Users\\Maste\\Downloads\\out")
	print("Filename, filepath, type, year, source, season, episode, series") # Need to get the year for everything
	f = open(filename , "r")
	g = open("database.csv", "a")
#	g.write("Filename, filepath, type, year, source, season, episode, series")
	lines = f.readlines()
	blackListWords = ['x264', 'WEB', 'Dual-Audio', 'HQ', 'BrRip','BDRip', 'Rip', 'BluRay', 'x265', 'AAC', 'DVD', 'RCVR', '10bit', 'Blu-ray', 'FLAC','Dual Audio','HEVC', 'MULTI-AUDIO', 'Subbed', '10-bit', 'HDTV', 'DTS-HD']
	for line in lines:
		if line[0:2] == "._": # File to delete
			logging.write("Delete file " + line + "\n")
			logging.flush()
#			print("Delete this file")
		else:
			split_tup = os.path.splitext(line)
			extension = ""
			season = -1
			episode = -1
			series = ""
			year = 0000
			resolution = -1
			extension = split_tup[1]
			if extension.strip() == ".mkv": # only doing mkv videos
				newLine = split_tup[0]
				for a in blackListWords:
					newLine = smartReplace(a, newLine)
#					newLine = newLine.replace(a, "")
#				newLine = newLine.replace("."," ").replace("_", " ")
				newLine = newLine.strip()
				source = ""
				if newLine[0] == "[":
					indexEnd = newLine.index("]")
					source = newLine[1:indexEnd]
					if source.isnumeric():
						logging.write("Something Fucked Up\n")
#						print("Something Fucked up")
						source = ""
					else:
						newLine = newLine[indexEnd + 1:]
#				newLine = newLine.replace("Episode", "E").replace("Season", "S") # Verify this one somehow
				newLine = newLine.replace("."," ").replace("_", " ").strip()
				myPath = re.compile('S[0-9]+[ ]*E[0-9]+')
				if len(myPath.findall(newLine)) > 0:
					se = myPath.findall(newLine)[0].split("E")
					season = int(se[0].replace("S",""))
					episode = int(se[1])
					wholePhrase = myPath.findall(newLine)[0]
					newPhrase = f'S{season:02d}E{episode:02d}'
#					newLine = newLine.replace(wholePhrase, "")
					newLine = newPhrase + " - " + newLine.replace(wholePhrase, "") # Need to fix duplicating this
					newLine = newLine.replace(" - - ", " - ")
#					newLine = newLine.replace(wholePhrase, newPhrase)

#					print(se)
				myPath2 = re.compile('[0-9]+p').findall(newLine)
				if len(myPath2) > 0: # What to do if P is capitalized
					curRes = myPath2[0].replace("p", "")
					resolution = int(curRes)
					newLine = newLine.replace(myPath2[0], "")
#				print(myPath.findall(newLine))
				else:
					myPath2 = re.compile('[0-9]+P').findall(newLine)
					if len(myPath2) > 0:
						curRes = myPath2[0].replace("P", "")
						resolution = int(curRes)
						newLine = newLine.replace(myPath2[0], "")
				newLine = newLine.replace("\[\]" , "").replace("\(\)", "")
				if not (source == ""):
					myPath3 = re.compile('\[[0-9A-Za-z]+\]').findall(newLine)
					if len(myPath3) == 1: # Remove weird ending
						endSubstring = newLine.rindex("[")
						newLine = newLine[0:endSubstring]
					elif len(myPath3) > 1: # More than weird ending
						# ERROR
						ugh = 'MyPath3 '
						for qx in myPath3:
							ugh += qx + " "
						ugh += newLine + "\n"
						logging.write(ugh)
						logging.flush()
#						print("MyPath3", myPath3) # Probably Remove Both
					myPath4 = re.compile('\([ 0-9A-Za-z]*\)').findall(newLine)
					if len(myPath4) > 1:
						for x in myPath4:
							if re.match(r'\(([1-2][0-9]{3})\)', x): # it is the year
								year = int(x[1:-1])
								newLine = newLine.replace(x, "") # Removes the year
							else: # ERRROR
								ugh = 'MyPath4 '
								for qx in myPath4:
									ugh += qx + " "
								ugh += newLine + "\n"
								logging.write(ugh)
								logging.flush()
#								print(x)
#						print("Standout ", myPath4)
						
				newLine = newLine.replace("\[\]" , "").replace("\(\)", "")
				newLine = newLine.strip()
				lineWrite = newLine + "," + newLine + "," + extension.strip() + "," + str(year) + "," + source + "," + str(season) + "," + str(episode) + "," + series + "," + str(resolution) + "\n"
				lineWrite = lineWrite.replace("[ ]+", " ")
				g.write(lineWrite)
				g.flush()
	g.close()
#metaFill()
#metaFill("astana_clean.txt")
#metaFill("CX_clean.txt")
#metaFill("nohr_clean.txt")


myString = "this is mine -- can it be  -"
print(myString.strip("-"))