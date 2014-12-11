v = (4,2)
w = (0,7)
def line(v, w):
    out = []
    x = v[0]
    y = v[1]
    dx = abs(w[0]-v[0])
    sx = 1 if v[0] < w[0] else -1
    dy = -abs(w[1]-v[1])
    sy = 1 if v[1] < w[1] else -1
    err = dx + dy

    while(x != w[0] or y != w[1]):
        out.append((x,y))
        e2 = 2 * err
        if e2 > dy:
            err += dy
            x += sx
        if e2 < dx:
            err += dx
            y += sy

    out.append((x,y))
    return out

print line(v,w)
