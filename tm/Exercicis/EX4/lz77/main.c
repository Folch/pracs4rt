#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <getopt.h>

#include "compressor.h"
#include "decompressor.h"

int size_ent = 16;
int size_llis = 2048;

/*void parse_opt(int argc,char *argv[]) {
	int c;
	static struct option long_options[] = {
		{"size_ent",     required_argument, 0, 'e'},
		{"size_llis",  required_argument, 0, 'l'},
		{0, 0, 0, 0}
	};


	while (1) {

		int option_index = 0;

		c = getopt_long (argc, argv, "e:l:",
				long_options, &option_index);

		if (c == -1) break;

		switch (c) {

			case 'e':
				size_ent = atoi(optarg);
				break;
			case 'l':
				size_llis = atoi(optarg);
				break;

			default:
				abort();
		 }
	}
}*/

int main(int argc,char *argv[]) {


	//parse_opt(argc, argv);


	/*
	char* in = malloc(100*sizeof(char));
	strcpy(in,"0101010011101010111001100110011001100");
	*/

	char *in = randomInput(10000);

	char* c = compress(in, size_llis, size_ent);
	char* d = decompress(c, size_llis, size_ent);


	printf("El tamany de l'entrada utilitzat: %d\n", size_ent);
	printf("El tamany de la finestra lliscant utilitzat: %d\n", size_llis);
	printf("Mida del input: %zu\n", strlen(in));
	printf("Mida de la compressió: %zu\n", strlen(c));
	printf("Mida de la descompressió: %zu\n", strlen(d));

	if(strcmp(in, d)==0)
		printf("La cadena d'entrada i la de descompressió són la mateixa.\n");
	else
		printf("La cadena d'entrada i la de descompressió NO són la mateixa.\n");


	free(in);
	free(c);
	free(d);
	return 0;
}
