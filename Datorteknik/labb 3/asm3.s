        NAME    main
        PUBLIC  main
        PUBLIC  SysTick_Handler
        PUBLIC  PIOA_Handler
        SECTION .text : CODE (2)
        THUMB
        
;===========================================================================
PIOA_WPMR EQU  0x400E0EE4      ; Write Protect Mode Register

PIOA_PER  EQU 0x400E0E00       ; PIO Enable Register
PIOA_ODR  EQU 0x400E0E14       ; Output Disable Register
PIOA_IDR  EQU 0x400E0E44       ; Interrupt Disable Register  
PIOA_IER  EQU 0x400E0E40       ; Interrupt Enable Register
PIOA_PUER EQU 0x400E0E64       ; Pull Up Enable Register
PIOA_PDSR EQU 0x400E0E3C       ; Pin Data Status Register
PIOA_FELLSR EQU 0x400E0ED0     ; Fall Status Register
PIOA_ISR EQU 0x400E0E4C        ; Interrupt Status Register 
PIOA_AIMER EQU 0x400E0EB0      ; Additional Interrupt Modes Enable Register

; C port - (leds)
PIOC_WPMR EQU  0x400E12E4      ; Write Protect Mode Register
PIOC_PER  EQU 0x400E1200       ; PIO Enable Register - Arduino Due
PIOC_OER  EQU 0x400E1210       ; Output Enable Register
PIOC_SODR EQU 0x400E1230       ; Set Output Data Register
PIOC_PUDR EQU 0x400E1260       ; Pull Up Disable Register
PIOC_CODR EQU 0x400E1234       ; Clear Output Data Register
PIOC_PDSR EQU 0x400E123C       ; Pin Data Status Register

; PMC
PMC_WPMR  EQU   0x400E06E4      ; PMC Write Protect Mode Register

PMC_PCER  EQU   0x400E0610      ; Peripheral Clock Enable Register 0
PMC_PCDR  EQU   0x400E0614      ; Peripheral Clock Disable Register 0
PMC_PCSR  EQU   0x400E0618      ; Peripheral Clock Status Register 0

; SYSTICK
SYSTICK_CTRL  EQU 0xE000E010    ; SysTick Control and Status Register
SYSTICK_LOAD  EQU 0xE000E014    ; SysTick Reload Value Register
SYSTICK_VAL   EQU 0xE000E018    ; SysTick Current Value Register

;NVIC
ISER EQU  0xE000E100            ; Interupt Set Enable Register
ICER  EQU 0xE000E180            ; Interupt Clear Enable Register
ISPR  EQU 0xE000E200            ; Interupt Set Pending Registers
ICPR  EQU 0xE000E280            ; Interupt Clear Pending Registers

;===========================================================================

; Initialization of Peripheral Clock
main      LDR   R0,=PMC_PCER
          LDR   R1,=(5 << 11)  ; Peripheral Identifier: bit11=PIOA, bit13=PIOC 
          STR   R1,[R0]
  

; Initialization InPort  
  LDR R0, =PIOA_PER
  MOV R1, #3<<14      ; enable PIOA on bit 14 and 15
  STR R1, [R0]
  LDR R0, =PIOA_ODR
  STR R1, [R0]        ; set bit 15 and 14 as inport
  LDR R0, =PIOA_PUER
  STR R1, [R0]
  MOV R1, #1<<14
  LDR R0, =PIOA_IER
  STR R1, [R0]
  LDR R0, =PIOA_FELLSR
  STR R1, [R0]
  LDR R0, =PIOA_AIMER
  STR R1, [R0]
  LDR R0, =ICPR
  MOV R1, #1<<11
  STR R1, [R0]
  LDR R0, =ISER
  STR R1, [R0]

  LDR R0, =PIOC_PER
  MOV R1, #14
  STR R1, [R0]
  LDR R0, =PIOC_OER
  STR R1, [R0]
  LDR R0, =PIOC_PUDR
  STR R1, [R0]

  LDR R0, =SYSTICK_CTRL
  LDR R1, =0
  STR R1, [R0]
  LDR R0, =SYSTICK_LOAD
  LDR R1, =0xB71AFF
  STR R1, [R0]
  LDR R0, =SYSTICK_VAL
  LDR R1, =0
  STR R1, [R0]
  LDR R0, =SYSTICK_CTRL
  LDR R1, =7
  STR R1, [R0]


