volatile unsigned int Ticks ; /* global systick counter */
volatile unsigned int KernalMode ; /* can equal either INIT o r RUNNING */
TCB *PreviousTask , *NextTask; /*changed on the 4th of March */
list *ReadyList , *WaitingList , *TimerList;

TCB* alloc_tcb(void){
	TCB* temp= (TCB *) calloc(1, sizeof(TCB));// allocate memory to TCB struct and send null if its not allocated
	if(!temp){return NULL;}
	return temp;
}
listobj* alloc_listobj(void){
	listobj* temp= (listobj *) calloc(1,sizeof(listobj));//allocate memory to listobj and send null if its not allocated
	if(!temp ){return NULL;}
	return temp;
}
list* alloc_list(void){//allocate memory to list and send null if its not allocated
  list* temp = (list *) calloc(1,sizeof(list));
  if(!temp){return NULL;}
  return temp;
}
msg* alloc_message(void){//allocate memory to list and send null if its not allocated
  msg* temp= (msg *) calloc(1,sizeof(msg));
  if(!temp){return NULL;}
  return temp;
}
mailbox* alloc_mailbox(void){//allocate memory to list and send null if its not allocated
  mailbox* temp= (mailbox *) calloc(1,sizeof(mailbox));
  if(!temp){return NULL;}
  return temp;
}
listobj *extractlistobj(listobj * pObj)
{//take out the listobj and connect next and previous together and disconnect and return the obj
    pObj->pPrevious->pNext = pObj->pNext;
    pObj->pNext->pPrevious = pObj->pPrevious;
    pObj->pNext = NULL;
    pObj->pPrevious = NULL;
    return pObj;
}
msg *  extractMessage(mailbox * pMailbox)
{//take out the message from the mailbox and connect next and previous together and disconnect and return the message
  msg *msg = pMailbox->pHead->pNext;
    msg->pPrevious->pNext = msg->pNext;
    msg->pNext->pPrevious = msg->pPrevious;
    if(pMailbox->nMessages > 0){
      pMailbox->nMessages--;
    }
    return msg;
}
listobj *createObject(TCB * tcb){
  listobj *temp = alloc_listobj();//use the alloc_listobj and send in a TCB inside
  if(temp == NULL){
    return NULL;
  }else{
  temp->pTask = tcb;
  temp->pNext= NULL;
  temp->pPrevious= NULL;
  }
  return temp;
}

list *createlist(){
  list * theList;//allocate memory for the list and for 2 dummie nodes for the Head and Tail and link them together in a wrap around
  theList = alloc_list();
  theList->pHead = alloc_listobj();
  theList->pTail = alloc_listobj();
  theList->pHead->pNext  = theList->pTail;
  theList->pTail->pPrevious = theList->pHead;
  theList->pHead->pPrevious = theList->pHead;
  theList->pTail->pNext = theList->pTail;
 return theList;
  }

void sort(list* scrambled){//sort the list
	if(scrambled->pHead->pNext == scrambled->pTail)//if the list is empty
		return;

	listobj* temp = scrambled->pHead->pNext;
	while(temp->pNext != scrambled->pTail){//go farward in the linkedlist
		if(temp->pNext->pTask->Deadline < temp->pTask->Deadline){//check the deadlines
			temp->pPrevious->pNext = temp->pNext;//rearrange the list
			temp->pNext->pPrevious = temp->pPrevious;
			temp->pPrevious = temp->pNext;
			temp->pNext = temp->pPrevious->pNext;
			temp->pPrevious->pNext = temp;
		}
		else{
			temp = temp->pNext;//go to next in the list
		}
	}
	scrambled->pTail->pPrevious = temp;
}

exception putSort(list *prRefList, listobj *prObj)
{//this it the insert for our linklist operation there we always have the list sorted from the beginning
    listobj *prTemp = prRefList->pHead;
    if(prRefList == NULL || prObj == NULL){
      return FAIL;
    }
    while(prTemp->pNext != prRefList->pTail && prTemp->pNext->pTask->Deadline < prObj->pTask->Deadline){//go in the linklist to the right position with deadline we sort after and check if we are at the end
      prTemp= prTemp->pNext;
    }
    prObj->pNext = prTemp->pNext;//put the listobj in the linklist with right connection
    prObj->pPrevious = prTemp->pPrevious;
    prTemp->pNext = prObj;
    prObj->pNext->pPrevious = prObj;
    return OK;
}

