#@Author tylermante10
.file "lab6.s"
.section .rodata
.output:
        .string "The short String is %s\n"
.lineOne:
        .string "The markings on your surface"
.lineTwo:
        .string "Your speckled face"
.lineThree:
        .string "Flawed crystals hang from your ears"
.lineFour:
        .string "I couldn't gauge your fears"
.lineFive:
        .string "I can't relate to my peers"
        #.string "I'd rather live outside"
        #.string "I'd rather chip my pride than lose my mind out here"
        #.string "Maybe I'm a fool"
.data
#required declaratives
.globl main
        .type main, @function
.text
short:
        pushq %rbp
        movq %rsp, %rbp

        pushq %r12              #r12 = max(used as constant)
        pushq %r13              #r13 = array
        pushq %r14              #r14 = index counter
        pushq %r15              #r15 = currentShort LENGTH
        pushq %rbx              #rbx = SHORTEST INDEX

        # Set up stack frame for array
        movq %rsi, %r12         #r12 set up to save 5 through calls
        movq %rdi, %r13         #r13 holds beginning of array

        # Set up parameters for loop
        movq $0, %r14           #r14 = 0 (COUNTER VARIABLE)
        movq $1110, %r15                #r15 = 1 (currentShort)
        movq $0, %rbx

        #Iterate through array
LoopStart:
        cmpq %r14, %r12                 #5 - i
        je Exit                         #Jump if 0
        movq (%r13, %r14,8), %rdi       #Arr(i) is in rdi
        cmpq %r14, %r12                 #5 - i
        je Exit                         #Jump if 0
        movq (%r13, %r14,8), %rdi       #Arr(i) is in rdi
        xorq %rax, %rax
        call strlen
        cmpq %r15, %rax                 #Compare returned length with currMax
	jg Iterate
        movq %rax, %r15                 #if rax < currShort, rax is set to found idx
        movq %r14, %rbx
Iterate:
        incq %r14
        jmp LoopStart

Exit:
        movq %rbx, %rax
        popq %rbx
        popq %r15
        popq %r14
        popq %r13
        popq %r12
        leave
        ret
main:
        pushq %rbp      # sets up stack frame
        movq %rsp, %rbp # Stack pointer is now set up

        pushq %r12      #r12 is going to be index
        pushq %r13      #r13 will point to beginning of array

        # Allocating space for the array
        movq %rsp, %rdi # rdi is at base of array
        subq $40, %rsp  # stack pointer has been moved down

        # Set up counter index
        movq $0, %rsi

        # Move all string addresses onto array: MODIFY HERE
        movq $.lineOne, (%rdi,%rsi,8)   # rdi[0] = line 1
        incq %rsi
        movq $.lineTwo, (%rdi,%rsi,8)   # rdi[1] = line 2
        incq %rsi
        movq $.lineThree, (%rdi,%rsi,8) # rdi[2] = line 3
        incq %rsi
        movq $.lineFour, (%rdi,%rsi,8)  # rdi[3] = line 4
        incq %rsi
        movq $.lineFive, (%rdi,%rsi,8)  # rdi[4] = line 5
        incq %rsi
        # ADD INTO ARRAY HERE
        movq %rdi, %r13

        xorq %rax, %rax                 #Clear rax before call to short
        # Calling Short: rdi is array, rsi is 5
        call short
        movq %rax, %r12                 # rax should be 3 here

        # Set up call to print shortest string
        movq $.output, %rdi
        movq (%r13,%r12,8), %rsi
        xorq %rax, %rax
        call printf
        # Exiting Stuff
        popq %r13
        popq %r12
        leave
        ret
.size main, .-main

