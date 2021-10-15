#include <stdio.h>

#include <stdlib.h>

#include <math.h>


struct c_list {
  int max;
  int min;
  int avg;
  int amount;
  struct c_list * next;

};

struct c_list * createNode() { //return a pointer for our new node
  struct c_list * newNode;

  newNode = (struct c_list * ) calloc(1, sizeof(struct c_list));

  newNode -> max = (-99);
  newNode -> min = 99;
  newNode -> avg = 0;
  newNode -> amount = 0;
  newNode -> next = NULL;

  return newNode;
}


void put(struct c_list **prRefList, struct c_list *prObj)
{
    /* Temporary pointer at first node in list */
    struct c_list *prTemp = *prRefList;

    if (*prRefList != NULL) {
	/* if list exist */
	while (prTemp->next != NULL) {
	    /* Traverse the list to the end */
	    prTemp = prTemp->next;
	}
	/* We have found the end of the list */
	prTemp->next = prObj;	/* Connect the last node */
    } else {
	/* List was empty, so the new list _is_ the node provided */
	*prRefList = prObj;
    }
}



void deleteNode(struct c_list ** list) { //delete first value in the linkedlist

  struct c_list * temp = * list;
  * list = ( * list) -> next;
  free(temp);
}

