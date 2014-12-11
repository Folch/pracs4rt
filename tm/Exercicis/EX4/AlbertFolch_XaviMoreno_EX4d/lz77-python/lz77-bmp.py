import time
import sys

from utils import utils
from decompress import decompress
from compress import compress



if __name__ == "__main__":
    Mdes = 2048
    Ment = 128

    print "Mdes:", Mdes
    print "Ment:", Ment
    if(len(sys.argv) == 3):
        file = utils.readbmp(sys.argv[1])
        if(file == None):
            print "Error I/O: the file doesn't exists."
            exit(0)
        print "Longitud de l'entrada a comprimir:",len(file)
        t1 = time.time()
        output = compress.compress(file,Mdes,Ment)
	t1 = time.time()-t1
        print "Temps compressio:", t1
        print "Mida comprimit:", len(output)
        print "Compressio: %f : 1" % (float(len(file))/len(output))
        utils.writeCompress(output, sys.argv[2])

    else:
        print "Hint: \n\t* compress: python lz77-bmp.py my/path/myfile.bmp /my/path/namecompressfile"
