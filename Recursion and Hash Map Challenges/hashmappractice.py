# 1 -> 5, 5 -> 2

# given a list of names, find the one that appears most frequently
names = ['Michael', 'Kenny', 'Shaurya', "Everyone else who isn't here", "Everyone else who isn't here"]

dictionary = dict()

for n in names:
    if not n in dictionary:
        dictionary[n] = 0
    dictionary[n] = dictionary[n] + 1

print(dictionary)

number, name = 0, 'random value that will be only be the answer if something goes wrong (the dictionary is empty)'

for k in dictionary:
    print(k, dictionary[k])

    if dictionary[k] > number:
        number = dictionary[k]
        name = k

print('answer:', name, number)