#ifndef _COMPRESSOR_H
#define _COMPRESSOR_H

#include <stdlib.h>

typedef struct CompressStruct_
{
  int distance;     
  char size;
} CompressStruct;


char *compress(char* input, int llis_size, int ent_size);

#endif
