import utils
import math

def decompress(compressed,Mdes, Ment):
    if utils.check_initial_conditions(compressed, Mdes, Ment) == -1:
        return -1
    data = compressed[:Mdes]
    bitsL = int(math.log(Ment,2))
    bitsD = int(math.log(Mdes,2))
    i = Mdes
    j = 0
    while len(compressed[i:]) > Ment:
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
