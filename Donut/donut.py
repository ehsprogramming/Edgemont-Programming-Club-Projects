import pygame
import math

pygame.init()

white = (255, 255, 255)
black = (0, 0, 0)

WIDTH = 1920
HEIGHT = 1080

x_start, y_start = 0, 0

x_separator = 10
y_separator = 20

rows = HEIGHT // y_separator
columns = WIDTH // x_separator
screen_size = rows * columns

x_offset = columns / 2
y_offset = rows / 2
# All code above describes how the pygame is set up dimension wise


A, B = 0, 0 # rotating animation
theta_spacing = 10 # controls density of primary circle
phi_spacing = 1 # Moves primary circle 360 degrees,
# for faster rotation change phi to 2, 3 or more, but first change 124-125 lines as commented

chars = ".,-~:;=!*#$@"  # luminance index
# All code above creates the characters used in the creation of the donut from darkest to brightest

screen = pygame.display.set_mode((WIDTH, HEIGHT))

display_surface = pygame.display.set_mode((WIDTH, HEIGHT))
pygame.display.set_caption('Donut')
font = pygame.font.SysFont('Arial', 18, bold=True)
# All code above describes the basics of how the donut is set up in the pygame


def text_display(letter, x_start, y_start):
   text = font.render(str(letter), True, white)
   display_surface.blit(text, (x_start, y_start))

# this function displays the ascii characters in the pygame


# All code below describes what happens when the game launches

run = True
while run:

   screen.fill(black)

   z = [0] * screen_size  # Donut. Fills donut space
   b = [' '] * screen_size  # Background. Fills empty space

   for j in range(0, 628, theta_spacing):  # from 0 to 2pi (360 degrees)
       for i in range(0, 628, phi_spacing):  # from 0 to 2pi (360 degrees)
           c = math.sin(i)
           d = math.cos(j)
           e = math.sin(A)
           f = math.sin(j)
           g = math.cos(A)
           h = d + 2
           D = 1 / (c * h * e + f * g + 5)
           l = math.cos(i)
           m = math.cos(B)
           n = math.sin(B)
           t = c * h * g - f * e
           x = int(x_offset + 40 * D * (l * h * m - t * n))  # 3D x coordinate after rotation
           y = int(y_offset + 20 * D * (l * h * n + t * m))  # 3D y coordinate after rotation
           o = int(x + columns * y)  # 3D z coordinate after rotation
           N = int(8 * ((f * e - c * d * g) * m - c * d * e - f * g - l * d * n))  # luminance index
           if rows > y and y > 0 and x > 0 and columns > x and D > z[o]:
               z[o] = D
               b[o] = chars[N if N > 0 else 0]

# crazy math shown above behind determining coordinates of an ascii character during rotations

   if y_start == rows * y_separator - y_separator:
       y_start = 0

   for i in range(len(b)):
       A += 0.000002 # for faster rotation change to 0.0002 (og: 0.000002)
       B += 0.000001 # for faster rotation change to 0.0001 (og: 0.000001)
       if i == 0 or i % columns:
           text_display(b[i], x_start, y_start)
           x_start += x_separator
       else:
           y_start += y_separator
           x_start = 0
           text_display(b[i], x_start, y_start)
           x_start += x_separator

   pygame.display.update()

# This ensures that the donut is constantly being reprojected in the screen and rotated throughout the run time of the program

   for event in pygame.event.get():
       if event.type == pygame.QUIT:
           run = False

# This ends the program when pygame is closed

