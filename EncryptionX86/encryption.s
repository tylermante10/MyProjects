#@Author tylermante10
.file "encryption.s"
.section .rodata
.output:
        .byte $c 
.data
#required declaratives
.globl main
        .type main, @function
.text
main:
        pushq %rbp      # sets up stack frame
        movq %rsp, %rbp # Stack pointer is now set up

        pushq %r12      #r12 is going to be index
        pushq %r13      #r13 will point to beginning of array
	
	movq $output, %rdi
	xorq %rax, %rax
	call printf
        # Allocating space for the array
        movq %rsp, %rdi # rdi is at base of array
        subq $40, %rsp  # stack pointer has been moved down

        # Set up counter index
        movq $0, %rsi

        # Move all string addresses onto array: MODIFY HERE
        #movq $.lineOne, (%rdi,%rsi,8)   # rdi[0] = line 1
        #incq %rsi
        #movq $.lineTwo, (%rdi,%rsi,8)   # rdi[1] = line 2
        #incq %rsi
        #movq $.lineThree, (%rdi,%rsi,8) # rdi[2] = line 3
        #incq %rsi
        #movq $.lineFour, (%rdi,%rsi,8)  # rdi[3] = line 4
        #incq %rsi
        #movq $.lineFive, (%rdi,%rsi,8)  # rdi[4] = line 5
        #incq %rsi
        # ADD INTO ARRAY HERE
        #movq %rdi, %r13

        xorq %rax, %rax                 #Clear rax before call to short
        # Calling Short: rdi is array, rsi is 5
        #call short
        #movq %rax, %r12                 # rax should be 3 here

        # Set up call to print shortest string
        #movq $.output, %rdi
        #movq (%r13,%r12,8), %rsi
        #xorq %rax, %rax
        #call printf
        # Exiting Stuff
        popq %r13
        popq %r12
        leave
        ret
.size main, .-main

