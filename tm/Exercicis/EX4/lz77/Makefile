# This is the makefile that generates the executable

# Files to compile
FILES_C = main.c utils.c decompressor.c compressor.c read.c

# Exectuable to generate
TARGET = lz77

# Compilation options
CFLAGS = -g #-Wall -Werror

# Linker options
LFLAGS = -lm #-lpthread

# There is no need to change the instructions below this
# line. Change if you really know what you are doing.

FILES_O = $(subst .c,.o,$(FILES_C))

$(TARGET): $(FILES_O) Makefile
	gcc $(FILES_O) -o $(TARGET) $(LFLAGS)

%.o: %.c Makefile
	gcc $(CFLAGS) -c $<

all: $(TARGET)

clean:
	/bin/rm -rf $(FILES_O) $(TARGET) *~
