from socket import *
import threading

clientSocket = socket(AF_INET, SOCK_DGRAM)

def receive(): # receive messages
    while True:
        message, serverAddress = clientSocket.recvfrom(1024)
        message = message.decode()

        print(message)

receive_thread = threading.Thread(target = receive)

clientSocket.sendto(''.encode(), ('10.79.67.82', 5000))

while True: # send messages
    message = input()
    clientSocket.sendto(message.encode(), ('10.79.67.82', 5000))
