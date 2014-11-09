#include "utils.h"

char *intToBits(int i, int size) {
	int s = (int) (log10(size)/log10(2));
	char *out = (char*) malloc((s+1)*sizeof(char));
	int j;
	if(i == size) {
		for(j=0;j<s;j++) {
			out[j] = '0';
		}
		out[s] = '\0';
		return out;
	}

	unsigned mask = 1 << (s-1);
	for(j=0;j<s;j++){
		out[j] = (i & mask ? '1' : '0');
		i <<= 1;
	}
	out[s] = '\0';
	return out;
}

int BitsToInt(char* bits, int size) {

	return 0;
}

char *substr(char* text, int pos, int size) {
	if(pos+size > strlen(text)) return NULL;
	char *out = (char*) malloc((size+1)*sizeof(char));
	int i;
	for(i=0;i<size; i++)
		out[i] = text[pos+i];
	out[size] = '\0';
	return out;
}
