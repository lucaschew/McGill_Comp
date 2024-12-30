import pandas as pd
import json
import re
from collections import Counter, defaultdict
from math import log


def clean_text(text):
    """Clean the input text by removing punctuation and converting to lowercase."""
    return re.sub(r"[^\w\s]", "", text).lower()


def load_excel_file(file_path):
    """
    Load titles, categories, and types from the specified Excel file.
    """
    try:
        df = pd.read_excel(file_path)
        df.columns = ['Title', 'Unused_Column', 'Category', 'Type']
        return df[['Title', 'Category', 'Type']]
    except FileNotFoundError:
        print(f"Error: File {file_path} not found.")
        return pd.DataFrame()
    except Exception as e:
        print(f"Error reading Excel file {file_path}: {e}")
        return pd.DataFrame()


def calculate_tfidf(word_counts, total_words, document_frequencies, num_documents):
    """Calculate the TF-IDF scores for each word."""
    tfidf_scores = {}

    for word, count in word_counts.items():
        tf = count / total_words
        idf = log(num_documents / len(document_frequencies[word]))
        tfidf_scores[word] = tf * idf
    return tfidf_scores


def process_excel(file_path, stop_words):
    """
    Process an Excel file to compute TF-IDF scores grouped by categories.
    """
    df = load_excel_file(file_path)
    if df.empty:
        return {}

    category_document_frequencies = defaultdict(Counter)
    category_word_counts = defaultdict(lambda: defaultdict(Counter))
    category_total_words = defaultdict(lambda: defaultdict(int))
    category_type_counts = defaultdict(Counter)
    category_num_documents = Counter()

    # First pass: calculate term frequencies and document frequencies per category
    for _, row in df.iterrows():
        title = row['Title']
        category = row['Category']
        _type = row['Type']

        if pd.isna(title) or pd.isna(category):
            continue

        words = clean_text(title).split()

        # Remove stop words
        words = [word for word in words if word not in stop_words]

        # Count word frequencies for this title
        word_counts = Counter(words)
        category_word_counts[category][file_path] += word_counts
        category_total_words[category][file_path] += len(words)

        # Update document frequencies for this category
        category_document_frequencies[category].update(set(word_counts.keys()))

        # Count type frequencies for each category
        category_type_counts[category][_type] += 1

        # Increment document count for this category
        category_num_documents[category] += 1

    # Initialize the dictionary
    word_to_categories = defaultdict(set)

    # Iterate through categories and words
    for category, word_counts in category_document_frequencies.items():
        for word in word_counts:
            word_to_categories[word].add(category)

    # Convert defaultdict to a regular dict if needed
    word_to_categories = dict(word_to_categories)

    # Second pass: calculate TF-IDF scores per category
    tfidf_results = {}
    for category, document_frequencies in category_document_frequencies.items():
        tfidf_results[category] = {"TF-IDF Scores": {}, "Type Counts": category_type_counts[category]}

        for file_path, word_counts in category_word_counts[category].items():
            total_words = category_total_words[category][file_path]
            tfidf_scores = calculate_tfidf(word_counts, total_words, word_to_categories, len(category_num_documents))

            # Get top 10 words by TF-IDF score
            top_words = sorted(tfidf_scores.items(), key=lambda x: x[1], reverse=True)[:10]
            tfidf_results[category]["TF-IDF Scores"][file_path] = top_words

    return tfidf_results


def main():
    import argparse

    parser = argparse.ArgumentParser(description="Compute the top 10 words in titles by categories based on TF-IDF scores.")
    parser.add_argument("-i", "--input", required=True, help="Input Excel file containing titles, categories, and types.")
    parser.add_argument("-o", "--output", required=True, help="Output file to save the TF-IDF scores (JSON format).")
    parser.add_argument("-s", "--stop-words", help="Optional stop word file. Words in this file will be excluded.")

    args = parser.parse_args()

    # Load stop words if a stop word file is provided
    stop_words = set()
    if args.stop_words:
        try:
            with open(args.stop_words, "r", encoding="utf-8") as file:
                stop_words = set(line.strip().lower() for line in file)
        except FileNotFoundError:
            print(f"Error: Stop word file {args.stop_words} not found.")
        except UnicodeDecodeError as e:
            print(f"Error decoding stop word file {args.stop_words}: {e}")

    # Process Excel file and calculate TF-IDF scores
    tfidf_results = process_excel(args.input, stop_words)

    # Save results to output JSON
    with open(args.output, "w", encoding="utf-8") as output_file:
        json.dump(tfidf_results, output_file, indent=4)

    print(f"TF-IDF scores saved to {args.output}")


if __name__ == "__main__":
    main()
