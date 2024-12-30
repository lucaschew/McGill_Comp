# Explanation

I believe the counting problem is occuring when multiple of the same tweets are within the dataset. 
Since we do not check for duplicates, this could inflate the dataset as well as the filtered dataset, causing a decrease in the % of tweets that mention Trump.
This could be fixed by having a set and checking if the content has already be added to the list, and if not, then we add it to the set.