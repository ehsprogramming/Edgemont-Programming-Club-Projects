def prime(n):
    for factor in range(2, round(n / 2) + 1):
        if n % factor == 0:
            return False
    return True

number = 600851475143

# factor 'number,' saving factors
largest_prime = 1
for i in range(2, round(number / 2) + 1): # approximately number / 2 times
    if number % i == 0: # 1 time
        #it's a factor, and it also must be prime
        largest_prime = i # 1 time
        while number % i == 0: #best case = 0, worst case = log_i number
            number /= i

    if number == 1:
        break

print(largest_prime)