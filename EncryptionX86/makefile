#change the last dependency for each new project
all:  tags headers fib

#this entry has to change 
# NEVER compile .c files with this rule - it lacks needed compile time flags
encryption: encryption.o
	 gcc -g -o $@ $^
#this is where you create an entry to build your zip file
#it will also update with new labs




#this entry stays for C code 
tags: *.c
	ctags -R .

# this entry stays for C code labs
headers: *.c tags
	headers.sh
#
#
#         # this entry takes care of C files depending on header files
# It has the flags for compiling the c code
# # It you don't like makefiles, consider typing the following line ten  thousand times  # and reconsider.
# Be sure that this one doesn't get an inserted line break anywhere
%.o:%.c *.h
	gcc -ansi -pedantic -Wimplicit-function-declaration -Wreturn-type -g -c $< -o $@
#
#
#
