import json
import argparse
import re
from collections import Counter

def clean_text(text):
    """
    Clean the input text by removing punctuation and converting to lowercase.
    """
    return re.sub(r'[^\w\s]', '', text).lower()

def extract_titles(json_file):
    """
    Extract post titles from a JSON file with the structure { data: { children: [] } }.
    """
    try:
        with open(json_file, 'r') as file:
            data = json.load(file)
    except FileNotFoundError:
        print(f"Error: File {json_file} not found.")
        return []
    except json.JSONDecodeError:
        print(f"Error: File {json_file} is not a valid JSON.")
        return []
    
    # Extract titles from the JSON structure
    children = data.get('data', {}).get('children', [])
    titles = [child.get('data', {}).get('title', '') for child in children]
    return titles[:200]  # Only consider the first 200 titles

def load_stop_words(stop_word_file):
    """
    Load stop words from the specified file.
    """
    try:
        with open(stop_word_file, 'r', encoding='utf-8') as file:
            return set(line.strip().lower() for line in file)
    except FileNotFoundError:
        print(f"Error: Stop word file {stop_word_file} not found.")
        return set()

def process_file(file_path, stop_words):
    """
    Process a single input JSON file to compute word frequencies in post titles,
    excluding stop words.
    """
    titles = extract_titles(file_path)
    if not titles:
        return None
    
    # Clean and split words
    words = []
    for title in titles:
        words.extend(clean_text(title).split())
    
    # Remove stop words
    words = [word for word in words if word not in stop_words]
    
    # Count word frequencies
    word_counts = Counter(words)
    return word_counts.most_common(10)

def main():
    parser = argparse.ArgumentParser(description="Compute the top 10 words in post titles based on absolute frequency.")
    parser.add_argument('-o', '--output', required=True, help="Output file to save the word counts (JSON format).")
    parser.add_argument('-s', '--stop-words', help="Optional stop word file. Words in this file will be excluded.")
    parser.add_argument('input_files', nargs='+', help="List of input JSON files containing post titles.")
    
    args = parser.parse_args()
    
    # Load stop words if a stop word file is provided
    stop_words = set()
    if args.stop_words:
        stop_words = load_stop_words(args.stop_words)

    result = {}
    
    for file_path in args.input_files:
        print(f"Processing file: {file_path}")
        word_list = process_file(file_path, stop_words)
        if word_list is not None:
            result[file_path] = word_list
    
    # Save results to output JSON
    with open(args.output, 'w') as output_file:
        json.dump(result, output_file, indent=4)
    
    print(f"Word counts saved to {args.output}")

if __name__ == "__main__":
    main()
