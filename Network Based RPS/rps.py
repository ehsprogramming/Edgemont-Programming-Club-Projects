from socket import *

serverSocket = socket(AF_INET, SOCK_DGRAM)

serverSocket.bind(('', 5000))

while True:
	print('waiting for move...')
	message, address = serverSocket.recvfrom(1024)
	message = message.decode()

	move = input('what is your move?')
	serverSocket.sendto(move.encode(), ('192.168.137.116', 5000))

	moves = ['r', 'p', 's']
	for i in range(len(moves)):
		if moves[i] == move:
			if moves[i - 1] == message:
				print('you win')
			elif moves[i] == message:
				print('tie')
			else:
				print('you lose')