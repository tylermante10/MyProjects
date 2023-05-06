#include <stdio.h>
#include <stdlib.h>
#include "constants.h"
#include "flipBits.h"
int main(){
	unsigned char* bytes = malloc(BYTE_SIZE * sizeof(unsigned char));
	char* flipped = malloc(LAST_BIT * sizeof(char));
	int i,j= 0;
	int ch;
	while(ch != EOF){
		ch = getchar();
		bytes[i] = (char) ch;
		if(i == LAST_BIT){
		      	flipped = flipArr(bytes);
		      	i=-1;
		     	while(j < LAST_BIT){
				putchar(flipped[j]);
				j++;
		      	}
		      	j=0;	
		}
		i++;	 
	}
	return 0;
}
