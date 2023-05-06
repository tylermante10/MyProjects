#include <stdio.h>
#include <stdlib.h>
#include "constants.h"
char* flipArr(unsigned char* raw){
        char* flipped_arr = malloc(LAST_BIT * sizeof(char));
        int i,j = 0;
	char c;
        unsigned char  operand, encrypt_bit;
        encrypt_bit = raw[LAST_BIT];
        for(i = LAST_ADJUSTED_BIT_IDX; i>= 0; i--){
                c  = raw[i];
                encrypt_bit = encrypt_bit << 1;
                operand = c ^ encrypt_bit;
                if(operand > 128){
                        c = c ^(1 << 7);
                        c = (char) c;	
                        flipped_arr[i] = c;
			/*operand = 0;*/
                }
                else{
                        flipped_arr[i] = c;
                }
        }
	return flipped_arr;
}

