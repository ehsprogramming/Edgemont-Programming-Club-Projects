def triple(a, b, c):
    return a * a + b * b == c * c

'''max_possibilities = 0
corresponding_p = 0
for p in range(12, 1001):
    possibilities = 0
    for a in range(p - 2 + 1):
        for b in range(a, p - 2 + 1):
            for c in range(b, p - 2 + 1):
                if triple(a, b, c) and (a + b + c) == p:
                    possibilities += 1
    if possibilities > max_possibilities:
        max_possibilities = possibilities
        corresponding_p = p

print(corresponding_p, max_possibilities)'''

m = 2

possibilities = dict()

a = 0
b = 0
c = 0

l = []

seen = set()
m = 2
while (a + b + c) <= 1000000:
    for n in range(1, m):
        for k in range(1, 1000):
            a = k * (m * m - n * n)
            b = k * 2 * m * n
            c = k * (m * m + n * n)

            triple = (a, b, c)

            if triple in seen:
                continue
            else:
                seen.add(triple)

            p = a + b + c

            l.append(p)
            if p not in possibilities:
                possibilities[p] = 0

            possibilities[p] += 1

    m += 1

max_possibilities = 0
corresponding_p = 0
for i in range(1001):
    if i in possibilities:
        if possibilities[i] > max_possibilities:
            max_possibilities = possibilities[i]
            corresponding_p = i

print(corresponding_p, max_possibilities)