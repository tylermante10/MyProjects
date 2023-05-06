#include <stdio.h>
#include <stdlib.h>
#include "constants.h"
#include "flipBits.h"
int main(){
	unsigned char* bytes = malloc(BYTE_SIZE * sizeof(unsigned char));
	char* flipped = malloc(LAST_BIT * sizeof(char));
	int i,j= 0;
	int ch;
	printf("Enter a line with only eight letters:\n");
	while(ch != 10){
		ch = getchar();
		bytes[i] = (char) ch;
		printf("Bytes[%d] is %c\n", i, bytes[i]);
		if(i == LAST_BIT){
		      	flipped = flipArr(bytes);
		      	i=-1;
		     	while(j < LAST_BIT){
				/*change to putchar for file*/
				printf("result: %c\n", flipped[j]);
				j++;
		      	}
		      	j=0;	
		}
		i++;	 
	}
	return 0;
}
