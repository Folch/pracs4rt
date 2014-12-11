import math
import numpy as np
# -*- coding: utf-8 -*-
def rice(n,m):
    sign = "1" if n >= 0 else "0"
    q = int(abs(n)/m)
    qBits = ""
    for i in range(q):
        qBits += "1"
    qBits += "0"
    r = abs(n)%m
    mBits = int(math.log(m,2))
    binaryR = '{0:0'+str(mBits)+'b}'
    rBits = binaryR.format(int(r))
    return sign+qBits+rBits

def riceByte(bits,m):
    return rice(int(bits,2),m)

def stringtorice(bits, n, m):
    i=0
    out = ""
    while(i<len(bits)):
	out += riceByte(bits[i:i+n],m)
	i+=n
    return out

def saving(vmin,vmax,m = 32):
    minim = vmax+1
    maxim = vmin-1
    estalviMaxim = 0
    for i in xrange(vmin,vmax):
        if i == 0: continue
        bitsN = int(math.log(abs(i),2))+1
        bitsR = len(rice(i,m))
        #print "valor a codificat:",i,", ",bitsN," bits en codificacio binaria natural",bitsR,"bits en codificacio Rice"
        if  bitsN > bitsR:
            minim = min(i,minim)
            maxim = max(i,maxim)
            estalviMaxim = max(abs(bitsN-bitsR),estalviMaxim)

    return estalviMaxim,minim,maxim
def show(estalvi,minim,maxim):
    if(minim < maxim):
        print "Rang [",str(minim),":",str(maxim),"]"#Rang de numeros en el qual amb Rice ocupen menys que amb binari natural amb signe
        print "Estalvi maxim: ",estalvi," bits"
    else:
        print "La codificacio en binari natural requereix menys bits en tot el rang"



def a():
    print "a)",
    suma = 0
    for n in range(-1023,1024):
        if n == 0: continue
        suma += int(math.log(abs(n),2))+1# el 1 es el bit de signe
    print suma,"bits"

def b():
    print "b)"
    m = 32
    vmin = -1023
    vmax = 1023
    for n in range(vmin,vmax+1):
        print "Rice de ",n," :",rice(n,m)
def c():
    print "c)",
    vmin = -1023
    vmax = 1023
    estalvi,minim,maxim = saving(vmin,vmax,32)
    show(estalvi,minim,maxim)

def d():
    print "d)",
    vmax = 1023
    est = 0
    mmax = 0
    trobat = False
    for m in np.arange(1,vmax+1,1):
        estalvi,minim,maxim = saving(-255,256,m)

        if (estalvi >= est and est > 0):
            est = estalvi
            mmax = m
            print "M = ",mmax,  "Estalvi maxim =",est
            trobat = True
    if(not trobat):
        print "La codificacio en binari natural requereix menys bits en tot el rang"


def e():
    print "e)",
    vmin = -1023
    vmax = 1023
    for m in np.arange(vmax,1,-1):
        estalvi,minim,maxim = saving(vmin,vmax+1,m)
        if estalvi == 6:
            print "M =",m
            break
    show(estalvi,minim,maxim)

if __name__ == "__main__":
    a()
    b()
    c()
    d()
    e()
