import time
import board

from adafruit_motorkit import MotorKit
from adafruit_motor import stepper
kit = MotorKit()

# Steps derrived from motor specs
ONE_SQUARE = 643
DIAG_MOVE = 321

current_pos = [0,0]

def move_Xmotor(x):
    x = int(x)
    if(x < 0):
        x = -x
        for k in range(x*ONE_SQUARE):
            kit.stepper1.onestep(direction=stepper.BACKWARD)
    else:
        for k in range(x*ONE_SQUARE):
            kit.stepper1.onestep(direction=stepper.FORWARD)

def move_Ymotor(y):
    y = int(y)
    if(y < 0):
        y = -y
        for k in range(y*ONE_SQUARE):
            kit.stepper2.onestep(direction=stepper.BACKWARD)
    else:
        for k in range(y*ONE_SQUARE):
            kit.stepper2.onestep(direction=stepper.FORWARD)

def move_Diag(d, xdir, ydir):
    d = int(d)
    for k in range(d):
        kit.stepper1.onestep()
        kit.stepper2.onestep()
