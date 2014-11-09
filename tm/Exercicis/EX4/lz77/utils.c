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

int bitsToInt(char* bits, int size) {
	int out = (int) strtol(bits, NULL, 2);
	if(out == 0) return (int)pow(2,size);
	return out;
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

char* randomInput(int n){
	int i;
	char* in = malloc((n+1)*sizeof(char));
	srand(time(NULL));
	for (i = 0;i < n; i++)
		in[i] = rand()%2 + '0';

	in[n] = '\0';
	return in;
}
