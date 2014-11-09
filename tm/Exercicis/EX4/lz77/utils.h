#ifndef _UTILS_H
#define _UTILS_H

#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <math.h>

char *intToBits(int, int size);
int BitsToInt(char*, int size);
char *substr(char*, int pos, int size);

#endif
