from socket import *
serverName = 'localhost'
serverPort = 3000

clientSocket = socket(AF_INET, SOCK_STREAM)
clientSocket.connect((serverName, serverPort)) #handshake

sentence = """GET / HTTP/1.1
Host: localhost
"""

print(sentence)

clientSocket.send(sentence.encode())

print('sent')
modifiedSentence = clientSocket.recv(2048)

print('From Server: ', modifiedSentence.decode())
clientSocket.close()