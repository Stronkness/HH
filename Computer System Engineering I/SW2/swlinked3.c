#include <stdio.h>

#include<stdlib.h>

#include<math.h>

#include<time.h>


struct DListNode {
  struct MsgObject * prMsgObj;
  struct DListNode * prev;
  struct DListNode * next;

};

struct DList {
  struct DListNode * head;
  struct DListNode * tail;
};

struct MsgObject {
  int nID;
  int nMsgType;
  /* 0 for standard message, 1 for priority
message */

};
struct MsgObject * createMsg(int ID, int Type) {
  struct MsgObject * newNode;

  newNode = (struct MsgObject * ) calloc(1, sizeof(struct MsgObject));
  newNode -> nID = ID;
  newNode -> nMsgType = Type;

  return newNode;
}

struct DListNode * createNode(int newData, int newType) {
  struct DListNode * newNode;

  newNode = (struct DListNode * ) calloc(1, sizeof(struct DListNode));

  newNode -> prMsgObj = createMsg(newData, newType);
  newNode -> next = NULL;
  newNode -> prev = NULL;

  return newNode;
}

struct DList * createList() {
  struct DList * newList;
  newList = (struct DList * ) calloc(1, sizeof(struct DList));
  newList -> head = NULL;
  newList -> tail = NULL;
  return newList;

}

void printAll(struct DList * list) {
  printf("print all: ");
  printf("\n");
  if (list == NULL) {
    return;
  }
  if(list -> head == NULL){
    return;
  }
  struct DListNode * temp = list -> head;
  while (temp != NULL) {
    printf("%d ", temp -> prMsgObj -> nID);
    temp = temp -> next;

  }
  printf("\n");

}

void printStandM(struct DList * list) {
  if (list == NULL) {
    return;
  }
  if(list -> head == NULL){
    return;
  }
  struct DListNode * temp = list -> head;
  while (temp != NULL) {
    if (temp -> prMsgObj -> nMsgType == 0) {
      printf("%d ", temp -> prMsgObj -> nID);

    }
    temp = temp -> next;

  }

  printf("\n");

}
void printPrioM(struct DList * list) {

  if (list == NULL) {
    return;
  }
   if(list -> head == NULL){
     return;
   }
  struct DListNode * temp = list -> head;
  while (temp != NULL) {
    if (temp -> prMsgObj -> nMsgType == 1) {
      printf("%d ", temp -> prMsgObj -> nID);

    }
    temp = temp -> next;

  }
  printf("\n");

}

void right_place(struct DList ** list, int newData, int newType) {
  struct DListNode * newNode = createNode(newData, newType);

  if ( * list == NULL) {
    * list = createList();
    ( * list) -> head = newNode;
    ( * list) -> tail = newNode;
    return;
  }

  if (newData < ( * list) -> head -> prMsgObj -> nID) {

    newNode -> next = ( * list) -> head;
    ( * list) -> head -> prev = newNode;
    ( * list) -> head = newNode;
    return;

  }
  struct DListNode * temp = ( * list) -> head;
  while (temp -> next != NULL) {
    if (newData < temp -> next -> prMsgObj -> nID) {
      newNode -> next = temp -> next;
      newNode -> next -> prev = newNode;
      temp -> next = newNode;

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

  struct DListNode * temp;
  temp = list -> head;
  if (temp -> next == NULL) {

    list -> head = NULL;
    list -> tail = NULL;
    free(temp);
    return;
  }
  list -> head = list -> head -> next;
  free(temp->prMsgObj);
  free(temp);

}

int main() {

  srand(time(NULL));
  struct DList * myList = NULL;

  printf("print ");
  printf("\n");
  for (int i = 0; i < 10; i++) {
    right_place( & myList, (int)(rand() % 100), (int)(rand() % 2));
  }
  printAll(myList);

  printf("\n");
  printf("priority ");
  printPrioM(myList);

  printf("\n");
  printf("standard ");
  printStandM(myList);

  printf("\n");
  printf("delete ");
  printf("\n");
  for (int i = 0; i < 10; i++) {
    deleteNode(myList);
    printAll(myList);

  }

}