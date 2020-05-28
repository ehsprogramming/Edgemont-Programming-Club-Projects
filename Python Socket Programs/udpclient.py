from socket import *
serverName = 'localhost'
serverPort = 12000
# 1.2.3.4 - IPv4
# 2607:f8b0:4006:804::200e - IPv6
'''IPv4'''
'''UDP'''
clientSocket = socket(AF_INET, SOCK_DGRAM);
message = input('Input lowercase sentence: ')
clientSocket.sendto(message.encode(), (serverName, serverPort))
# message.encode() - converts to raw binary bits (e.g. 10011011)
'''Number of bits of data'''
modifiedMessage, serverAddress = clientSocket.recvfrom(2048)
print(modifiedMessage.decode())
'''convert from bits into text'''
clientSocket.close() # kind of like cleanup in opengl
# telling the computer that it can reuse the port number for
# another application