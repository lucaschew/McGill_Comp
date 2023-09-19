#! /bin/bash
#set -x

#Error Checker

file=$1

if [[ -z $file ]]
then
	printf "Error: No log file given. \nUsage: ./webmetrics.sh <logfile> \n"
	exit 1
elif ! [[ -f $file ]]
then
	printf "Error: File \"$file\" does not exist. \nUsage: ./webmetrics.sh <logfile> \n"
	exit 2
fi

#Number of requests per web browser

printf "Number of requests per web browser \n"

safari=$(grep -c "Safari" $file)
fox=$(grep -c "Firefox" $file)
chrome=$(grep -c "Chrome" $file)

printf "Safari,$safari \nFirefox,$fox \nChrome,$chrome \n\n"


#Number of distinct users per day

printf "Number of distinct users per day \n"

declare -a dates
dates=$( cat $file | awk '{print substr($4, 2, index($4, ":")-2)}' | awk '!seen[$0]++ {print}')

for date in $dates
do
	count=$( grep $date $file | awk '{print $1}' | awk -v "count=0" '!seen[$0]++ {count++} END {print count}' )
	printf "$date,$count \n"

done

#Top 20 popular product requests

printf "\nTop 20 popular product requests\n"

prefix="/product/"

ids=$( cat $file | grep "GET /product/" | awk '{print $7}' | cut -d/ -f3 | cut -d/ -f1 | cut -d? -f1 | awk '{arr[$0]++} END {for (id in arr) print id " " arr[id]}' | sort -nr -k 2 -k 1  | uniq | head -n 20 | awk '{print $1","$2}' )

for id in $ids
do
	printf "$id \n"
done

exit 0
