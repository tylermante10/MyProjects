#include <stdio.h>
#include <stdlib.h>
#include "constants.h"
/* Given an array of 8 characters, change array contents by bitwise comparison encryption scheme.
 *  * @param: raw- the untouched character array from the file input
 *   * @returns: flipped_arr- the new character array, adjusted via encryption scheme
 *    */
char* flipArr(unsigned char* raw){
        char* flipped_arr = malloc(LAST_BIT * sizeof(char));
        int i,j = 0;
        char c;
        unsigned char  operand, encrypt_bit;
        /* Take the last character of the input array */
        encrypt_bit = 0;
        for(i = LAST_ADJUSTED_BIT_IDX; i>= 0; i--){
                c  = raw[i];
                /* XOR result stored in operand variable */
                operand = c ^ encrypt_bit;
                /* If the msb is > 128, the next bit is a 1*/
                if(operand > 128){
                        c = c ^(1 << i);
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


