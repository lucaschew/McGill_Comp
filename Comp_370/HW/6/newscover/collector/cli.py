import json
import os
import argparse
from newscover.newsapi import fetch_latest_news

def main(api_key, lookback_days, input_file, output_dir):
    # Step 1: Load the input file
    with open(input_file, 'r') as file:
        keyword_dict = json.load(file)
    
    # Step 2: Create output directory if it doesn't exist
    os.makedirs(output_dir, exist_ok=True)

    # Step 3: Fetch news for each keyword set and save results
    for name, keywords in keyword_dict.items():
        print(f"Fetching news for {name} with keywords: {keywords}")
        results = fetch_latest_news(api_key=api_key, news_keywords=keywords, lookback_days=lookback_days)
        
        # Save the results to a JSON file
        output_file_path = os.path.join(output_dir, f"{name}.json")
        with open(output_file_path, 'w') as output_file:
            json.dump(results, output_file, indent=4)
        
        print(f"Results saved to {output_file_path}")

if __name__ == '__main__':
    # Step 4: Set up argument parsing
    parser = argparse.ArgumentParser(description='Fetch latest news based on keywords.')
    parser.add_argument('-k', '--api_key', required=True, help='API key for news service')
    parser.add_argument('-b', '--lookback', type=int, default=10, help='Number of days to look back')
    parser.add_argument('-i', '--input_file', required=True, help='Input JSON file with keyword lists')
    parser.add_argument('-o', '--output_dir', required=True, help='Directory to save output JSON files')

    args = parser.parse_args()

    # Call the main function with parsed arguments
    main(api_key=args.api_key, lookback_days=args.lookback, input_file=args.input_file, output_dir=args.output_dir)