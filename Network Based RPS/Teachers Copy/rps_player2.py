# We will need the following module to generate randomized lost packets
from socket import *

# Create a UDP socket
# Notice the use of SOCK_DGRAM for UDP packets
serverSocket = socket(AF_INET, SOCK_DGRAM)

# Assign IP address and port number to socket
serverSocket.bind(('', 5001))

print('you are player 2')
while True:
    print('waiting for their move')
    # Receive the client packet along with the address it is coming from
    message, address = serverSocket.recvfrom(1024)
    message = message.decode()

    move = raw_input('what is your move?')
    serverSocket.sendto(move.encode(), ('127.0.0.1', 5000))

    if move == 'r' and message == 'p':
        print('you lose!')
    elif move == 'r' and message == 'r':
        print("it's a tie!")
    elif move == 'r' and message == 's':
        print('you win!')

    if move == 'p' and message == 's':
        print('you lose!')
    elif move == 'p' and message == 'p':
        print("it's a tie!")
    elif move == 'p' and message == 'r':
        print('you win!')

    if move == 's' and message == 'r':
        print('you lose!')
    elif move == 's' and message == 's':
        print("it's a tie!")
    elif move == 's' and message == 'p':
        print('you win!')

    # more cases here