# Instructions
To test, first clone the repository and switch to the cloned directory.
```
$ git clone git@github.com:tylermante10/myProjects/EncryptionInC.git
$ cd EncryptionInC
```
Then, to test functionality with any text file, (e.g. input.txt) run the command:
```
$ encrypt <input.txt> output.txt 
```
### This will encrypt the given input text, and write the digest to the output.txt file
To view the encryption, either re-open the file output.txt or type "vim output.txt" to view in console
```
$ vim output.txt
```
To then decrypt the text back, type the following command to 1) decrypt the given message and 2) view the actual message:
```
$ decrypt <output.txt> back.txt
$ vim back.txt
```
### This will decrypt the given input text, and write the translation of the digest to the back.txt file

# File breakdown

## abc.txt
Run command encrypt < abc.txt > output.txt to test encryption scheme on first 8 letters 

## constants.h
Constants 6,7,8, which tend to be significant constants used throughout indexing, etc.

## decryption.c
New file 

## encrypt.c
Main functionality of command line interference/ handling encryption function call

## encrypt_command_line.c 
Useful intputs to command line on what to enter to see encryption described 

## encrypt.o
Output file from makefile

## flipBits.h
header file in encryptC that contains the encryption functionality, given an array 

## makefile
build executable 

## output.txt
Was used for file redirection to test encryption.c

## wf.txt
Sample input text (a favorite song of mine)
