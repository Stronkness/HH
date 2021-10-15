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
          LDR   R1,=PIOC_PER ;enable the pins we will use output
          MOV   R2, #14
          STR   R2, [R1]
          LDR   R1,=PIOC_OER ; Output Enable Register
          MOV   R2, #14
          STR   R2, [R1]
          LDR   R1,=PIOC_PUDR  ; Pull Up Disable Register
          MOV   R2, #14
          STR   R2, [R1]
;===========================================================================

; Initialization InPort  
; TODO: Write your code here
 
; Huvudprogram -------------------------------------
read
      BL LED_p0
      B read
     
Loop  B read
; ------------------------------------------------------------------
STOP	B STOP

;Subrutin Delay_ms ----------------------------------------
; Vänta ett antal ms
; Inparameter: R7 - delay i ms
; ---------------------------------------------------------
DELAY_CALIB EQU 1200 ; utprovat värde (baserat på master clock – 12Mhz)
Delay_ms
  STMFD SP!,{R0, R1}
  MOV R0,R7
do_delay_ms
  LDR R1,=DELAY_CALIB
loop_ms
  SUBS R1,R1,#1
  BNE loop_ms
  SUBS R0,R0,#1
  BNE do_delay_ms
  LDMFD SP!,{R0,R1}
  BX LR
; -------------------------------------------------------------
;Subrutin Read_p0 ---------------------------------------
; Avläsning av knapp i subrutin
; Utparametrar: R0
; ------------------------------------------------------
LED_p0
  STMFD SP!,{R1}
  MOV R5, R14
  MOV R2, #14
  LDR R1, =PIOC_SODR ;turn on the leds
  STR R2, [R1]
  MOV R7, #1000
  BL Delay_ms
  LDR R1, =PIOC_CODR ;turn off the leds
  STR R2, [R1] 
  LDR R0,[R1]
  MOV R7, #1000
  BL Delay_ms
  LDMFD SP!,{R1}
  BX R5
; ------------------------------------------------------

        END