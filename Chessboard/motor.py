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
    travel = 0
    curr_X = current_pos[0]
    x = int(x)

    travel = x - curr_X
    
    if(travel < 0):
        travel = -travel
        for k in range(travel*ONE_SQUARE):
            kit.stepper1.onestep(direction=stepper.BACKWARD)
    else:
        for k in range(travel*ONE_SQUARE):
            kit.stepper1.onestep(direction=stepper.FORWARD)

    current_pos[0] = x
    print("New position: " + str(current_pos))

def move_Ymotor(y):
    travel = 0
    curr_Y = current_pos[1]
    y = int(y)

    travel = y - curr_Y
    
    if(travel < 0):
        travel = -travel
        for k in range(travel*ONE_SQUARE):
            kit.stepper1.onestep(direction=stepper.BACKWARD)
    else:
        for k in range(travel*ONE_SQUARE):
            kit.stepper1.onestep(direction=stepper.FORWARD)

    current_pos[1] = y
    print("New position: " + str(current_pos))

def move_Diag(d, xdir, ydir):
    d = int(d)
    for k in range(d):
        kit.stepper1.onestep()
        kit.stepper2.onestep()
