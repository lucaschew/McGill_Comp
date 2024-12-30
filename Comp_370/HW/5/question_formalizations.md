# HW 5 Technical Questions

## Steps

**Objective 1:** Formalize the question in a way that you can actually answer. When you do this, follow the example given in class (online political violence) in which the question was iteratively refined. For each question, show at least four versions of the question.

- Version 1: the question as posed by the stakeholder.
- Version 2: one refinement of the question that is more measurable and maps better onto the available data.
- Version 3: a second refinement that maps even better onto the available data.
- Version 4: the final version of the question that is directly quantifiable and maps onto the available data.

**Objective 2:** Use python (Jupyter notebooks, CLIs, or any other pythonic approach) to build a visualization that credibly answers the question

## Task 1: Noise

### T1 - Objective 1

1. What type of noise complains come throughout different times in the year?
2. What is the noise complaint reasons for each month?
3. For the entirety of NY, what is the reason for a noise complaint grouped by each month?
4. For the entirety of NY, what is the noise complaint reason and its count for each month?

### T1 - Objective 2

```
import pandas as pd
import matplotlib.pyplot as plt

# Load the CSV file
file_path = 'trimed2020_data.csv'  # Replace with your actual file path
df = pd.read_csv(file_path)

# Extract relevant columns
df['Creation Date'] = pd.to_datetime(df.iloc[:, 1], format='%m/%d/%Y %I:%M:%S %p')  # Parse 'Creation Date'
df['Complaint Type'] = df.iloc[:, 5]  # Extract column 6 (Complaint Type)

# Filter complaint types that contain the word "noise" (case-insensitive)
df = df[df['Complaint Type'].str.contains('noise', case=False, na=False)]

# Remove the prefix "Noise - " from complaint types
df['Complaint Type'] = df['Complaint Type'].str.replace('Noise - ', '', case=False)

# Group by month
df['Month'] = df['Creation Date'].dt.strftime('%Y-%m')  # Extract month in 'YYYY-MM' format
complaint_group = df.groupby(['Month', 'Complaint Type']).size().unstack(fill_value=0)

# Determine the number of subplots needed
num_months = len(complaint_group)
num_cols = 3  # Adjust the number of columns based on your preference
num_rows = (num_months // num_cols) + (num_months % num_cols > 0)  # Calculate required rows

# Create subplots
fig, axes = plt.subplots(num_rows, num_cols, figsize=(15, num_rows * 5))
axes = axes.flatten()  # Flatten the 2D axes array for easier iteration

# Plot bar chart for each month
for i, month in enumerate(complaint_group.index):
    axes[i].bar(complaint_group.columns, complaint_group.loc[month], color='skyblue')
    axes[i].set_title(f'Noise-related Complaints for {month}', fontsize=12)
    axes[i].set_ylabel('Count')
    axes[i].set_xticklabels(complaint_group.columns, rotation=45, ha='right', fontsize=10)  # Rotate labels for readability

# Remove any empty subplots
for j in range(i + 1, len(axes)):
    fig.delaxes(axes[j])

# Adjust layout to avoid overlap between subplots
plt.tight_layout()
plt.subplots_adjust(wspace=0.4, hspace=0.6)  # Add space between plots

# Show plot
plt.show()
```

## Task 2: Urban Rodents

### T2 - Objective 1

1. Where in the city rats and mice are most likely to create sanitization issues?
2. Which boroughs in the city are sanitization issues most likely to occur due to rats and mice?
3. Which buildings in the city are sanitization issues mostly likely to occur due to rats and mice?
4. What is number of sanitization issues caused by rodents grouped by the type of building or structure?

### T2 - Objective 2

```
import pandas as pd
import matplotlib.pyplot as plt

# Load the CSV file
file_path = 'trimed2020_data.csv'  # Replace with your actual file path
df = pd.read_csv(file_path)

# Extract relevant columns
df['Complaint Type'] = df.iloc[:, 5]  # Extract column 6 (Complaint Type)
df['Location'] = df.iloc[:, 7]  # Extract column 8 (Location)

# Filter complaint types that contain the word "rodent" (case-insensitive)
df = df[df['Complaint Type'].str.contains('rodent', case=False, na=False)]

# Group by 'Location' and count occurrences
location_complaint_counts = df['Location'].value_counts()

# Plot bar graph
plt.figure(figsize=(10, 6))
location_complaint_counts.plot(kind='bar', color='skyblue')

# Add labels and title
plt.title('Rodent-Related Complaints by Location', fontsize=14)
plt.xlabel('Location', fontsize=12)
plt.ylabel('Number of Complaints', fontsize=12)

# Rotate x-axis labels for readability
plt.xticks(rotation=45, ha='right', fontsize=10)

# Show the plot
plt.tight_layout()
plt.show()
```
