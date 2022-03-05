from pprint import pprint
import boto3

def put_entry(id, name, uploader, typeName, bpm, duration, levelAuthorName, plays, downloads, upvotes, downvotes, score, automapper, sageScore, levels, flag, dynamodb=None):
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
            'levelMeta': levels,
            'flag': flag
        }
    )
    return response


if __name__ == '__main__':
    levels = [{
                    "njs": 21.0,
                    "offset": -0.1,
                    "notes": 124,
                    "bombs": 0,
                    "obstacles": 0,
                    "nps": 6.74,
                    "length": 39.25,
                    "characteristic": "Standard",
                    "difficulty": "ExpertPlus",
                    "events": 223,
                    "chroma": false,
                    "me": false,
                    "ne": false,
                    "cinema": false,
                    "paritySummary": {
                        "errors": 0,
                        "warns": 0,
                        "resets": 0
                    }
                }]
    movie_resp = put_entry("1111111","Tensei Shitara Slime Datta Ken-OP 2  bombs", "linkz006",
                           "SIMPLE",'120.0',200,"Beat Sage", 0, 0, 5, 5, '0.5', True, -13, levels, False)
    pprint(movie_resp, sort_dicts=False)