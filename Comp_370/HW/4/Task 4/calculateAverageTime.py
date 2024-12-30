import pandas as pd

def calculate_average_hours(input_file_path, output_file_path):
    # Read the CSV file
    df = pd.read_csv(input_file_path, header=None, usecols=[0, 1, 2], names=['zipcode', 'month', 'hours_taken'])

    # Group by zipcode and month, and calculate the average hours_taken
    average_hours = df.groupby(['zipcode', 'month'], as_index=False)['hours_taken'].mean()

    # Pivot the table to have months as columns
    pivot_table = average_hours.pivot(index='zipcode', columns='month', values='hours_taken')

    # Fill NaN values with 0
    pivot_table.fillna(0, inplace=True)

    # Rename columns to match the required format
    pivot_table.columns = [f"{pd.to_datetime(str(int(month)), format='%m').strftime('%B')}Average" for month in pivot_table.columns]

    # Reset the index to have zipcode as a column
    pivot_table.reset_index(inplace=True)

    # Save the result to a new CSV file
    pivot_table.to_csv(output_file_path, index=False)

    print(f"Average hours taken have been calculated and saved to {output_file_path}")

# Example usage
if __name__ == "__main__":
    # Replace 'input_file.csv' and 'output_file.csv' with your actual file paths
    input_file = 'responseTime.csv'   # The CSV file to read
    output_file = 'averageResponseTime.csv'  # The output CSV file
    calculate_average_hours(input_file, output_file)