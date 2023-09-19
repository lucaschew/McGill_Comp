#! /bin/bash
#set -x

dirInput=$1
dirOutput=$2
currentDate=$(date +%Y%m%d)

#Error Checking

#Input Empty

if [[ -z "$1" || -z "$2" ]]
then
	printf "Error: Expected two input parameters \nUsage: ./backup.sh <outputLocation> <fileOrDirectoryToBackup>\n"
	exit 1
fi

#Create Path

tempPath=$(basename "$2")
path=${tempPath%.*}

#echo "$tempPath"

#Exists

if ! [[ -f $2 || -d $2 ]]
then
	echo "Error: The File or Directory \"$2\" Does Not Exist"
	exit 2
elif ! [[ -d $1 ]]
then
	echo "Error: Backup directory \"$1\" does not exist"
	echo "Usage: ./backup.sh <backupdirectory> <fileordirtobackup>"
	exit 2
elif [[ "realpath $1" = "realpath $2" ]]
then
	echo "Error: Paths are Identical"
	exit 2
fi

#Overwrite

if [[ -f $1/$path.$currentDate.tar ]]
then
	echo "Error: The Backup File $1/$path.$currentDate.tar Already Exists. Overwrite (y/n)?"
        read answer
	
	if ! [[ $answer = "y" ]]
	then
		echo "Error: File already exists. Not Overwriting"
		exit 3
	fi
fi

#Backup Code


#1: Location   2:FilesYouTar
#echo "$1/$path.$currentDate.tar"
tar -cf "$1/$path.$currentDate.tar" $dirOutput > /dev/null 

#echo "Backup Successful"
exit 0
