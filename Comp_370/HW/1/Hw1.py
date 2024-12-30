import csv
import re


def check_surrounding_chars(input_string):
    # Define the pattern to check if 'Trump' is surrounded by space, non-alphanumeric characters, or no characters
    pattern = r'(^|(?<!\w))Trump($|(?=\W))'

    # Search for the pattern in the input string
    match = re.search(pattern, input_string)

    return bool(match)


# Filter Data Section
filterTrump = True

with open('IRAhandle_tweets_1.csv', "r", newline='', encoding="utf8") as csvFile:
    reader = csv.reader(csvFile)

    #with open('output.tsv', 'w', newline='', encoding="utf8") as outputFile:
    with open('outputWithFilter.tsv', 'w', newline='', encoding="utf8") as outputFile:
        tsv_output = csv.writer(outputFile, delimiter='\t')

        for i, line in enumerate(reader):
            if i != 0:

                if i == 10000:
                    break

                if line[4] != "English":
                    continue

                if "?" in line[2]:
                    continue

                if filterTrump and not check_surrounding_chars(line[2]):
                    continue

            print(line[2])
            tsv_output.writerow(line)

# --------------------------------------------------------------------------------------------------------------------

with open('output.tsv', "r", newline='', encoding="utf8") as file:

    with open('outputWithFilter.tsv', 'r', newline='', encoding="utf8") as filteredFile:
        reader = csv.reader(file)
        filteredReader = csv.reader(filteredFile)

        lineCount = len(list(reader))
        filteredLineCount = len(list(filteredReader))

        with open('result.tsv', "w", newline='', encoding="utf8") as writeFile:

            writeFile.write("result\tvalue\n")
            writeFile.write("frac-trump-mentions\t" + str(round(filteredLineCount/lineCount, 3)))