listobj* extract(list* list){//extract for the list there we take the first listobj in the list we send in. disconnect and connect and return the listobj
  listobj * temp = list->pHead->pNext;
    if(temp != NULL){
      list->pHead->pNext = list->pHead->pNext->pNext;//connect the listobjects
      list->pHead->pNext->pPrevious= list->pHead;
      temp->pNext =NULL;//disconnect the listobjects
      temp->pPrevious =NULL;
      return temp;}
  return NULL;

}
exception wait(uint nTicks){//will block a task with nTicks return OK/DEADLINE_REACHED 
    isr_off();
    exception status;
    PreviousTask = NextTask;//update previoustask
    ReadyList->pHead->pTask->Deadline=ReadyList->pHead->pTask->Deadline + nTicks;
    ReadyList->pHead->nTCnt = nTicks;
    putSort(TimerList, extract(ReadyList));//place the task in timerlist
    NextTask = ReadyList->pHead->pNext->pTask;//update the nexttask
    SwitchContext();
    if(NextTask->Deadline <= Ticks){
      status = DEADLINE_REACHED;
    }else{
      status = OK;
    }
    return status;
}
void set_ticks(uint nTicks){//give the value on Ticks
    Ticks = nTicks;
}

uint ticks(void){//take out the ticks and return it
    return Ticks;
}
uint deadline(void){//return the deadline on the nexttask
    return NextTask->Deadline;
}
void TimerInt(void){//this function is not called ny the user and will
  listobj *temp = TimerList->pHead->pNext;
  Ticks++;//increment tick counter
  while(temp != TimerList->pTail)//loop the timer list until the endof the timerlist
  {
    if(temp->nTCnt == 0 || temp->pTask->Deadline <= Ticks)//check for the nTCnt to be 0 or a deadline have reached
    {
      temp = temp->pNext;//go forward in linkedlist
      PreviousTask = NextTask;//update the previoustask
      putSort(ReadyList,extractlistobj(temp->pPrevious));//put the ready task to the readylist
      NextTask = ReadyList->pHead->pNext->pTask;//update nexttask
      }
    temp->nTCnt--;//decrement
    temp = temp->pNext;//go forward in the linkedlist
   }
  while(WaitingList->pHead->pNext->pTask->Deadline <= Ticks && WaitingList->pHead->pNext != WaitingList->pTail)//check if the deadline have reached and if we are at the tail
    {
      PreviousTask = NextTask;//update previoustask
      putSort(ReadyList, extract(WaitingList));//put the reached task in the readylist
      NextTask = ReadyList->pHead->pNext->pTask;//update the nexttask
     }
}

