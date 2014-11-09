#ifndef _COMPRESSOR_H
#define _COMPRESSOR_H

#include <stdlib.h>

#include "utils.h"

typedef struct CompressStruct_ {
  char* size;
  char* distance;
} CompressStruct;

char *compress(char* input, int llis_size, int ent_size);

#endif
