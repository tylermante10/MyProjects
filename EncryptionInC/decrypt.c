#include <stdio.h>
#include <stdlib.h>
#include "constants.h"
#include "flipBackBits.h"
/* Main function to handle file input, array memory, and calling the encryption function */
int main(){
        /* Variables:
 *         * input_chars: an array of the most recent 8 characters seen in the file
 *         * flipped: array holding the resulting 7 characters from encryption
 *         * i,j: index variables
 *         * ch: individual character to check
 *         */
        unsigned char* input_chars = malloc(LAST_BIT * sizeof(unsigned char));
        char* flippedBack = malloc(BYTE_SIZE * sizeof(char));
        int i,j,remaining_chars = 0; 
	int encrypt_idx = 0;
        int file_idx = 0;
        char ch;
        while(ch != EOF){
                ch = getchar();
                input_chars[i] = ch;
		file_idx++;
                if(i == LAST_ADJUSTED_BIT_IDX){
                        flippedBack = flipBackArr(input_chars);
                        /* i incremented to zero in line 30 */
                        i=-1;
			encrypt_idx = encrypt_idx + 7;
                        while(j < BYTE_SIZE){
				if(flippedBack[j] == 'H' && input_chars[j] != 'h'){
					putchar('h');
				}
				else{
                                putchar(flippedBack[j]);
                        	}
				j++;
			}
                        j=0;
                }
                i++;
        }
        remaining_chars = file_idx - encrypt_idx;
        for (i =0; i< remaining_chars; i++){
                ch = input_chars[i];
                if(ch != EOF){
                        putchar(ch);
                }
        }

        return 0;
}


