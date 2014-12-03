import time
import sys

from utils import utils
from decompress import decompress
from compress import compress
from compress import rice



if __name__ == "__main__":
    Mdes = 32768
    Ment = 64

    print rice.rice(16, 32)

    print "Mdes:",Mdes
    print "Ment:",Ment
    if(len(sys.argv) == 3):
        f = utils.read(sys.argv[1])
        if(f == None):
            print "Error I/O: the file doesn't exist."
            exit(0)
        print "Longitud de l'entrada a comprimir:",len(f)
        t1 = time.time()
	i = rice.stringtorice(f, 16, 16384)
	print "Longitud Rice:",len(i)
        output = compress.compress(i,Mdes,Ment)
	t1 = time.time()-t1
        print "Temps compressio:", t1
        print "Compressio: %f : 1" % (float(len(f))/len(output))
	print "Compressio size:",len(output)
        utils.writeCompress(output, sys.argv[2])

    else:
        print "Hint: \n\t* compress: python lz77-wav.py my/path/myfile.txt /my/path/namecompressfile"


