#include <stdio.h>
#include <stdlib.h>
#include "constants.h"
/* Given an array of 8 characters, change array contents by bitwise comparison encryption scheme.
 *  * @param: raw- the untouched character array from the file input
 *   * @returns: flipped_arr- the new character array, adjusted via encryption scheme
 *    */
char* flipBackArr(unsigned char* raw){
        char* flipped_arr = malloc(LAST_BIT * sizeof(char));
        int i,j = 0;
        char c;
        unsigned char  operand, encrypt_bit;
        /*Initialize to zero */
        encrypt_bit = 0;
        for(i = LAST_ADJUSTED_BIT_IDX; i>= 0; i--){
                c  = raw[i];
                /* If the msb is > 128, the next bit is a 1*/
                if(operand > 128){
                        c = c ^(0 << 7);                      
                        flipped_arr[i] = c;
                } /*Otherwise, the character stays the same*/
                else{
                        flipped_arr[i] = c;
                }
        }
        return flipped_arr;
}


