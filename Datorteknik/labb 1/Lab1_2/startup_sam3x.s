;
; Complete Assembly Startup Code for Arduino Due
; (based on SAM3U code by Andreas Johansson)
;
; contains:
; - weak 'main' routine
; - interrupt vector table
; - weak dummy interrupt handlers
;   -> declare your own handlers as "PUBLIC Handler_name" (name from list below)
; - startup code including clock setup
;   -> Watchdog disabled
;   -> SCK (Slow   Clock) at 32 kHz from crystal
;   -> MCK (Master Clock) at 12 MHz from crystal
;   -> Vector table pointer set to SRAM0
;
; 2015-03-xx Sebastian Raase, initial version
        NAME Startup
        
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Interrupt Vector Table
        SECTION .intvec : CODE (2)
        THUMB
        DATA
__vector_table
        DCD 0x20008000          ; initial SP value
        ; Cortex-M3 Exceptions
        DCD Reset_Handler       ; reset vector -> startup code at end of file
        DCD NMI_Handler
        DCD HardFault_Handler
        DCD MemoryManagementFault_Handler
        DCD BusFault_Handler
        DCD UsageFault_Handler
        DCD 0x00000000          ; Reserved
        DCD 0x00000000          ; Reserved
        DCD 0x00000000          ; Reserved
        DCD 0x00000000          ; Reserved
        DCD SVCall_Handler
        DCD 0x00000000          ; Reserved
        DCD 0x00000000          ; Reserved
        DCD PendSV_Handler
        DCD SysTick_Handler
        ; SAM3X/A Interrupts
        DCD SUPC_Handler        ; Supply Controller
        DCD RSTC_Handler        ; Reset Controller
        DCD RTC_Handler         ; Real Time Clock
        DCD RTT_Handler         ; Real Time Timer
        DCD WDG_Handler         ; Watchdog Timer
        DCD PMC_Handler         ; Power Management Controller
        DCD EEFC0_Handler       ; Enhanced Flash Controller 0
        DCD EEFC1_Handler       ; Enhanced Flash Controller 1
        DCD UART_Handler        ; Universal Asynchronous Receiver Transceiver
        DCD SMC_SDRAMC_Handler  ; Static Memory Controller / SDRAM Controller
        DCD SDRAMC_Handler      ; SDRAM Controller
        DCD PIOA_Handler        ; Parallel I/O Controller A
        DCD PIOB_Handler        ; Parallel I/O Controller B
        DCD PIOC_Handler        ; Parallel I/O Controller C
        DCD PIOD_Handler        ; Parallel I/O Controller D
        DCD PIOE_Handler        ; Parallel I/O Controller E
        DCD PIOF_Handler        ; Parallel I/O Controller F
        DCD USART0_Handler      ; USART 0
        DCD USART1_Handler      ; USART 1
        DCD USART2_Handler      ; USART 2
        DCD USART3_Handler      ; USART 3
        DCD HSMCI_Handler       ; High Speed Multimedia Card Interface
        DCD TWI0_Handler        ; Two-Wire Interface 0
        DCD TWI1_Handler        ; Two-Wire Interface 1
        DCD SPI0_Handler        ; Serial Peripheral Interface 0
        DCD SPI1_Handler        ; Serial Peripheral Interface 1
        DCD SSC_Handler         ; Synchronous Serial Controller
        DCD TC0_Handler         ; Timer Counter 0
        DCD TC1_Handler         ; Timer Counter 1
        DCD TC2_Handler         ; Timer Counter 2
        DCD TC3_Handler         ; Timer Counter 3
        DCD TC4_Handler         ; Timer Counter 4
        DCD TC5_Handler         ; Timer Counter 5
        DCD TC6_Handler         ; Timer Counter 6
        DCD TC7_Handler         ; Timer Counter 7
        DCD TC8_Handler         ; Timer Counter 8
        DCD PWM_Handler         ; Pulse Width Modulation Controller
        DCD ADC_Handler         ; ADC Controller
        DCD DACC_Handler        ; DAC Controller
        DCD DMAC_Handler        ; DMA Controller
        DCD UOTGHS_Handler      ; USB OTG High Speed
        DCD TRNG_Handler        ; True Random Number Generator
        DCD EMAC_Handler        ; Ethernet MAC
        DCD CAN0_Handler        ; CAN Controller 0
        DCD CAN1_Handler        ; CAN Controller 1

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Dummy Interrupt Handlers
        SECTION .text : CODE:NOROOT (2)
        THUMB
        
        PUBWEAK NMI_Handler
