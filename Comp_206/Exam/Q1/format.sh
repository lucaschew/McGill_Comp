#! /bin/bash

doc=$1

if [[ $doc -z || $doc -r]]					//Check if file is given or Readable
then
	echo "Error: File is not Given, Readable, or Exists"
	exit 1
fi

while read line						//Iterate through file, line by line
do	
	echo $line | $(sed -e 's/\//,/g' | awk -F',' '{printf"%s,%s,%s,%s,%s\n", $2,$5,$3,$4,$1}')	//Replace "/" with "," in sed. Then delimit on "," then output using awk
done < $doc