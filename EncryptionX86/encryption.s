#@Author tylermante10
.file "encryption.s"
.section .rodata
# Optional

# .output:
#        .string "Enter the string:\n"
#.input:
#	.string "%s"
.format:
	.string "%8s"
.final:
	.string "%c"
.EOF:
	.string "EOF"
.flipBit:
	.int 7
# .next:
#	.byte 'o' 
.data
input_chars:
	.quad 0
#required declaratives
.globl main
        .type main, @function
.text
main:
	pushq %rbp
        movq %rsp, %rbp # Stack pointer is now set up

	subq $8, %rsp 	# Array allocation
	
        pushq %r12      #r12 is going to be index
        pushq %r13      #r13 will point to beginning of array
	movq %rbp, %r13	 #r13 set up to arr[0]

<<<<<<< HEAD
        xorw %ax, %ax
        call getchar
        movw %ax, %di
        xorw %ax , %ax
        call putchar

	
        # Allocating space for the array
        #movq %rsp, %rdi # rdi is at base of array
        #subq $8, %rsp  # stack pointer has been moved down

        # Set up counter index
        #movq $0, %rsi
	
	#movq $.next, (%rdi, %rsi, 1) 
	#incq %rsi	
	#movq (%rdi, %rsi, 1), %rdi
	#movq %rdi, %r13
=======
	# Scan the input for 8 chars at a time
	movq $.format, %rdi
	movq $input_chars, %rsi
        xorq %rax , %rax
        call scanf
>>>>>>> db4c890a9efcfb3510d3d113dc7c4d8f74a2ed79
	
	movq $input_chars, %r13
#CONTINUE HERE	
#Note: Look into data types in x86 (conversion of char -> int) (EOF -> -1) 
	#While no characters in the array are EOF, continue
	movq $-1, %r12
Check:
	incq %r12
	cmpq $8, %r12 # issue most likely here (comparing to EOF)
	je Exit
	movq (%r13, %r12, 1), %rsi
	movq $.final, %rdi
	xorq %rax, %rax
	call printf
	jmp Check
#END TBD: 
# Skips code below when uncommented
	#Grab the 8th character in the array and print it 
#	movq $7, %r12 	#The char we want is always at position 7
#	movq $input_chars, %r13
#	movq (%r13, %r12, 1), %rsi
#	movq $.final, %rdi
#	xorq %rax, %rax
#	call printf	
#Prints all inputted chars	 
	#movq $x, %rsi
	#movq $.final, %rdi
	#xorq %rax, %rax
	#call printf
Exit:
        popq %r13
        popq %r12
        leave
        ret
.size main, .-main

