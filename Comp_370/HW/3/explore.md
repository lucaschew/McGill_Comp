# Comp 370 - Homework 3

1. How big is the dataset?

   - 36860
   - find /v /c '""' clean_dialog.csv

2. What’s the structure of the data? (i.e., what are the field and what are values in them)

   - The format is:
     - Title/Episode Name
     - Writer
     - Pony
     - Dialog
   - gc .\clean_dialog.csv | select -first 10

3. How many episodes does it cover?

   - 197
   - (Import-Csv -path .\clean_dialog.csv | Select-Object -ExpandProperty title Unique).Count

4. During the exploration phase, find at least one aspect of the dataset that is unexpected – meaning that it seems like it could create issues for later analysis.

   - An issue of the dataset that could occur is that some of the Pony's sometimes respond with a noise, such as "hmm". This could be an issue if we were to track the number of times a character speaks, as it is not necessairly a word, but more of a sound.
