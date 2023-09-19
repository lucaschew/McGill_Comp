#include <stdlib.h>
#include <stdio.h>
#include "secret.h"

//secretapp -e # inputfile outputfile
int main(int argc, char *argv[]){
	
	if (argc != 5){
		printf("Error: Not Enough Variables Given\nExpected Input: secretapp -[ed] # inputFile.txt outputFile.txt\n");	//Error Checking for Input
		exit(1);
	}
	
	File *f = fopen(argv[3], "r");
	if (f == NULL){
		printf("File does not exist or is not readable");		//Error checking for whether file exists
		fclose(f);
		exit(1);
	}
	fclose(f);
	
	readFile(argv[3], argv[4], argv[2]);						//Calls functions from secret.c
	
}