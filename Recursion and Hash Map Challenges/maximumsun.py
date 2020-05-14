# we have a list of numbers, and we want to find the maximum sum of any two numbers
# we have to add exactly two numbers

numbers = [1, 5, 3, 2, 3, 7, 5, -5]

def recurse(l, r):
    if l == r:
        return numbers[l], 0

    if l + 1 == r:
        return max(numbers[l], numbers[r]), numbers[l] + numbers[r]

    # three possible cases
    # first: the highest two numbers are in the left side of this portion
    # second: the highest two numbers are in the right half of this portion
    # third: the highest is in one half, and the second highest is in the other
    mid = round((l + r) / 2)

    # check if the highest two numbers are in the left half, then right
    # check third case
    highest_in_half_1, sum_of_highest_two_numbers_1 = recurse(l, mid)
    highest_in_half_2, sum_of_highest_two_numbers_2 = recurse(mid + 1, r)

    ans = max(sum_of_highest_two_numbers_1, sum_of_highest_two_numbers_2, highest_in_half_1 + highest_in_half_2)

    return max(highest_in_half_1, highest_in_half_2), ans

print(recurse(0, len(numbers) - 1))

# 1, 5, 3, 2, 3, 7, 5, -5