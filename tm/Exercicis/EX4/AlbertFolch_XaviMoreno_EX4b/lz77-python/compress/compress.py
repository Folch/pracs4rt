from utils import utils
import math

def compress(data,Mdes, Ment):
    if utils.check_initial_conditions(data, Mdes, Ment) == -1:
            return -1
    dataCompressed = data[:Mdes]

    iDes = 0#index deslizante
    iEnt = Mdes#index entrada

    bitsL = int(math.log(Ment,2))
    bitsD = int(math.log(Mdes,2))

    binaryLength = '{0:0'+str(bitsL)+'b}'
    binaryDistance = '{0:0'+str(bitsD)+'b}'

    while(len(data[iEnt:]) > Ment):
        for i in range(Ment,0,-1):

            pattern = data[iEnt:iEnt+i]
            vDeslizante = data[iDes:iEnt]
            index = utils.find(vDeslizante,pattern)

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
