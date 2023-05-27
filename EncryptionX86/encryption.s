#@Author tylermante10
.file "encryption.s"
.section .rodata
.output:
        .string "Enter the string:\n"
.input:
	.string "%s"
.format:
	.string "%19s"
.final:
	.string "%s"
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

	subq $8, %rsp
	
        pushq  %r12      #r12 is going to be index
        pushq  %r13      #r13 will point to beginning of array

	movq %rbp, %r13

	movq $.output , %rdi
        xorq %rax, %rax
        call printf
	movq $.format, %rdi
	movq $x, %rsi
        xorq %rax , %rax
        call scanf

	movq $x, %rsi
	movq $.final, %rdi
	xorq %rax, %rax
	call printf

	xorq %rax, %rax
        popq %r13
        popq %r12
        leave
        ret
.size main, .-main

