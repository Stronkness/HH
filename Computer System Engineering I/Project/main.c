#include "system_sam3x.h"
#include "at91sam3x8.h"
#include "core_cm3.h"
#include <math.h>
#include "swlinked.h"



  
  int Set_Led2(unsigned int i);// turn off and on without register
  void Turn_onoff (void);
  void KeyPadInit(void);
  int readKeybutton(void);
  void Delay(int Value);
  void Write_Command_2_Display(unsigned char Command);
  void Write_Data_2_Display(unsigned char Data);
  void Init_Display(void);
  void SysTick_Handler(void);
  void PIOD_Handler(void);
  void make_display_empty(void);
  void write_key(int key);
  void PIOC_Handler(void);
  void tempSensorInit(void);
  void start_temp(void);
  void TempToDisplay(int temp);
  int readTemp(void);
  void lightInit(void);
  double readlight(void);
  void writeLight(double lumen);
  void serviInit(void);
  void turnservo(int degree);
  void turnservokey(int key);
  int findpos(void);
  void gooddelay(void);
  void writePos(int pos);
  void menydisplay(void);
  void Alarm(int execution);
  void maximum(struct c_list *list2, int temp, int day);
  void minimum(struct c_list *list2, int temp,int day);
  void average(struct c_list *list2, int temp, int day);
  void averageEnd(struct c_list *list2,int day);
  void resetlink(struct c_list *list2, int day);
  void tempweek(struct c_list *list);
  void highlow(int high, int low);
  void displayalarm(void);
  
