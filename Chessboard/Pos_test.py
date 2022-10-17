import math

while True:
    i = int(input('Enter the sensor address'))
    normalized_num = i - 65
    x = int(normalized_num % 8)
    y = int(math.floor(normalized_num/8))
    print('X = ', x)
    print('Y = ', y)