; Main
 MOV R7, #500          ;Delay
Loop 
 LDR R0, =PIOA_PDSR
 LDR R1, [R0]
 AND R1, R1, #0x8000     ; mask left button 
 CMP R1, #0
 BNE Loop
 
 LDR R0, =PIOA_IDR
 LDR R2, =0x8000
 STR R2, [R0]
 
 LDR R0, =PIOC_PDSR
 LDR R1, [R0]
 AND R1, R1, #6          ; mask bit 2 and 1
 LSR R1, R1, #1          ; move to bit 1 and 0
 ADD R1, R1, #1          ; add LEDs +1
 LSL R1, R1, #1          ; shift bits to align for LEDs 
 
 LDR R0, =PIOC_SODR
 STR R1, [R0]
 EOR R1, R1, #6          ; invert bit 2 and 1
 LDR R0, =PIOC_CODR      ; address to Clear Output Data Register
 STR R1, [R0]
 LDR R0, =PIOA_IER       ; address to Interrupt Enable Register
 STR R2, [R0]            ; enable Interrupt again
 
main_release
 LDR R0, =PIOA_PDSR
 LDR R1, [R0]
 AND R1, R1 , #1<<15
 LSR R1, R1, #15
 CMP R1, #1
 BNE  main_release
 BL Delay_ms
 B  main_loop


;Subrutin Delay_ms 
;---------------------------------------- 
; Vänta ett antal ms  
; Inparameter: R7 -delay i ms  
; --------------------------------------------------------- 
DELAY_CALIB EQU 1200 
; utprovat värde (baserat på master clock –12Mhz) 
Delay_ms 
  STMFD SP!,{R0,R1} 
  MOV  R0,R7 
do_delay_ms 
  LDR  R1,=DELAY_CALIB 
loop_ms 
  SUBS R1,R1,#1 
  BNE  loop_ms 
  SUBS R0,R0,#1 
  BNE  do_delay_ms 
  LDMFD SP!,{R0,R1} 
  BX LR 
  
SysTick_Handler
  LDR R0, =PIOC_PDSR
  LDR R1, [R0]
  AND R1, R1, #8            ; mask 8 (LED on bit 3)
  LDR R0, =PIOC_CODR        ; toggle LED 3
  STR R1, [R0]
  EOR R1, R1, #8
  LDR R0, =PIOC_SODR
  STR R1, [R0]
  BX LR
  
PIOA_Handler
  LDR R0, =PIOA_ISR
  LDR R1, [R0]            ; read Interrupt Status Register
  AND R1, R1, #1<<14      ; mask bit 14 (left button)
  LSR R1, R1, #14         ; move to right
  CMP R1, #1
  BNE PIOA_Handler_Exit
  LDR R0, =PIOA_PDSR
  LDR R1, [R0]
  AND R1, R1, #1<<15      ; mask bit 15 (right button)
  CMP R1, #0
  BNE PIOA_Handler_Set
  LDR R0, =PIOC_CODR       ; reset by turning off LEDs and set systick_load to default  
  MOV R1, #14              
  STR R1, [R0]             
  LDR R0, =SYSTICK_LOAD
  LDR R1, =0xB71AFF
  STR R1, [R0]
  B PIOA_Handler_Exit 
  
PIOA_Handler_Set          ;new freq
  LDR R0, =PIOC_PDSR
  LDR R1, [R0]
  AND R1, R1, #6
  LSR R1, R1, #1
  LDR R2, =0xB71B00
  LSR R2, R2, R1
  SUB R2, R2, #1
  LDR R0, =SYSTICK_LOAD
  STR R2, [R0]
  
PIOA_Handler_Exit
  BX LR
  
  END