#include <stdio.h>

#include <stdlib.h>

#include <math.h>


struct c_list {
  int ninfo;
  struct c_list * next;

};

struct c_list * createNode(int newninfo) { //return a pointer for our new node
  struct c_list * newNode;

  newNode = (struct c_list * ) calloc(1, sizeof(struct c_list));

  newNode -> ninfo = newninfo;
  newNode -> next = NULL;

  return newNode;
}

void printList(struct c_list * list) {

  struct c_list * temp = list; //pointer to our temp so we can travel in side linkedlist
  while (temp != NULL) {
    printf("%d ", temp -> ninfo);
    temp = temp -> next;

  }
  printf("\n");

}

void right_place(struct c_list ** list, int newninfo) {
  struct c_list * newNode = createNode(newninfo);

  if ( * list == NULL ) { //check if the list exist 
    newNode -> next = * list; //if its not exist it will put in the beginning
    * list = newNode;
    return;
  }
  if ( newninfo < ( * list) -> ninfo ) { //check if less den newninfo 
    newNode -> next = * list; //if its not exist it will put in the beginning
    * list = newNode;
    return;
  }
  
  struct c_list * temp = * list;
  while (temp -> next != NULL) {
    if (temp -> next -> ninfo > newninfo) {
      newNode -> next = temp -> next;
      temp -> next = newNode;
      return;

    }
    temp = temp -> next;

  }

  temp -> next = newNode;

}

void deleteNode(struct c_list ** list) { //delete first value in the linkedlist

  struct c_list * temp = * list;
  * list = ( * list) -> next;
  free(temp);
}

int main() {

  struct c_list * myList = NULL;

  printf("insert");
  printf("\n");
  for (int i = 0; i < 10; i++) {
    right_place( & myList, (rand() % 100));
    printList(myList);
  }

  printf("delete");
  printf("\n");
  for (int i = 0; i < 10; i++) {
    deleteNode( & myList);
    printList(myList);
  }

}