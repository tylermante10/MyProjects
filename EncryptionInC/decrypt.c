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
        unsigned char* input_chars = malloc(BYTE_SIZE * sizeof(unsigned char));
        char* flippedBack = malloc(LAST_BIT * sizeof(char));
        int i,j= 0;
        int ch;
        while(ch != EOF){
                ch = getchar();
                input_chars[i] = (char) ch;
                if(i == LAST_ADJUSTED_BIT_IDX){
                        flippedBack = flipBackArr(input_chars);
                        /* i incremented to zero in line 30 */
                        i=-1;
                        while(j < LAST_BIT){
                                putchar(flippedBack[j]);
                                j++;
                        }
                        j=0;
                }
                i++;
        }
        return 0;
}


