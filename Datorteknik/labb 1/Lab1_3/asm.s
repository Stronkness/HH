        NAME    main
        PUBLIC  main
        SECTION .text : CODE (2)
        THUMB
main
        LDR R0, =vector  ; (R0) <-- vector
        LDR R1, [R0], #4 ;läser varje 4 bit
        MOV R7, R1  ; (R7)<-- R1
        MOV R6, R1  ; (R6)<-- R1
        MOV R2,#0 
LOOP    CMP R6,R1    ; jämför (R1) med 9
        BGT SET0    ; lägre --> hoppa till SET0
        BLE NEXT    ; lägre --> hoppa till SET1    
SET0    MOV R6, R1 ;set the value
NEXT    CMP R7,R1   ;compare R7 R1
        BLT SET1    ; större --> hoppa till SET1
        BGE NEXT2 ; jump o next NEXT2
SET1    MOV R7, R1 ; set th value
NEXT2   LDR R1, [R0],#4 ;jump to next value 
        ADD R2,R2,#1
        CMP R2, #20  ; compare if the value is empty
        BNE LOOP      ; olika --> hoppa till LOOP, upprepa
STOP    B   STOP      ; infinite lopp
        ALIGNROM 2
        DATA
vector
        DC32 14, 25, 2, 27, 3
        DC32 22, 13, 4, 24, 6
        DC32 26, 18, 8, 15, 9
        DC32 28, 10, 7, 17, 5
        END