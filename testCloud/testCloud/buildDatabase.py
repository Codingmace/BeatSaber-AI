import json
import boto3
import os
'''
with open('path_to_file/person.json', 'r') as f:
  data = json.load(f)

print(data)
'''

def put_entry(id, name, uploader, typeName, bpm, duration, 
    levelAuthorName, plays, downloads, upvotes, downvotes, score, automapper, 
    sageScore, levels, percent, downloadURL, songAuthorName, dynamodb=None):
    if not dynamodb:
        dynamodb = boto3.resource('dynamodb', region_name='us-east-1')
       
#        dynamodb = boto3.client('dynamodb', region_name='us-west-2')
#        dynamodb = boto3.resource('dynamodb', endpoint_url="http://localhost:8000")

    table = dynamodb.Table('BeatSaverMain')
    response = table.put_item(
       Item={
            'id': id,
            'name': name,
            'uploader': uploader,
            'typeName': typeName,
            'bpm': bpm,
            'duration': duration,
            'levelAuthorName': levelAuthorName,
            'plays': plays,
            'downloads': downloads,
            'upvotes': upvotes,
            'downvotes': downvotes,
            'score': score,
            'automapper': automapper,
            'sageScore': sageScore,
            'percent': percent,
            'downloadUrl': downloadURL,
            'levelMeta': levels,
            'songAuthorName': songAuthorName
        }
    )
    return response



filebase = 'H:\\'
filename = '12be3.json'
'''
f = open(filebase+ filename)
x = f.read()
#print(x)
y = json.loads(x)
print(y)
'''
with open(filebase+filename, 'r') as f:
  y = json.load(f)
print(y)
#print(y['uploader']['name'])
print(os.listdir(filebase))

''' Fields to get '''
id=y['id']
name=y['name']
uploader=y['uploader']['name']
typeName=y['uploader']['type']
bpm=y['metadata']['bpm']
duration=y['metadata']['duration']
songAuthorName = y['metadata']['songAuthorName']
levelAuthorName=y['metadata']['levelAuthorName']
plays=y['stats']['plays']
downloads=y['stats']['downloads']
upvotes= y['stats']['upvotes']
downvotes= y['stats']['downvotes']
score= str(y['stats']['score'])
automapper=y['automapper']
sageScore=y['versions'][0]['sageScore']
levels=y['versions'][0]['diffs']
percent = str(upvotes/(downvotes+upvotes))
downloadUrl = y['versions'][0]['downloadURL']

#print(levels)
for a in range(0,len(levels)):
    levels[a]['njs'] = str(levels[a]['njs'])
    levels[a]['offset'] = str(levels[a]['offset'])
    levels[a]['nps'] = str(levels[a]['nps'])
    levels[a]['length'] = str(levels[a]['length'])
#    levels[a]['nps'] = str(levels[a]['n'])
#    curLevel = levels[a]
#    print(curLevel)


#print(downloadUrl)
#print(levels)
'''
flag = False # Flags to add
if bpm % 1 != 0:
    flag = True
elif automapper:
    flag = True

'''
response = put_entry(id, name, uploader, typeName, str(bpm), duration, levelAuthorName, plays, downloads, upvotes, downvotes, score,
          automapper, sageScore, levels, percent, downloadUrl, songAuthorName)
#'''
if(response['ResponseMetadata']['HTTPStatusCode'] == 200):
    os.remove(filebase + filename)
# print(response['ResponseMetadata']['HTTPStatusCode'])

# read in json
# remove fields not needed
# Reformat to what I want
# Send to the database