mailbox* create_mailbox(uint nMessages, uint nDataSize){//create mailbox with dummie nodes in the head and tail and wrap around and return a mailbox or fail
    mailbox *newMailbox;
    newMailbox  = alloc_mailbox();// allocate memory for the mailbox
    msg *Dhead = alloc_message();//allocate memory for the message
    msg *Dtail = alloc_message();
    if (!Dhead || !Dtail || !newMailbox){//check NULL on the messages
       return FAIL;
     }
    newMailbox->nDataSize = nDataSize;//initialize the mailbox with maxMessages and Datasize and connect the dummie messages
    newMailbox->nMaxMessages = nMessages;
    newMailbox->nMessages = 0;
    newMailbox->nBlockedMsg = 0;
    Dhead->pNext = Dtail;
    Dtail->pPrevious = Dhead;
    newMailbox->pHead = Dhead;
    newMailbox->pTail = Dtail;
    return newMailbox;
}
msg* create_message(char *Data){//create the message with a data and return the message
  msg* newMsg = alloc_message();//allocate memory for the message
    if(!newMsg)
    {
      return NULL;
    }
    newMsg->pData = Data;//initialize the message with the data
    newMsg->Status = NULL ;
    newMsg->pBlock = NULL;
    newMsg->pPrevious = NULL;
    newMsg->pNext = NULL;
    return newMsg;
}
exception insert_message(mailbox* mBox, msg* message, exception status){//insert the message in the mailbox with a status and return ok or fail
  msg * temp = mBox->pHead->pNext;//go to the beginning of the mailbox
 message->Status = status;
 mBox->nMessages++;
 if(temp == mBox->pTail)//check if its the tail then insert it
 {
  mBox->pHead->pNext = message;
  mBox->pTail->pPrevious = message;
  message->pPrevious = mBox->pHead;
  message->pNext = mBox->pTail;
 }
 else
 {
   while(temp->pNext != mBox->pTail)//go to tail
   {
     temp = temp->pNext;
   }
   message->pNext = temp->pNext;//insert it last
  temp->pNext->pPrevious = message;
  temp->pNext = message;
  message->pPrevious = temp;
 }
 return OK;
}

exception remove_mailbox(mailbox* mBox){//remove the mailbox and return ok or not_empty
  if(mBox->pHead->pNext == mBox->pTail){//check if heads next is equal to tail then we can free it return OK
    free(mBox->pHead);
    free(mBox->pTail);
    free(mBox);
    return OK;
  }else{
    return NOT_EMPTY;//not empty and then send not_Empty
  }
    }

exception send_wait(mailbox* mBox, void* Data){//this function will send a message to a receiving task if not fund it will wait until it come one that will receive the message returns OK or fail
    isr_off();
    if(mBox->pHead->pNext->Status == RECEIVER)//check in the mailbox heads next if its a recevier then we can send the data and unblock the receving task
   {
     memcpy(mBox->pHead->pNext->pData, Data, mBox->nDataSize);//copy data to mailbox heads next pdata with data
     msg *temp = extractMessage(mBox);//extract the message
     PreviousTask = NextTask;//update previoustask
     putSort(ReadyList,extractlistobj(temp->pBlock));//insert the task in pBlock to the Readylist sorted
     NextTask = ReadyList->pHead->pNext->pTask;//update nexttask
     mBox->nBlockedMsg--;//decrement nBlockedMsg
   }
  else
  {
    msg* theMessage = alloc_message();
    theMessage->pData = (char*)calloc(1,mBox->nDataSize);//allocate memory for the data size
    if(!theMessage ||!theMessage->pData)//check if its NULL 
     return FAIL;
    if(mBox->nMaxMessages==mBox->nMessages)//if max message is equal to messages then free the oldest message
     free(extractMessage(mBox));
   else
     mBox->nBlockedMsg++;//increment blockedMsg
    memcpy(theMessage->pData, Data, mBox->nDataSize);//copy the data to message data
    theMessage->pBlock = ReadyList->pHead->pNext;//add the running listobj to pBlock
    insert_message(mBox, theMessage, SENDER);//insert message with message and Sender status
    PreviousTask = NextTask;//update previoustask
    putSort(WaitingList,extract(ReadyList));//insert in waiting list the sending task sorted
    NextTask = ReadyList->pHead->pNext->pTask;//update nexttask
  }
  SwitchContext();

  if(ReadyList->pHead->pNext->pTask->Deadline <= Ticks)//During the blocked period the task could have reached the deadline so we check it here.
  {
    isr_off();
    msg* temp = extractMessage(mBox);//extract the first message in the mailbox
    if( mBox->nBlockedMsg > 0)//check if blockedmsg is bigger then 0 then decremnet blocked message and free message and set the message box to null
      mBox->nBlockedMsg--;
    free(temp);
    ReadyList->pHead->pNext->pMessage = NULL;
    isr_on();
    return DEADLINE_REACHED;
  }
  else
    return OK;
}

