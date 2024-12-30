from datetime import datetime, timedelta
import requests

def check_alpha_words(word_list):
    return all(word.isalpha() for word in word_list)

def fetch_latest_news(api_key, news_keywords, lookback_days=10):
    
    curDate = (datetime.today() - timedelta(days=lookback_days)).strftime('%Y-%m-%d')

    if not news_keywords:
        raise Exception("No keywords given")
    
    if not check_alpha_words(news_keywords):
        raise Exception("Non-alphabetical characters were given in the news keywords")

    url = ('https://newsapi.org/v2/everything?'
       'q=' + ' OR '.join(news_keywords) + 
       '&from=' + curDate + 
       '&apiKey=' + api_key)

    response = requests.get(url)

    if (response.status_code != "OK"):
        print(response.status_code)
        print (response.text)
    
    return response.json()
