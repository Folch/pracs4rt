import math
import numpy as np
# -*- coding: utf-8 -*-
def rice(n,m):
    sign = "1" if n >= 0 else "0"
    q = int(n/m)
    qBits = ""
    for i in range(q):
        qBits += "1"
    qBits += "0"
    r = n%m
    mBits = int(math.log(m,2))
    binaryR = '{0:0'+str(mBits)+'b}'
    rBits = binaryR.format(int(r))
    return sign+qBits+rBits

def riceByte(bits,m):
    return rice(int(bits,2),m)

def saving(vmin,vmax,m = 32):
    minim = vmax+1
    maxim = vmin-1
    estalviMaxim = 0
    for i in range(vmin,vmax):
        if i == 0: continue
        bitsN = int(math.log(abs(i),2))+1
        bitsR = len(rice(i,m))
        if  bitsN > bitsR:
            minim = min(i,minim)
            maxim = max(i,maxim)
            estalviMaxim = max(abs(bitsN-bitsR),estalviMaxim)

    return estalviMaxim,minim,maxim
def show(estalvi,minim,maxim):
    if(minim < maxim):
        print "Rang [",str(minim),":",str(maxim),"]"#Rang de numeros en el qual amb Rice ocupen menys que amb binari natural amb signe
        print "Ahorro maximo: ",estalvi," bits"

def a():
    print "a)",
    num = 1023
    bits = int(math.log(num,2))+1# el 1 es el bit de signe
    print bits*(num*2+1),"bits"

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
    trobat = False
    vmin = -1023
    vmax = 1023
    est = 0#temp
    mini = 0#temp
    maxi = 0#temp
    for m in np.arange(2,vmax+1,0.01):

        #estalvi,minim,maxim = saving(vmin,vmax+1,m)
        estalvi,minim,maxim = saving(-255,256,m)

        if (estalvi > est):
            est = estalvi
        print m, "minim =",minim," maxim=",maxim, "est=",
        if(minim == -255 and maxim == 255):
            print "M = ",m
            show(estalvi,minim,maxim)
            trobat = True
        '''
        elif(minim >= -255 and minim <= 255 and maxim <= 255 and maxim >=-255):
            print "M = ",m
            show(estalvi,minim,maxim)
            trobat = True
        '''
    if(not trobat):
        print "No se extiende entre -255 y +255"

def e():
    print "e)",
    vmin = -1023
    vmax = 1023
    for m in range(vmax,1,-1):
        estalvi,minim,maxim = saving(vmin,vmax+1,m)
        if estalvi == 6:
            print "M =",m
            show(estalvi,minim,maxim)
            break

'''
a()
b()
c()
d()
e()
'''
