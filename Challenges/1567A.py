t = int(input())

for i in range(t):
	len = int(input())
	str = input()

	ans = ""

	for c in str:
		if c == 'U':
			ans += 'D'
		elif c == 'D':
			ans += 'U'
		elif c == 'L':
			ans += 'L'
		elif c == 'R':
			ans += 'R'

	print(ans)