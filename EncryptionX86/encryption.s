# :Nameme: Tyler Mante
.file "helloWorld.s"

.LCO:
        .string "Hello World\n" #String to be printed
.globl main
        .type main, @ function #Required directive for main
.text   #required directive
main:
        pushq %rbp #stack housekeeping #1
        movq %rsp, %rbp #stack housekeeping #2
        movq $.LCO, %rdi # want %rdi to be address of Hello World string

        movq $0, %rax #rax must be set to zero before any print function
        call printf

        leave  #necessary, trivial calls follow
        ret

        .size main, .-main



