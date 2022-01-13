# board
# a way to draw on the board
# a way to erase the board
# an opponent
import random

array = [['_' for i in range(3)] for j in range(3)]

running = True
while running:
    for i in range(3):
        for j in range(3):
            print(array[i][j], end='')
        print()

    #future assignment: make sure the user can't place their X in an occupied spot
    u_row = int(input('what row?'))
    u_col = int(input('what column?'))

    array[u_row][u_col] = 'X' # few ways this can go wrong, fix later

    c_move = True
    while c_move:
        c_row = random.randrange(0, 3)
        c_col = random.randrange(0, 3)

        if array[c_row][c_col] == '_':
            array[c_row][c_col] = 'O'
            c_move = False

    for i in range(3):
        for j in range(3):
            print(array[i][j], end='')
        print()

    win = input('did someone win?')

    if win == 'yes':
        print('Game over')
        running = False