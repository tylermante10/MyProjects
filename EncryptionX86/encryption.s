#@Author tylermante10
.file "encryption.s"
.section .rodata
.output:
        .byte 'c'
# .next:
#	.byte 'o' 
.data
#required declaratives
.globl main
        .type main, @function
.text
main:
        movw %sp, %bp # Stack pointer is now set up

        pushw  %r12w      #r12 is going to be index
        pushw  %r13w      #r13 will point to beginning of array

        xorw %ax, %ax
        call getchar
        movw %ax, %di
        xorw %ax , %ax
        call putchar
	ret
	
        # Allocating space for the array
        #movq %rsp, %rdi # rdi is at base of array
        #subq $8, %rsp  # stack pointer has been moved down

        # Set up counter index
        #movq $0, %rsi
	
	#movq $.next, (%rdi, %rsi, 1) 
	#incq %rsi	
	#movq (%rdi, %rsi, 1), %rdi
	#movq %rdi, %r13
	
	#xorq %rax, %rax
	#call printf
	

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

        # xorq %rax, %rax                 #Clear rax before call to short
        # Calling Short: rdi is array, rsi is 5
        #call short
        #movq %rax, %r12                 # rax should be 3 here

        # Set up call to print shortest string
        #movq $.output, %rdi
        #movq (%r13,%r12,8), %rsi
        #xorq %rax, %rax
        #call printf
        # Exiting Stuff
End:
        popw %r13w
        popw %r12w
        leave
        ret
.size main, .-main

