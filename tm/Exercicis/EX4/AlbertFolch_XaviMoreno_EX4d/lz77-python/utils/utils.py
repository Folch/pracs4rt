import math
from random import *
from bitarray import bitarray
from skimage import data
import numpy as np



def find(bits, pattern):
    patternL = len(pattern)
    for iBit in range(len(bits),0,-1):
        if pattern == bits[iBit-patternL:iBit]:
            return len(bits[iBit:]) + patternL
    return -1

def randomBits(n):
    bits = ''
    for i in range(1,n+1):
        bits += str(randint(0,1))
    return bits

def check_initial_conditions(data, Mdes, Ment):
    if(int(math.log(Mdes,2)) != math.log(Mdes,2) or int(math.log(Ment,2)) != math.log(Ment,2)):
        print "Mdes i Ment han de ser potencies de 2"
        return -1
    if(Ment > Mdes):
        print "Ment ha de ser ha de ser <= a Mdest"
        return -1
    if((Mdes+Ment) > len(data)):
        print "La longitud de les dades ha de ser <= a Mdes+Ment"
        return -1
    return 0

def readCompress(path):
    with open(path, mode='rb') as file:
        fileContent = file.read()
        b = bitarray()
        b.frombytes(fileContent)

        padd = int(b[:8].tobytes())
        b = b[8:]
        for i in xrange(padd):
            b.pop()

        file.close()
        return b.to01()
    return None

def readbmp(path):
    RGB= np.array(data.imread(path))
    img_R = RGB[:,:,0]
    img_R = np.reshape(img_R, img_R.shape[0] * img_R.shape[1])
    img_G = RGB[:,:,1]
    img_G = np.reshape(img_G, img_G.shape[0] * img_G.shape[1])
    img_B = RGB[:,:,2]
    img_B = np.reshape(img_B, img_B.shape[0] * img_B.shape[1])
    img = np.concatenate((img_R,img_G,img_B))
    img = str(bytearray(img))
    b = bitarray()
    b.frombytes(img)
    return b.to01()

def writeCompress(file,path):
    b = bitarray(file)
    padd = bitarray()
    padd.fromstring(str(b.fill()))
    b = padd + b
    f = open(path, 'w+')
    f.write(b.tobytes())
    f.close()

def read(path):
    with open(path, mode='rb') as file:
        fileContent = file.read()
        b = bitarray()
        b.frombytes(fileContent)
        file.close()
        return b.to01()
    return None

def write(file,path):
    b = bitarray(file)
    f = open(path, 'w+')
    f.write(b.tobytes())
    f.close()
