import requests
from bs4 import BeautifulSoup
import json
import os
import argparse
import time

CACHE_FILE = 'cache.html'

def fetch_homepage(url):
    headers = {
        'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'
    }
    
    if os.path.exists(CACHE_FILE):
        with open(CACHE_FILE, 'r', encoding='utf-8') as f:
            return f.read()
    else:
        response = requests.get(url, headers=headers)
        if response.status_code == 200:
            with open(CACHE_FILE, 'w', encoding='utf-8') as f:
                f.write(response.text)
            return response.text
        else:
            raise Exception(f"Failed to fetch homepage: {response.status_code}")

def parse_homepage(html):
    soup = BeautifulSoup(html, 'html.parser')
    articles = []
    
    # Modify this selector based on the actual structure of the homepage
    for item in soup.select('article div div a'):  # Adjust this selector as necessary
        link = item['href']
        if link:
            articles.append(link)
    
    return articles[:5]  # Get only the top 5 articles

def fetch_article(url):
    headers = {
        'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36'
    }

    response = requests.get("https://montrealgazette.com/" + url, headers=headers)
    if response.status_code == 200:
        return response.text
    else:
        raise Exception(f"Failed to fetch article: {response.status_code}")

def parse_article(html):
    soup = BeautifulSoup(html, 'html.parser')
    
    title = soup.find('h1').get_text(strip=True)
    publication_date = soup.find('span', class_='published-date__since').get_text(strip=True)
    author = soup.find('span', class_='published-by__author').get_text(strip=True) if soup.find('span', class_='published-by__author') else 'Unknown'
    blurb = soup.find('p', class_='article-subtitle').get_text(strip=True) if soup.find('p', class_='article-subtitle') else ''

    return {
        "title": title,
        "publication_date": publication_date,
        "author": author,
        "blurb": blurb
    }

def main(output_file):
    url = "https://montrealgazette.com/category/news/"
    
    # Fetch and parse homepage
    homepage_html = fetch_homepage(url)
    trending_links = parse_homepage(homepage_html)
    print(trending_links)

    # Collect articles
    articles_info = []
    for link in trending_links:
        article_html = fetch_article(link)
        article_info = parse_article(article_html)
        articles_info.append(article_info)
        time.sleep(1)  # Respectful delay between requests

    # Write to output file
    with open(output_file, 'w', encoding='utf-8') as f:
        json.dump(articles_info, f, ensure_ascii=False, indent=4)

if __name__ == "__main__":
    parser = argparse.ArgumentParser(description='Scrape trending stories from Montreal Gazette.')
    parser.add_argument('-o', '--output', required=True, help='Output JSON file')
    args = parser.parse_args()
    
    main(args.output)