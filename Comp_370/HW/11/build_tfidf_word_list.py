import json
import argparse
import re
from collections import Counter
from math import log

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
        with open(json_file, 'r', encoding='utf-8') as file:
            data = json.load(file)
    except FileNotFoundError:
        print(f"Error: File {json_file} not found.")
        return []
    except json.JSONDecodeError:
        print(f"Error: File {json_file} is not a valid JSON.")
        return []
    except UnicodeDecodeError as e:
        print(f"Error decoding file {json_file}: {e}")
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
    except UnicodeDecodeError as e:
        print(f"Error decoding stop word file {stop_word_file}: {e}")
        return set()

def calculate_tfidf(word_counts, total_words, document_frequencies, num_documents):
    """
    Calculate the TF-IDF scores for each word.
    TF = (word frequency in file) / (total words in file)
    IDF = log(num_documents / (1 + document frequency of word))
    """
    tfidf_scores = {}
    for word, count in word_counts.items():
        tf = count / total_words
        idf = log(num_documents / (1 + document_frequencies[word]))
        tfidf_scores[word] = tf * idf
    return tfidf_scores

def process_files(file_paths, stop_words):
    """
    Process multiple input JSON files to compute word TF-IDF scores.
    """
    document_frequencies = Counter()
    file_word_counts = {}
    file_total_words = {}
    num_documents = len(file_paths)
    
    # First pass: calculate term frequencies and document frequencies
    for file_path in file_paths:
        titles = extract_titles(file_path)
        words = []
        for title in titles:
            words.extend(clean_text(title).split())
        
        # Remove stop words
        words = [word for word in words if word not in stop_words]
        
        # Count word frequencies
        word_counts = Counter(words)
        file_word_counts[file_path] = word_counts
        file_total_words[file_path] = len(words)
        
        # Update document frequencies
        document_frequencies.update(set(word_counts.keys()))
    
    # Second pass: calculate TF-IDF scores
    tfidf_results = {}
    for file_path, word_counts in file_word_counts.items():
        total_words = file_total_words[file_path]
        tfidf_scores = calculate_tfidf(word_counts, total_words, document_frequencies, num_documents)
        
        # Get top 10 words by TF-IDF score
        top_words = sorted(tfidf_scores.items(), key=lambda x: x[1], reverse=True)[:10]
        tfidf_results[file_path] = top_words
    
    return tfidf_results

def main():
    parser = argparse.ArgumentParser(description="Compute the top 10 words in post titles based on TF-IDF scores.")
    parser.add_argument('-o', '--output', required=True, help="Output file to save the TF-IDF scores (JSON format).")
    parser.add_argument('-s', '--stop-words', help="Optional stop word file. Words in this file will be excluded.")
    parser.add_argument('input_files', nargs='+', help="List of input JSON files containing post titles.")
    
    args = parser.parse_args()
    
    # Load stop words if a stop word file is provided
    stop_words = set()
    if args.stop_words:
        stop_words = load_stop_words(args.stop_words)
    
    # Process files and calculate TF-IDF scores
    tfidf_results = process_files(args.input_files, stop_words)
    
    # Save results to output JSON
    with open(args.output, 'w', encoding='utf-8') as output_file:
        json.dump(tfidf_results, output_file, indent=4)
    
    print(f"TF-IDF scores saved to {args.output}")

if __name__ == "__main__":
    main()
