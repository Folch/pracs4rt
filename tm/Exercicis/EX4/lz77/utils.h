#ifndef _UTILS_H
#define _UTILS_H

#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <math.h>

char *intToBits(int, int size);
int bitsToInt(char*, int size);
char *substr(char*, int pos, int size);
char* randomInput(int n);

#endif