exception receive_wait(mailbox* mBox, void* Data){//this function will receive a message from a sending tasj if not fund it will wait until it come one that will send the message. return OK or fail
    isr_off();
    if(mBox->pHead->pNext->Status == SENDER)//check in the mailbox heads next if its a receiver then we can send the data and unblock the sending task
     {
       memcpy(Data, mBox->pHead->pNext->pData, mBox->nDataSize);//copy data to data from mailbox heads next data
       msg *temp = extractMessage(mBox);//extract message from mailbox
       if(mBox->nBlockedMsg > 0)//check if blockedmsg is bigger then 0
       {
         PreviousTask = NextTask;//update previoustask
         putSort(ReadyList,extractlistobj(temp->pBlock));//insert listobj pblock in the readylist sorted
         NextTask = ReadyList->pHead->pNext->pTask;//update nexttask
         mBox->nBlockedMsg--;//decremnet blockedmsg
       }
       else{
         free(temp->pData);//free data and temp
      
         free(temp);
      mBox->nMessages--;//decremnet blockedmsg
       }
       
     }
    else{
    msg * theMessage = alloc_message();//allocate memory for the message
    theMessage->pData = (char*)calloc(1,mBox->nDataSize);//allocate memory for the datasize
    if(!theMessage || !theMessage->pData){//if null return fail
      return FAIL;
    }
    if(mBox->nMaxMessages==mBox->nMessages){//if max message is equal to messages then free the oldest message
          free(extractMessage(mBox));
    }
    else{
       mBox->nBlockedMsg++;//increment
    }
    theMessage->pData = Data;//put the data in message
    theMessage->pBlock = ReadyList->pHead->pNext;//put the running task in pBlock
    insert_message(mBox, theMessage, RECEIVER);//insert the message with message and the status recevier
    PreviousTask = NextTask;//update previoustask
    putSort(WaitingList,extract(ReadyList));//insert in waiting list the receving task
    NextTask = ReadyList->pHead->pNext->pTask;//update the nexttask
    }
   SwitchContext();
    if(ReadyList->pHead->pNext->pTask->Deadline <= Ticks)//During the blocking period of the task its deadline might be reached.
    {
      isr_off();
      msg*temp = extractMessage(mBox);//extract the message form the mailbox
      ReadyList->pHead->pNext->pMessage = NULL;
      mBox->nBlockedMsg--;//decrement the blockedmsg
      free(temp);//free the message
      isr_on();
      return DEADLINE_REACHED;
    }
    else
    {
      return OK;
    }
}

exception send_no_wait(mailbox* mBox,void* Data){//this function will send a message and continue executing returns ok or fail
    isr_off();
    if(mBox->pHead->pNext->Status == RECEIVER)//check in the mailbox heads next if its a recevier then we can send the data
     {
       memcpy(mBox->pHead->pNext->pData, Data, mBox->nDataSize);//copy data from mailbox heads next to data
       msg *temp = extractMessage(mBox);//extract the message
       PreviousTask = NextTask;//update the previoustask
       putSort(ReadyList,extractlistobj(temp->pBlock));//insert the block to the readylist sorted
       NextTask = ReadyList->pHead->pNext->pTask;//update the nexttask
       SwitchContext();
     }
    else
    {
      //send_wait and send_no_wait dont use the same mailbox.
      if(mBox->nBlockedMsg > 0)//check if mailbox blockedmsg is bigger then 0
        return FAIL;
          msg * theMessage = alloc_message();//allocate memory for the message
      theMessage->pData = (char*)calloc(1,mBox->nDataSize);//allocate fot the datasize
      if(!theMessage || !theMessage->pData)//check if its NULL then return fail
          return FAIL;
      memcpy(theMessage->pData, Data, mBox->nDataSize);//copy the message data to data
      mBox->nBlockedMsg = 0;//set the blocked message to 0
      if(mBox->nMaxMessages == mBox->nMessages)//if max message is equal to messages then free the oldest message
     {
       free(extractMessage(mBox));
     }
      insert_message(mBox, theMessage, SENDER);//insert the message with the message and status to sender
     isr_on();
    }
   return OK;
}

