import argparse
import pandas as pd
from datetime import datetime


def parse_arguments():
    parser = argparse.ArgumentParser(
        description="Count the number of each complaint type per borough for a given date range."
    )
    parser.add_argument(
        '-i', '--input', required=True, help="Path to the input CSV file."
    )
    parser.add_argument(
        '-s', '--start', required=True, help="Start date in MM/DD/YYYY format."
    )
    parser.add_argument(
        '-e', '--end', required=True, help="End date in MM/DD/YYYY format."
    )
    parser.add_argument(
        '-o', '--output', help="Path to the output file (optional). If not provided, results are printed."
    )

    return parser.parse_args()


def parse_date(date_str):
    """Parse a date string in the format MM/DD/YYYY to a datetime object."""
    return datetime.strptime(date_str, '%m/%d/%Y')



def read_and_process_complaints(file_path, start_date, end_date):
    """Read the CSV file using pandas and filter complaints by the specified date range."""
    # Read the CSV into a DataFrame
    df = pd.read_csv(file_path, header=None, usecols=[1, 5], names=['creation_date', 'complaint_type'])

    # Convert the 'creation_date' from "MM/DD/YYYY HH:MM:SS AM/PM" to pandas datetime object
    df['creation_date'] = pd.to_datetime(df['creation_date'], format='%m/%d/%Y %I:%M:%S %p')

    # Extract only the date part (DD/MM/YYYY) for filtering
    df['creation_date'] = df['creation_date'].dt.strftime('%m/%d/%Y')

    # Filter the DataFrame by the given date range
    df['creation_date'] = pd.to_datetime(df['creation_date'], format='%m/%d/%Y')
    mask = (df['creation_date'] >= start_date) & (df['creation_date'] <= end_date)
    filtered_df = df.loc[mask]

    # Group by borough and complaint type, then count occurrences
    grouped = filtered_df.groupby(['complaint_type']).size().reset_index(name='count').sort_values(by='count', ascending=False)

    return grouped


def print_or_save_results(grouped, output_file=None):
    """Print the results to stdout or save them to an output file."""
    output_lines = []

    # Loop through the grouped DataFrame and format the results
    for _, row in grouped.iterrows():
        complaint_type = row['complaint_type']
        count = row['count']
        # Append the formatted result as a single line: "complaint_type, borough, count"
        output_lines.append(f"{complaint_type}, {count}")

    # Output to file or print to stdout
    if output_file:
        with open(output_file, 'w') as f:
            f.write("\n".join(output_lines))
    else:
        print("\n".join(output_lines))


def main():
    args = parse_arguments()

    # Parse the start and end dates
    start_date = parse_date(args.start)
    end_date = parse_date(args.end)

    # Read and process the complaints
    grouped_complaints = read_and_process_complaints(args.input, start_date, end_date)

    # Output the results (either print or save to a file)
    print_or_save_results(grouped_complaints, args.output)


if __name__ == '__main__':
    main()