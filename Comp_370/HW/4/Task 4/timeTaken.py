import pandas as pd
import numpy as np
from datetime import datetime

def calculate_hours(file_path):
    # Read the CSV file
    df = pd.read_csv(file_path, header=None, usecols=[1, 2, 8], names=['start_time', 'end_time', 'zipcode'])

    # Ensure start_time and end_time columns are in the correct format
    df['start_time'] = pd.to_datetime(df['start_time'], format='%m/%d/%Y %I:%M:%S %p', errors='coerce')
    df['end_time'] = pd.to_datetime(df['end_time'], format='%m/%d/%Y %I:%M:%S %p', errors='coerce')

    # Calculate the time taken in hours and round up
    df['hours_taken'] = np.where(
        (df['end_time'].isna()) | (df['end_time'] < df['start_time']),
        np.nan,
        np.ceil((df['end_time'] - df['start_time']).dt.total_seconds() / 3600)
    )

    # Drop rows with NaN values in the hours_taken column
    df = df.dropna(subset=['hours_taken', 'zipcode'])

    # Extract month from start_time
    df['month'] = df['start_time'].dt.month
    df['zipcode'] = df['zipcode'].astype(int)
    df['hours_taken'] = df['hours_taken'].astype(int)

    # Select relevant columns for the output
    result_df = df[['zipcode', 'month', 'hours_taken']]
    
    return result_df


# Replace 'tasks.csv' with your CSV file path
result_df = calculate_hours('trimed2020_data.csv')
print(result_df)

output_lines = []

# Loop through the grouped DataFrame and format the results
for _, row in result_df.iterrows():
    # Append the formatted result as a single line: "complaint_type, borough, count"
    output_lines.append(f"{row['zipcode']}, {row['month']}, {row['hours_taken']}")

# Output to file or print to stdout

with open("responseTime.csv", 'w') as f:
    f.write("\n".join(output_lines))
