from json import load

def load_and_sum_sales(years):

    total_sales = {}
    
    for year in years:
        total_sales[year] = 0

        book_sales = load(f"data/book_sales_{year}.csv")
        game_sales = load(f"data/game_sales_{year}.csv")

        total_sales[year] = sum_sales(book_sales, game_sales)
    
    return total_sales

# Define years
years = [2022, 2023, 2024]

# Load data and calculate total sales
total_sales = load_and_sum_sales(years)

# Access total sales for each year
total_sales_2022 = total_sales[2022]
total_sales_2023 = total_sales[2023]
total_sales_2024 = total_sales[2024]