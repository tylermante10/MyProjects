.section .rodata
.output:
    .string "Enter the string:\n"
.format:
    .string "%19s"
.final:
    .string "Entered Name: %s\n"

.data
input_buffer: .space 20  # Allocate space for input string

.globl main
.type main, @function
main:
    pushq %rbp
    movq %rsp, %rbp  # Set up the stack frame

    subq $8, %rsp

    movq $.output, %rdi
    xorq %rax, %rax
    call printf

    lea input_buffer(%rip), %rdi
    movq $.format, %rsi
    xorq %rax, %rax
    call scanf

    movq $.final, %rdi
    lea input_buffer(%rip), %rsi
    xorq %rax, %rax
    call printf

    xorq %rax, %rax
    popq %rbp
    leave
    ret
.size main, .-main

