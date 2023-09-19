#include <stdio.h>
#include <string.h>

//CSV File, Student Name, Report File
int main(int argc[], char *argv[]) {

	FILE *f = fopen(argv[1], "r");

	char line[200];

	fgets(line, 200, f);

	//printf("%s", line);
	
	char * name = strtok(line, ",");

	printf("%s\n", name);

	char * temp[200];

	if (temp[1] == NULL)
		printf("temp is null");
	

}

