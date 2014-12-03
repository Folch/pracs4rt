import time
import sys

from utils import utils
from decompress import decompress
from compress import compress



if __name__ == "__main__":
    Mdes = 32768
    Ment = 64

    print "Mdes:",Mdes
    print "Ment:",Ment
    if(len(sys.argv) == 3):
        file = utils.read(sys.argv[1])
        if(file == None):
            print "Error I/O: the file doesn't exists."
            exit(0)
        print "Longitud de l'entrada a comprimir:",len(file)
        t1 = time.time()
        output = compress.compress(file,Mdes,Ment)
	t1 = time.time()-t1
        print "Temps compressio:", t1
        print "Compressio: %f : 1" % (float(len(file))/len(output))
        utils.writeCompress(output, sys.argv[2])

    elif(len(sys.argv) == 2):
        file = utils.readCompress(sys.argv[1])
        t1 = time.time()
        decompressed = decompress.decompress(file,Mdes,Ment)
        print "Temps descompressio:",time.time()-t1
        print "Descompressio: ",len(decompressed)
        utils.write(decompressed, sys.argv[1]+".wav")

    else:
        print "Hint: \n\t* compress: python lz77-wav.py my/path/myfile.txt /my/path/namecompressfile"
        print "\t* decompress: python lz77-wav.py my/path/namecompressfile"
