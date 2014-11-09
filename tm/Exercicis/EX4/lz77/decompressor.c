#include "decompressor.h"

char *decompress(char* input, int llis_size, int ent_size) {

	char *out, *trama, *size_bits, *distance_bits, *init_input;
	int size, distance, l_trama_distance, l_trama_size, input_size = strlen(input);
	int p_trama=llis_size, size_out = llis_size+1, inc_realloc;


	l_trama_distance = (int)(log10(llis_size)/log10(2));
	l_trama_size = (int)(log10(ent_size)/log10(2));

	out = (char*) malloc(size_out*sizeof(char));
	init_input = substr(input, 0, llis_size);
	strcpy(out,init_input);
	free(init_input);


	while(l_trama_size+l_trama_distance <= input_size-p_trama){
		size_bits = substr(input, p_trama, l_trama_size);
		size = bitsToInt(size_bits, l_trama_size);
		p_trama += l_trama_size;
		distance_bits = substr(input, p_trama, l_trama_distance);
		distance = bitsToInt(distance_bits, l_trama_distance);
		p_trama += l_trama_distance;

		/*printf("input: %s\n", input);
		printf("out: %s\n", out);
		printf("size_bits: %s\n", size_bits);
		printf("size: %d\n", size);
		printf("distance_bits: %s\n", distance_bits);
		printf("distance: %d\n\n", distance);*/

		trama = substr(out, strlen(out)-distance, size);
		if(trama == NULL){
			//printf("distance: %d y size: %d\n", distance, size);
			break;
		}

		size_out += size;
		out = (char*) realloc(out, size_out*sizeof(char));
		strcat(out, trama);

		free(distance_bits);
		free(size_bits);
		free(trama);
	}

	//printf("out: %s\n", out);
	if(input_size-p_trama >0) {
		char *padding = substr(input, p_trama, input_size-p_trama);
		size_out+=input_size-p_trama+1;
		out = (char*) realloc(out, size_out*sizeof(char));
		strcat(out, padding);
		free(padding);
	}
	//out[size_out] = '\0';
	return out;
}
