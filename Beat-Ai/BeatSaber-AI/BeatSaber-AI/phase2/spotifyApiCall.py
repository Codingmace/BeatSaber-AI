# TODO
# Create a search panel for the songs
# Grab an Album Cover for the song (part of stage 2)

import requests
import json

client_id = '144030cbe99a4bfe84c4a817abe56ebb'
client_secret = '99eae76250514ecdbb1f30bc222dc5b7'

auth_url = 'https://accounts.spotify.com/api/token'

# POST
data = {
    'grant_type': 'client_credentials',
    'client_id': client_id,
    'client_secret': client_secret,
}

auth_response = requests.post(auth_url, data=data)

# convert the response to JSON
auth_response_data = auth_response.json()

# save the access token
access_token = auth_response_data['access_token']

print(access_token)
# BQDcapuPZgRilVnrTiGvqVEDPZom79zPPmMqG2zQ5FI22CQizKaenv7_ogScldvn7fFQfwNLZ5z3sZDNRNI

# base URL of all Spotify API endpoints
base_url = 'https://api.spotify.com/v1/'

headers = {
    'Authorization': 'Bearer {}'.format(access_token)
}
# Track ID from the URI
# track_id = '6y0igZArWVi6Iz0rj35c1Y'
track_id = '0hWzB4dR1zwcokPvccww0k'

# actual GET request with proper header
r = requests.get(base_url + 'audio-features/' + track_id, headers=headers)


d = r.json()
print(d)

print("Beat =", d['tempo'])

''' Creating a version for searching
Goal is to make it easier to search for a song
https://pypi.org/project/spotify/?msclkid=b553379eaeb711ecb2872a5ec9c21e35
'''

# Get the Song File
''' Downloading the song
https://pypi.org/project/spotdl/?msclkid=8426a3c8aeb711ec8ac0f8907caa2f10
'''
# Be Able to search for the file through application

''' Search Documentation
https://developer.spotify.com/documentation/web-api/reference/#/operations/search
q
string
required
Your search query.

You can narrow down your search using field filters. The available filters are album, artist, track, year, upc, tag:hipster, tag:new, isrc, and genre. Each field filter only applies to certain result types.

The artist filter can be used while searching albums, artists or tracks.
The album and year filters can be used while searching albums or tracks. You can filter on a single year or a range (e.g. 1955-1960).
The genre filter can be use while searching tracks and artists.
The isrc and track filters can be used while searching tracks.
The upc, tag:new and tag:hipster filters can only be used while searching albums. The tag:new filter will return albums released in the past two weeks and tag:hipster can be used to return only albums with the lowest 10% popularity.

You can also use the NOT operator to exclude keywords from your search.

Example value:
"remaster%20track:Doxy+artist:Miles%20Davis"
type
array
required
A comma-separated list of item types to search across. Search results include hits from all the specified item types. For example: q=name:abacab&type=album,track returns both albums and tracks with "abacab" included in their name.

Allowed values:
"album"
"artist"
"playlist"
"track"
"show"
"episode"
Example value:
"track,artist"
include_external
string
If include_external=audio is specified it signals that the client can play externally hosted audio content, and marks the content as playable in the response. By default externally hosted audio content is marked as unplayable in the response.

Allowed value:
"audio"
limit
integer
The maximum number of results to return in each item type.

>= 0
<= 50
Default value:
20
Example value:
10
market
string
An ISO 3166-1 alpha-2 country code. If a country code is specified, only content that is available in that market will be returned.
If a valid user access token is specified in the request header, the country associated with the user account will take priority over this parameter.
Note: If neither market or user country are provided, the content is considered unavailable for the client.
Users can view the country that is associated with their account in the account settings.

Example value:
"ES"
offset
integer
The index of the first result to return. Use with limit to get the next page of search results.

>= 0
<= 1000
Default value:
0
Example value:
5
'''
