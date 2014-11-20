import math
from random import *
from bitarray import bitarray



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


def read(path):
    with open(path, mode='rb') as file: # b is important -> binary
        fileContent = file.read()
        b = bitarray()
        b.fromstring(fileContent)
        return b.to01()
    return None


def write(file,path):
    return ""
