import csv
import json
import argparse
from collections import defaultdict

def build_interaction_network(input_path, output_path):
    interaction_network = defaultdict(lambda: defaultdict(int))

    with open(input_path, 'r', encoding='utf-8') as f:
        reader = csv.reader(f)
        next(reader)  # Skip header row if present

        previous_speaker = None
        for row in reader:
            if len(row) < 3:
                continue  # Skip rows with insufficient columns

            speaker = row[2].strip().lower()
            
            # Skip invalid speakers
            if any(exclude in speaker for exclude in ["others", "ponies", "and", "all"]):
                previous_speaker = None
                continue

            # Add interaction if there's a valid previous speaker
            if previous_speaker and previous_speaker != speaker:
                interaction_network[previous_speaker][speaker] += 1
                interaction_network[speaker][previous_speaker] += 1

            previous_speaker = speaker

    # Write interaction network to JSON
    with open(output_path, 'w', encoding='utf-8') as f:
        json.dump(interaction_network, f, indent=4)

if __name__ == "__main__":
    parser = argparse.ArgumentParser(description="Build interaction network from dialog CSV.")
    parser.add_argument("-i", "--input", required=True, help="Path to input CSV file.")
    parser.add_argument("-o", "--output", required=True, help="Path to output JSON file.")
    args = parser.parse_args()

    build_interaction_network(args.input, args.output)
