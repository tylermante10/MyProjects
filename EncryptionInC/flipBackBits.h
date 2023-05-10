#include <stdio.h>
#include <stdlib.h>
#include "constants.h"
/* Given an array of 8 characters, change array contents by bitwise comparison encryption scheme.
 *  * @param: raw- the untouched character array from the file input
 *   * @returns: flipped_arr- the new character array, adjusted via encryption scheme
 *    */
char* flipBackArr(unsigned char* raw){
        char* flipped_arr = malloc(BYTE_SIZE * sizeof(char));
        int i,j = 0;
        unsigned char  c, operand, encrypt_bit;
        /*Initialize to zero */
        encrypt_bit = 0;
        for(i = LAST_ADJUSTED_BIT_IDX; i>= 0; i--){
                c  = raw[i];
                /* If the msb is > 128, the next bit is a 1*/
                if(c > 128){
			encrypt_bit = encrypt_bit ^ (1 << i);
                        c = c ^(1 << 7);                      
                        flipped_arr[i] = c;
                } /*Otherwise, the character stays the same*/
                else{
                        flipped_arr[i] = c;
                }
        }
	flipped_arr[LAST_BIT] = encrypt_bit;
        return flipped_arr;
}


