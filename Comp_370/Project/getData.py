import csv
from datetime import datetime, timedelta
import json
import random
import requests
from time import sleep

def fetch_news(api_key, news_keywords, sources, pages, totalCount, outFileName):

    if not news_keywords:
        raise Exception("No keywords given")
    
    totalPosts = []

    for page in range(1, 6):

        url = ('https://newsapi.org/v2/everything?'
        'q="Kamala Harris"' + 
        '&sources=' + ",".join(sources) +
        '&language=en' + 
        '&searchIn=title' +
        '&page=' + str(page) +
        '&apiKey=' + api_key)

        print(url)

        response = requests.get(url)

        if (response.status_code != 200):
            print(response.status_code)
            print("Error")
            #print (response.text)
        
        print("Success")
        data = response.json()
        print(data['totalResults'])
        totalPosts = totalPosts + data['articles']
        #sleep(1)

    # If num_posts exceeds total_posts, select all posts; otherwise, sample
    selected_posts = random.sample(totalPosts, 200)

    # Extract relevant fields from each post
    extracted_data = [
        {
            'Title': post.get('title', ''),
            'Description': post.get('description', ''),
            'Coding': ""
        }
        for post in selected_posts
    ]

    print(extracted_data)

    # Write the selected posts to a TSV file
    with open(outFileName, 'w', newline='', encoding='utf-8') as out_tsv:
        writer = csv.DictWriter(out_tsv, fieldnames=extracted_data[0].keys(), delimiter='\t')
        writer.writeheader()
        writer.writerows(extracted_data)

key1 = "6833c405f23e4dd1a005ca09e3254a8d"
key2 = "2d95ca8abd294e6dbcf7c719eaa1a786"

keywords = ["Justin Trudeau", "Trudeau"]
sources = ["msnbc", "cnn", "newsweek", "the-hill", "fox-news"]
#domains = ["bbc.com", "businessinsider.com", "yahoo.com", "theverge.com", "time.com", "cbc.ca", "torontosun.com", "newsweek.com"]

data = fetch_news(key2, keywords, sources, 12, 500, "openCoding.tsv")

with open("data.json", "w") as file:
    json.dump(data, file, indent=4)