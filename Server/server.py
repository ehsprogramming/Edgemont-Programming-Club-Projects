from socket import *

port = 5000

serverSocket = socket(AF_INET, SOCK_DGRAM)
serverSocket.bind(('', port))

messages = [] # each message is just a string with a message
people = [] # each item is an address

while True:
    message, clientAddress = serverSocket.recvfrom(1024)
    message = message.decode()

    print('message', message)
    print('people', people)

    if message == '':
        # this person has just joined the chat room, and needs all previous messages
        # add them to the chat room, and send all previous messages
        people.append(clientAddress)

        for message in messages:
            serverSocket.sendto(message.encode(), clientAddress)
    else:
        # send message to everyone else in the chat room and save it
        messages += message
        for person in people:
            serverSocket.sendto(message.encode(), person)
