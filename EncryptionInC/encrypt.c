#include <stdio.h>
#include <stdlib.h>
#include "constants.h"
#include "flipBits.h"
/* Main function to handle file input, array memory, and calling the encryption function */ 
int main(){
	/* Variables: 
 	* input_chars: an array of the most recent 8 characters seen in the file
 	* flipped: array holding the resulting 7 characters from encryption
 	* i,j: index variables
 	* ch: individual character to check 
 	*/
	unsigned char* input_chars = malloc(BYTE_SIZE * sizeof(unsigned char));
	char* flipped = malloc(LAST_BIT * sizeof(char));
	int i,j,remaining_chars = 0;
	int encrypt_idx = 0;
	int file_idx = 0;
	int ch;
	while(ch != EOF){
		ch = getchar();
		input_chars[i] = (char) ch;
		file_idx++;
		if(i == LAST_BIT){
		      	flipped = flipArr(input_chars);
			/* i incremented to zero in line 30 */
		      	i=-1;
			encrypt_idx = encrypt_idx + 8;
		     	while(j < LAST_BIT){
				putchar(flipped[j]);
				j++;
		      	}
		      	j=0;	
		}
		i++;	 
	}
/*	remaining_chars = file_idx - encrypt_idx;
	for (i =0; i< remaining_chars; i++){
		ch = (int) input_chars[i];
		if(ch != EOF){
			printf("New char is %c\n", ch);
		}
	}
*/
	return 0;
}
