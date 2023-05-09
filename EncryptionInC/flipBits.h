#include <stdio.h>
#include <stdlib.h>
#include "constants.h"
/* Given an array of 8 characters, change array contents by bitwise comparison encryption scheme. 
 * @param: raw- the untouched character array from the file input
 * @returns: flipped_arr- the new character array, adjusted via encryption scheme
 */
char* flipArr(unsigned char* raw){
        char* flipped_arr = malloc(LAST_BIT * sizeof(char));
        int i,j = 0;
	char c;
        unsigned char  operand, encrypt_bit;
	/* Take the last character of the input array */
        encrypt_bit = raw[LAST_BIT];
        for(i = LAST_ADJUSTED_BIT_IDX; i>= 0; i--){
                c  = raw[i];
		/* Shift each bit 1 to the left */
                encrypt_bit = encrypt_bit << 1;
		/* XOR result stored in operand variable */
                operand = c ^ encrypt_bit;
		/* If operand is > 128, there is a difference in msbs, so we encrypt*/
                if(operand > 128){
                        c = c ^(1 << 7);
			/* Cast necessary to store as a char */
                        c = (char) c;	
                        flipped_arr[i] = c;
                } /*Otherwise, the character stays the same*/
                else{
                        flipped_arr[i] = c;
                }
        }
	return flipped_arr;
}

