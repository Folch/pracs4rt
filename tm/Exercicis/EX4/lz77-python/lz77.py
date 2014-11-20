import time
import sys

import compress
import decompress
import utils



if __name__ == "__main__":
    Mdes = 8
    Ment = 4

    if(len(sys.argv) == 3):
        file = utils.read(sys.argv[1])
        if(file == None):
            print "Error I/O: the file doesn't exists."
            exit(0)
        print "Longitud de l'entrada a comprimir:",len(file)
        t1 = time.time()
        output = compress.compress(file,Mdes,Ment)
        print "Temps compressio:",time.time()-t1
        print "Compressio: ",len(output)
        utils.write(output, sys.argv[2])

    elif(len(sys.argv) == 2):
        file = utils.read(sys.argv[1])
        t1 = time.time()
        decompressed = decompress.decompress(file,Mdes,Ment)
        print "Temps descompressio:",time.time()-t1
        print "Descompressio: ",len(decompressed)
        utils.write(decompressed, sys.argv[1]+".txt")

    else:
        print "Hint: \n\t* compress: python lz77.py /my/path/myfile.txt /my/path/namecompressfile"
        print "\t* decompress: python lz77.py /my/path/namecompressfile"

    print utils.read("hola.txt")
