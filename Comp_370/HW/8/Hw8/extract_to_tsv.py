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
            posts = data.get('data', {}).get('children', [])
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
    selected_posts = posts if num_posts >= total_posts else random.sample(posts, num_posts)

    # Extract relevant fields from each post
    extracted_data = [
        {
            'Name': post['data'].get('name', ''),
            'Title': post['data'].get('title', ''),
            'Coding': ""
        }
        for post in selected_posts
    ]

    # Write the selected posts to a TSV file
    with open(output_file, 'w', newline='', encoding='utf-8') as out_tsv:
        writer = csv.DictWriter(out_tsv, fieldnames=extracted_data[0].keys(), delimiter='\t')
        writer.writeheader()
        writer.writerows(extracted_data)

    print(f"Extracted {len(extracted_data)} posts to {output_file}.")

def main():
    # Parse command-line arguments
    parser = argparse.ArgumentParser(description="Extract random posts from a Reddit JSON file and output to TSV.")
    parser.add_argument('json_file', type=str, help="Path to the input JSON file.")
    parser.add_argument('-o', '--output', type=str, required=True, help="Path to the output TSV file.")
    parser.add_argument('num_posts', type=int, help="Number of posts to output.")

    args = parser.parse_args()

    # Run the extraction function
    extract_posts(args.json_file, args.output, args.num_posts)

if __name__ == "__main__":
    main()