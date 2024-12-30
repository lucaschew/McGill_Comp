import pandas as pd
from bokeh.io import curdoc
from bokeh.models import ColumnDataSource, Select, Legend
from bokeh.plotting import figure
from bokeh.layouts import column

# Load the dataset
data = pd.read_csv('averageResponseTime.csv')

# Prepare the data for the year 2020
# Assuming the dataset contains janAvg, febAvg, ..., decAvg for the year 2020
months = ['January', 'February', 'March', 'April', 'May', 'June', 
          'July', 'August', 'September']

# Function to get the average for a given zipcode
def get_monthly_avg(zipcode):
    return data[data['zipcode'] == int(zipcode)].iloc[0, 1:].values

# Prepare the source for the plot
source = ColumnDataSource(data=dict(x=months, all_avg=[0,0,0,0,0,0,0,0,0] * 9, zipcode1_avg=[0,0,0,0,0,0,0,0,0], zipcode2_avg=[0,0,0,0,0,0,0,0,0]))

# Create the figure for the plot
plot = figure(title='Monthly Average Incident Response Time (in hours)', 
              x_axis_label='Month', 
              y_axis_label='Average Response Time (hours)', 
              x_range=months)

# Initial plot data
def update_data(zipcode1, zipcode2):
    # Get the average for zipcode1 and zipcode2
    avg_zipcode1 = get_monthly_avg(zipcode1)
    avg_zipcode2 = get_monthly_avg(zipcode2)
    
    # Get the average for all zipcodes
    avg_all = data.iloc[:, 1:].mean(axis=0).values
    
    # Update the data source
    source.data = {
        'x': months,
        'all_avg': avg_all,
        'zipcode1_avg': avg_zipcode1,
        'zipcode2_avg': avg_zipcode2
    }

# Initialize zipcodes for dropdowns
zipcodes = data['zipcode'].unique()
print(zipcodes)
zipcode1_select = Select(title="Select Zipcode 1:", value=str(zipcodes[0]), options=[str(zip) for zip in zipcodes])
zipcode2_select = Select(title="Select Zipcode 2:", value=str(zipcodes[1]), options=[str(zip) for zip in zipcodes])

# Create initial plot
update_data(zipcode1_select.value, zipcode2_select.value)

# Add the lines to the plot
plot.line('x', 'all_avg', source=source, line_width=2, color='blue', legend_label='All 2020 Data')
plot.line('x', 'zipcode1_avg', source=source, line_width=2, color='green', legend_label='Zipcode 1')
plot.line('x', 'zipcode2_avg', source=source, line_width=2, color='red', legend_label='Zipcode 2')

# Create legend
legend = Legend(items=[
    ("All 2020 Data", [plot.renderers[0]]),
    ("Zipcode 1", [plot.renderers[1]]),
    ("Zipcode 2", [plot.renderers[2]])
], location="top_left")
plot.add_layout(legend, 'right')

# Callback function for dropdowns
def update_plot(attr, old, new):
    update_data(zipcode1_select.value, zipcode2_select.value)

# Attach the callback to the dropdowns
zipcode1_select.on_change('value', update_plot)
zipcode2_select.on_change('value', update_plot)

# Layout the dashboard
layout = column(zipcode1_select, zipcode2_select, plot)

# Add the layout to the current document
curdoc().add_root(layout)
curdoc().title = "nyc_dash"