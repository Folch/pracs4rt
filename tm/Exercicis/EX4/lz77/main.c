#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <getopt.h>

#include "compressor.h"
#include "decompressor.h"

int size_ent = 8;
int size_llis = 16;

void parse_opt(int argc,char *argv[]) {
	int c;
	static struct option long_options[] = {
		{"size_ent",     required_argument, 0, 'e'},
		{"size_llis",  required_argument, 0, 'l'},
		{0, 0, 0, 0}
	};


	while (1) {
		/* getopt_long stores the option index here. */
		int option_index = 0;

		c = getopt_long (argc, argv, "e:l:",
				long_options, &option_index);

		/* Detect the end of the options. */
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
}

int main(int argc,char *argv[]) {


	parse_opt(argc, argv);


	char* in = malloc(100*sizeof(char));
	strcpy(in,"0101010011101010111001100110011001100");

	char* c = compress(in, size_llis, size_ent);
	//char* sub = substr(in, 10, 4);
	//char* p = intToBits(3,8);

	//printf("size: %d\n", sizeof(sub));

	printf("entrada: %s\n", in);
	printf("compress: %s\n", c);
	printf("%d entrada\n", size_ent);
	printf("%d lliscant\n", size_llis);
	return 0;
}
