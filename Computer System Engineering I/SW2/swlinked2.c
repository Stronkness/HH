#include <stdio.h>

#include <stdlib.h>

#include <math.h>


struct DListNode {
  int ninfo;
  struct DListNode * prev;
  struct DListNode * next;

};

struct DList {
  struct DListNode * head;
  struct DListNode * tail;
};

struct DListNode * createNode(int newninfo) { // create the only node we want to create 
  struct DListNode * newNode;

  newNode = (struct DListNode * ) calloc(1, sizeof(struct DListNode)); //check for the memory if its free for a neew node

  newNode -> ninfo = newninfo;
  newNode -> next = NULL;
  newNode -> prev = NULL;

  return newNode;
}

struct DList * createList() { //creat a new linked list that we can use
  struct DList * newList;
  newList = (struct DList * ) calloc(1, sizeof(struct DList));
  newList -> head = NULL;
  newList -> tail = NULL;
  return newList;
}

void printList(struct DList * list) { // print the whole list 

  printf("print list:");
  printf("\n");
  if (list == NULL) {
    return;
  }
  if(list -> head == NULL){
    return;
  }
  struct DListNode * temp = list -> head;
  while (temp != NULL) {
    printf("%d ", temp -> ninfo);
    temp = temp -> next;

  }
  printf("\n");

}

void right_place(struct DList ** list, int newninfo) { // insert on the right place 
  struct DListNode * newNode = createNode(newninfo);

  if ( * list == NULL) {
    * list = createList();
    ( * list) -> head = newNode;
    ( * list) -> tail = newNode;
    return;
  }

  if (newninfo < ( * list) -> head -> ninfo) {
    newNode -> next = ( * list) -> head;
    ( * list) -> head -> prev = newNode;
    ( * list) -> head = newNode; // head points to the first value.
    return;

  }
  struct DListNode * temp = ( * list) -> head;
  while (temp -> next != NULL) {
    if (newninfo < temp -> next -> ninfo) {
      newNode -> next = temp -> next;
      newNode -> next -> prev = newNode;
      temp -> next = newNode;
      newNode -> prev = temp;

      return;

    }
    temp = temp -> next;

  }
  temp -> next = newNode;
  newNode -> prev = temp;
  ( * list) -> tail = newNode;

}

void deleteNode(struct DList * list) {

  if (list == NULL) {
    return;
  }

  if(list -> head == NULL){
    return;
  }

  struct DListNode * temp; //delete first node
  temp = list -> head;
  if (temp == NULL) {
    list -> head = NULL;
    list -> tail = NULL;
    free(temp);
    return;

  }
  list -> head = list -> head -> next;
  free(temp);

}
int main() {

  struct DList * myList = NULL;

  printf("insert");
  printf("\n");
  for (int i = 0; i < 10; i++) {
    right_place( & myList, rand() % 100);
    printList(myList);
  }

  printf("\n");
  printf("delete");
  printf("\n");
  for (int i = 0; i < 10; i++) {
    deleteNode(myList);
    printList(myList);
  }

}