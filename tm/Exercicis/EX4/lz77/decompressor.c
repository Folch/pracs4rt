#include "decompressor.h"

char *decompress(char* input, int llis_size, int ent_size) {

	char *out, *trama;
	int size, distance, l_trama_distance, l_trama_size, input_size = strlen(input);
	int p_trama=llis_size, size_out = llis_size+1, inc_realloc;


	l_trama_distance = (int)(log10(llis_size)/log10(2));
	l_trama_size = (int)(log10(ent_size)/log10(2));

	out = (char*) malloc(size_out*sizeof(char));
	strcpy(out,substr(input, 0, llis_size));


	while(p_trama+l_trama_size+l_trama_distance <= input_size){

		size = bitsToInt(substr(input, p_trama, l_trama_size), l_trama_size);
		p_trama += l_trama_size;
		distance = bitsToInt(substr(input, p_trama, l_trama_distance), l_trama_distance);
		p_trama += l_trama_distance;

		trama = substr(out, size_out-1-distance, size);
		size_out += size;
		out = (char*) realloc(out, size_out*sizeof(char));
		strcat(out, trama);

		printf("input %s\n", input);
		printf("output %s\n", out);
		printf("size %d\n", size);
		printf("distance %d\n", distance);
		printf("trama %s\n\n", trama);


	}

	out[size_out] = '\0';
	return out;
}
