#! /bin/bash

declare -a files
files=("weblog1.txt" "weblog2.txt" "weblog3.txt")

for file in ${files[@]}
do
	printf "Web metrics for log file $file \n====================\n"
	output=`./webmetrics.sh $file`
	
	echo "$output"

	printf "\n\n"
done

exit 0


