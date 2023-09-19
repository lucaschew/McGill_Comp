#! /bin/bash

threshold=$1	//Set Threshold

if [[ $1 -z ]]
then
	$threshold=7		//If no input given, threshold = 7
fi

declare -a dirArray

for dir in *		//Iterate through the directory
do
	name = $(basename $dir)			//Length of folder name
	if [[ $dir -d && $name -eq 9 ]]	//If the location is a directory and is a student ID
	then
		dirArray+=($name)			//Add Directory to array
	fi
done

count=1								//Iteration to stop repeats
for ID in "${dirArray[@]}"
do
	
	for ID2 "${dirArray[@]:$count}"											//Array with offset
	do
		if [[ "${ID}/a2/srcdiff.sh" -r && "${ID2}/a2/srcdiff.sh" -r]]		//Check if Files is Readable and Exists
		then
			result=$(kscore ${ID}/a2/srcdiff.sh ${ID2}/a2/srcdiff.sh)	//Get Score
			if [[ $result -gt $threshold ]]
			then
				echo ${result},${ID}/a2/srcdiff.sh,${ID2}/a2/srcdiff.sh	//Output
			fi
		fi
		
		if [[ "${ID}/a2/webmetrics.sh" -r && "${ID2}/a2/webmetrics.sh" -r]]		//Check if Files is Readable and Exists
		then
			result=$(kscore ${ID}/a2/webmetrics.sh ${ID2}/a2/webmetrics.sh)	//Get Score
			if [[ $result -gt $threshold ]]
			then
				echo ${result},${ID}/a2/webmetrics.sh,${ID2}/a2/webmetrics.sh	//Output
			fi
		fi
	done
	
	((count++))																	//Increment Offset
done
