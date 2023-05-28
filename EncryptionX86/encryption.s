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

# .next:
#	.byte 'o' 
.data
x:
	.quad 0
#required declaratives
.globl main
        .type main, @function
.text
main:
	pushq %rbp
        movq %rsp, %rbp # Stack pointer is now set up

	subq $8, %rsp 	# Array allocation
	
        pushq  %r12      #r12 is going to be index
        pushq  %r13      #r13 will point to beginning of array

	movq %rbp, %r13	 #r13 set up to arr[0]

	movq $.format, %rdi
	movq $x, %rsi
        xorq %rax , %rax
        call scanf
	
	movq $7, %r12
	movq $x, %r13
	movq (%r13, %r12, 1), %rsi
	movq $.final, %rdi
	xorq %rax, %rax
	call printf	
	 
	#movq $x, %rsi
	#movq $.final, %rdi
	#xorq %rax, %rax
	#call printf

        popq %r13
        popq %r12
        leave
        ret
.size main, .-main

