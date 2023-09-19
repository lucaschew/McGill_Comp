#! /bin/bash
#set -x

dir1=$1
dir2=$2

num=0

#Error Check

#Input

if [[ -z $1 || -z $2 ]]
then 
	printf "Error: Expected Two Input Parameters (Directories) \nUsage: ./srcdiff.sh <originalDirectory> <comparisonDirectory>\n"
	exit 1

elif ! [[ -d $1 ]]
then
	printf "Error: Input Parameter #1 \"$1\" is not a directory \nUsage: ./srcdiff.sh <originalDirectory> <comparisonDirectory>\n"
	exit 2

elif ! [[ -d $2 ]]
then
	printf "Error: Input Parameter #2 \"$2\" is not a directory \nUsage: ./srcdiff.sh <originalDirectory> <comparisonDirectory>\n"
	exit 2

elif [[ "realpath $1" = "realpath $2" ]]
then
	printf "Error: Paths are Identical \nUsage: ./srcdiff.sh <originalDirectory> <comparisonDirectory>\n"
	exit 2

fi

if [ "$(ls -A $dir1)" ]
then
	for n in $dir1/*
	do
	
		if [[ -f "$dir2/${n##*/}" ]]
		then 

			diff "$n" "$dir2/${n##*/}" > /dev/null
			output=$?
		
			if [[ $output != 0 ]]
			then
				echo "`realpath $n` differs"
				num=1
			fi
		else
			echo "`realpath $dir2`/${n##*/}" is missing 
			num=1
		fi
	done
fi

if [ "$(ls -A $dir2)" ]
then
	for n in $dir2/*
	do
		if ! [[ -f $dir1/${n##*/} ]]
		then
			
			if [[ $n = '*' ]]
			then
				continue
			fi
		
			echo "`realpath $dir1`/${n##*/}" is missing 
			num=1
		fi
	done
fi
	
if [ $num -ne 0 ]
then
	exit 3
else
	exit 0
fi
