import math
from random import *
import time

def find(bits, pattern):
    patternL = len(pattern)
    for iBit in range(len(bits),0,-1):
        if pattern == bits[iBit-patternL:iBit]:
            return len(bits[iBit:]) + patternL
    return -1
def decompress(compressed,Mdes, Ment):
    if(int(math.log(Mdes,2)) != math.log(Mdes,2) or int(math.log(Ment,2)) != math.log(Ment,2)):
        print "Mdes i Ment han de ser potencies de 2"
        return -1
    if(Ment > Mdes):
        print "Ment ha de ser ha de ser <= a Mdest"
        return -1
    if((Mdes+Ment) > len(compressed)):
        print "La longitud de les dades ha de ser <= a Mdes+Ment"
        return -1
    data = compressed[:Mdes]
    bitsL = int(math.log(Ment,2))
    bitsD = int(math.log(Mdes,2))
    i = Mdes
    j = 0
    while len(compressed[i:]) > Ment:
    #while len(compressed[i:]) >= (bitsL+bitsD):
        l = int(compressed[i:i+bitsL],2)
        if l == 0:
            l = 2**bitsL
        d = int(compressed[i+bitsL:i+bitsL+bitsD],2)
        if d == 0:
            d = 2**bitsD
        j = len(data) - d # per agafar els bits que s'afegiran

        bits = data[j:j+l]
        data += bits
        i += bitsL+bitsD


    if len(compressed[i:]) > 0:
        data += compressed[i:]
    return data

def compress(data,Mdes, Ment):
    if(int(math.log(Mdes,2)) != math.log(Mdes,2) or int(math.log(Ment,2)) != math.log(Ment,2)):
        print "Mdes i Ment han de ser potencies de 2"
        return -1
    if(Ment > Mdes):
        print "Ment ha de ser ha de ser <= a Mdest"
        return -1
    if((Mdes+Ment) > len(data)):
        print "La longitud de les dades ha de ser <= a Mdes+Ment"
        return -1
    dataCompressed = data[:Mdes]
    iDes = 0#index deslizante
    iEnt = Mdes#index entrada

    #print "iEnt inicial:",iEnt
    bitsL = int(math.log(Ment,2))
    bitsD = int(math.log(Mdes,2))

    binaryLength = '{0:0'+str(bitsL)+'b}'
    binaryDistance = '{0:0'+str(bitsD)+'b}'

    while(len(data[iEnt:]) > Ment):
        for i in range(Ment,0,-1):

            pattern = data[iEnt:iEnt+i]
            vDeslizante = data[iDes:iEnt]
            index = find(vDeslizante,pattern)

            if index != -1: #si el troba

                lDecimal = len(pattern)#longitud decimal

                l = binaryLength.format(lDecimal)#longitud binaria
                if len(l) > bitsL:
                    l = l[1:]
                dDecimal =  index

                d = binaryDistance.format(dDecimal)
                if len(d) > bitsD:
                    d = d[1:]

                dataCompressed += str(l)+str(d)

                iEnt += lDecimal
                iDes += lDecimal
                break
            else:
                continue

    if len(data[iEnt:]) > 0:
        dataCompressed += data[iEnt:]
    

    return dataCompressed
def randomBits(n):
    bits = ''
    for i in range(1,n+1):
        bits += str(randint(0,1))
    return bits


input1 = randomBits(10000)
#input1 = '0101010011101010111001100110011001100'
#input2= '01010100000001110100100001000010000111'
#input3 = '11110000'
Mdes = 1024
Ment = 32
print "Entrada a comprimir ",len(input1),":",input1

t1 = time.time()
output = compress(input1,Mdes,Ment)
print "Temps compressio:",time.time()-t1
print "Compressio: ",len(output)#,":",output
'''
t1 = time.time()
decompressed = decompress(output,Mdes,Ment)
print "Temps descompressio:",time.time()-t1
print "Descompressio: ",len(decompressed),":",decompressed
'''

#1024 256 16502
#2048 1024 15792
#512 256 17509
#256 128 18079
#1024 512 17245
#2048 64 13960
#2048 16 12579
