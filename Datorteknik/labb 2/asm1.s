;===========================================================================

; Lab_2_1_student.s - Template for DAT Lab 2_1
;
; Description   : A more complete template for Lab 2_1

; Reference(s)  : ARM IAR Assembler Reference Guide

; Course        : Datorteknik (DAT) DT4009
;
; Authors       : Süleyman Savas
;
;
;===========================================================================

        NAME    main
        PUBLIC  main
        SECTION .text : CODE (2)
        THUMB
        
;===========================================================================

; TODO: Find and add the correct addresses, 
; Check sam3x datasheet - chapter 30 to find the addresses.
PIOA_WPMR EQU  0x400E0EE4       ; Write Protect Mode Register

PIOA_PER  EQU   0x400E0E00     ; PIO Enable Register
PIOA_ODR  EQU   0x400E0E14    ; Output Disable Register
PIOA_IDR  EQU   0x400E0E44    ; Interrupt Disable Register  
PIOA_PUER EQU   0x400E0E64     ; Pull Up Enable Register
PIOA_PDSR EQU   0x400E0E3C     ; Pin Data Status Register

; C port - (leds)
PIOC_WPMR EQU  0x400E12E4       ; Write Protect Mode Register

PIOC_PER  EQU  0x400E1200     ; PIO Enable Register - Arduino Due
PIOC_OER  EQU  0x400E1210      ; Output Enable Register
PIOC_SODR EQU  0x400E1230     ; Set Output Data Register
PIOC_PUDR EQU  0x400E1264      ; Pull Up Disable Register
PIOC_CODR EQU  0x400E1234      ; Clear Output Data Register

; PMC
PMC_WPMR  EQU   0x400E06E4      ; PMC Write Protect Mode Register

PMC_PCER  EQU   0x400E0610      ; Peripheral Clock Enable Register 0
PMC_PCDR  EQU   0x400E0614      ; Peripheral Clock Disable Register 0
PMC_PCSR  EQU   0x400E0618      ; Peripheral Clock Status Register 0

;===========================================================================

; Initialization of Peripheral Clock
main      LDR   R0,=PMC_PCER
          LDR   R1,=(5 << 11)  ; Peripheral Identifier: bit11=PIOA, bit13=PIOC 
          STR   R1,[R0]
          LDR R1,=PIOC_PER ;enable the pins we will use output
          MOV R2, #6
          STR R2, [R1]
          LDR R1,=PIOC_OER ; Output Enable Register
          MOV R2, #6
          STR R2, [R1]
          LDR R1,=PIOC_PUDR  ; Pull Up Disable Register
          MOV R2, #6
          STR R2, [R1]
;===========================================================================

; Initialization InPort  
; TODO: Write your code here
 
LOOP
  LDR R1,=PIOA_PDSR ; Adress till PortA:s dataregister
  LDR R0,[R1] ; Läser från adress i R1 till R0 
  AND R1, R0, #0X4000 ; R0 AND:as med 
  AND R2, R0, #0X8000 ; R0 AND:as med
  CMP R1, #0
  BEQ   Left
  CMP R2, #0
  BEQ   Right
  B LOOP ; En evig loop
Left
  MOV R2, #2
  LDR R1, =PIOC_SODR ;turn on the leds
  
  STR R2, [R1] 
  LDR R1,=PIOA_PDSR ; Adress till PortA:s dataregister
  LDR R0,[R1] ; Läser från adress i R1 till R0
  AND R2, R0, #0X8000 ; R0 AND:as med
  CMP R2, #0
  BEQ   Both
  B Left
Right
  MOV R2, #4
  LDR R1, =PIOC_SODR ;turn on the leds
  
  STR R2, [R1] 
  LDR R1,=PIOA_PDSR ; Adress till PortA:s dataregister
  LDR R0,[R1] ; Läser från adress i R1 till R0
  AND R2, R0, #0X4000 ; R0 AND:as med
  CMP R2, #0
  BEQ   Both
  B Right
Both
  MOV R2, #6
  LDR R1, =PIOC_SODR ;turn on the leds
  STR R2,[R1]
  
  B Both
;===========================================================================

; Initialization OutPort
; TODO: Write your code here

;===========================================================================

; Turn LED off 
; TODO: Write your code here

;===========================================================================

; Main program
; TODO: Write your code here


;===========================================================================

STOP	B STOP


        END