//global variables
  unsigned int counter = 0;
  unsigned int increment = 0;
  unsigned int mincount =0;
  unsigned int minut = 0;
  unsigned int tempmode = 60000;
  unsigned int what_button = 13;
  signed int temp = 0;
  unsigned int low_temp = 20;
  unsigned int high_temp = 25;
  double lux = 0;
  int post=0;
  int flag=0;
  int day=0;
  
  
  int main(){
     SystemInit(); 
 
 
  
  
  *AT91C_PMC_PCER = 1<<11;// tioa clock
  *AT91C_PMC_PCER = 11<<13;//pioc piod clock
  *AT91C_PIOD_PER = 0x9;
  *AT91C_PIOD_OER = 1<<3;
  *AT91C_PIOC_OER = 0x1;
  *AT91C_PIOC_SODR =0x1;
  *AT91C_PIOC_PER = 0xFF3FC;
  
  *AT91C_PIOD_PPUDR = 0x00000008; // pull up data register
  SysTick_Config(83999);
  
  *AT91C_PIOD_IER = 0x00000002; // turn on the interrupt
  
   
  int exe = 1;
  KeyPadInit();
  Init_Display();
  tempSensorInit();
  lightInit();
  serviInit();
  start_temp();
  make_display_empty();
  menydisplay();
   struct c_list * list = NULL;

	for (int i=0; i < 7;i++)
	{
		put(&list, createNode());
	}
  //----------------------------------------------------------------------------------------
  while(1){
    
   if(1==flag){
   temp=readTemp();
   start_temp();
   }
   if(mincount>tempmode){//check if the value is time to messure
     if(minut > 1440){//minut count for a day 
       if(day == 6){//restet day to 0
         averageEnd(list,day);
         day =0;
         resetlink(list,day);
       }else{
       averageEnd(list,day);
       day++;
       resetlink(list,day);
       }
       minut=0;
     }else{
       if(temp>0){
       maximum(list,temp,day);
       minimum(list,temp,day);
       average(list,temp,day);
       minut++;
       }
     }
     mincount=0;
   }
  
    
    what_button = readKeybutton();
    lux = ((readlight()/0xfff)*3.3);
    
    if(temp > 0){
      if(what_button == 1){
        averageEnd(list,day);
        tempweek(list);
        while(1){
          start_temp();
           if(1==flag){
           temp=readTemp();
           start_temp();
           } 
             
           
          what_button = readKeybutton();
        if(what_button==11){
           make_display_empty();
           menydisplay();
           flag=1;
            break;
          }else if(what_button==1){
            
            if(temp >0){
             TempToDisplay(temp);
             
            }
          }
        
        }
      
      }else if(what_button==2){
        post = findpos();
        make_display_empty();
        writePos(post);
        
        while(1){
          what_button = readKeybutton();
          if(what_button==11){
           make_display_empty();
           menydisplay();
           flag=1;
            break;
          }else if(what_button==1){
             post = findpos();
             make_display_empty();
             writePos(post);
          }
        }
      }
        else if(what_button==3){
        tempmode=1000;
      }else if(what_button==4){
        tempmode=60000;
      }else if(what_button==5){
        exe = 0;
      }else if(what_button == 6){
        exe = 1;
      }else if(what_button ==7){
       displayalarm();
        while(1){
          what_button = readKeybutton();
        
  
          if(what_button==11){
           make_display_empty();
           menydisplay();
           flag=1;
            break;
          }else if(what_button ==1){//high++
            high_temp++;
            displayalarm();
          }else if(what_button ==2){//high--
            high_temp--;
            displayalarm();
          }else if(what_button ==3){//low++
            low_temp++;
            displayalarm();
          }else if(what_button ==4){//low--
            low_temp--;
            displayalarm();
          }
          counter=0;
          while(counter<200){}
        }
      }
         
       what_button = 13;
       
    }
    if(temp != -1){
    Alarm(exe);
    }
 
    }
      }
   int Set_Led2(unsigned int x){
     if(x == 1){
       *AT91C_PIOD_OER = 1<<3;
        *AT91C_PIOD_SODR = 1<<3; // turn on the light 
        return 0;
      }else{
        *AT91C_PIOD_OER = 1<<3;
        *AT91C_PIOD_CODR = 1<<3;//turn off the light 
        return 1;
       }
    }
    void Turn_onoff (void){
            *AT91C_PIOD_IDR = 0x00000008;
            unsigned int have = *AT91C_PIOD_ISR;
            
            if(increment % 2 == 0){
            *AT91C_PIOD_ODR = 0x00000008;//disable output register
            
            }
            else{
            *AT91C_PIOD_OER = 0x000000008; // output data register 
            make_display_empty();
             Write_Data_2_Display(0x00);
              Write_Data_2_Display(0x00);
              Write_Command_2_Display(0x24);
            } 
    }

   void KeyPadInit(void){
        *AT91C_PIOC_PER = 0x3BC;
        *AT91C_PIOC_PPUDR = 0x3BC;
   }
      
  int readKeybutton(void){
   int value = 14;
   //clearbusthingy
   *AT91C_PIOC_ODR = (0xf<<2);
   *AT91C_PIOD_OER = (1<<2); 
   *AT91C_PIOD_CODR = (1<<2);
   *AT91C_PIOC_OER = (111<<7);
   *AT91C_PIOC_SODR = (111<<7);
   
   
   for(int i = 0; i <= 2; i++){
     int column = 7+i;
     *AT91C_PIOC_CODR = 1<<(column);
     for(int j = 0; j <= 3; j++){
         int row = 5-j;
         unsigned int read = (*AT91C_PIOC_PDSR & (1<<row));
         
        if(read == 0){ //detta e fel condition, PDSR
            value = (j*3) + i + 1; 
            
           
        }
        
     }
     *AT91C_PIOC_SODR = 111<<7;
   }
   *AT91C_PIOC_ODR = 111<<7;
   *AT91C_PIOD_SODR= (1<<2);
   *AT91C_PIOC_OER = (1<<12);
   *AT91C_PIOC_CODR = (1<<12);
   return value;
  }
  
  void Delay(int Value){
    for(int i = 0; i < Value ; i++){
    asm("nop"); 
    }
  }
  
  unsigned char Read_Status_Display(void){
   unsigned char temp;
   //Set databus
   *AT91C_PIOC_ODR = 0x3FC;
   
   //Set input
   //Directory pin 50
   *AT91C_PIOC_OER = 1<<13;
   *AT91C_PIOC_SODR = 1<<13; // Input
    
   
   //Set output
   //Output pin51 (according to datasheet
   *AT91C_PIOC_OER = 1<<12;
   *AT91C_PIOC_CODR = 1<<12;
   
   //Set C/D
   // C/D PC14
   *AT91C_PIOC_OER = 1<<14;
   *AT91C_PIOC_SODR = 1<<14;
   
   //CLEAR CE (CHIP SLECT DISPLAY)
    //CE PC15
   *AT91C_PIOC_OER = 1<<15;
   *AT91C_PIOC_CODR = 1<<15; //Clear
   
   //CLEAR RD
   *AT91C_PIOC_OER = 1<<16;
   *AT91C_PIOC_CODR = 1<<16;
   
   Delay(10);
   
   //Read the data bus
   temp = (*AT91C_PIOC_PDSR & 0x3FC);
   
   //Set Chip select display
   *AT91C_PIOC_SODR = 1<<15;
           
   //Set read display
   *AT91C_PIOC_SODR = 1<<16;
   
   //Disable output
   *AT91C_PIOC_SODR = 1<<12;
   
   //Set directory as output
   *AT91C_PIOC_CODR = 1<<13;
   
   return temp;   
  }
  
  void Write_Command_2_Display(unsigned char Command){
    //Wait till it returns OK
    while((~Read_Status_Display() & 0xC) == 0xC){
      }
    //Clear databus
    *AT91C_PIOC_OER = 0x3FC;
    *AT91C_PIOC_CODR = 0x3FC; 
    
    //Set Command to databus
    *AT91C_PIOC_SODR = (unsigned int)Command<<2;
    
    //Set directory as output
    *AT91C_PIOC_OER = 1<<13;
    *AT91C_PIOC_CODR = 1<<13;
    
    //Enable output
    *AT91C_PIOC_OER = 1<<12;
    *AT91C_PIOC_CODR = 1<<12;
    
    //Set databus as output
    *AT91C_PIOC_OER = 0x3FC;
    
    //Set C/D signal high (1= command)
    *AT91C_PIOC_OER = 1<<14;
    *AT91C_PIOC_SODR = 1<<14;
    
    //Clear chip select display
    *AT91C_PIOC_OER = 1<<15;
    *AT91C_PIOC_CODR = 1<<15; 
    
    //Clear write display
    //*AT91C_PIOC_PER = 1<<17;
    *AT91C_PIOC_OER = 1<<17;
    *AT91C_PIOC_CODR = 1<<17;
    
    Delay(10);
    
    //Set chip enable display
    *AT91C_PIOC_OER = 1<<15;
    *AT91C_PIOC_SODR = 1<<15;
    
    //Set write display
    *AT91C_PIOC_OER = 1<<17;
    *AT91C_PIOC_SODR = 1<<17;
    
    //Disable output (74chip)
    *AT91C_PIOC_OER = 1<<12;
    *AT91C_PIOC_SODR = 1<<12;
    
    //Make databus as input
    *AT91C_PIOC_ODR = 0x3FC;
  }
  
  void Write_Data_2_Display(unsigned char Data){
    //Wait until its OK
    while((~Read_Status_Display() & 0xC) == 0xC){
      }
    //Clear databus
    *AT91C_PIOC_OER = 0x3FC;
    *AT91C_PIOC_CODR = 0x3FC;
    
    //Set Data to databus
    *AT91C_PIOC_SODR = (unsigned int)Data<<2; //vet inte om detta är rätt, kanske ska va 0xFF
    
    //Set directory as output
    *AT91C_PIOC_OER = 1<<13;
    *AT91C_PIOC_CODR = 1<<13;
    
    //Enable output
    *AT91C_PIOC_OER = 1<<12;
    *AT91C_PIOC_CODR = 1<<12;
    
    //Set databus as output
    *AT91C_PIOC_OER = 0x3FC;
    
    //Clear C/D signal High (0 = Data)
    *AT91C_PIOC_OER = 1<<14;
    *AT91C_PIOC_CODR = 1<<14;
    
    //Clear chip select display
    *AT91C_PIOC_OER = 1<<15;
    *AT91C_PIOC_CODR = 1<<15;
    
   //Clear write display
    *AT91C_PIOC_OER = 1<<17;
   *AT91C_PIOC_CODR = 1<<17;
   
   Delay(10);
   
   //Set chip enable display
    *AT91C_PIOC_SODR = 1<<15;
    
    //Set write display
    *AT91C_PIOC_SODR = 1<<17;
    
    //Disable output (74chip)
    *AT91C_PIOC_SODR = 1<<12;
    
    //Make databus as input
    *AT91C_PIOC_ODR = 0x3FC;
} 
  
  
  void Init_Display(void){
      //Clear Reset display
      *AT91C_PIOD_CODR = 0x1;
      Delay(10);
      //Set Reset display   
      *AT91C_PIOD_SODR = 0x1;
      Write_Data_2_Display(0x00); 
      Write_Data_2_Display(0x00); 
      Write_Command_2_Display(0x40);//Set text home address 
      Write_Data_2_Display(0x00); 
      Write_Data_2_Display(0x40); 
      Write_Command_2_Display(0x42); //Set graphic home address 
      Write_Data_2_Display(0x30);  
      Write_Data_2_Display(0x00);
      Write_Command_2_Display(0x41); // Set +text area 
      Write_Data_2_Display(0x30);
      Write_Data_2_Display(0x00);
      Write_Command_2_Display(0x43); // Set graphic area 
      Write_Command_2_Display(0x80); // text mode 
      Write_Command_2_Display(0x94); // Text on graphic off 
  }
  
  void SysTick_Handler(void){
        counter++;
        increment++;
        mincount++;
  }
  void TC0_Handler(void){
    *AT91C_TC0_IDR = 1<<6;
      flag=1;
    
  }

  void make_display_empty(void){
    while((~Read_Status_Display() & 0xC) == 0xC){
      }
    Write_Data_2_Display(0x00);
    Write_Data_2_Display(0x00);
    Write_Command_2_Display(0x24);
    for(int i=0;i <21*40;i++){
       Write_Data_2_Display(0x00); 
       Write_Data_2_Display(0x00);
       Write_Command_2_Display(0xC0);
    }
    Write_Data_2_Display(0x00);
    Write_Data_2_Display(0x00);
    Write_Command_2_Display(0x24);
  }
  
  void write_key(int key){
    if(key==11){
   Write_Data_2_Display(0x10); 
   Write_Data_2_Display(0x10);
   Write_Command_2_Display(0xC0);
    }else if(key==1){
   Write_Data_2_Display(0x11); 
   Write_Data_2_Display(0x11);
   Write_Command_2_Display(0xC0);
    }else  if(key==2){
   Write_Data_2_Display(0x12); 
   Write_Data_2_Display(0x12);
   Write_Command_2_Display(0xC0);
    }else  if(key==3){
   Write_Data_2_Display(0x13); 
   Write_Data_2_Display(0x13);
   Write_Command_2_Display(0xC0);
    }else  if(key==4){
   Write_Data_2_Display(0x14); 
   Write_Data_2_Display(0x14);
   Write_Command_2_Display(0xC0);
    }else  if(key==5){
   Write_Data_2_Display(0x15); 
   Write_Data_2_Display(0x15);
   Write_Command_2_Display(0xC0);
    }else  if(key==6){
   Write_Data_2_Display(0x16); 
   Write_Data_2_Display(0x16);
   Write_Command_2_Display(0xC0);
    }else  if(key==7){
   Write_Data_2_Display(0x17); 
   Write_Data_2_Display(0x17);
   Write_Command_2_Display(0xC0);
    }else  if(key==8){
   Write_Data_2_Display(0x18); 
   Write_Data_2_Display(0x18);
   Write_Command_2_Display(0xC0);
    }else  if(key==9){
   Write_Data_2_Display(0x19); 
   Write_Data_2_Display(0x19);
   Write_Command_2_Display(0xC0);
    }
  }
  void tempSensorInit(void){
  *AT91C_PMC_PCER = 1<<12; //Sätter på klockan till piob
  *AT91C_PIOB_PER = 1<<25;
  *AT91C_PMC_PCER = 1<<27; //timer counter
  *AT91C_TC0_CMR = 0x0;
  *AT91C_TC0_CCR = 5<<0;
  *AT91C_TC0_CMR = (*AT91C_TC0_CMR |(2 << 16));//falling edge RA
  *AT91C_TC0_CMR = (*AT91C_TC0_CMR |(1 << 18));//rising edge RB
  
  NVIC_EnableIRQ((IRQn_Type)27); 
  NVIC_ClearPendingIRQ((IRQn_Type)27);
  
  }

  void start_temp(void){
  
  *AT91C_PIOB_OER = 1<<25; //Start pulse
  *AT91C_PIOB_CODR = 1<<25;
  Delay(25);
  *AT91C_PIOB_SODR = 1<<25; 
  *AT91C_PIOB_ODR = 1<<25; //end pulse
  
    *AT91C_TC0_SR; 
  
  *AT91C_TC0_CCR = 1<<2; // reset interrupt
  *AT91C_TC0_IER = 1<<6; // enable interrupt on TC_IER_LDRBS
  flag=0;
  counter=0;
  while(counter < 300){}
  }
  
  void TempToDisplay(int temp){
    
    make_display_empty();
    Write_Data_2_Display(0x34);
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x45);
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x4D);
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x50);
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
    
    Write_Data_2_Display(0x10+(temp/10)); //integer
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x10+(temp%10)); // decimal
    Write_Command_2_Display(0xC0);
       for(int i=0;i<41;i++){
    Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
    }
     Write_Data_2_Display(0x11); // 1 restart
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x32);//R
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x45);//e
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x53);//s
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x54);//t
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x41);//a
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x52);//r
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x54);//t
    Write_Command_2_Display(0xC0);
    
    for(int i=0;i<39;i++){
    Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
    }
     Write_Data_2_Display(0x10); // 0 back
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x22);//B
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x41);//a
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x43);//c
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x4B);//k
    Write_Command_2_Display(0xC0);
    
  }
  
  void tempweek(struct c_list *list){
      make_display_empty();
    struct c_list *test = list;
    Write_Data_2_Display(0x2D);//monday
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x6F);
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x2E);
    Write_Command_2_Display(0xC0);
    
    Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
    
    
     Write_Data_2_Display(0x2D);//max temp
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x41);
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x58);
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
    
    Write_Data_2_Display(0x10+(test->max/10)); //integer
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x10+(test->max%10)); // decimal
    Write_Command_2_Display(0xC0);
    
    Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
    
     Write_Data_2_Display(0x2D);//min temp
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x49);
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x4E);
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
    
    Write_Data_2_Display(0x10+(test->min/10)); //integer
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x10+(test->min%10)); // decimal
    Write_Command_2_Display(0xC0);
    
     Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
    
    Write_Data_2_Display(0x21);//avg temp
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x56);
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x47);
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
    
    Write_Data_2_Display(0x10+(test->avg/10)); //integer
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x10+(test->avg%10)); // decimal
    Write_Command_2_Display(0xC0);
     for(int i=0;i<24;i++){
    Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
    }
    test = test->next;
     Write_Data_2_Display(0x34);//tisdag
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x29);
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x33);
    Write_Command_2_Display(0xC0);
    
    Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
    
    
     Write_Data_2_Display(0x2D);//max temp
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x41);
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x58);
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
    
    Write_Data_2_Display(0x10+(test->max/10)); //integer
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x10+(test->max%10)); // decimal
    Write_Command_2_Display(0xC0);
    
    Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
    
     Write_Data_2_Display(0x2D);//min temp
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x49);
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x4E);
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
    
    Write_Data_2_Display(0x10+(test->min/10)); //integer
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x10+(test->min%10)); // decimal
    Write_Command_2_Display(0xC0);
    
     Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
    
    Write_Data_2_Display(0x21);//avg temp
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x56);
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x47);
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
    
    Write_Data_2_Display(0x10+(test->avg/10)); //integer
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x10+(test->avg%10)); // decimal
    Write_Command_2_Display(0xC0);
     for(int i=0;i<24;i++){
    Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
    }
    test = test->next;
    
 Write_Data_2_Display(0x2F);//onsday
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x2E);
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x33);
    Write_Command_2_Display(0xC0);
    
    Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
    
    
     Write_Data_2_Display(0x2D);//max temp
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x41);
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x58);
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
    
    Write_Data_2_Display(0x10+(test->max/10)); //integer
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x10+(test->max%10)); // decimal
    Write_Command_2_Display(0xC0);
    
    Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
    
     Write_Data_2_Display(0x2D);//min temp
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x49);
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x4E);
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
    
    Write_Data_2_Display(0x10+(test->min/10)); //integer
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x10+(test->min%10)); // decimal
    Write_Command_2_Display(0xC0);
    
     Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
    
    Write_Data_2_Display(0x21);//avg temp
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x56);
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x47);
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
    
    Write_Data_2_Display(0x10+(test->avg/10)); //integer
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x10+(test->avg%10)); // decimal
    Write_Command_2_Display(0xC0);
     for(int i=0;i<24;i++){
    Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
    }
    test = test->next;
     Write_Data_2_Display(0x34);//torsdag
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x2F);
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x32);
    Write_Command_2_Display(0xC0);
    
    Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
    
    
     Write_Data_2_Display(0x2D);//max temp
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x41);
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x58);
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
    
    Write_Data_2_Display(0x10+(test->max/10)); //integer
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x10+(test->max%10)); // decimal
    Write_Command_2_Display(0xC0);
    
    Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
    
     Write_Data_2_Display(0x2D);//min temp
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x49);
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x4E);
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
    
    Write_Data_2_Display(0x10+(test->min/10)); //integer
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x10+(test->min%10)); // decimal
    Write_Command_2_Display(0xC0);
    
     Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
    
    Write_Data_2_Display(0x21);//avg temp
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x56);
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x47);
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
    
    Write_Data_2_Display(0x10+(test->avg/10)); //integer
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x10+(test->avg%10)); // decimal
    Write_Command_2_Display(0xC0);
 for(int i=0;i<24;i++){
    Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
    }
 test = test->next;
    Write_Data_2_Display(0x26);//fredag
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x32);
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x25);
    Write_Command_2_Display(0xC0);
    
    Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
    
    
     Write_Data_2_Display(0x2D);//max temp
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x41);
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x58);
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
    
    Write_Data_2_Display(0x10+(test->max/10)); //integer
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x10+(test->max%10)); // decimal
    Write_Command_2_Display(0xC0);
    
    Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
    
     Write_Data_2_Display(0x2D);//min temp
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x49);
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x4E);
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
    
    Write_Data_2_Display(0x10+(test->min/10)); //integer
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x10+(test->min%10)); // decimal
    Write_Command_2_Display(0xC0);
    
     Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
    
    Write_Data_2_Display(0x21);//avg temp
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x56);
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x47);
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
    
    Write_Data_2_Display(0x10+(test->avg/10)); //integer
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x10+(test->avg%10)); // decimal
    Write_Command_2_Display(0xC0);
     for(int i=0;i<24;i++){
    Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
    }
    test = test->next;
     Write_Data_2_Display(0x2C);//lördag
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x79);
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x32);
    Write_Command_2_Display(0xC0);
    
    Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
    
    
     Write_Data_2_Display(0x2D);//max temp
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x41);
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x58);
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
    
    Write_Data_2_Display(0x10+(test->max/10)); //integer
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x10+(test->max%10)); // decimal
    Write_Command_2_Display(0xC0);
    
    Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
    
     Write_Data_2_Display(0x2D);//min temp
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x49);
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x4E);
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
    
    Write_Data_2_Display(0x10+(test->min/10)); //integer
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x10+(test->min%10)); // decimal
    Write_Command_2_Display(0xC0);
    
     Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
    
    Write_Data_2_Display(0x21);//avg temp
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x56);
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x47);
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
    
    Write_Data_2_Display(0x10+(test->avg/10)); //integer
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x10+(test->avg%10)); // decimal
    Write_Command_2_Display(0xC0);
     for(int i=0;i<24;i++){
    Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
    }
     Write_Data_2_Display(0x33);//söndag
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x79);
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x2E);
    Write_Command_2_Display(0xC0);
    
    Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
    
    
     Write_Data_2_Display(0x2D);//max temp
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x41);
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x58);
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
    
    Write_Data_2_Display(0x10+(test->max/10)); //integer
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x10+(test->max%10)); // decimal
    Write_Command_2_Display(0xC0);
    
    Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
    
     Write_Data_2_Display(0x2D);//min temp
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x49);
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x4E);
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
    
    Write_Data_2_Display(0x10+(test->min/10)); //integer
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x10+(test->min%10)); // decimal
    Write_Command_2_Display(0xC0);
    
     Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
    
    Write_Data_2_Display(0x21);//avg temp
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x56);
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x47);
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
    
    Write_Data_2_Display(0x10+(test->avg/10)); //integer
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x10+(test->avg%10)); // decimal
    Write_Command_2_Display(0xC0);
    
        for(int i=0;i<24;i++){
    Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
    }
     Write_Data_2_Display(0x11); // 1 restart
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x32);//R
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x45);//e
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x53);//s
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x54);//t
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x41);//a
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x52);//r
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x54);//t
    Write_Command_2_Display(0xC0);
    
    for(int i=0;i<39;i++){
    Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
    }
     Write_Data_2_Display(0x10); // 0 back
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x22);//B
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x41);//a
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x43);//c
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x4B);//k
    Write_Command_2_Display(0xC0);
  }
  
  int readTemp(void){
    int first = *AT91C_TC0_RA;
    int second = *AT91C_TC0_RB;
    
    int trueTemp = (((second-first)/(42.0*5.0))-273.15); // kolla upp T-ready på datayeet
    return trueTemp;
 
    
  }
  
  void lightInit(void){
   //1
   *AT91C_PMC_PCER1 = 1<<5;
   *AT91C_ADCC_MR = 1<<10;
  }
  
  double readlight(void){
    //1
    *AT91C_ADCC_CHER = 1<<2; //select channel 2    
    *AT91C_ADCC_CR = 1<<1; // start ADC
    
    while(~(*AT91C_ADCC_SR & (1 << 24)) == (1 << 24)){ //while until IDR_DRDY ready
      //used status regidter to check with ADC_IDR /DRDY
    }
    double light = (*AT91C_ADCC_LCDR);
    return light;
  }

  void writeLight(double lumen){
   
    Write_Data_2_Display(0x36);
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x4F);
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x4C);
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x54);
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
    
    Write_Data_2_Display(0x10+((int)lumen)); //integer
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x0C);
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x10+((int)(lumen*10)%10)); // decimal
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x10+(((int)(lumen*100))%10)); // decimal
    Write_Command_2_Display(0xC0);
    
  }
 
  void serviInit(void){
    
  *AT91C_PMC_PCER1 = (1 << 4);
  *AT91C_PMC_PCER = (1 << 12);
  *AT91C_PIOB_ABMR = (1 << 17);
  *AT91C_PIOB_PDR = (1 << 17);
  *AT91C_PIOB_PPUDR = (1 << 17);
  *AT91C_PWMC_ENA = (1 << 1);
  *AT91C_PWMC_CH1_CMR = 5;
  *AT91C_PWMC_CH1_CPRDR = 0xCD14;
  *AT91C_PWMC_CH1_CDTYR = 0x714;//noll = 0
  
  }
  void turnservo(int degree){
    
    *AT91C_PWMC_CH1_CDTYR = 1812+degree*25; 
  }
  
  void turnservokey(int key){
    int value;
    if(key == 10 || key== 12){
      return;
    }else if(key==11){
    value = 0;
    turnservo(0);
    }else{
    value = key*10;
      turnservo(value);
    }
  }
  
  int findpos(void){
  
  int max = 3300;
  int position = 0;
  for(int i = 18; i >= 0; i--){
    gooddelay();
    turnservo(i * 10);
    int light = readlight();
    printf("%d\n",light);
    if(light < max){
      max = light;
      position = i * 10;
    } 
  }
   return position;
  } 
  void gooddelay(void) {
  int c, d;
   
   for (c = 1; c <= 32767; c++)
       for (d = 1; d <= 100; d++)
       {}
   return;
  }
  void writePos(int pos){
     Write_Data_2_Display(0x27);
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x52);
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x41);
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x44);
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x45);
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x52);
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
    
    if((int)(pos/100) != 0){
    Write_Data_2_Display(0x10+(int)(pos/100)); //integer
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x10 + (int)((pos/10)%10));
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x10+((int)(pos%10))); // decimal
    Write_Command_2_Display(0xC0);
    }else{
       Write_Data_2_Display(0x10 + (int)(pos/10));
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x10+((int)(pos%10))); // decimal
    Write_Command_2_Display(0xC0);
    }
     for(int i=0;i<39;i++){
    Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
    }
    Write_Data_2_Display(0x11); // 1 restart
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x32);//R
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x45);//e
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x53);//s
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x54);//t
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x41);//a
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x52);//r
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x54);//t
    Write_Command_2_Display(0xC0);
    
    for(int i=0;i<39;i++){
    Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
    }
     Write_Data_2_Display(0x10); // 0 back
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x22);//B
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x41);//a
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x43);//c
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x4B);//k
    Write_Command_2_Display(0xC0);
    
  }
  void menydisplay(void){
    Write_Data_2_Display(0x11); // 1 week temp
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x37);//W-
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x45);//e
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x45);//e
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x4B);//k
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x49);//i
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x4E);//n
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x46);//f
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x4F);//o
    Write_Command_2_Display(0xC0);
    
    for(int i = 0;i<37;i++){//make room
      Write_Data_2_Display(0x00);
      Write_Command_2_Display(0xC0);
    }
     Write_Data_2_Display(0x12); // 2 sun orentation
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x33);//s
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x55);//u
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x4E);//n
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x50);// p
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x4F);//o
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x53);//s
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x49);//i
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x54);//t
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x4F);//o
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x4E);//n
    Write_Command_2_Display(0xC0);
    
    for(int i = 0;i<35;i++){//make room
      Write_Data_2_Display(0x00);
      Write_Command_2_Display(0xC0);
    }
    Write_Data_2_Display(0x13); // 3 fast mode
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x26);//f
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x41);//a
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x53);//s
    Write_Command_2_Display(0xC0);
      Write_Data_2_Display(0x54);//t
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x4D);//m
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x4F);//o
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x44);//d
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x45);//e
    Write_Command_2_Display(0xC0);
     
    for(int i = 0;i<37;i++){//make room
      Write_Data_2_Display(0x00);
      Write_Command_2_Display(0xC0);
    }
        Write_Data_2_Display(0x14); // 4 normal mode
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x2E);//N
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x4F);//o
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x52);//r
    Write_Command_2_Display(0xC0);
      Write_Data_2_Display(0x4D);//m
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x41);//a
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x4C);//l
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x4D);//m
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x4F);//o
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x44);//d
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x45);//e
    Write_Command_2_Display(0xC0);
    
     for(int i = 0;i<35;i++){//make room
      Write_Data_2_Display(0x00);
      Write_Command_2_Display(0xC0);
    }
     Write_Data_2_Display(0x15); // 5 turn off alram
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x21);//a
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x4C);//l
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x41);//a
    Write_Command_2_Display(0xC0);
      Write_Data_2_Display(0x52);//r
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x4D);//m
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x4F);//o
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x46);//f
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x46);//f
    Write_Command_2_Display(0xC0);
     for(int i = 0;i<37;i++){//make room
      Write_Data_2_Display(0x00);
      Write_Command_2_Display(0xC0);
    }
     Write_Data_2_Display(0x16); // 6 turn on alarm
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x21);//a
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x4C);//l
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x41);//a
    Write_Command_2_Display(0xC0);
      Write_Data_2_Display(0x52);//r
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x4D);//m
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x4F);//o
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x4E);//n
    Write_Command_2_Display(0xC0);
     for(int i = 0;i<38;i++){//make room
      Write_Data_2_Display(0x00);
      Write_Command_2_Display(0xC0);
    }
     Write_Data_2_Display(0x17); // 7 set alarm
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
      Write_Data_2_Display(0x33);//s
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x45);//e
    Write_Command_2_Display(0xC0);
      Write_Data_2_Display(0x54);//t
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x41);//a
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x4C);//l
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x41);//a
    Write_Command_2_Display(0xC0);
      Write_Data_2_Display(0x52);//r
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x4D);//m
    Write_Command_2_Display(0xC0);
   
    
  }
  void Alarm(int execution){ 
   if(execution == 1){
    if(temp > high_temp || temp < low_temp){
      Set_Led2(1);
      *AT91C_PIOD_SODR = 1<<3;
    }else{
      Set_Led2(0);
    } 
    }
   else {
     Set_Led2(0);
   }
  }
 void maximum(struct c_list *list2, int temp, int day){
    int newMax = 0;
     struct c_list *tp = list2;
    for(int i =0;i<day;i++){
      if(tp->next!=NULL){
        tp = tp->next;
      }
    }
    if(tp->max < temp){
    newMax = temp;
     tp->max = newMax;
    }
   
  }
  
  void minimum(struct c_list *list2, int temp,int day){
    int newMin = 0;
     struct c_list *tp = list2;
    for(int i =0;i<day;i++){
      if(tp->next!=NULL){
        tp = tp->next;
      }
    }
    if(tp->min > temp){
    newMin = temp;
    tp->min = newMin;
    }
    
  }
  
  void average(struct c_list *list2, int temp, int day){
     struct c_list *tp = list2;
    for(int i =0;i<day;i++){
      if(tp->next!=NULL){
        tp = tp->next;
      }
    }
    tp->avg += temp;
    tp->amount++;
  }
  void averageEnd(struct c_list *list2, int day){ 
     struct c_list *tp = list2;
    for(int i =0;i<day;i++){
      if(tp->next!=NULL){
        tp = tp->next;
      }
    }
    tp->avg = tp->avg/(tp->amount);
    tp->amount=1;
  }
  void resetlink(struct c_list *list2, int day){ 
     struct c_list *tp = list2;
    for(int i =0;i<day;i++){
      if(tp->next!=NULL){
        tp = tp->next;
      }
    }
   tp -> max = (-99);
  tp -> min = 99;
  tp -> avg = 0;
  tp -> amount = 0;
  }
  void displayalarm(void){
    make_display_empty();
     Write_Data_2_Display(0x11); // 2 HIGH++
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x00);
     Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x28);//H
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x29);//I
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x27);//G
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x28);//H
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x0B);//+
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x0B);//+
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
    
    for(int i = 0;i<39;i++){//make room
      Write_Data_2_Display(0x00);
      Write_Command_2_Display(0xC0);
    }
    Write_Data_2_Display(0x12); // 3 HIGH--
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
      Write_Data_2_Display(0x28);//H
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x29);//I
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x27);//G
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x28);//H
    Write_Command_2_Display(0xC0); 
     Write_Data_2_Display(0x0D);//-
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x0D);//-
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
     
    for(int i = 0;i<39;i++){//make room
      Write_Data_2_Display(0x00);
      Write_Command_2_Display(0xC0);
    }
        Write_Data_2_Display(0x13); // 4 LOW++
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x2C);//L
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x2F);//O
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x37);//W
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x0B);//+
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x0B);//+
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
    
     for(int i = 0;i<40;i++){//make room
      Write_Data_2_Display(0x00);
      Write_Command_2_Display(0xC0);
    }
     Write_Data_2_Display(0x14); // 5 LOW--
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x2C);//L
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x2F);//O
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x37);//W
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x0D);//-
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x0D);//-
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
    
    for(int i=0;i<40;i++){
    Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
    }
     Write_Data_2_Display(0x10); // 0 back
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x22);//B
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x41);//a
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x43);//c
    Write_Command_2_Display(0xC0);
     Write_Data_2_Display(0x4B);//k
    Write_Command_2_Display(0xC0);
       
    for(int i=0;i<42;i++){
    Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
    }
     Write_Data_2_Display(0x28);//H
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x29);//I
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x27);//G
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x28);//H
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
    
    Write_Data_2_Display(0x10+high_temp/10);
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x10+high_temp%10);
    Write_Command_2_Display(0xC0);
    
    Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
    
    Write_Data_2_Display(0x2C);//L
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x2F);//O
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x37);//W
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x00);
    Write_Command_2_Display(0xC0);
    
    Write_Data_2_Display(0x10+low_temp/10);
    Write_Command_2_Display(0xC0);
    Write_Data_2_Display(0x10+low_temp%10);
    Write_Command_2_Display(0xC0);
  }
 