#include <stdio.h>
#include <stdlib.h>
#include "linklist.h"
#include "lcheck.h"

int isSorted(struct node *head){
	
	//Assume that the node has property called next, referring to the next node
	
	int type = 0;		// 1 = decreasing array, -1 = increasing array
	
	while (head->next != NULL){						//Will run until the final node
		
		int result = cmp(head, head->next);
		
		if (type == 0 && result != 0)				//If type has not been set, set type accordingly
			type = result;
		if ( (type == 1 && result == -1) || (type == -1 && result == 1) )	//if the pattern is broken (excluding equals) then result false
			return 0;
		
		head=head->next;									//Iterate next node
	}
	
	return 1;										//If function loops finish, return true
	
}

int hasDuplicates(struct node *head) {
	
	if (!isSorted(head))							//If not sorted, return -1 (Error)
		return -1;
	
	while (head->next != NULL){
		
		int result = cmp(head,head->next);			//Compare the two nodes
		
		if (result == 0)							//If it is a duplicate, return 1
			return 1;
	
		head = head->next;							//Iterate next node
		
	}
	
	return 0;										//If no duplicates, return 0
	
}