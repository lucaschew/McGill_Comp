import unittest
import json
from datetime import datetime, timedelta
from newscover.newsapi import fetch_latest_news

# Helper function to load API key from test_secrets.json
def load_api_key():
    with open('newscover/tests/test_secrets.json') as f:
        data = json.load(f)
    return data['key']

class TestFetchLatestNews(unittest.TestCase):
    @classmethod
    def setUpClass(cls):
        cls.api_key = load_api_key()

    def test_no_keywords(self):
        """
        Ensure fetch_latest_news fails when no news_keywords are provided.
        """
        with self.assertRaises(Exception):
            fetch_latest_news(api_key=self.api_key, news_keywords=[])

    def test_lookback_days(self):
        """
        Ensure that when lookback_days is set, it doesn’t produce articles outside that timeframe.
        """
        # Mock the API call if necessary or use a testing framework like `unittest.mock` to simulate the response
        # For now, assuming fetch_latest_news works as intended
        recent_article_date = datetime.now().date()
        old_article_date = (datetime.now() - timedelta(days=10)).date()
        
        # Call the method with a 10-day lookback period
        results = fetch_latest_news(api_key=self.api_key, news_keywords=["technology"], lookback_days=10)

        # Access the 'articles' from the JSON response
        articles = results['articles']  # Ensure this accesses the list of articles
        
        # Assuming articles have the 'publishedAt' field
        first_article_date = datetime.strptime(articles[0]['publishedAt'][:10], '%Y-%m-%d').date()
        last_article_date = datetime.strptime(articles[-1]['publishedAt'][:10], '%Y-%m-%d').date()
        
        # Ensure only the recent article is included
        self.assertTrue(recent_article_date >= first_article_date)  # Check if the first article is not older than today
        self.assertTrue(old_article_date <= last_article_date)  # Check if the last article is not older than the lookback period
        
    
    def test_invalid_keyword(self):
        """
        Ensure fetch_latest_news fails when a keyword contains a non-alphabetic character.
        """
        
        with self.assertRaises(Exception):
            fetch_latest_news(api_key=self.api_key, news_keywords=["tech123"])

if __name__ == '__main__':
    unittest.main()