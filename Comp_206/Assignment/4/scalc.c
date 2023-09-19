/*
 * Program to implement a scientific calculator
 * ----------------------------------------------------------------------
 *  Author       Dept.      Date             Notes
 *-----------------------------------------------------------------------
 *  Lucas C      Arts       Oct 28 2020      Initial Version
 *  Lucas C      Arts       Oct 31 2020      Number Adding through Char
 */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

void errorMsg(int);
int intCheck(char *);
void sum(char a[], char b[]);

int main (int argc, char *argv[])
{
	//Check for # of arguments
	if (argc < 4)
		errorMsg(1);	

	
	//Check for decimals and positive ints
	int result;

	intCheck(argv[1]);
	intCheck(argv[3]);	

	//Operator Check
	char operator[] = "+";	
	result = strcmp(argv[2], operator);
	if (result != 0)
		errorMsg(2);

	//Printing System
	//int end = (int) num1 + (int) num2;
	
	sum(argv[1],argv[3]);
	
	return 0;
	//printf("%s + %s\n", argv[1], argv[3]);

}

void errorMsg(int type)
{
	//# of arguments
	//operator
	//positive int
	
	switch (type) {
	case 1:
		printf("Error: invalid number of arguments!\nscalc <operand1> <operator> <operand2>\n");
		break;
	case 2: 
		printf("Error: operator can only be + !\n");
		break;
	case 3: 
		printf("Error!! operand can only be positive integers\n");
		break;
	
	}
	exit(1);
}

int intCheck (char *input){

	char temp[strlen(input)];
	strcpy(temp, input);

	for (int i = 0; i < strlen(input); i++){

		if (isdigit(temp[i]) == 0){
			errorMsg(3);
			return 1;
		}

	}

	return 0;


}

int max(int a, int b){

	if (a > b)
		return a;
	else 
		return b;


}

void sum(char a[] , char b[]){

	int start=0;
	if (strlen(a) >= strlen(b))
		start = strlen(a);
	else
		start = strlen(b);
	
	int sum[start];
	int carry = 0;


	sum[0] = 0;

	for (int i = 1; i <= start; i++){
		
		int num1, num2;
		if ( ( ( (int) strlen(a) ) - i) < 0) 
			num1 = 0;
		else 
			num1 = max((a[strlen(a)-i]- '0'), 0);

		if ( ( ( (int) strlen(b) ) - i) < 0) 
			num2 = 0;
		else
			num2 = max((b[strlen(b)-i] - '0'), 0);

		

		sum[start-i] = num1    + num2    + carry;
		
		
		//printf("sum = %d (%d) + %d (%d) + %d ", num1, ((int) strlen(a)-1), num2, ((int) strlen(b)-1) , carry);

		carry = sum[start-i]/10;
		sum[start-i] %= 10;
	
		//printf("pos: %d sum: %d carry: %d \n", start-i, sum[start-i], carry);
	}

	if (carry == 1)
		printf("%d", carry);

	for (int i = 0; i < start; i++){
		printf("%d", sum[i]);
	}
	
	printf("\n");

}
