#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include "secret.h"
#include "crypto.h"		//void encode (char *arr, int key);

char *swapWords(char[] line){
	
	char *temp = line;									//Let pointer access string
	char *token = strtok_r(temp, " ", &temp)			//Tokenize the string
	
	char **arr = malloc(sizeof(line) * sizeof(char *));	//Create an array to hold the tokens
	
	int arraySize;										//Counter keeps track of the size of the array
	
	for (int i = 0; token != NULL; i++){
		
		arr[i] = token;									//Set array[i] to the value of the tokens
		token = strtok_r(temp, " ", &temp);				//Take new token
		arraySize=i;									//Add size
	}
	
	char result[sizeof(line)+1] = "";					//Create array to hold char
	
	for (int i = arraySize; i > -1; i--) {				//Iterate through the array (top to bottom)
		
		strcat(result, arr[i]);							//Add word to array
		
		if (i != 0)										//While it is not the last word, add a space
			strcat(result, " ");
		
	}
	
	char *abc = result;									//Set it back to a pointer
	
	free(arr);											//Return memory 
	
	return abc;											//Return
	
	
}

void addToFile(char* fileName, char *line){
	
	File *output = fopen(fileName, "a");		//Open file and append
	fprintf(output, "%s\n", line);				//Print line
	fclose(output);								//Close File
	
}

void readFile(char *inputFile, char* outputFile, int encode) {
	
	File *f = fopen(inputFile, "r");			//Open File
	
	char[1001] line;
	
	while (fgets(line, sizeof(line), f) != NULL) {		//Read Line
		
		char *tempLine = swapWords(line);				//Swap Words
		encode(tempLine, encode);						//Encode
		addToFile(outputFile, tempLine);				//Print to file
		
	}
	
	fclose(f)											//Close File
	
}