NMI_Handler
        NOP
        B NMI_Handler

        PUBWEAK HardFault_Handler
HardFault_Handler
        NOP
        B HardFault_Handler

        PUBWEAK MemoryManagementFault_Handler
MemoryManagementFault_Handler
        NOP
        B MemoryManagementFault_Handler

        PUBWEAK BusFault_Handler
BusFault_Handler
        NOP
        B BusFault_Handler

        PUBWEAK UsageFault_Handler
UsageFault_Handler
        NOP
        B UsageFault_Handler

        PUBWEAK SVCall_Handler
SVCall_Handler
        NOP
        B SVCall_Handler

        PUBWEAK PendSV_Handler
PendSV_Handler
        NOP
        B PendSV_Handler

        PUBWEAK SysTick_Handler
SysTick_Handler
        NOP
        B SysTick_Handler

        PUBWEAK SUPC_Handler
SUPC_Handler
        NOP
        B SUPC_Handler

        PUBWEAK RSTC_Handler
RSTC_Handler
        NOP
        B RSTC_Handler

        PUBWEAK RTC_Handler
RTC_Handler
        NOP
        B RTC_Handler

        PUBWEAK RTT_Handler
RTT_Handler
        NOP
        B RTT_Handler

        PUBWEAK WDG_Handler
WDG_Handler
        NOP
        B WDG_Handler

        PUBWEAK PMC_Handler
PMC_Handler
        NOP
        B PMC_Handler

        PUBWEAK EEFC0_Handler
EEFC0_Handler
        NOP
        B EEFC0_Handler

        PUBWEAK EEFC1_Handler
EEFC1_Handler
        NOP
        B EEFC1_Handler

        PUBWEAK UART_Handler
UART_Handler
        NOP
        B UART_Handler

        PUBWEAK SMC_SDRAMC_Handler
SMC_SDRAMC_Handler
        NOP
        B SMC_SDRAMC_Handler

        PUBWEAK SDRAMC_Handler
SDRAMC_Handler
        NOP
        B SDRAMC_Handler

        PUBWEAK PIOA_Handler
PIOA_Handler
        NOP
        B PIOA_Handler

        PUBWEAK PIOB_Handler
PIOB_Handler
        NOP
        B PIOB_Handler

        PUBWEAK PIOC_Handler
PIOC_Handler
        NOP
        B PIOC_Handler

        PUBWEAK PIOD_Handler
PIOD_Handler
        NOP
        B PIOD_Handler

        PUBWEAK PIOE_Handler
PIOE_Handler
        NOP
        B PIOE_Handler

        PUBWEAK PIOF_Handler
PIOF_Handler
        NOP
        B PIOF_Handler

        PUBWEAK USART0_Handler
USART0_Handler
        NOP
        B USART0_Handler

        PUBWEAK USART1_Handler
USART1_Handler
        NOP
        B USART1_Handler

        PUBWEAK USART2_Handler
USART2_Handler
        NOP
        B USART2_Handler

        PUBWEAK USART3_Handler
USART3_Handler
        NOP
        B USART3_Handler

        PUBWEAK HSMCI_Handler
HSMCI_Handler
        NOP
        B HSMCI_Handler

        PUBWEAK TWI0_Handler
TWI0_Handler
        NOP
        B TWI0_Handler

        PUBWEAK TWI1_Handler
TWI1_Handler
        NOP
        B TWI1_Handler

        PUBWEAK SPI0_Handler
SPI0_Handler
        NOP
        B SPI0_Handler

        PUBWEAK SPI1_Handler
SPI1_Handler
        NOP
        B SPI1_Handler

        PUBWEAK SSC_Handler
SSC_Handler
        NOP
        B SSC_Handler

        PUBWEAK TC0_Handler
TC0_Handler
        NOP
        B TC0_Handler

        PUBWEAK TC1_Handler
TC1_Handler
        NOP
        B TC1_Handler

        PUBWEAK TC2_Handler
TC2_Handler
        NOP
        B TC2_Handler

        PUBWEAK TC3_Handler
