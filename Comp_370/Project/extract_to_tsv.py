import json
import random
import argparse
import csv
import sys

def extract_posts(input_file, output_file, num_posts):
    # Load the JSON data from the file
    try:
        with open(input_file, 'r') as f:
            data = json.load(f)
            posts = data.get('articles', {})
    except json.JSONDecodeError:
        print(f"Error: Failed to decode JSON from {input_file}")
        sys.exit(1)
    except FileNotFoundError:
        print(f"Error: File {input_file} not found")
        sys.exit(1)

    # Check if there are any posts
    total_posts = len(posts)
    if total_posts == 0:
        print("No posts found in the input file.")
        return

    # If num_posts exceeds total_posts, select all posts; otherwise, sample
    selected_posts = random.sample(posts, num_posts)

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
    with open(output_file, 'w', newline='', encoding='utf-8') as out_tsv:
        writer = csv.DictWriter(out_tsv, fieldnames=extracted_data[0].keys(), delimiter='\t')
        writer.writeheader()
        writer.writerows(extracted_data)

    print(f"Extracted {len(extracted_data)} posts to {output_file}.")

def main():
    # Run the extraction function
    extract_posts("data.json", "openCoding.tsv", 200)

if __name__ == "__main__":
    main()