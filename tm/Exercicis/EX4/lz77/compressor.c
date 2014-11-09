#include "compressor.h"

int search_pattern(char* text, int size_text, char* pattern, int size_partial_pattern, int size_pattern, CompressStruct* cs) {
	int i,j;
	int count;
	for(i=size_text-size_partial_pattern;i>=0;i--) {
		count=0;
		for(j=0; j<size_partial_pattern; j++) {
			if(text[i+j] == pattern[j])
				count++;
			else
				break;
		}
		if(count == size_partial_pattern) {
			/*if(size_text-i<size_partial_pattern) {
				printf("PROVAAA");
			}*/
			cs->size = intToBits(size_partial_pattern, size_pattern);
			cs->distance = intToBits(size_text-i, size_text);
			return size_partial_pattern;
		}
	}
	return -1;
}

/*
BENCH SEARCH FUNCTION
=====================
char* text = malloc(17*sizeof(char));
strcpy(text,"0100101101101010");
text[17] = '\0';

char* pattern = malloc(5*sizeof(char));
strcpy(pattern,"0111");
pattern[5] = '\0';

int pos = search(text, 16, pattern, 4, cs);

printf("pos: %d\n", pos);
printf("cs->size: %s\n", cs->size);
printf("cs->distance: %s\n", cs->distance);
*/


int search(char* text, int size_text, char* pattern, int size_pattern, CompressStruct* cs) {

	int i, pos;
	char* sub_pattern;
	for(i=size_pattern;i>=1;i--) {
		sub_pattern = substr(pattern, 0, i);
		pos = search_pattern(text, size_text, sub_pattern, i, size_pattern, cs);
		free(sub_pattern);
		if(pos != -1) return pos;
	}
	return -1;
}

char *compress(char* input, int llis_size, int ent_size) {

	char *llis, *ent, *out;
	int p_llis=0, p_ent=llis_size, size_out = llis_size+1, inc_realloc, input_size = strlen(input);
	CompressStruct *cs;

	inc_realloc = (int)((log10(llis_size)/log10(2))+(log10(ent_size)/log10(2)));

	//printf("inc_realloc: %d\n", inc_realloc);

	out = (char*) malloc(size_out*sizeof(char));

	llis = substr(input, p_llis, llis_size);
	ent = substr(input, p_ent, ent_size);
	strcpy(out,llis);


	while(ent != NULL) { //TODO
		cs = (CompressStruct*) malloc(sizeof(CompressStruct));
		int pos = search(llis, llis_size, ent, ent_size, cs);
		if(pos == -1) {
			printf("ERROR DE BUSQUEDA");
			exit(0);
		}
		size_out += inc_realloc;
		out = (char*) realloc(out, size_out*sizeof(char));

		//printf("input -> %s\n", input);
		/*printf("lliscant -> %s\n", llis);
		printf("entrada -> %s\n", ent);
		printf("pos (hauria de ser igual que L)-> %d\n", pos);
		printf("L -> %d\n", bitsToInt(cs->size,5));
		printf("D -> %d\n\n", bitsToInt(cs->distance,6));*/

		strcat(out, cs->size);
		strcat(out, cs->distance);

		p_llis += pos;
		p_ent += pos;

		free(cs->size);
		free(cs->distance);
		free(cs);
		free(llis);
		free(ent);
		llis = substr(input, p_llis, llis_size);
		ent = substr(input, p_ent, ent_size);
	}
	free(llis);
	free(ent);

	if(input_size-p_ent > 0) {
		char *padding = substr(input, p_ent, input_size-p_ent);
		size_out+=input_size-p_ent+1;
		out = (char*) realloc(out, size_out*sizeof(char));
		strcat(out, padding);
		free(padding);
	}
	//out[size_out] = '\0';
	return out;
}