TC3_Handler
        NOP
        B TC3_Handler

        PUBWEAK TC4_Handler
TC4_Handler
        NOP
        B TC4_Handler

        PUBWEAK TC5_Handler
TC5_Handler
        NOP
        B TC5_Handler

        PUBWEAK TC6_Handler
TC6_Handler
        NOP
        B TC6_Handler

        PUBWEAK TC7_Handler
TC7_Handler
        NOP
        B TC7_Handler

        PUBWEAK TC8_Handler
TC8_Handler
        NOP
        B TC8_Handler

        PUBWEAK PWM_Handler
PWM_Handler
        NOP
        B PWM_Handler

        PUBWEAK ADC_Handler
ADC_Handler
        NOP
        B ADC_Handler

        PUBWEAK DACC_Handler
DACC_Handler
        NOP
        B DACC_Handler

        PUBWEAK DMAC_Handler
DMAC_Handler
        NOP
        B DMAC_Handler

        PUBWEAK UOTGHS_Handler
UOTGHS_Handler
        NOP
        B UOTGHS_Handler

        PUBWEAK TRNG_Handler
TRNG_Handler
        NOP
        B TRNG_Handler

        PUBWEAK EMAC_Handler
EMAC_Handler
        NOP
        B EMAC_Handler

        PUBWEAK CAN0_Handler
CAN0_Handler
        NOP
        B CAN0_Handler

        PUBWEAK CAN1_Handler
CAN1_Handler
        NOP
        B CAN1_Handler

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; dummy main routine
        PUBWEAK main
main
        NOP
        B main

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; startup code
        SECTION .text : CODE (2)
        THUMB
        PUBLIC __iar_program_start

__iar_program_start
Reset_Handler
        ; turn off watchdog
        LDR R0, =0x400E1A54   ; WDT_MR
        LDR R1, =0x00008000   ; set WD_DIS
        STR R1, [R0]
        
        ; point vector table to SRAM0
        LDR R0, =0xE000ED08   ; SCB_VTORS
        LDR R1, =0x20000000   ; address of vector table
        STR R1, [R0]

        ; set SCK (slow clock) to external 32.768 kHz crystal
        LDR R0, =0x400E1A10   ; SUPC_CR
        LDR R1, =0xA5000008   ; set key and XTALSEL
        STR R1, [R0]
        
        ; wait for SCK to be ready
        LDR R0, =0x400E1A24   ; SUPC_SR
sck_loop
        LDR  R1, [R0]
        ANDS R1, R1, #0x80    ; check OSCSEL
        BEQ  sck_loop

        ; enable Main Crystal Oscillator as MAINCK
        LDR R0, =0x400E0620   ; CKGR_MOR
        LDR R1, =0x0137FF01   ; set MOSCSEL and MOSCXTEN, startup time 255
        STR R1, [R0]
        
        ; wait for Main Crystal Oscillator to be ready
        LDR R0, =0x400E0668   ; PMC_SR
xten_loop
        LDR  R1, [R0]
        ANDS R1, R1, #0x01    ; check MOSCXTS
        BEQ  xten_loop

        ; set MCK as MAINCK
        LDR R0, =0x400E0630   ; PMC_MCKR
        LDR R1, [R0]
        AND R1, R1, #0xFFFFFFFC   ; clear CSS bits
        ORR R1, R1, #0x00000001   ; set CSS field to 1
        STR R1, [R0]
        
        ; wait until ready
        LDR R0, =0x400E0668   ; PMC_SR
css_loop
        LDR  R1, [R0]
        ANDS R1, R1, #0x8     ; check MCKRDY
        BEQ css_loop

        ; set MCK prescaler to zero
        LDR R0, =0x400E0630   ; PMC_MCKR
        LDR R1, [R0]
        AND R1, R1, #0xFFFFFF8F   ; clear PRES bits
        STR R1, [R0]
        
        ; wait until ready
        LDR R0, =0x400E0668   ; PMC_SR
pres_loop
        LDR R1, [R0]
        ANDS R1, R1, #0x8     ; check MCKRDY
        BEQ pres_loop

        ; now, the CPU should run at 12 MHz
        ; MAINF in CKGR_MCFR should read ca. 5859
        ; (lower 2 bytes at 0x400E0624 should read ca. 0x16E3)

        ; jump to main
        B main
        
        END