exception receive_no_wait(mailbox* mBox, void* Data){//this function will recevie a message and continue executing. returns ok or fail
   isr_off();
   if(mBox->pHead->pNext->Status == SENDER)//check if the mailbox heads next if its a sender then we can recevie the data
    {
      memcpy(Data,mBox->pHead->pNext->pData,mBox->nDataSize);//copy data from data to the message
      msg* theMessage = extractMessage(mBox);//extract the message
      if(mBox->nBlockedMsg > 0)//check if mailbox blockedmsg is bigger then 0
      {
        
        PreviousTask = NextTask;//update previoustask
        putSort(ReadyList, extractlistobj(theMessage->pBlock));//insert the block to the readylist sorted
        NextTask = ReadyList->pHead->pNext->pTask;//update the nexttask
        SwitchContext();
      }
      else
      {
        free(theMessage->pData);//free the data and temp
        free(theMessage);
      }
    }
    else
    {
    isr_on();
    return FAIL;
    }
  isr_on();
  return OK;
}

void idle_task(void){//while(1)
  while(1){
  }
}

exception init_kernel(){ //start up the kernal
 Ticks = 0;
 ReadyList = createlist();//create the dummie nodes for the lists
  WaitingList = createlist();
  TimerList = createlist();
  if(!ReadyList&& !WaitingList && !TimerList){//check if the list is null
    return FAIL;
  }
  uint  statetask = create_task(&idle_task,INT_MAX);//make idle task
 KernalMode = INIT; //RUNNING = 1
 if (statetask == OK){
  return OK;
 }else{
  return FAIL;
 }
}


void terminate(void){ //will terminate the running task
 isr_off();
 listobj *leavingObj = extract(ReadyList);//extract listobj of the running task
 NextTask = ReadyList->pHead->pNext->pTask;//update the nexttask
  switch_to_stack_of_next_task();
  free(leavingObj->pTask);//free the memory from the running task
  free(leavingObj);
  LoadContext_In_Terminate();
    /*        -- supplied to you in the assembly file ---------<
     *  does not save any registers; in specifik, does not save the
     *  process stack pointer (psp)
     *  but simply restores registers from saved values
     *  from the TCB of NextTask
     *  note: the stack pointer is restored from NextTask-SP
     */
}
exception create_task(void(*taskBody)(), unsigned int deadline){ //this function will create a task and return ok or fail
 TCB *new_tcb;
 new_tcb = alloc_tcb();//allocate memory for tcb
  if(!new_tcb){
    return FAIL;
  }
  new_tcb->    PC  = taskBody;//initialize the TCB
 new_tcb->    SPSR = 0x21000000;
 new_tcb->    Deadline = deadline;
 new_tcb->    StackSeg[STACK_SIZE - 2] = 0x21000000;
 new_tcb->    StackSeg[STACK_SIZE - 3] = (uint) taskBody;
 new_tcb->    SP = &(new_tcb->StackSeg[STACK_SIZE - 9]);
 if(KernalMode == INIT){//if its not running krenal
   putSort(ReadyList, createObject(new_tcb));//insert the tcb in the readylist sorted
    return OK;
}else{
   isr_off();//if running kernal
  PreviousTask = NextTask;// update PreviousTask
  putSort(ReadyList, createObject(new_tcb));// insert new task
  NextTask = ReadyList->pHead->pNext->pTask;// update NextTask
  SwitchContext();
 }
  return OK;
}
void run(void){//run the kernal
Ticks = 0;
KernalMode = RUNNING;
 NextTask = ReadyList->pHead->pNext->pTask;//update nexttask
 LoadContext_In_Run();
 /*   -- supplied to you in the assembly file -------<
  *      does not save any registers
  *      but simply restores registers from saved values
  *      from the TCB (Task Control Block) of NextTask
  */
}
void set_deadline(unsigned int d){//set the deadline on the calling task
 isr_off();
 NextTask->Deadline = d;//set the deadline
 PreviousTask = NextTask;//update previoustask
 sort(ReadyList);//sort the list
 NextTask = ReadyList->pHead->pNext->pTask;//update nexttask
 SwitchContext();
}