
# 1. Import the requests library
import requests
URL = "https://instagram.com/favicon.ico"
# 2. download the data behind the URL
response = requests.get(URL)
# 3. Open the response into a new file called instagram.ico
open("instagram.ico", "wb").write(response.content)