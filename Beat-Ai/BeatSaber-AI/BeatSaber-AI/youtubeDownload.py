# TODO
# Download youtube file that is given
# Might need pytube3 or version higher
# https://www.journaldev.com/40720/python-script-to-download-youtube-videos?msclkid=08d6b7a9af8e11ecad34f73fd78fb947

from pytube import YouTube
import os
import ffmpeg
from moviepy.editor import *
from pydub import AudioSegment
    
youtube_video_url = 'https://www.youtube.com/watch?v=lxRwEPvL-mQ'
filename = "whenIGrowUp"
vfn = "./tmp/" + filename + ".mp4"
afn = "./tmp/" + filename + ".mp3"

try:
    yt_obj =YouTube(youtube_video_url)
#    print(yt_obj.streams.get_audio_only())
    yt_obj.streams.get_audio_only().download(output_path='./tmp/', filename=filename + ".mp4")
    # f = open("./tmp/" + filename + ".mp3").read()
    videoclip = VideoFileClip(vfn)
    audioclip = videoclip.audio
    audioclip.write_audiofile(afn)
    videoclip.close()
    audioclip.close()
#    yt_obj.streams.get_audio_only()
#   AudioSegment.from_mp3(f.name).export('./tmp/result.ogg', format = 'ogg')
    f.close()
    print('YouTube video audio downloaded successfully')
    print("Successful Conversion")
    print(f'Video Title is {yt_obj.title}')
    print(f'Video Length is {yt_obj.length} seconds')
    print(f'Video Description is {yt_obj.description}')
    print(f'Video Rating is {yt_obj.rating}')
    print(f'Video Views Count is {yt_obj.views}')
    print(f'Video Author is {yt_obj.author}')
 
except Exception as e:
    print(e)